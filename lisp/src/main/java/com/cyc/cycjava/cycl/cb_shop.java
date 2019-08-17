/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.cb_parameters.$cb_constant_input_width$;
import static com.cyc.cycjava.cycl.cb_parameters.$cb_permit_robots_to_followP$;
import static com.cyc.cycjava.cycl.cb_parameters.$cb_permit_robots_to_indexP$;
import static com.cyc.cycjava.cycl.cb_parameters.cb_head_shortcut_icon;
import static com.cyc.cycjava.cycl.cb_parameters.cyc_cgi_url_int;
import static com.cyc.cycjava.cycl.cb_utilities.cb_error;
import static com.cyc.cycjava.cycl.cb_utilities.cb_form;
import static com.cyc.cycjava.cycl.cb_utilities.cb_frame;
import static com.cyc.cycjava.cycl.cb_utilities.cb_frame_name;
import static com.cyc.cycjava.cycl.cb_utilities.cb_guess_assertion;
import static com.cyc.cycjava.cycl.cb_utilities.cb_help_preamble;
import static com.cyc.cycjava.cycl.cb_utilities.cb_link;
import static com.cyc.cycjava.cycl.cb_utilities.cb_show_assertion_readably;
import static com.cyc.cycjava.cycl.cb_utilities.cb_simple_message_page;
import static com.cyc.cycjava.cycl.cb_utilities.declare_cb_tool;
import static com.cyc.cycjava.cycl.cb_utilities.register_cb_frame;
import static com.cyc.cycjava.cycl.cb_utilities.setup_cb_link_method;
import static com.cyc.cycjava.cycl.cyc_file_dependencies.$cb_help_definitions$;
import static com.cyc.cycjava.cycl.dhtml_macros.dhtml_with_dom_script;
import static com.cyc.cycjava.cycl.dhtml_macros.dhtml_with_toggle_visibility_support;
import static com.cyc.cycjava.cycl.html_utilities.html_align;
import static com.cyc.cycjava.cycl.html_utilities.html_char;
import static com.cyc.cycjava.cycl.html_utilities.html_color;
import static com.cyc.cycjava.cycl.html_utilities.html_copyright_notice;
import static com.cyc.cycjava.cycl.html_utilities.html_extract_input;
import static com.cyc.cycjava.cycl.html_utilities.html_hidden_input;
import static com.cyc.cycjava.cycl.html_utilities.html_hr;
import static com.cyc.cycjava.cycl.html_utilities.html_indent;
import static com.cyc.cycjava.cycl.html_utilities.html_markup;
import static com.cyc.cycjava.cycl.html_utilities.html_meta_robot_instructions;
import static com.cyc.cycjava.cycl.html_utilities.html_newline;
import static com.cyc.cycjava.cycl.html_utilities.html_prin1;
import static com.cyc.cycjava.cycl.html_utilities.html_princ;
import static com.cyc.cycjava.cycl.html_utilities.html_princ_doc_string;
import static com.cyc.cycjava.cycl.html_utilities.html_radio_input;
import static com.cyc.cycjava.cycl.html_utilities.html_refresh;
import static com.cyc.cycjava.cycl.html_utilities.html_simple_attribute;
import static com.cyc.cycjava.cycl.html_utilities.html_source_readability_terpri;
import static com.cyc.cycjava.cycl.html_utilities.html_submit_input;
import static com.cyc.cycjava.cycl.html_utilities.html_text_input;
import static com.cyc.cycjava.cycl.utilities_macros.register_html_state_variable;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_greater;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_quotation;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.equal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.sethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numE;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numG;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numGE;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.write_to_string;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.reverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.read_from_string_ignoring_errors;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Strings;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      CB-SHOP
 * source file: /cyc/top/cycl/cb-shop.lisp
 * created:     2019/07/03 17:38:27
 */
public final class cb_shop extends SubLTranslatedFile implements V12 {
    static private final SubLString $str_alt243$ = makeString("");

    public static final SubLFile me = new cb_shop();

 public static final String myName = "com.cyc.cycjava.cycl.cb_shop";


    // defparameter
    @LispMethod(comment = "defparameter")
    // Definitions
    private static final SubLSymbol $cyc_development_environment$ = makeSymbol("*CYC-DEVELOPMENT-ENVIRONMENT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $cb_planner_default_domain_mt$ = makeSymbol("*CB-PLANNER-DEFAULT-DOMAIN-MT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $cb_shop_planner$ = makeSymbol("*CB-SHOP-PLANNER*");



    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $cb_plan_displayers$ = makeSymbol("*CB-PLAN-DISPLAYERS*");

    // deflexical
    // the interval which the summary page should refresh its content.
    /**
     * the interval which the summary page should refresh its content.
     */
    @LispMethod(comment = "the interval which the summary page should refresh its content.\ndeflexical")
    private static final SubLSymbol $cb_shop_refresh_rate$ = makeSymbol("*CB-SHOP-REFRESH-RATE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $cb_shop_process_label$ = makeSymbol("*CB-SHOP-PROCESS-LABEL*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $cb_shop_processes$ = makeSymbol("*CB-SHOP-PROCESSES*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    public static final SubLSymbol $cb_shop_task_string$ = makeSymbol("*CB-SHOP-TASK-STRING*");

    public static final SubLSymbol $cb_planner_warning_message$ = makeSymbol("*CB-PLANNER-WARNING-MESSAGE*");

    public static final SubLSymbol $cb_shop_planner_thread$ = makeSymbol("*CB-SHOP-PLANNER-THREAD*");

    public static final SubLSymbol $cb_planner_task$ = makeSymbol("*CB-PLANNER-TASK*");

    public static final SubLSymbol $cb_planner_domain_mt$ = makeSymbol("*CB-PLANNER-DOMAIN-MT*");

    public static final SubLSymbol $cb_last_planner_format$ = makeSymbol("*CB-LAST-PLANNER-FORMAT*");

    public static final SubLSymbol $cb_last_planner_structure$ = makeSymbol("*CB-LAST-PLANNER-STRUCTURE*");

    public static final SubLSymbol $cb_last_planner_number$ = makeSymbol("*CB-LAST-PLANNER-NUMBER*");

    private static final SubLInteger $int$500 = makeInteger(500);

    public static final SubLSymbol $cb_shop_time_cutoff$ = makeSymbol("*CB-SHOP-TIME-CUTOFF*");

    private static final SubLInteger $int$75 = makeInteger(75);

    public static final SubLSymbol $cb_shop_depth_limit$ = makeSymbol("*CB-SHOP-DEPTH-LIMIT*");

    public static final SubLSymbol $cb_shop_num_backchains$ = makeSymbol("*CB-SHOP-NUM-BACKCHAINS*");

    public static final SubLSymbol $cb_last_planner_do_fi_asks$ = makeSymbol("*CB-LAST-PLANNER-DO-FI-ASKS*");

    public static final SubLSymbol $cb_shop_cycle_checking_enabledP$ = makeSymbol("*CB-SHOP-CYCLE-CHECKING-ENABLED?*");

    public static final SubLSymbol $cb_last_planner_show_advanced_settings$ = makeSymbol("*CB-LAST-PLANNER-SHOW-ADVANCED-SETTINGS*");

    public static final SubLSymbol $cb_plan_search_type$ = makeSymbol("*CB-PLAN-SEARCH-TYPE*");

    public static final SubLSymbol $cb_shop_verbosity$ = makeSymbol("*CB-SHOP-VERBOSITY*");

    static private final SubLList $list22 = list(list(makeSymbol("*SHOP-METHOD-CYCLE-CHECKING-ENABLED?*"), makeSymbol("*CB-SHOP-CYCLE-CHECKING-ENABLED?*")), list(makeSymbol("*SHOP-DEFAULT-DEPTH-LIMIT*"), makeSymbol("*CB-SHOP-DEPTH-LIMIT*")));

    public static final SubLSymbol $cb_shop_plan_checking_enabled$ = makeSymbol("*CB-SHOP-PLAN-CHECKING-ENABLED*");

    public static final SubLSymbol $cb_shop_plan_checking_rules$ = makeSymbol("*CB-SHOP-PLAN-CHECKING-RULES*");

    static private final SubLString $str26$Not_a_planning_assertion_ = makeString("Not a planning assertion.");

    static private final SubLString $str27$Rule_has_been_added_to_SHOP_plan_ = makeString("Rule has been added to SHOP plan checking rule set.");

    private static final SubLSymbol CB_SHOP_USE_IN_PLAN_CHECKING = makeSymbol("CB-SHOP-USE-IN-PLAN-CHECKING");

    private static final SubLString $str31$_Use_in_Plan_Checking_ = makeString("[Use in Plan Checking]");

    private static final SubLString $str33$cb_shop_use_in_plan_checking__a = makeString("cb-shop-use-in-plan-checking&~a");

    private static final SubLSymbol $USE_IN_PLAN_CHECKING = makeKeyword("USE-IN-PLAN-CHECKING");

    private static final SubLSymbol CB_LINK_USE_IN_PLAN_CHECKING = makeSymbol("CB-LINK-USE-IN-PLAN-CHECKING");

    private static final SubLSymbol $CB_SHOP_PLAN_CHECKING = makeKeyword("CB-SHOP-PLAN-CHECKING");

    private static final SubLString $str37$cb_shop_plan_checking_html = makeString("cb-shop-plan-checking.html");

    private static final SubLString $$$Plan_Checking_Control = makeString("Plan Checking Control");

    private static final SubLString $str44$__DOCTYPE_html_PUBLIC_____W3C__DT = makeString("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");

    private static final SubLString $str45$_meta_http_equiv__X_UA_Compatible = makeString("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\" >");

    private static final SubLSymbol $SAM_AUTOCOMPLETE_CSS = makeKeyword("SAM-AUTOCOMPLETE-CSS");

    private static final SubLString $str50$yui_skin_sam = makeString("yui-skin-sam");

    private static final SubLString $$$reloadFrameButton = makeString("reloadFrameButton");

    private static final SubLString $$$button = makeString("button");

    private static final SubLString $$$reload = makeString("reload");

    private static final SubLString $$$Refresh_Frames = makeString("Refresh Frames");

    private static final SubLString $$$post = makeString("post");

    private static final SubLString $str56$cb_shop_plan_checking = makeString("cb-shop-plan-checking");

    private static final SubLString $str58$_Back_to_Planner_ = makeString("[Back to Planner]");

    private static final SubLString $$$Plan_Checking_is_ = makeString("Plan Checking is ");

    private static final SubLString $str60$Enabled_ = makeString("Enabled.");

    private static final SubLString $str61$Disabled_ = makeString("Disabled.");

    private static final SubLString $$$Disable = makeString("Disable");

    private static final SubLString $$$Enable = makeString("Enable");

    private static final SubLString $$$Update = makeString("Update");

    private static final SubLString $$$Discard_All = makeString("Discard All");

    private static final SubLString $str66$Choose_Planning_Rules_for_inclusi = makeString("Choose Planning Rules for inclusion:");

    private static final SubLString $str67$No_Rules_in_plan_checking_set_ = makeString("No Rules in plan checking set.");

    private static final SubLSymbol CB_SHOP_PLAN_CHECKING = makeSymbol("CB-SHOP-PLAN-CHECKING");

    private static final SubLString $str70$_Plan_Checking_ = makeString("[Plan Checking]");

    private static final SubLSymbol CB_LINK_PLAN_CHECKING = makeSymbol("CB-LINK-PLAN-CHECKING");

    private static final SubLSymbol CYC_DEVELOPMENT_PLANNER = makeSymbol("CYC-DEVELOPMENT-PLANNER");

    private static final SubLSymbol SHOP = makeSymbol("SHOP");

    private static final SubLList $list76 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("MAKE-DOMAIN-FROM-PROOF-CHECKER"), NIL, makeKeyword("PUBLIC")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-CYC-DEVELOPMENT-PLANNER-CLASS");

    private static final SubLSymbol DISPLAYER_FACTORY = makeSymbol("DISPLAYER-FACTORY");

    private static final SubLSymbol DOMAIN_TABLE = makeSymbol("DOMAIN-TABLE");

    private static final SubLSymbol PROBLEM_TABLE = makeSymbol("PROBLEM-TABLE");

    private static final SubLSymbol THREADS = makeSymbol("THREADS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-CYC-DEVELOPMENT-PLANNER-INSTANCE");

    private static final SubLList $list88 = list(makeKeyword("PROTECTED"));

    private static final SubLList $list89 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(makeSymbol("CSETQ"), makeSymbol("DISPLAYER-FACTORY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("CYC-DEVELOPMENT-PLAN-DISPLAYER-FACTORY")), list(QUOTE, makeSymbol("CYC-DEVELOPMENT-PLAN-DISPLAYER-FACTORY")))), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol $sym90$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-CYC-DEVELOPMENT-PLANNER-METHOD");

    private static final SubLSymbol CYC_DEVELOPMENT_PLAN_DISPLAYER_FACTORY = makeSymbol("CYC-DEVELOPMENT-PLAN-DISPLAYER-FACTORY");

    private static final SubLSymbol CYC_DEVELOPMENT_PLANNER_INITIALIZE_METHOD = makeSymbol("CYC-DEVELOPMENT-PLANNER-INITIALIZE-METHOD");

    private static final SubLSymbol MAKE_DOMAIN_WITH_RULES = makeSymbol("MAKE-DOMAIN-WITH-RULES");

    private static final SubLList $list94 = list(makeKeyword("PUBLIC"));

    private static final SubLList $list95 = list(makeSymbol("RULES"), makeSymbol("TASK-MT"));

    private static final SubLList $list96 = list(list(makeSymbol("CLET"), list(list(makeSymbol("DOMAIN"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-CYC-DOMAIN")), list(QUOTE, makeSymbol("SHOP-CYC-DOMAIN"))))), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT-DOMAIN-WITH-RULES"), makeSymbol("SHOP-CYC-DOMAIN")), makeSymbol("DOMAIN"), makeSymbol("RULES"), makeSymbol("TASK-MT")), list(makeSymbol("CSETF"), list(makeSymbol("GETHASH"), makeSymbol("TASK-MT"), makeSymbol("DOMAIN-TABLE")), makeSymbol("DOMAIN")), list(RET, makeSymbol("SELF"))));

    private static final SubLSymbol $sym97$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-CYC-DEVELOPMENT-PLANNER-METHOD");

    private static final SubLSymbol CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_WITH_RULES_METHOD = makeSymbol("CYC-DEVELOPMENT-PLANNER-MAKE-DOMAIN-WITH-RULES-METHOD");

    private static final SubLSymbol MAKE_DOMAIN_FROM_PROOF_CHECKER = makeSymbol("MAKE-DOMAIN-FROM-PROOF-CHECKER");

    private static final SubLList $list101 = list(makeSymbol("TASK-MT"));

    private static final SubLList $list102 = list(makeString("@param TASK-MT microtheory-p"), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTIONS"), list(makeSymbol("CB-SHOP-PLAN-CHECKING-RULES")))), list(RET, list(makeSymbol("MAKE-DOMAIN-WITH-RULES"), makeSymbol("SELF"), makeSymbol("ASSERTIONS"), makeSymbol("TASK-MT")))));

    private static final SubLSymbol CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_FROM_PROOF_CHECKER_METHOD = makeSymbol("CYC-DEVELOPMENT-PLANNER-MAKE-DOMAIN-FROM-PROOF-CHECKER-METHOD");

    private static final SubLString $$$Plan_Finder = makeString("Plan Finder");

    private static final SubLString $str105$Cannot_have_a_FRAMESET_tag_inside = makeString("Cannot have a FRAMESET tag inside a BODY tag.");

    private static final SubLString $str106$400__ = makeString("400,*");

    private static final SubLSymbol $CB_SHOP_CONTROLS = makeKeyword("CB-SHOP-CONTROLS");

    private static final SubLSymbol $CB_SHOP_SUMMARY = makeKeyword("CB-SHOP-SUMMARY");

    private static final SubLSymbol PLAN_FINDER = makeSymbol("PLAN-FINDER");

    private static final SubLString $$$Planner_Examiner = makeString("Planner Examiner");

    private static final SubLSymbol PLAN_EXAMINER = makeSymbol("PLAN-EXAMINER");

    private static final SubLString $$$Plan_Verbose_Examiner = makeString("Plan Verbose Examiner");

    private static final SubLSymbol PLAN_VERBOSE_EXAMINER = makeSymbol("PLAN-VERBOSE-EXAMINER");

    private static final SubLString $$$Plan_Task_Examiner = makeString("Plan Task Examiner");

    private static final SubLSymbol PLAN_TASK_EXAMINER = makeSymbol("PLAN-TASK-EXAMINER");

    private static final SubLString $$$controls = makeString("controls");

    private static final SubLString $str117$cb_shop_controls = makeString("cb-shop-controls");

    private static final SubLString $$$info = makeString("info");

    private static final SubLString $str119$cb_shop_information = makeString("cb-shop-information");

    private static final SubLString $str120$_Summary_ = makeString("[Summary]");

    private static final SubLSymbol CB_LINK_CB_SHOP_SUMMARY = makeSymbol("CB-LINK-CB-SHOP-SUMMARY");

    private static final SubLString $str123$_Controls_ = makeString("[Controls]");

    private static final SubLSymbol CB_LINK_CB_SHOP_CONTROLS = makeSymbol("CB-LINK-CB-SHOP-CONTROLS");

    private static final SubLString $str125$_Plan_Finder_ = makeString("[Plan Finder]");

    private static final SubLString $str126$plan_finder = makeString("plan-finder");

    private static final SubLSymbol CB_LINK_PLANNER = makeSymbol("CB-LINK-PLANNER");

    private static final SubLString $str128$_Plan_Examiner_ = makeString("[Plan Examiner]");

    private static final SubLString $str129$plan_examiner = makeString("plan-examiner");

    private static final SubLSymbol CB_LINK_PLAN_EXAMINER = makeSymbol("CB-LINK-PLAN-EXAMINER");

    private static final SubLString $str132$_Plan_Verbose_Examiner_ = makeString("[Plan Verbose Examiner]");

    private static final SubLString $str133$plan_verbose_examiner = makeString("plan-verbose-examiner");

    private static final SubLSymbol $PLAN_VERBOSE_EXAMINER = makeKeyword("PLAN-VERBOSE-EXAMINER");

    private static final SubLSymbol CB_LINK_PLAN_VERBOSE_EXAMINER = makeSymbol("CB-LINK-PLAN-VERBOSE-EXAMINER");

    private static final SubLString $$$Planner = makeString("Planner");

    private static final SubLString $str137$Simple_Hierarchical_Ordered_Plann = makeString("Simple Hierarchical Ordered Planner");

    private static final SubLSymbol CB_SHOP_CONTROLS = makeSymbol("CB-SHOP-CONTROLS");

    private static final SubLString $$$Summary = makeString("Summary");

    private static final SubLString $str140$_New_Plan_ = makeString("[New Plan]");

    private static final SubLString $str141$_Full_Screen_ = makeString("[Full Screen]");

    private static final SubLSymbol CB_SHOP_INFORMATION = makeSymbol("CB-SHOP-INFORMATION");

    private static final SubLString $$$Hierarchical_Ordered_Planner = makeString("Hierarchical Ordered Planner");

    private static final SubLString $str144$find_plans = makeString("find-plans");

    private static final SubLString $str145$show_advanced_parameters = makeString("show-advanced-parameters");

    private static final SubLString $$$red = makeString("red");

    private static final SubLString $str147$Warning_ = makeString("Warning:");

    private static final SubLString $str148$verbosity_level = makeString("verbosity-level");

    private static final SubLString $str149$cutoff_number = makeString("cutoff-number");

    private static final SubLString $$$number = makeString("number");

    private static final SubLString $str151$cutoff_time = makeString("cutoff-time");

    private static final SubLString $$$time = makeString("time");

    private static final SubLString $str153$do_fi_asks = makeString("do-fi-asks");

    private static final SubLString $str154$do_asks = makeString("do-asks");

    private static final SubLString $str155$num_backchains = makeString("num-backchains");

    private static final SubLString $str156$cycle_checking = makeString("cycle-checking");

    private static final SubLString $str157$depth_limit_p = makeString("depth-limit-p");

    private static final SubLString $str158$depth_limit = makeString("depth-limit");

    private static final SubLString $str159$search_type = makeString("search-type");

    private static final SubLString $$$logging = makeString("logging");

    private static final SubLString $str161$no_logging = makeString("no-logging");

    private static final SubLString $str162$domain_mt = makeString("domain-mt");

    private static final SubLString $$$task = makeString("task");

    private static final SubLString $str164$Task_syntax_not_recognized_ = makeString("Task syntax not recognized.");

    private static final SubLSymbol MAKE_NEW_THREAD = makeSymbol("MAKE-NEW-THREAD");

    private static final SubLString $str167$___s_is_not_a_valid__s_microtheor = makeString("~%~s is not a valid ~s microtheory.");



    private static final SubLString $$$Domain = makeString("Domain");

    private static final SubLString $$$Task = makeString("Task");

    private static final SubLString $str171$__Please_enter_an_instance_of__s_ = makeString("~%Please enter an instance of ~s.");

    private static final SubLSymbol FIND_PLANS = makeSymbol("FIND-PLANS");

    private static final SubLString $$$Show = makeString("Show");

    private static final SubLString $$$Hide = makeString("Hide");

    private static final SubLString $str175$Last_task_ = makeString("Last task:");

    private static final SubLString $str176$Planner_halted_because__ = makeString("Planner halted because: ");

    private static final SubLString $str178$Search_space_was_exhausted_ = makeString("Search space was exhausted.");

    private static final SubLString $str180$Depth_limit_was_reached_ = makeString("Depth limit was reached.");

    private static final SubLString $str182$Number_cutoff_was_reached_ = makeString("Number cutoff was reached.");

    private static final SubLString $str184$Time_cutoff_was_reached_ = makeString("Time cutoff was reached.");

    private static final SubLString $str185$_Examine_Plans_ = makeString("[Examine Plans]");

    private static final SubLString $str186$_Examine_Verbose_Output_ = makeString("[Examine Verbose Output]");

    private static final SubLString $str188$plan_finder_html = makeString("plan-finder.html");

    private static final SubLString $$$Plan_Checking_Enabled = makeString("Plan Checking Enabled");

    private static final SubLString $$$Complete = makeString("Complete");

    private static final SubLString $str191$Mt_ = makeString("Mt:");

    private static final SubLString $$$Clear_Mt = makeString("Clear Mt");

    private static final SubLString $str193$Task_ = makeString("Task:");

    private static final SubLString $$$Clear_Task = makeString("Clear Task");



    private static final SubLString $$$Find_Plans = makeString("Find Plans");

    private static final SubLInteger $int$80 = makeInteger(80);

    private static final SubLString $$$virtual = makeString("virtual");

    private static final SubLString $str199$95_ = makeString("95%");

    private static final SubLString $$$50 = makeString("50");

    private static final SubLString $$$Advanced_Planning_Parameters = makeString("Advanced Planning Parameters");

    private static final SubLString $$$SHOP_Domain_Examiner = makeString("SHOP Domain Examiner");

    private static final SubLString $str204$_Verbose_Output_ = makeString("[Verbose Output]");

    private static final SubLString $str205$Go_to_ = makeString("Go to:");

    private static final SubLString $str206$_methods = makeString("#methods");

    private static final SubLString $$$Methods = makeString("Methods");

    private static final SubLString $str208$_operators = makeString("#operators");

    private static final SubLString $$$Operators = makeString("Operators");

    private static final SubLString $str210$_cond_operators = makeString("#cond-operators");

    private static final SubLString $$$Conditional_Ops = makeString("Conditional Ops");

    private static final SubLString $str212$_sconds = makeString("#sconds");

    private static final SubLString $$$Sufficient_Conditions = makeString("Sufficient Conditions");

    private static final SubLString $str214$Planning_Mt_ = makeString("Planning Mt:");

    private static final SubLString $$$methods = makeString("methods");

    private static final SubLSymbol HTML_DISPLAY_ALL_METHODS = makeSymbol("HTML-DISPLAY-ALL-METHODS");

    private static final SubLString $$$operators = makeString("operators");

    private static final SubLSymbol HTML_DISPLAY_ALL_OPERATORS = makeSymbol("HTML-DISPLAY-ALL-OPERATORS");

    private static final SubLString $str220$cond_operators = makeString("cond-operators");

    private static final SubLString $$$Conditional_Operators = makeString("Conditional Operators");

    private static final SubLSymbol HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS = makeSymbol("HTML-DISPLAY-ALL-CONDITIONAL-OPERATORS");

    private static final SubLString $$$sconds = makeString("sconds");

    private static final SubLSymbol HTML_DISPLAY_ALL_SCONDS = makeSymbol("HTML-DISPLAY-ALL-SCONDS");

    private static final SubLString $$$SHOP_Log = makeString("SHOP Log");

    private static final SubLSymbol P_WRAPPER = makeSymbol("P-WRAPPER");

    private static final SubLString $str229$No_Verbose_Output__Set_Verbosity_ = makeString("No Verbose Output! Set Verbosity to a value higher than 0.");

    private static final SubLSymbol DISPLAY_HTML_LOG = makeSymbol("DISPLAY-HTML-LOG");

    private static final SubLString $str231$plan_examiner_html = makeString("plan-examiner.html");

    private static final SubLString $$$Plan_Examiner = makeString("Plan Examiner");

    private static final SubLString $str233$handle_plan_examiner_input = makeString("handle-plan-examiner-input");

    private static final SubLString $$$format = makeString("format");

    private static final SubLString $$$structure = makeString("structure");

    private static final SubLString $str238$_cycl = makeString(":cycl");

    private static final SubLString $str240$_hierarchical = makeString(":hierarchical");

    private static final SubLSymbol HANDLE_PLAN_EXAMINER_INPUT = makeSymbol("HANDLE-PLAN-EXAMINER-INPUT");

    private static final SubLString $$$Change_View = makeString("Change View");

    private static final SubLString $$$submit = makeString("submit");

    private static final SubLString $str245$Format_ = makeString("Format:");

    private static final SubLString $str246$_english = makeString(":english");

    private static final SubLString $$$English = makeString("English");

    private static final SubLString $$$CycL = makeString("CycL");

    private static final SubLString $str249$Structure_ = makeString("Structure:");

    private static final SubLString $str250$_primitive = makeString(":primitive");

    private static final SubLString $$$Primitive = makeString("Primitive");

    private static final SubLString $$$Hierarchical = makeString("Hierarchical");

    private static final SubLString $str253$ = makeString("");

    private static final SubLString $str254$Number_Cutoff_ = makeString("Number Cutoff:");

    private static final SubLString $str255$no_number = makeString("no-number");

    private static final SubLString $$$All_plans = makeString("All plans");

    private static final SubLString $$$_plans = makeString(" plans");

    private static final SubLString $str258$Time_Cutoff_ = makeString("Time Cutoff:");

    private static final SubLString $str259$no_time = makeString("no-time");

    private static final SubLString $$$Run_forever = makeString("Run forever");

    private static final SubLString $$$_seconds = makeString(" seconds");

    private static final SubLString $str262$Depth_Cutoff_ = makeString("Depth Cutoff:");

    private static final SubLString $str263$no_depth_limit = makeString("no-depth-limit");

    private static final SubLString $$$No = makeString("No");

    private static final SubLString $$$Yes = makeString("Yes");

    private static final SubLString $str266$Back_Chaining_ = makeString("Back Chaining:");

    private static final SubLString $$$nil = makeString("nil");

    private static final SubLString $$$No_backchaining = makeString("No backchaining");

    private static final SubLString $$$_backchains = makeString(" backchains");

    private static final SubLString $str270$Cycle_Checking_ = makeString("Cycle Checking:");

    private static final SubLString $str271$no_cycle_checking = makeString("no-cycle-checking");

    private static final SubLString $str272$Log_Process_ = makeString("Log Process:");

    private static final SubLString $str273$_all = makeString(":all");

    private static final SubLString $str274$_first = makeString(":first");

    private static final SubLString $str276$_shallowest = makeString(":shallowest");

    private static final SubLString $str278$_all_shallowest = makeString(":all-shallowest");

    private static final SubLString $str280$_id_first = makeString(":id-first");

    private static final SubLString $str282$_id_all = makeString(":id-all");

    private static final SubLSymbol HTML_TASK_DISPLAYER = makeSymbol("HTML-TASK-DISPLAYER");

    private static final SubLSymbol FIND_INSTANCE_BY_ID_STRING = makeSymbol("FIND-INSTANCE-BY-ID-STRING");

    private static final SubLSymbol HTML_TASK_PAGE = makeSymbol("HTML-TASK-PAGE");

    private static final SubLString $str287$Number_of_plans_found__ = makeString("Number of plans found: ");

    private static final SubLString $str288$Plans_ = makeString("Plans:");

    private static final SubLSymbol $TASK_EXAMINER_LINKS = makeKeyword("TASK-EXAMINER-LINKS");

    private static final SubLString $$$Plan_ = makeString("Plan ");

    private static final SubLString $str291$_ = makeString(":");

    private static final SubLString $str292$No_plans_found_ = makeString("No plans found!");

    private static final SubLString $$$CB_SHOP_PLANNING = makeString("CB SHOP PLANNING");

    private static final SubLSymbol PROCESS_WRAPPER_GET_ID = makeSymbol("PROCESS-WRAPPER-GET-ID");

    private static final SubLList $list296 = list(makeSymbol("ID-STR"));

    private static final SubLSymbol CB_SHOP_ABORT_LINK_HANDLER = makeSymbol("CB-SHOP-ABORT-LINK-HANDLER");

    private static final SubLString $str298$_a__a = makeString("~a&~a");

    private static final SubLString $str299$cb_shop_abort_link_handler = makeString("cb-shop-abort-link-handler");

    private static final SubLString $str300$_Abort_ = makeString("[Abort]");

    private static final SubLSymbol CB_SHOP_PAUSE_LINK_HANDLER = makeSymbol("CB-SHOP-PAUSE-LINK-HANDLER");

    private static final SubLString $str302$cb_shop_pause_link_handler = makeString("cb-shop-pause-link-handler");

    private static final SubLString $str303$_Pause_ = makeString("[Pause]");

    private static final SubLSymbol CB_SHOP_RESUME_LINK_HANDLER = makeSymbol("CB-SHOP-RESUME-LINK-HANDLER");

    private static final SubLString $str305$cb_shop_resume_link_handler = makeString("cb-shop-resume-link-handler");

    private static final SubLString $str306$_Resume_ = makeString("[Resume]");

    private static final SubLSymbol CB_SHOP_VIEW_LINK_HANDLER = makeSymbol("CB-SHOP-VIEW-LINK-HANDLER");

    private static final SubLString $str308$cb_shop_view_link_handler = makeString("cb-shop-view-link-handler");

    private static final SubLString $str309$_View_ = makeString("[View]");

    private static final SubLSymbol CB_SHOP_REPLAN_LINK_HANDLER = makeSymbol("CB-SHOP-REPLAN-LINK-HANDLER");

    private static final SubLString $str311$cb_shop_replan_link_handler = makeString("cb-shop-replan-link-handler");

    private static final SubLString $str312$_Replan_ = makeString("[Replan]");

    private static final SubLSymbol CB_SHOP_VERBOSE_LINK_HANDLER = makeSymbol("CB-SHOP-VERBOSE-LINK-HANDLER");

    private static final SubLString $str314$cb_shop_verbose_link_handler = makeString("cb-shop-verbose-link-handler");

    private static final SubLString $str315$_Log_ = makeString("[Log]");

    private static final SubLSymbol CB_SHOP_DELETE_LINK_HANDLER = makeSymbol("CB-SHOP-DELETE-LINK-HANDLER");

    private static final SubLString $str317$cb_shop_delete_link_handler = makeString("cb-shop-delete-link-handler");

    private static final SubLString $str318$_Delete_ = makeString("[Delete]");

    private static final SubLSymbol CB_SHOP_DOMAIN_EXAMINER_LINK_HANDLER = makeSymbol("CB-SHOP-DOMAIN-EXAMINER-LINK-HANDLER");

    private static final SubLString $str320$cb_shop_domain_examiner_link_hand = makeString("cb-shop-domain-examiner-link-handler");

    private static final SubLString $str321$_Domain_ = makeString("[Domain]");

    private static final SubLString $$$Initializing = makeString("Initializing");

    private static final SubLString $str328$No_Plans_Currently_Available_ = makeString("No Plans Currently Available.");

    private static final SubLString $$$Status = makeString("Status");

    private static final SubLString $$$Actions = makeString("Actions");

    private static final SubLSymbol MAKE_DOMAIN_FROM_MT = makeSymbol("MAKE-DOMAIN-FROM-MT");

    private static final SubLSymbol CB_SHOP_PW_PROOF_CHECKING_FUNC = makeSymbol("CB-SHOP-PW-PROOF-CHECKING-FUNC");

    private static final SubLSymbol CB_SHOP_PW_FUNC = makeSymbol("CB-SHOP-PW-FUNC");



    private static final SubLString $$$Open_Nodes = makeString("Open Nodes");

    private static final SubLString $$$Inferences = makeString("Inferences");

    private static final SubLString $$$Expansions = makeString("Expansions");

    private static final SubLString $$$Plans = makeString("Plans");

    private static final SubLString $$$Depth_Cutoff = makeString("Depth Cutoff");

    private static final SubLString $$$Number_Cutoff = makeString("Number Cutoff");

    private static final SubLString $$$Time_Cutoff = makeString("Time Cutoff");

    private static final SubLString $$$Paused_By_User = makeString("Paused By User");

    private static final SubLString $$$Completed = makeString("Completed");

    private static final SubLString $$$Pausing = makeString("Pausing");

    private static final SubLString $$$Running = makeString("Running");

    private static final SubLSymbol CYCL_PLANS = makeSymbol("CYCL-PLANS");

    private static final SubLSymbol CREATE_PLAN_DISPLAYER = makeSymbol("CREATE-PLAN-DISPLAYER");

    public static final SubLObject with_cb_shop_defaults_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject body = current;
            return listS(CLET, $list_alt22, append(body, NIL));
        }
    }

    public static SubLObject with_cb_shop_defaults(final SubLObject macroform, final SubLObject environment) {
        final SubLObject datum = macroform.rest();
        final SubLObject body;
        final SubLObject current = body = datum;
        return listS(CLET, cb_shop.$list22, append(body, NIL));
    }

    public static final SubLObject cb_shop_plan_checking_enabled_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return $cb_shop_plan_checking_enabled$.getDynamicValue(thread);
        }
    }

    public static SubLObject cb_shop_plan_checking_enabled() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return cb_shop.$cb_shop_plan_checking_enabled$.getDynamicValue(thread);
    }

    public static final SubLObject cb_shop_enable_plan_checking_alt() {
        $cb_shop_plan_checking_enabled$.setDynamicValue(T);
        return NIL;
    }

    public static SubLObject cb_shop_enable_plan_checking() {
        cb_shop.$cb_shop_plan_checking_enabled$.setDynamicValue(T);
        return NIL;
    }

    public static final SubLObject cb_shop_disable_plan_checking_alt() {
        $cb_shop_plan_checking_enabled$.setDynamicValue(NIL);
        return NIL;
    }

    public static SubLObject cb_shop_disable_plan_checking() {
        cb_shop.$cb_shop_plan_checking_enabled$.setDynamicValue(NIL);
        return NIL;
    }

    public static final SubLObject cb_shop_plan_checking_rules_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            $cb_shop_plan_checking_rules$.setDynamicValue(list_utilities.delete_if_not(symbol_function(VALID_ASSERTION), $cb_shop_plan_checking_rules$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            return copy_list($cb_shop_plan_checking_rules$.getDynamicValue(thread));
        }
    }

    public static SubLObject cb_shop_plan_checking_rules() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        cb_shop.$cb_shop_plan_checking_rules$.setDynamicValue(list_utilities.delete_if_not(symbol_function(VALID_ASSERTION), cb_shop.$cb_shop_plan_checking_rules$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
        return copy_list(cb_shop.$cb_shop_plan_checking_rules$.getDynamicValue(thread));
    }

    public static final SubLObject cb_shop_add_to_plan_checking_rules_alt(SubLObject assertion) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != assertion_handles.assertion_p(assertion)) {
                {
                    SubLObject item_var = assertion;
                    if (NIL == member(item_var, $cb_shop_plan_checking_rules$.getDynamicValue(thread), symbol_function(EQL), symbol_function(IDENTITY))) {
                        $cb_shop_plan_checking_rules$.setDynamicValue(cons(item_var, $cb_shop_plan_checking_rules$.getDynamicValue(thread)), thread);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_add_to_plan_checking_rules(final SubLObject assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL != assertion_handles.assertion_p(assertion)) && (NIL == member(assertion, cb_shop.$cb_shop_plan_checking_rules$.getDynamicValue(thread), symbol_function(EQL), symbol_function(IDENTITY)))) {
            cb_shop.$cb_shop_plan_checking_rules$.setDynamicValue(cons(assertion, cb_shop.$cb_shop_plan_checking_rules$.getDynamicValue(thread)), thread);
        }
        return NIL;
    }

    public static final SubLObject cb_shop_remove_from_plan_checking_rules_alt(SubLObject assertion) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            $cb_shop_plan_checking_rules$.setDynamicValue(delete(assertion, $cb_shop_plan_checking_rules$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            return NIL;
        }
    }

    public static SubLObject cb_shop_remove_from_plan_checking_rules(final SubLObject assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        cb_shop.$cb_shop_plan_checking_rules$.setDynamicValue(delete(assertion, cb_shop.$cb_shop_plan_checking_rules$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
        return NIL;
    }

    public static final SubLObject cb_shop_clear_all_plan_checking_rules_alt() {
        $cb_shop_plan_checking_rules$.setDynamicValue(NIL);
        return NIL;
    }

    public static SubLObject cb_shop_clear_all_plan_checking_rules() {
        cb_shop.$cb_shop_plan_checking_rules$.setDynamicValue(NIL);
        return NIL;
    }

    public static final SubLObject cb_shop_use_in_plan_checking_alt(SubLObject args) {
        {
            SubLObject assertion = cb_guess_assertion(args);
            if (NIL == shop_main.shop_planning_assertionP(assertion)) {
                return cb_error($str_alt26$Not_a_planning_assertion_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            } else {
                com.cyc.cycjava.cycl.cb_shop.cb_shop_add_to_plan_checking_rules(assertion);
                return cb_simple_message_page($str_alt27$Rule_has_been_added_to_SHOP_plan_, UNPROVIDED, UNPROVIDED);
            }
        }
    }

    public static SubLObject cb_shop_use_in_plan_checking(final SubLObject args) {
        final SubLObject assertion = cb_guess_assertion(args);
        if (NIL == shop_main.shop_planning_assertionP(assertion)) {
            return cb_error(cb_shop.$str26$Not_a_planning_assertion_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        cb_shop.cb_shop_add_to_plan_checking_rules(assertion);
        return cb_simple_message_page(cb_shop.$str27$Rule_has_been_added_to_SHOP_plan_, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject cb_link_use_in_plan_checking_alt(SubLObject assertion, SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(assertion, ASSERTION_P);
            if (NIL == linktext) {
                linktext = $str_alt30$_Use_in_Plan_Checking_;
            }
            {
                SubLObject frame_name_var = cb_frame_name($SELF);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt32$cb_shop_use_in_plan_checking__a, assertion_handles.assertion_id(assertion));
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(linktext);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
            }
            return assertion;
        }
    }

    public static SubLObject cb_link_use_in_plan_checking(final SubLObject assertion, SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != assertion_handles.assertion_p(assertion) : "! assertion_handles.assertion_p(assertion) " + ("assertion_handles.assertion_p(assertion) " + "CommonSymbols.NIL != assertion_handles.assertion_p(assertion) ") + assertion;
        if (NIL == linktext) {
            linktext = cb_shop.$str31$_Use_in_Plan_Checking_;
        }
        final SubLObject frame_name_var = cb_frame_name($SELF);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str33$cb_shop_use_in_plan_checking__a, assertion_handles.assertion_id(assertion));
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(linktext);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return assertion;
    }

    public static final SubLObject cb_shop_plan_checking_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject arghash = html_arghash.arglist_to_arghash(args);
                if (NIL != html_arghash.get_arghash_value($DISABLE, arghash)) {
                    com.cyc.cycjava.cycl.cb_shop.cb_shop_disable_plan_checking();
                } else {
                    if (NIL != html_arghash.get_arghash_value($ENABLE, arghash)) {
                        com.cyc.cycjava.cycl.cb_shop.cb_shop_enable_plan_checking();
                    } else {
                        if (NIL != html_arghash.get_arghash_value($DISCARD, arghash)) {
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_clear_all_plan_checking_rules();
                        } else {
                            if (NIL != html_arghash.get_arghash_value($UPDATE, arghash)) {
                                {
                                    SubLObject discards = html_arghash.get_arghash_value_list($SELECTED_RULES, arghash);
                                    SubLObject cdolist_list_var = discards;
                                    SubLObject discard_id = NIL;
                                    for (discard_id = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , discard_id = cdolist_list_var.first()) {
                                        com.cyc.cycjava.cycl.cb_shop.cb_shop_remove_from_plan_checking_rules(assertion_handles.find_assertion_by_id(discard_id));
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject enabledP = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_checking_enabled();
                    SubLObject rules = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_checking_rules();
                    SubLObject title_var = $$$Plan_Checking_Control;
                    {
                        SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
                        try {
                            html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? ((SubLObject) (html_macros.$html_id_space_id_generator$.getDynamicValue(thread))) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
                            html_markup(html_macros.$html_html_head$.getGlobalValue());
                            html_markup(html_macros.$html_head_head$.getGlobalValue());
                            html_macros.html_head_content_type();
                            cb_head_shortcut_icon();
                            html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                            if (NIL != title_var) {
                                html_source_readability_terpri(UNPROVIDED);
                                html_markup(html_macros.$html_title_head$.getGlobalValue());
                                html_princ(title_var);
                                html_markup(html_macros.$html_title_tail$.getGlobalValue());
                            }
                            html_markup(html_macros.$html_head_tail$.getGlobalValue());
                            html_source_readability_terpri(UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_1 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                                try {
                                    html_macros.$html_inside_bodyP$.bind(T, thread);
                                    html_markup(html_macros.$html_body_head$.getGlobalValue());
                                    if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                                        html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    if (true) {
                                        html_markup(html_macros.$html_body_class$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup($str_alt45$yui_skin_sam);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_2 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            if (NIL != title_var) {
                                                html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                                html_markup(TWO_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                html_princ(title_var);
                                                html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                                html_markup(TWO_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                            }
                                            {
                                                SubLObject frame_name_var = cb_frame_name(NIL);
                                                html_markup(html_macros.$html_form_head$.getGlobalValue());
                                                html_markup(html_macros.$html_form_action$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                if (NIL != html_macros.$html_form_method_post$.getGlobalValue()) {
                                                    html_markup(html_macros.$html_form_method$.getGlobalValue());
                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                    html_markup(html_macros.$html_form_method_post$.getGlobalValue());
                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                }
                                                if (NIL != frame_name_var) {
                                                    html_markup(html_macros.$html_form_target$.getGlobalValue());
                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                    html_markup(frame_name_var);
                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                }
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_3 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    SubLObject _prev_bind_1 = html_macros.$within_html_form$.currentBinding(thread);
                                                    SubLObject _prev_bind_2 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_macros.$within_html_form$.bind(T, thread);
                                                        html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                                                        html_hidden_input($str_alt46$cb_shop_plan_checking, T, UNPROVIDED);
                                                        cb_help_preamble($CB_SHOP_PLAN_CHECKING, UNPROVIDED, UNPROVIDED);
                                                        cb_link($PLANNER, $str_alt48$_Back_to_Planner_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                        html_newline(UNPROVIDED);
                                                        html_princ($str_alt49$Plan_Checking_is_);
                                                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                        if (NIL != enabledP) {
                                                            html_princ($str_alt50$Enabled_);
                                                        } else {
                                                            html_princ($str_alt51$Disabled_);
                                                        }
                                                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                        html_newline(UNPROVIDED);
                                                        if (NIL != enabledP) {
                                                            html_arghash.html_arghash_submit_input(arghash, $$$Disable, $DISABLE, UNPROVIDED);
                                                        } else {
                                                            html_arghash.html_arghash_submit_input(arghash, $$$Enable, $ENABLE, UNPROVIDED);
                                                        }
                                                        html_indent(UNPROVIDED);
                                                        html_arghash.html_arghash_submit_input(arghash, $$$Update, $UPDATE, UNPROVIDED);
                                                        html_indent(UNPROVIDED);
                                                        html_arghash.html_arghash_submit_input(arghash, $$$Discard_All, $DISCARD, UNPROVIDED);
                                                        html_hr(UNPROVIDED, UNPROVIDED);
                                                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                        if (NIL != rules) {
                                                            html_princ($str_alt56$Choose_Planning_Rules_for_inclusi);
                                                        } else {
                                                            html_princ($str_alt57$No_Rules_in_plan_checking_set_);
                                                        }
                                                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                        html_newline(UNPROVIDED);
                                                        html_markup(html_macros.$html_table_head$.getGlobalValue());
                                                        if (true) {
                                                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                                                            html_char(CHAR_quotation, UNPROVIDED);
                                                            html_markup(ZERO_INTEGER);
                                                            html_char(CHAR_quotation, UNPROVIDED);
                                                        }
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_4 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                {
                                                                    SubLObject cdolist_list_var = rules;
                                                                    SubLObject cur_rule = NIL;
                                                                    for (cur_rule = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_rule = cdolist_list_var.first()) {
                                                                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                                        html_char(CHAR_greater, UNPROVIDED);
                                                                        {
                                                                            SubLObject _prev_bind_0_5 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                            try {
                                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                                                if (true) {
                                                                                    html_markup(html_macros.$html_table_data_valign$.getGlobalValue());
                                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                                    html_markup(html_align($TOP));
                                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                                }
                                                                                html_char(CHAR_greater, UNPROVIDED);
                                                                                {
                                                                                    SubLObject _prev_bind_0_6 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                                    try {
                                                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                                                        html_arghash.html_arghash_checkbox_input(arghash, $SELECTED_RULES, assertion_handles.assertion_id(cur_rule), UNPROVIDED, UNPROVIDED);
                                                                                    } finally {
                                                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_6, thread);
                                                                                    }
                                                                                }
                                                                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                                                html_char(CHAR_greater, UNPROVIDED);
                                                                                {
                                                                                    SubLObject _prev_bind_0_7 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                                    try {
                                                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                                                        cb_show_assertion_readably(cur_rule, UNPROVIDED, UNPROVIDED);
                                                                                    } finally {
                                                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_7, thread);
                                                                                    }
                                                                                }
                                                                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                                            } finally {
                                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_5, thread);
                                                                            }
                                                                        }
                                                                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                                        html_source_readability_terpri(UNPROVIDED);
                                                                    }
                                                                }
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_4, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                                        html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                                                    } finally {
                                                        html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_2, thread);
                                                        html_macros.$within_html_form$.rebind(_prev_bind_1, thread);
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_3, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_form_tail$.getGlobalValue());
                                            }
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_copyright_notice();
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_2, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_body_tail$.getGlobalValue());
                                    html_source_readability_terpri(UNPROVIDED);
                                } finally {
                                    html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_1, thread);
                                }
                            }
                            html_markup(html_macros.$html_html_tail$.getGlobalValue());
                            html_source_readability_terpri(UNPROVIDED);
                        } finally {
                            html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_plan_checking(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject arghash = html_arghash.arglist_to_arghash(args);
        if (NIL != html_arghash.get_arghash_value($DISABLE, arghash)) {
            cb_shop.cb_shop_disable_plan_checking();
        } else
            if (NIL != html_arghash.get_arghash_value($ENABLE, arghash)) {
                cb_shop.cb_shop_enable_plan_checking();
            } else
                if (NIL != html_arghash.get_arghash_value($DISCARD, arghash)) {
                    cb_shop.cb_shop_clear_all_plan_checking_rules();
                } else
                    if (NIL != html_arghash.get_arghash_value($UPDATE, arghash)) {
                        SubLObject cdolist_list_var;
                        final SubLObject discards = cdolist_list_var = html_arghash.get_arghash_value_list($SELECTED_RULES, arghash);
                        SubLObject discard_id = NIL;
                        discard_id = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            cb_shop.cb_shop_remove_from_plan_checking_rules(assertion_handles.find_assertion_by_id(discard_id));
                            cdolist_list_var = cdolist_list_var.rest();
                            discard_id = cdolist_list_var.first();
                        } 
                    }



        final SubLObject enabledP = cb_shop.cb_shop_plan_checking_enabled();
        final SubLObject rules = cb_shop.cb_shop_plan_checking_rules();
        final SubLObject title_var = cb_shop.$$$Plan_Checking_Control;
        final SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
        try {
            html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? html_macros.$html_id_space_id_generator$.getDynamicValue(thread) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            html_markup(cb_shop.$str44$__DOCTYPE_html_PUBLIC_____W3C__DT);
            if (NIL != html_macros.$html_force_ie_standards_mode$.getDynamicValue(thread)) {
                html_source_readability_terpri(UNPROVIDED);
                html_markup(cb_shop.$str45$_meta_http_equiv__X_UA_Compatible);
            }
            html_source_readability_terpri(UNPROVIDED);
            final SubLObject _prev_bind_0_$1 = cyc_file_dependencies.$html_emitted_file_dependencies$.currentBinding(thread);
            try {
                cyc_file_dependencies.$html_emitted_file_dependencies$.bind(cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread) == $UNINITIALIZED ? list(EMPTY_SUBL_OBJECT_ARRAY) : cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread), thread);
                html_markup(html_macros.$html_html_head$.getGlobalValue());
                html_markup(html_macros.$html_head_head$.getGlobalValue());
                html_macros.html_head_content_type();
                cb_head_shortcut_icon();
                html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                cyc_file_dependencies.css($CB_CYC);
                dhtml_macros.html_basic_cb_scripts();
                if (NIL != title_var) {
                    html_source_readability_terpri(UNPROVIDED);
                    html_markup(html_macros.$html_title_head$.getGlobalValue());
                    html_princ(title_var);
                    html_markup(html_macros.$html_title_tail$.getGlobalValue());
                }
                html_markup(html_macros.$html_head_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
                final SubLObject _prev_bind_0_$2 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                try {
                    html_macros.$html_inside_bodyP$.bind(T, thread);
                    html_markup(html_macros.$html_body_head$.getGlobalValue());
                    if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                        html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_markup(html_macros.$html_body_class$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(cb_shop.$str50$yui_skin_sam);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$3 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_div_head$.getGlobalValue());
                        html_markup(html_macros.$html_attribute_id$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$reloadFrameButton);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$4 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_input_head$.getGlobalValue());
                            html_markup(html_macros.$html_input_type$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$button);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_name$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$reload);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_value$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_princ(cb_shop.$$$Refresh_Frames);
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_input_disabledP$.getDynamicValue(thread)) {
                                html_simple_attribute(html_macros.$html_input_disabled$.getGlobalValue());
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$4, thread);
                        }
                        html_markup(html_macros.$html_div_tail$.getGlobalValue());
                        if (NIL != title_var) {
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(title_var);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                        }
                        final SubLObject frame_name_var = cb_frame_name(NIL);
                        html_markup(html_macros.$html_form_head$.getGlobalValue());
                        html_markup(html_macros.$html_form_action$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_macros.$html_form_method$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$post);
                        html_char(CHAR_quotation, UNPROVIDED);
                        if (NIL != frame_name_var) {
                            html_markup(html_macros.$html_form_target$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(frame_name_var);
                            html_char(CHAR_quotation, UNPROVIDED);
                        }
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$5 = html_macros.$html_safe_print$.currentBinding(thread);
                        final SubLObject _prev_bind_2 = html_macros.$within_html_form$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_macros.$within_html_form$.bind(T, thread);
                            html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                            html_hidden_input(cb_shop.$str56$cb_shop_plan_checking, T, UNPROVIDED);
                            cb_help_preamble(cb_shop.$CB_SHOP_PLAN_CHECKING, UNPROVIDED, UNPROVIDED);
                            cb_link($PLANNER, cb_shop.$str58$_Back_to_Planner_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            html_newline(UNPROVIDED);
                            html_princ(cb_shop.$$$Plan_Checking_is_);
                            html_markup(html_macros.$html_strong_head$.getGlobalValue());
                            if (NIL != enabledP) {
                                html_princ(cb_shop.$str60$Enabled_);
                            } else {
                                html_princ(cb_shop.$str61$Disabled_);
                            }
                            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            html_newline(UNPROVIDED);
                            if (NIL != enabledP) {
                                html_arghash.html_arghash_submit_input(arghash, cb_shop.$$$Disable, $DISABLE, UNPROVIDED);
                            } else {
                                html_arghash.html_arghash_submit_input(arghash, cb_shop.$$$Enable, $ENABLE, UNPROVIDED);
                            }
                            html_indent(UNPROVIDED);
                            html_arghash.html_arghash_submit_input(arghash, cb_shop.$$$Update, $UPDATE, UNPROVIDED);
                            html_indent(UNPROVIDED);
                            html_arghash.html_arghash_submit_input(arghash, cb_shop.$$$Discard_All, $DISCARD, UNPROVIDED);
                            html_hr(UNPROVIDED, UNPROVIDED);
                            html_markup(html_macros.$html_strong_head$.getGlobalValue());
                            if (NIL != rules) {
                                html_princ(cb_shop.$str66$Choose_Planning_Rules_for_inclusi);
                            } else {
                                html_princ(cb_shop.$str67$No_Rules_in_plan_checking_set_);
                            }
                            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            html_newline(UNPROVIDED);
                            html_markup(html_macros.$html_table_head$.getGlobalValue());
                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(ZERO_INTEGER);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$6 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                SubLObject cdolist_list_var2 = rules;
                                SubLObject cur_rule = NIL;
                                cur_rule = cdolist_list_var2.first();
                                while (NIL != cdolist_list_var2) {
                                    html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                    html_char(CHAR_greater, UNPROVIDED);
                                    final SubLObject _prev_bind_0_$7 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                        html_markup(html_macros.$html_table_data_valign$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(html_align($TOP));
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_char(CHAR_greater, UNPROVIDED);
                                        final SubLObject _prev_bind_0_$8 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            html_arghash.html_arghash_checkbox_input(arghash, $SELECTED_RULES, assertion_handles.assertion_id(cur_rule), UNPROVIDED, UNPROVIDED);
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$8, thread);
                                        }
                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                        html_char(CHAR_greater, UNPROVIDED);
                                        final SubLObject _prev_bind_0_$9 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            cb_show_assertion_readably(cur_rule, UNPROVIDED, UNPROVIDED);
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$9, thread);
                                        }
                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$7, thread);
                                    }
                                    html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                    html_source_readability_terpri(UNPROVIDED);
                                    cdolist_list_var2 = cdolist_list_var2.rest();
                                    cur_rule = cdolist_list_var2.first();
                                } 
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$6, thread);
                            }
                            html_markup(html_macros.$html_table_tail$.getGlobalValue());
                            html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                        } finally {
                            html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_3, thread);
                            html_macros.$within_html_form$.rebind(_prev_bind_2, thread);
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$5, thread);
                        }
                        html_markup(html_macros.$html_form_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_copyright_notice();
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$3, thread);
                    }
                    html_markup(html_macros.$html_body_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                } finally {
                    html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_$2, thread);
                }
                html_markup(html_macros.$html_html_tail$.getGlobalValue());
            } finally {
                cyc_file_dependencies.$html_emitted_file_dependencies$.rebind(_prev_bind_0_$1, thread);
            }
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static final SubLObject cb_link_plan_checking_alt(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == linktext) {
                linktext = $str_alt60$_Plan_Checking_;
            }
            {
                SubLObject frame_name_var = cb_frame_name($SELF);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                html_princ($str_alt46$cb_shop_plan_checking);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(linktext);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    public static SubLObject cb_link_plan_checking(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == linktext) {
            linktext = cb_shop.$str70$_Plan_Checking_;
        }
        final SubLObject frame_name_var = cb_frame_name($SELF);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        html_princ(cb_shop.$str56$cb_shop_plan_checking);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(linktext);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    /**
     *
     *
     * @param MT
    hlmt-p
     * 		
     * @unknown so much for privacy. I feel ill.
     */
    @LispMethod(comment = "@param MT\nhlmt-p\r\n\t\t\r\n@unknown so much for privacy. I feel ill.")
    public static final SubLObject cb_shop_gather_simple_action_rules_in_proof_checker_alt(SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject assertions = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        {
                            SubLObject cdolist_list_var = shop_main.$shop_simple_action_definition_predicates$.getGlobalValue();
                            SubLObject cur_pred = NIL;
                            for (cur_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_pred = cdolist_list_var.first()) {
                                assertions = append(assertions, kb_mapping.gather_predicate_rule_index(cur_pred, $POS, UNPROVIDED, UNPROVIDED));
                            }
                        }
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                {
                    SubLObject cdolist_list_var = assertions;
                    SubLObject cur_assertion = NIL;
                    for (cur_assertion = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_assertion = cdolist_list_var.first()) {
                        cb_assertion_editor.cb_add_to_proof_checker_rules(cur_assertion);
                    }
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param MT
    hlmt-p
     * 		
     * @unknown so much for privacy. I feel ill.
     */
    @LispMethod(comment = "@param MT\nhlmt-p\r\n\t\t\r\n@unknown so much for privacy. I feel ill.")
    public static SubLObject cb_shop_gather_simple_action_rules_in_proof_checker(final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject assertions = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            SubLObject cdolist_list_var = shop_main.$shop_simple_action_definition_predicates$.getGlobalValue();
            SubLObject cur_pred = NIL;
            cur_pred = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                assertions = append(assertions, kb_mapping.gather_predicate_rule_index(cur_pred, $POS, UNPROVIDED, UNPROVIDED));
                cdolist_list_var = cdolist_list_var.rest();
                cur_pred = cdolist_list_var.first();
            } 
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        SubLObject cdolist_list_var2 = assertions;
        SubLObject cur_assertion = NIL;
        cur_assertion = cdolist_list_var2.first();
        while (NIL != cdolist_list_var2) {
            cb_assertion_editor.cb_add_to_proof_checker_rules(cur_assertion);
            cdolist_list_var2 = cdolist_list_var2.rest();
            cur_assertion = cdolist_list_var2.first();
        } 
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_cyc_development_planner_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_cyc_development_planner_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_cyc_development_planner_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP, DISPLAYER_FACTORY, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP, DOMAIN_TABLE, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP, PROBLEM_TABLE, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP, THREADS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_cyc_development_planner_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, cb_shop.SHOP, cb_shop.DISPLAYER_FACTORY, NIL);
        classes.subloop_initialize_slot(new_instance, cb_shop.SHOP, cb_shop.DOMAIN_TABLE, NIL);
        classes.subloop_initialize_slot(new_instance, cb_shop.SHOP, cb_shop.PROBLEM_TABLE, NIL);
        classes.subloop_initialize_slot(new_instance, cb_shop.SHOP, cb_shop.THREADS, NIL);
        return NIL;
    }

    public static final SubLObject cyc_development_planner_p_alt(SubLObject cyc_development_planner) {
        return classes.subloop_instanceof_class(cyc_development_planner, CYC_DEVELOPMENT_PLANNER);
    }

    public static SubLObject cyc_development_planner_p(final SubLObject cyc_development_planner) {
        return classes.subloop_instanceof_class(cyc_development_planner, cb_shop.CYC_DEVELOPMENT_PLANNER);
    }

    public static final SubLObject cyc_development_planner_initialize_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_cyc_development_planner_method = NIL;
            SubLObject displayer_factory = shop_main.get_shop_displayer_factory(self);
            try {
                try {
                    shop_main.shop_initialize_method(self);
                    displayer_factory = object.object_new_method(CYC_DEVELOPMENT_PLAN_DISPLAYER_FACTORY);
                    sublisp_throw($sym80$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            shop_main.set_shop_displayer_factory(self, displayer_factory);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_cyc_development_planner_method = Errors.handleThrowable(ccatch_env_var, $sym80$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD);
            }
            return catch_var_for_cyc_development_planner_method;
        }
    }

    public static SubLObject cyc_development_planner_initialize_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_cyc_development_planner_method = NIL;
        SubLObject displayer_factory = shop_main.get_shop_displayer_factory(self);
        try {
            thread.throwStack.push(cb_shop.$sym90$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD);
            try {
                shop_main.shop_initialize_method(self);
                displayer_factory = object.object_new_method(cb_shop.CYC_DEVELOPMENT_PLAN_DISPLAYER_FACTORY);
                sublisp_throw(cb_shop.$sym90$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    shop_main.set_shop_displayer_factory(self, displayer_factory);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_cyc_development_planner_method = Errors.handleThrowable(ccatch_env_var, cb_shop.$sym90$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_cyc_development_planner_method;
    }

    public static final SubLObject cyc_development_planner_make_domain_with_rules_method_alt(SubLObject self, SubLObject rules, SubLObject task_mt) {
        {
            SubLObject catch_var_for_cyc_development_planner_method = NIL;
            SubLObject domain_table = shop_main.get_shop_domain_table(self);
            try {
                try {
                    {
                        SubLObject domain = object.object_new_method(SHOP_CYC_DOMAIN);
                        shop_domain.shop_cyc_domain_init_domain_with_rules_method(domain, rules, task_mt);
                        sethash(task_mt, domain_table, domain);
                        sublisp_throw($sym87$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD, self);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            shop_main.set_shop_domain_table(self, domain_table);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_cyc_development_planner_method = Errors.handleThrowable(ccatch_env_var, $sym87$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD);
            }
            return catch_var_for_cyc_development_planner_method;
        }
    }

    public static SubLObject cyc_development_planner_make_domain_with_rules_method(final SubLObject self, final SubLObject rules, final SubLObject task_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_cyc_development_planner_method = NIL;
        final SubLObject domain_table = shop_main.get_shop_domain_table(self);
        try {
            thread.throwStack.push(cb_shop.$sym97$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD);
            try {
                final SubLObject domain = object.object_new_method(SHOP_CYC_DOMAIN);
                shop_domain.shop_cyc_domain_init_domain_with_rules_method(domain, rules, task_mt);
                sethash(task_mt, domain_table, domain);
                sublisp_throw(cb_shop.$sym97$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    shop_main.set_shop_domain_table(self, domain_table);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_cyc_development_planner_method = Errors.handleThrowable(ccatch_env_var, cb_shop.$sym97$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_cyc_development_planner_method;
    }

    /**
     *
     *
     * @param TASK-MT
    microtheory-p
     * 		
     */
    @LispMethod(comment = "@param TASK-MT\nmicrotheory-p")
    public static final SubLObject cyc_development_planner_make_domain_from_proof_checker_method_alt(SubLObject self, SubLObject task_mt) {
        {
            SubLObject assertions = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_checking_rules();
            return com.cyc.cycjava.cycl.cb_shop.cyc_development_planner_make_domain_with_rules_method(self, assertions, task_mt);
        }
    }

    /**
     *
     *
     * @param TASK-MT
    microtheory-p
     * 		
     */
    @LispMethod(comment = "@param TASK-MT\nmicrotheory-p")
    public static SubLObject cyc_development_planner_make_domain_from_proof_checker_method(final SubLObject self, final SubLObject task_mt) {
        final SubLObject assertions = cb_shop.cb_shop_plan_checking_rules();
        return cb_shop.cyc_development_planner_make_domain_with_rules_method(self, assertions, task_mt);
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static final SubLObject plan_finder_alt(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != ke.cyclist_is_guest()) {
                cyc_navigator_internals.guest_warn($$$Plan_Finder);
            } else {
                if (NIL != $cyc_development_environment$.getDynamicValue(thread)) {
                    $cb_shop_planner$.setDynamicValue(object.object_new_method(CYC_DEVELOPMENT_PLANNER), thread);
                } else {
                    $cb_shop_planner$.setDynamicValue(object.object_new_method(SHOP), thread);
                }
                if (NIL != html_macros.html_inside_body_p()) {
                    Errors.error($str_alt95$Cannot_have_a_FRAMESET_tag_inside);
                }
                html_markup(html_macros.$html_frameset_head$.getGlobalValue());
                if (true) {
                    html_markup(html_macros.$html_frameset_rows$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup($str_alt96$400__);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_frameset_frameborder$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ONE_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        cb_frame($CB_SHOP_CONTROLS, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        cb_frame($CB_SHOP_SUMMARY, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_frameset_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
            }
            com.cyc.cycjava.cycl.cb_shop.plan_finder_internal(args);
            return NIL;
        }
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static SubLObject plan_finder(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn(cb_shop.$$$Plan_Finder);
        } else {
            if (NIL != cb_shop.$cyc_development_environment$.getDynamicValue(thread)) {
                cb_shop.$cb_shop_planner$.setDynamicValue(object.object_new_method(cb_shop.CYC_DEVELOPMENT_PLANNER), thread);
            } else {
                cb_shop.$cb_shop_planner$.setDynamicValue(object.object_new_method(cb_shop.SHOP), thread);
            }
            if (NIL != html_macros.html_inside_body_p()) {
                Errors.error(cb_shop.$str105$Cannot_have_a_FRAMESET_tag_inside);
            }
            html_markup(html_macros.$html_frameset_head$.getGlobalValue());
            html_markup(html_macros.$html_frameset_rows$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(cb_shop.$str106$400__);
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(html_macros.$html_frameset_frameborder$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(ONE_INTEGER);
            html_char(CHAR_quotation, UNPROVIDED);
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                cb_frame(cb_shop.$CB_SHOP_CONTROLS, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                cb_frame(cb_shop.$CB_SHOP_SUMMARY, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
            }
            html_markup(html_macros.$html_frameset_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        }
        cb_shop.plan_finder_internal(args);
        return NIL;
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static final SubLObject plan_examiner_alt(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn($$$Planner_Examiner);
        } else {
            com.cyc.cycjava.cycl.cb_shop.plan_examiner_internal(args);
        }
        return NIL;
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static SubLObject plan_examiner(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn(cb_shop.$$$Planner_Examiner);
        } else {
            cb_shop.plan_examiner_internal(args);
        }
        return NIL;
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static final SubLObject plan_verbose_examiner_alt(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn($$$Plan_Verbose_Examiner);
        } else {
            com.cyc.cycjava.cycl.cb_shop.plan_verbose_examiner_internal(args);
        }
        return NIL;
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static SubLObject plan_verbose_examiner(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn(cb_shop.$$$Plan_Verbose_Examiner);
        } else {
            cb_shop.plan_verbose_examiner_internal(args);
        }
        return NIL;
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static final SubLObject plan_task_examiner_alt(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn($$$Plan_Task_Examiner);
        } else {
            com.cyc.cycjava.cycl.cb_shop.plan_task_examiner_internal(args);
        }
        return NIL;
    }

    /**
     *
     *
     * @param args;
     * 		ignored, but necessary for compatibility
     * @return nil
     */
    @LispMethod(comment = "@param args;\r\n\t\tignored, but necessary for compatibility\r\n@return nil")
    public static SubLObject plan_task_examiner(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn(cb_shop.$$$Plan_Task_Examiner);
        } else {
            cb_shop.plan_task_examiner_internal(args);
        }
        return NIL;
    }

    public static final SubLObject cb_link_cb_shop_summary_alt(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == linktext) {
                linktext = $str_alt110$_Summary_;
            }
            {
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                html_princ($str_alt109$cb_shop_information);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(linktext);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    public static SubLObject cb_link_cb_shop_summary(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == linktext) {
            linktext = cb_shop.$str120$_Summary_;
        }
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        html_princ(cb_shop.$str119$cb_shop_information);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(linktext);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_link_cb_shop_controls_alt(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == linktext) {
                linktext = $str_alt113$_Controls_;
            }
            {
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                html_princ($str_alt107$cb_shop_controls);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(linktext);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    public static SubLObject cb_link_cb_shop_controls(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == linktext) {
            linktext = cb_shop.$str123$_Controls_;
        }
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        html_princ(cb_shop.$str117$cb_shop_controls);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(linktext);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    /**
     *
     *
     * @param linktext;
     * 		a different link name if wanted.
     * @return nil
    Defines a CYC Browser link method called :planner.
     */
    @LispMethod(comment = "@param linktext;\r\n\t\ta different link name if wanted.\r\n@return nil\r\nDefines a CYC Browser link method called :planner.")
    public static final SubLObject cb_link_planner_alt(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == linktext) {
                linktext = $str_alt115$_Plan_Finder_;
            }
            {
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                html_princ($str_alt116$plan_finder);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(linktext);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param linktext;
     * 		a different link name if wanted.
     * @return nil
    Defines a CYC Browser link method called :planner.
     */
    @LispMethod(comment = "@param linktext;\r\n\t\ta different link name if wanted.\r\n@return nil\r\nDefines a CYC Browser link method called :planner.")
    public static SubLObject cb_link_planner(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == linktext) {
            linktext = cb_shop.$str125$_Plan_Finder_;
        }
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        html_princ(cb_shop.$str126$plan_finder);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(linktext);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    /**
     *
     *
     * @param linktext;
     * 		a different link name if wanted.
     * @return nil
    Defines a CYC Browser link method called :plan-examiner.
     */
    @LispMethod(comment = "@param linktext;\r\n\t\ta different link name if wanted.\r\n@return nil\r\nDefines a CYC Browser link method called :plan-examiner.")
    public static final SubLObject cb_link_plan_examiner_alt(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == linktext) {
                linktext = $str_alt118$_Plan_Examiner_;
            }
            {
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                html_princ($str_alt119$plan_examiner);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(linktext);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param linktext;
     * 		a different link name if wanted.
     * @return nil
    Defines a CYC Browser link method called :plan-examiner.
     */
    @LispMethod(comment = "@param linktext;\r\n\t\ta different link name if wanted.\r\n@return nil\r\nDefines a CYC Browser link method called :plan-examiner.")
    public static SubLObject cb_link_plan_examiner(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == linktext) {
            linktext = cb_shop.$str128$_Plan_Examiner_;
        }
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        html_princ(cb_shop.$str129$plan_examiner);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(linktext);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    /**
     *
     *
     * @param linktext;
     * 		a different link name if wanted.
     * @return nil
    Defines a CYC Browser link method called :plan-verbose-examiner.
     */
    @LispMethod(comment = "@param linktext;\r\n\t\ta different link name if wanted.\r\n@return nil\r\nDefines a CYC Browser link method called :plan-verbose-examiner.")
    public static final SubLObject cb_link_plan_verbose_examiner_alt(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == linktext) {
                linktext = $str_alt122$_Plan_Verbose_Examiner_;
            }
            {
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                html_princ($str_alt123$plan_verbose_examiner);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(linktext);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param linktext;
     * 		a different link name if wanted.
     * @return nil
    Defines a CYC Browser link method called :plan-verbose-examiner.
     */
    @LispMethod(comment = "@param linktext;\r\n\t\ta different link name if wanted.\r\n@return nil\r\nDefines a CYC Browser link method called :plan-verbose-examiner.")
    public static SubLObject cb_link_plan_verbose_examiner(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == linktext) {
            linktext = cb_shop.$str132$_Plan_Verbose_Examiner_;
        }
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        html_princ(cb_shop.$str133$plan_verbose_examiner);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(linktext);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject clear_cb_planner_warning_message_alt() {
        $cb_planner_warning_message$.setDynamicValue(NIL);
        return NIL;
    }

    public static SubLObject clear_cb_planner_warning_message() {
        cb_shop.$cb_planner_warning_message$.setDynamicValue(NIL);
        return NIL;
    }

    public static final SubLObject cb_shop_controls_alt(SubLObject args) {
        return com.cyc.cycjava.cycl.cb_shop.plan_finder_internal(args);
    }

    public static SubLObject cb_shop_controls(final SubLObject args) {
        return cb_shop.plan_finder_internal(args);
    }

    public static final SubLObject cb_shop_information_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject refreshingP = com.cyc.cycjava.cycl.cb_shop.cb_shop_information_refreshP();
                if (NIL != refreshingP) {
                    html_markup(html_macros.$html_head_head$.getGlobalValue());
                    html_macros.html_head_content_type();
                    html_refresh($cb_shop_refresh_rate$.getGlobalValue());
                    html_markup(html_macros.$html_head_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                }
                {
                    SubLObject color_value = html_macros.$html_default_bgcolor$.getDynamicValue(thread);
                    {
                        SubLObject _prev_bind_0 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                        try {
                            html_macros.$html_inside_bodyP$.bind(T, thread);
                            html_markup(html_macros.$html_body_head$.getGlobalValue());
                            if (NIL != color_value) {
                                html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_color(color_value));
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_8 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                    html_markup(THREE_INTEGER);
                                    html_char(CHAR_greater, UNPROVIDED);
                                    html_princ($$$Summary);
                                    html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                    html_markup(THREE_INTEGER);
                                    html_char(CHAR_greater, UNPROVIDED);
                                    cb_link($CB_SHOP_CONTROLS, $str_alt130$_New_Plan_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    html_indent(UNPROVIDED);
                                    cb_link($CB_SHOP_SUMMARY, $str_alt131$_Full_Screen_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    html_newline(UNPROVIDED);
                                    com.cyc.cycjava.cycl.cb_shop.cb_shop_display_processes();
                                    html_source_readability_terpri(UNPROVIDED);
                                    html_copyright_notice();
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_8, thread);
                                }
                            }
                            html_markup(html_macros.$html_body_tail$.getGlobalValue());
                            html_source_readability_terpri(UNPROVIDED);
                        } finally {
                            html_macros.$html_inside_bodyP$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_information(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject refreshingP = cb_shop.cb_shop_information_refreshP();
        if (NIL != refreshingP) {
            html_markup(html_macros.$html_head_head$.getGlobalValue());
            html_macros.html_head_content_type();
            html_refresh(cb_shop.$cb_shop_refresh_rate$.getGlobalValue(), UNPROVIDED);
            html_markup(html_macros.$html_head_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        }
        final SubLObject color_value = html_macros.$html_default_bgcolor$.getDynamicValue(thread);
        final SubLObject _prev_bind_0 = html_macros.$html_inside_bodyP$.currentBinding(thread);
        try {
            html_macros.$html_inside_bodyP$.bind(T, thread);
            html_markup(html_macros.$html_body_head$.getGlobalValue());
            if (NIL != color_value) {
                html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_color(color_value));
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$10 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_heading_head$.getGlobalValue());
                html_markup(THREE_INTEGER);
                html_char(CHAR_greater, UNPROVIDED);
                html_princ(cb_shop.$$$Summary);
                html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                html_markup(THREE_INTEGER);
                html_char(CHAR_greater, UNPROVIDED);
                cb_link(cb_shop.$CB_SHOP_CONTROLS, cb_shop.$str140$_New_Plan_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                html_indent(UNPROVIDED);
                cb_link(cb_shop.$CB_SHOP_SUMMARY, cb_shop.$str141$_Full_Screen_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                html_newline(UNPROVIDED);
                cb_shop.cb_shop_display_processes();
                html_source_readability_terpri(UNPROVIDED);
                html_copyright_notice();
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$10, thread);
            }
            html_markup(html_macros.$html_body_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_inside_bodyP$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static final SubLObject plan_finder_internal_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject title_var = $$$Hierarchical_Ordered_Planner;
                {
                    SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
                    try {
                        html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? ((SubLObject) (html_macros.$html_id_space_id_generator$.getDynamicValue(thread))) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
                        html_markup(html_macros.$html_html_head$.getGlobalValue());
                        html_markup(html_macros.$html_head_head$.getGlobalValue());
                        html_macros.html_head_content_type();
                        cb_head_shortcut_icon();
                        html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                        if (NIL != title_var) {
                            html_source_readability_terpri(UNPROVIDED);
                            html_markup(html_macros.$html_title_head$.getGlobalValue());
                            html_princ(title_var);
                            html_markup(html_macros.$html_title_tail$.getGlobalValue());
                        }
                        html_complete.html_complete_script();
                        html_complete.html_autocomplete_css();
                        html_complete.html_autocomplete_scripts();
                        html_markup(html_macros.$html_head_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_9 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                            try {
                                html_macros.$html_inside_bodyP$.bind(T, thread);
                                html_markup(html_macros.$html_body_head$.getGlobalValue());
                                if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                                    html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (true) {
                                    html_markup(html_macros.$html_body_class$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup($str_alt45$yui_skin_sam);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_10 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        if (NIL != title_var) {
                                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                            html_markup(TWO_INTEGER);
                                            html_char(CHAR_greater, UNPROVIDED);
                                            html_princ(title_var);
                                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                            html_markup(TWO_INTEGER);
                                            html_char(CHAR_greater, UNPROVIDED);
                                        }
                                        {
                                            SubLObject frame_name_var = cb_frame_name($MAIN);
                                            html_markup(html_macros.$html_form_head$.getGlobalValue());
                                            html_markup(html_macros.$html_form_action$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            if (NIL != html_macros.$html_form_method_post$.getGlobalValue()) {
                                                html_markup(html_macros.$html_form_method$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(html_macros.$html_form_method_post$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            if (NIL != frame_name_var) {
                                                html_markup(html_macros.$html_form_target$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(frame_name_var);
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_11 = html_macros.$html_safe_print$.currentBinding(thread);
                                                SubLObject _prev_bind_1 = html_macros.$within_html_form$.currentBinding(thread);
                                                SubLObject _prev_bind_2 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_macros.$within_html_form$.bind(T, thread);
                                                    html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                                                    html_hidden_input($str_alt116$plan_finder, T, UNPROVIDED);
                                                    {
                                                        SubLObject new_taskP = list_utilities.sublisp_boolean(html_extract_input($str_alt134$find_plans, args));
                                                        SubLObject show_advanced_settingsP = list_utilities.sublisp_boolean(html_extract_input($str_alt135$show_advanced_parameters, args));
                                                        com.cyc.cycjava.cycl.cb_shop.extract_settings(args);
                                                        if ((NIL != $cb_last_planner_show_advanced_settings$.getDynamicValue(thread)) && (NIL != args)) {
                                                            com.cyc.cycjava.cycl.cb_shop.extract_advanced_settings(args);
                                                        }
                                                        com.cyc.cycjava.cycl.cb_shop.cb_shop_extract_problem(args);
                                                        if (NIL != show_advanced_settingsP) {
                                                            com.cyc.cycjava.cycl.cb_shop.plan_finder_change_advanced_parameters(args);
                                                        } else {
                                                            if (NIL != new_taskP) {
                                                                com.cyc.cycjava.cycl.cb_shop.cb_shop_new_task(args);
                                                            }
                                                        }
                                                        if (NIL != $cb_planner_warning_message$.getDynamicValue(thread)) {
                                                            {
                                                                SubLObject color_val = $$$red;
                                                                html_markup(html_macros.$html_font_head$.getGlobalValue());
                                                                if (NIL != color_val) {
                                                                    html_markup(html_macros.$html_font_color$.getGlobalValue());
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                    html_markup(html_color(color_val));
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                }
                                                                html_char(CHAR_greater, UNPROVIDED);
                                                                {
                                                                    SubLObject _prev_bind_0_12 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                    try {
                                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                                        html_princ($str_alt137$Warning_);
                                                                    } finally {
                                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_12, thread);
                                                                    }
                                                                }
                                                                html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                                            }
                                                            html_princ_doc_string($cb_planner_warning_message$.getDynamicValue(thread), UNPROVIDED);
                                                            html_newline(UNPROVIDED);
                                                        }
                                                        com.cyc.cycjava.cycl.cb_shop.cb_shop_ask_for_new_task(args);
                                                    }
                                                    html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                                                } finally {
                                                    html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_2, thread);
                                                    html_macros.$within_html_form$.rebind(_prev_bind_1, thread);
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_11, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_form_tail$.getGlobalValue());
                                        }
                                        html_source_readability_terpri(UNPROVIDED);
                                        html_copyright_notice();
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_10, thread);
                                    }
                                }
                                html_markup(html_macros.$html_body_tail$.getGlobalValue());
                                html_source_readability_terpri(UNPROVIDED);
                            } finally {
                                html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_9, thread);
                            }
                        }
                        html_markup(html_macros.$html_html_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject plan_finder_internal(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject title_var = cb_shop.$$$Hierarchical_Ordered_Planner;
        final SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
        try {
            html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? html_macros.$html_id_space_id_generator$.getDynamicValue(thread) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            html_markup(cb_shop.$str44$__DOCTYPE_html_PUBLIC_____W3C__DT);
            if (NIL != html_macros.$html_force_ie_standards_mode$.getDynamicValue(thread)) {
                html_source_readability_terpri(UNPROVIDED);
                html_markup(cb_shop.$str45$_meta_http_equiv__X_UA_Compatible);
            }
            html_source_readability_terpri(UNPROVIDED);
            final SubLObject _prev_bind_0_$11 = cyc_file_dependencies.$html_emitted_file_dependencies$.currentBinding(thread);
            try {
                cyc_file_dependencies.$html_emitted_file_dependencies$.bind(cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread) == $UNINITIALIZED ? list(EMPTY_SUBL_OBJECT_ARRAY) : cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread), thread);
                html_markup(html_macros.$html_html_head$.getGlobalValue());
                html_markup(html_macros.$html_head_head$.getGlobalValue());
                html_macros.html_head_content_type();
                cb_head_shortcut_icon();
                html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                cyc_file_dependencies.css($CB_CYC);
                dhtml_macros.html_basic_cb_scripts();
                if (NIL != title_var) {
                    html_source_readability_terpri(UNPROVIDED);
                    html_markup(html_macros.$html_title_head$.getGlobalValue());
                    html_princ(title_var);
                    html_markup(html_macros.$html_title_tail$.getGlobalValue());
                }
                html_complete.html_complete_script();
                cyc_file_dependencies.css(cb_shop.$SAM_AUTOCOMPLETE_CSS);
                html_markup(html_macros.$html_head_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
                final SubLObject _prev_bind_0_$12 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                try {
                    html_macros.$html_inside_bodyP$.bind(T, thread);
                    html_markup(html_macros.$html_body_head$.getGlobalValue());
                    if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                        html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_markup(html_macros.$html_body_class$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(cb_shop.$str50$yui_skin_sam);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$13 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_div_head$.getGlobalValue());
                        html_markup(html_macros.$html_attribute_id$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$reloadFrameButton);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$14 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_input_head$.getGlobalValue());
                            html_markup(html_macros.$html_input_type$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$button);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_name$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$reload);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_value$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_princ(cb_shop.$$$Refresh_Frames);
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_input_disabledP$.getDynamicValue(thread)) {
                                html_simple_attribute(html_macros.$html_input_disabled$.getGlobalValue());
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$14, thread);
                        }
                        html_markup(html_macros.$html_div_tail$.getGlobalValue());
                        if (NIL != title_var) {
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(title_var);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                        }
                        final SubLObject frame_name_var = cb_frame_name($MAIN);
                        html_markup(html_macros.$html_form_head$.getGlobalValue());
                        html_markup(html_macros.$html_form_action$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_macros.$html_form_method$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$post);
                        html_char(CHAR_quotation, UNPROVIDED);
                        if (NIL != frame_name_var) {
                            html_markup(html_macros.$html_form_target$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(frame_name_var);
                            html_char(CHAR_quotation, UNPROVIDED);
                        }
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$15 = html_macros.$html_safe_print$.currentBinding(thread);
                        final SubLObject _prev_bind_2 = html_macros.$within_html_form$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_macros.$within_html_form$.bind(T, thread);
                            html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                            html_hidden_input(cb_shop.$str126$plan_finder, T, UNPROVIDED);
                            final SubLObject new_taskP = list_utilities.sublisp_boolean(html_extract_input(cb_shop.$str144$find_plans, args));
                            final SubLObject show_advanced_settingsP = list_utilities.sublisp_boolean(html_extract_input(cb_shop.$str145$show_advanced_parameters, args));
                            cb_shop.extract_settings(args);
                            if ((NIL != cb_shop.$cb_last_planner_show_advanced_settings$.getDynamicValue(thread)) && (NIL != args)) {
                                cb_shop.extract_advanced_settings(args);
                            }
                            cb_shop.cb_shop_extract_problem(args);
                            if (NIL != show_advanced_settingsP) {
                                cb_shop.plan_finder_change_advanced_parameters(args);
                            } else
                                if (NIL != new_taskP) {
                                    cb_shop.cb_shop_new_task(args);
                                }

                            if (NIL != cb_shop.$cb_planner_warning_message$.getDynamicValue(thread)) {
                                final SubLObject color_val = cb_shop.$$$red;
                                html_markup(html_macros.$html_font_head$.getGlobalValue());
                                if (NIL != color_val) {
                                    html_markup(html_macros.$html_font_color$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_color(color_val));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                final SubLObject _prev_bind_0_$16 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_princ(cb_shop.$str147$Warning_);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$16, thread);
                                }
                                html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                html_princ_doc_string(cb_shop.$cb_planner_warning_message$.getDynamicValue(thread), UNPROVIDED);
                                html_newline(UNPROVIDED);
                            }
                            cb_shop.cb_shop_ask_for_new_task(args);
                            html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                        } finally {
                            html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_3, thread);
                            html_macros.$within_html_form$.rebind(_prev_bind_2, thread);
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$15, thread);
                        }
                        html_markup(html_macros.$html_form_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_copyright_notice();
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$13, thread);
                    }
                    html_markup(html_macros.$html_body_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                } finally {
                    html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_$12, thread);
                }
                html_markup(html_macros.$html_html_tail$.getGlobalValue());
            } finally {
                cyc_file_dependencies.$html_emitted_file_dependencies$.rebind(_prev_bind_0_$11, thread);
            }
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     */
    @LispMethod(comment = "@param ARGS\nlistp")
    public static final SubLObject extract_settings_alt(SubLObject args) {
        {
            SubLObject verbosity_string = html_extract_input($str_alt138$verbosity_level, args);
            SubLObject verbosity_level = (verbosity_string.isString()) ? ((SubLObject) (string_utilities.string_to_integer(string_utilities.trim_whitespace(verbosity_string)))) : NIL;
            if (((NIL != verbosity_level) && verbosity_level.numGE(ZERO_INTEGER)) && verbosity_level.numLE(NINE_INTEGER)) {
                $cb_shop_verbosity$.setDynamicValue(verbosity_level);
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     */
    @LispMethod(comment = "@param ARGS\nlistp")
    public static SubLObject extract_settings(final SubLObject args) {
        final SubLObject verbosity_string = html_extract_input(cb_shop.$str148$verbosity_level, args);
        final SubLObject verbosity_level = (verbosity_string.isString()) ? string_utilities.string_to_integer(string_utilities.trim_whitespace(verbosity_string)) : NIL;
        if (((NIL != verbosity_level) && verbosity_level.numGE(ZERO_INTEGER)) && verbosity_level.numLE(NINE_INTEGER)) {
            cb_shop.$cb_shop_verbosity$.setDynamicValue(verbosity_level);
        }
        return NIL;
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     */
    @LispMethod(comment = "@param ARGS\nlistp")
    public static final SubLObject extract_advanced_settings_alt(SubLObject args) {
        {
            SubLObject cutoff_number_string = html_extract_input($str_alt139$cutoff_number, args);
            SubLObject cutoff_numberP = (cutoff_number_string.isString()) ? ((SubLObject) (equal(string_utilities.trim_whitespace(cutoff_number_string), $$$number))) : NIL;
            SubLObject cutoff_time_string = html_extract_input($str_alt141$cutoff_time, args);
            SubLObject cutoff_timeP = (cutoff_time_string.isString()) ? ((SubLObject) (equal(string_utilities.trim_whitespace(cutoff_time_string), $$$time))) : NIL;
            SubLObject cutoff_number = (NIL != cutoff_numberP) ? ((SubLObject) (string_utilities.string_to_integer(string_utilities.trim_whitespace(html_extract_input($$$number, args))))) : NIL;
            SubLObject cutoff_time = (NIL != cutoff_timeP) ? ((SubLObject) (string_utilities.string_to_integer(string_utilities.trim_whitespace(html_extract_input($$$time, args))))) : NIL;
            SubLObject do_fi_asksP = equal(html_extract_input($str_alt143$do_fi_asks, args), $str_alt144$do_asks);
            SubLObject num_backchains_string = (NIL != do_fi_asksP) ? ((SubLObject) (html_extract_input($str_alt145$num_backchains, args))) : NIL;
            SubLObject num_backchains = (NIL != number_utilities.number_string_p(num_backchains_string)) ? ((SubLObject) (string_utilities.string_to_integer(string_utilities.trim_whitespace(num_backchains_string)))) : ZERO_INTEGER;
            SubLObject cycle_checking_string = html_extract_input($str_alt146$cycle_checking, args);
            SubLObject logging = html_extract_input($str_alt138$verbosity_level, args);
            SubLObject depth_limitP = equal(html_extract_input($str_alt147$depth_limit_p, args), $str_alt148$depth_limit);
            SubLObject depth_limit_string = (NIL != depth_limitP) ? ((SubLObject) (html_extract_input($str_alt148$depth_limit, args))) : NIL;
            SubLObject depth_limit = (depth_limit_string.isString()) ? ((SubLObject) (string_utilities.string_to_integer(string_utilities.trim_whitespace(depth_limit_string)))) : NIL;
            SubLObject search_type = html_extract_input($str_alt149$search_type, args);
            $cb_plan_search_type$.setDynamicValue(com.cyc.cycjava.cycl.cb_shop.find_keyword_for_search_type(search_type));
            $cb_last_planner_number$.setDynamicValue(cutoff_number);
            $cb_shop_time_cutoff$.setDynamicValue(cutoff_time);
            $cb_shop_num_backchains$.setDynamicValue(num_backchains);
            $cb_shop_depth_limit$.setDynamicValue(depth_limit);
            if (cycle_checking_string.isString()) {
                $cb_shop_cycle_checking_enabledP$.setDynamicValue(equal(cycle_checking_string, $str_alt146$cycle_checking));
            }
            if (logging.isString()) {
                if (NIL != Strings.stringE(logging, $$$logging, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                    $cb_shop_verbosity$.setDynamicValue(NINE_INTEGER);
                }
                if (NIL != Strings.stringE(logging, $str_alt151$no_logging, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                    $cb_shop_verbosity$.setDynamicValue(ZERO_INTEGER);
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     */
    @LispMethod(comment = "@param ARGS\nlistp")
    public static SubLObject extract_advanced_settings(final SubLObject args) {
        final SubLObject cutoff_number_string = html_extract_input(cb_shop.$str149$cutoff_number, args);
        final SubLObject cutoff_numberP = (cutoff_number_string.isString()) ? equal(string_utilities.trim_whitespace(cutoff_number_string), cb_shop.$$$number) : NIL;
        final SubLObject cutoff_time_string = html_extract_input(cb_shop.$str151$cutoff_time, args);
        final SubLObject cutoff_timeP = (cutoff_time_string.isString()) ? equal(string_utilities.trim_whitespace(cutoff_time_string), cb_shop.$$$time) : NIL;
        final SubLObject cutoff_number = (NIL != cutoff_numberP) ? string_utilities.string_to_integer(string_utilities.trim_whitespace(html_extract_input(cb_shop.$$$number, args))) : NIL;
        final SubLObject cutoff_time = (NIL != cutoff_timeP) ? string_utilities.string_to_integer(string_utilities.trim_whitespace(html_extract_input(cb_shop.$$$time, args))) : NIL;
        final SubLObject do_fi_asksP = equal(html_extract_input(cb_shop.$str153$do_fi_asks, args), cb_shop.$str154$do_asks);
        final SubLObject num_backchains_string = (NIL != do_fi_asksP) ? html_extract_input(cb_shop.$str155$num_backchains, args) : NIL;
        final SubLObject num_backchains = (NIL != number_utilities.number_string_p(num_backchains_string)) ? string_utilities.string_to_integer(string_utilities.trim_whitespace(num_backchains_string)) : ZERO_INTEGER;
        final SubLObject cycle_checking_string = html_extract_input(cb_shop.$str156$cycle_checking, args);
        final SubLObject logging = html_extract_input(cb_shop.$str148$verbosity_level, args);
        final SubLObject depth_limitP = equal(html_extract_input(cb_shop.$str157$depth_limit_p, args), cb_shop.$str158$depth_limit);
        final SubLObject depth_limit_string = (NIL != depth_limitP) ? html_extract_input(cb_shop.$str158$depth_limit, args) : NIL;
        final SubLObject depth_limit = (depth_limit_string.isString()) ? string_utilities.string_to_integer(string_utilities.trim_whitespace(depth_limit_string)) : NIL;
        final SubLObject search_type = html_extract_input(cb_shop.$str159$search_type, args);
        cb_shop.$cb_plan_search_type$.setDynamicValue(cb_shop.find_keyword_for_search_type(search_type));
        cb_shop.$cb_last_planner_number$.setDynamicValue(cutoff_number);
        cb_shop.$cb_shop_time_cutoff$.setDynamicValue(cutoff_time);
        cb_shop.$cb_shop_num_backchains$.setDynamicValue(num_backchains);
        cb_shop.$cb_shop_depth_limit$.setDynamicValue(depth_limit);
        if (cycle_checking_string.isString()) {
            cb_shop.$cb_shop_cycle_checking_enabledP$.setDynamicValue(equal(cycle_checking_string, cb_shop.$str156$cycle_checking));
        }
        if (logging.isString()) {
            if (NIL != Strings.stringE(logging, cb_shop.$$$logging, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                cb_shop.$cb_shop_verbosity$.setDynamicValue(NINE_INTEGER);
            }
            if (NIL != Strings.stringE(logging, cb_shop.$str161$no_logging, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                cb_shop.$cb_shop_verbosity$.setDynamicValue(ZERO_INTEGER);
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     * @return booleanp
     * @unknown *CB-SHOP-TASK-STRING* *CB-PLANNER-TASK*
     */
    @LispMethod(comment = "@param ARGS\nlistp\r\n\t\t\r\n@return booleanp\r\n@unknown *CB-SHOP-TASK-STRING* *CB-PLANNER-TASK*")
    public static final SubLObject cb_shop_extract_problem_alt(SubLObject args) {
        if (NIL != args) {
            {
                SubLObject domain_mt_string = string_utilities.trim_whitespace(html_extract_input($str_alt152$domain_mt, args));
                SubLObject domain_mt = constants_high.find_constant(domain_mt_string);
                SubLObject task_string = string_utilities.trim_whitespace(html_extract_input($$$task, args));
                SubLObject task = cb_form_widgets.cb_extract_query_formula_from_string(task_string, domain_mt);
                if (NIL == task) {
                    $cb_shop_task_string$.setDynamicValue(task_string);
                    $cb_planner_warning_message$.setDynamicValue($str_alt154$Task_syntax_not_recognized_);
                } else {
                    com.cyc.cycjava.cycl.cb_shop.clear_cb_planner_warning_message();
                }
                $cb_planner_task$.setDynamicValue(task);
                $cb_planner_domain_mt$.setDynamicValue(domain_mt);
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     * @return booleanp
     * @unknown *CB-SHOP-TASK-STRING* *CB-PLANNER-TASK*
     */
    @LispMethod(comment = "@param ARGS\nlistp\r\n\t\t\r\n@return booleanp\r\n@unknown *CB-SHOP-TASK-STRING* *CB-PLANNER-TASK*")
    public static SubLObject cb_shop_extract_problem(final SubLObject args) {
        if (NIL != args) {
            final SubLObject domain_mt_string = string_utilities.trim_whitespace(html_extract_input(cb_shop.$str162$domain_mt, args));
            final SubLObject domain_mt = constants_high.find_constant(domain_mt_string);
            final SubLObject task_string = string_utilities.trim_whitespace(html_extract_input(cb_shop.$$$task, args));
            final SubLObject task = cb_form_widgets.cb_extract_query_formula_from_string(task_string, domain_mt);
            if (NIL == task) {
                cb_shop.$cb_shop_task_string$.setDynamicValue(task_string);
                cb_shop.$cb_planner_warning_message$.setDynamicValue(cb_shop.$str164$Task_syntax_not_recognized_);
            } else {
                cb_shop.clear_cb_planner_warning_message();
            }
            cb_shop.$cb_planner_task$.setDynamicValue(task);
            cb_shop.$cb_planner_domain_mt$.setDynamicValue(domain_mt);
        }
        return NIL;
    }

    /**
     *
     *
     * @return shop-basic-planner-thread-p
     */
    @LispMethod(comment = "@return shop-basic-planner-thread-p")
    public static final SubLObject cb_shop_new_plan_thread_alt(SubLObject v_planner, SubLObject verbose) {
        {
            SubLObject thread = methods.funcall_instance_method_with_0_args(v_planner, MAKE_NEW_THREAD);
            if (verbose.numG(ZERO_INTEGER)) {
                instances.set_slot(thread, OUTPUT_STREAM, NIL);
            }
            return thread;
        }
    }

    /**
     *
     *
     * @return shop-basic-planner-thread-p
     */
    @LispMethod(comment = "@return shop-basic-planner-thread-p")
    public static SubLObject cb_shop_new_plan_thread(final SubLObject planner, final SubLObject verbose) {
        final SubLObject thread = methods.funcall_instance_method_with_0_args(planner, cb_shop.MAKE_NEW_THREAD);
        if (verbose.numG(ZERO_INTEGER)) {
            instances.set_slot(thread, OUTPUT_STREAM, NIL);
        }
        return thread;
    }

    /**
     *
     *
     * @unknown 
     */
    @LispMethod(comment = "@unknown")
    public static final SubLObject initialize_planner_thread_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            $cb_shop_planner_thread$.setDynamicValue(com.cyc.cycjava.cycl.cb_shop.cb_shop_new_plan_thread($cb_shop_planner$.getDynamicValue(thread), $cb_shop_verbosity$.getDynamicValue(thread)), thread);
            return NIL;
        }
    }

    /**
     *
     *
     * @unknown 
     */
    @LispMethod(comment = "@unknown")
    public static SubLObject initialize_planner_thread() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        cb_shop.$cb_shop_planner_thread$.setDynamicValue(cb_shop.cb_shop_new_plan_thread(cb_shop.$cb_shop_planner$.getDynamicValue(thread), cb_shop.$cb_shop_verbosity$.getDynamicValue(thread)), thread);
        return NIL;
    }

    public static final SubLObject invalid_mt_warning_string_alt(SubLObject mt, SubLObject mt_type) {
        return cconcatenate(format(NIL, $str_alt157$___s_is_not_a_valid__s_microtheor, mt, mt_type == $$PlanningDomainMicrotheory ? ((SubLObject) ($$$Domain)) : $$$Task), format(NIL, $str_alt161$__Please_enter_an_instance_of__s_, mt_type));
    }

    public static SubLObject invalid_mt_warning_string(final SubLObject mt, final SubLObject mt_type) {
        return cconcatenate(format(NIL, cb_shop.$str167$___s_is_not_a_valid__s_microtheor, mt, NIL != kb_utilities.kbeq(mt_type, cb_shop.$$PlanningDomainMicrotheory) ? cb_shop.$$$Domain : cb_shop.$$$Task), format(NIL, cb_shop.$str171$__Please_enter_an_instance_of__s_, mt_type));
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     */
    @LispMethod(comment = "@param ARGS\nlistp")
    public static final SubLObject cb_shop_new_task_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject domain_mt = $cb_planner_domain_mt$.getDynamicValue(thread);
                SubLObject task = $cb_planner_task$.getDynamicValue(thread);
                com.cyc.cycjava.cycl.cb_shop.clear_plan_displayers();
                com.cyc.cycjava.cycl.cb_shop.cb_shop_new_plan_process(task, domain_mt);
                return NIL;
            }
        }
    }

    /**
     *
     *
     * @param ARGS
    listp
     * 		
     */
    @LispMethod(comment = "@param ARGS\nlistp")
    public static SubLObject cb_shop_new_task(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject domain_mt = cb_shop.$cb_planner_domain_mt$.getDynamicValue(thread);
        final SubLObject task = cb_shop.$cb_planner_task$.getDynamicValue(thread);
        cb_shop.clear_plan_displayers();
        cb_shop.cb_shop_new_plan_process(task, domain_mt);
        return NIL;
    }

    public static final SubLObject plan_finder_continue_task_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject _prev_bind_0 = shop_parameters.$shop_method_cycle_checking_enabledP$.currentBinding(thread);
                SubLObject _prev_bind_1 = shop_parameters.$shop_default_depth_limit$.currentBinding(thread);
                try {
                    shop_parameters.$shop_method_cycle_checking_enabledP$.bind($cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), thread);
                    shop_parameters.$shop_default_depth_limit$.bind($cb_shop_depth_limit$.getDynamicValue(thread), thread);
                    methods.funcall_instance_method_with_6_args($cb_shop_planner_thread$.getDynamicValue(thread), FIND_PLANS, $cb_planner_task$.getDynamicValue(thread), $cb_plan_search_type$.getDynamicValue(thread), $cb_shop_verbosity$.getDynamicValue(thread), $cb_last_planner_number$.getDynamicValue(thread), $cb_shop_time_cutoff$.getDynamicValue(thread), shop_parameters.$shop_default_depth_limit$.getDynamicValue(thread));
                } finally {
                    shop_parameters.$shop_default_depth_limit$.rebind(_prev_bind_1, thread);
                    shop_parameters.$shop_method_cycle_checking_enabledP$.rebind(_prev_bind_0, thread);
                }
            }
            return NIL;
        }
    }

    public static SubLObject plan_finder_continue_task() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject _prev_bind_0 = shop_parameters.$shop_method_cycle_checking_enabledP$.currentBinding(thread);
        final SubLObject _prev_bind_2 = shop_parameters.$shop_default_depth_limit$.currentBinding(thread);
        try {
            shop_parameters.$shop_method_cycle_checking_enabledP$.bind(cb_shop.$cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), thread);
            shop_parameters.$shop_default_depth_limit$.bind(cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread), thread);
            methods.funcall_instance_method_with_6_args(cb_shop.$cb_shop_planner_thread$.getDynamicValue(thread), cb_shop.FIND_PLANS, cb_shop.$cb_planner_task$.getDynamicValue(thread), cb_shop.$cb_plan_search_type$.getDynamicValue(thread), cb_shop.$cb_shop_verbosity$.getDynamicValue(thread), cb_shop.$cb_last_planner_number$.getDynamicValue(thread), cb_shop.$cb_shop_time_cutoff$.getDynamicValue(thread), shop_parameters.$shop_default_depth_limit$.getDynamicValue(thread));
        } finally {
            shop_parameters.$shop_default_depth_limit$.rebind(_prev_bind_2, thread);
            shop_parameters.$shop_method_cycle_checking_enabledP$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static final SubLObject plan_finder_change_advanced_parameters_alt(SubLObject args) {
        {
            SubLObject show_advanced_settings = html_extract_input($str_alt135$show_advanced_parameters, args);
            if (NIL != string_utilities.starts_with(show_advanced_settings, $$$Show)) {
                $cb_last_planner_show_advanced_settings$.setDynamicValue(T);
            } else {
                if (NIL != string_utilities.starts_with(show_advanced_settings, $$$Hide)) {
                    $cb_last_planner_show_advanced_settings$.setDynamicValue(NIL);
                }
            }
            return NIL;
        }
    }

    public static SubLObject plan_finder_change_advanced_parameters(final SubLObject args) {
        final SubLObject show_advanced_settings = html_extract_input(cb_shop.$str145$show_advanced_parameters, args);
        if (NIL != string_utilities.starts_with(show_advanced_settings, cb_shop.$$$Show)) {
            cb_shop.$cb_last_planner_show_advanced_settings$.setDynamicValue(T);
        } else
            if (NIL != string_utilities.starts_with(show_advanced_settings, cb_shop.$$$Hide)) {
                cb_shop.$cb_last_planner_show_advanced_settings$.setDynamicValue(NIL);
            }

        return NIL;
    }

    /**
     * Displays the results of the last task in format OUT-FORMAT (English or CycL).
     */
    @LispMethod(comment = "Displays the results of the last task in format OUT-FORMAT (English or CycL).")
    public static final SubLObject show_last_task_alt(SubLObject last_task, SubLObject plan_displayers, SubLObject halt_reason) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_newline(UNPROVIDED);
            html_markup(html_macros.$html_strong_head$.getGlobalValue());
            html_princ($str_alt165$Last_task_);
            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
            html_newline(UNPROVIDED);
            {
                SubLObject _prev_bind_0 = pph_vars.$pph_quantify_varsP$.currentBinding(thread);
                try {
                    pph_vars.$pph_quantify_varsP$.bind(NIL, thread);
                    pph_html.html_text_with_target(last_task, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                } finally {
                    pph_vars.$pph_quantify_varsP$.rebind(_prev_bind_0, thread);
                }
            }
            html_newline(UNPROVIDED);
            html_newline(TWO_INTEGER);
            com.cyc.cycjava.cycl.cb_shop.display_html_plans(plan_displayers, halt_reason);
            html_newline(TWO_INTEGER);
            return NIL;
        }
    }

    /**
     * Displays the results of the last task in format OUT-FORMAT (English or CycL).
     */
    @LispMethod(comment = "Displays the results of the last task in format OUT-FORMAT (English or CycL).")
    public static SubLObject show_last_task(final SubLObject last_task, final SubLObject plan_displayers, final SubLObject halt_reason) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_newline(UNPROVIDED);
        html_markup(html_macros.$html_strong_head$.getGlobalValue());
        html_princ(cb_shop.$str175$Last_task_);
        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        html_newline(UNPROVIDED);
        final SubLObject _prev_bind_0 = pph_vars.$pph_quantify_varsP$.currentBinding(thread);
        try {
            pph_vars.$pph_quantify_varsP$.bind(NIL, thread);
            pph_html.html_text_with_target(last_task, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        } finally {
            pph_vars.$pph_quantify_varsP$.rebind(_prev_bind_0, thread);
        }
        html_newline(UNPROVIDED);
        html_newline(TWO_INTEGER);
        cb_shop.display_html_plans(plan_displayers, halt_reason);
        html_newline(TWO_INTEGER);
        return NIL;
    }

    /**
     *
     *
     * @param REASON
    keywordp.
     * 		
     */
    @LispMethod(comment = "@param REASON\nkeywordp.")
    public static final SubLObject cb_planner_display_halt_reason_alt(SubLObject reason) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_strong_head$.getGlobalValue());
            html_princ($str_alt166$Planner_halted_because__);
            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
            {
                SubLObject pcase_var = reason;
                if (pcase_var.eql($SEARCH_EXHAUSTED)) {
                    html_princ($str_alt168$Search_space_was_exhausted_);
                } else {
                    if (pcase_var.eql($DEPTH_CUTOFF)) {
                        {
                            SubLObject color_val = $$$red;
                            html_markup(html_macros.$html_font_head$.getGlobalValue());
                            if (NIL != color_val) {
                                html_markup(html_macros.$html_font_color$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_color(color_val));
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_princ($str_alt170$Depth_limit_was_reached_);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                                }
                            }
                            html_markup(html_macros.$html_font_tail$.getGlobalValue());
                        }
                    } else {
                        if (pcase_var.eql($NUMBER_CUTOFF)) {
                            {
                                SubLObject color_val = $$$red;
                                html_markup(html_macros.$html_font_head$.getGlobalValue());
                                if (NIL != color_val) {
                                    html_markup(html_macros.$html_font_color$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_color(color_val));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_princ($str_alt172$Number_cutoff_was_reached_);
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                                    }
                                }
                                html_markup(html_macros.$html_font_tail$.getGlobalValue());
                            }
                        } else {
                            if (pcase_var.eql($TIME_CUTOFF)) {
                                {
                                    SubLObject color_val = $$$red;
                                    html_markup(html_macros.$html_font_head$.getGlobalValue());
                                    if (NIL != color_val) {
                                        html_markup(html_macros.$html_font_color$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(html_color(color_val));
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            html_princ($str_alt174$Time_cutoff_was_reached_);
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                }
                            }
                        }
                    }
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param REASON
    keywordp.
     * 		
     */
    @LispMethod(comment = "@param REASON\nkeywordp.")
    public static SubLObject cb_planner_display_halt_reason(final SubLObject reason) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_strong_head$.getGlobalValue());
        html_princ(cb_shop.$str176$Planner_halted_because__);
        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        if (reason.eql($SEARCH_EXHAUSTED)) {
            html_princ(cb_shop.$str178$Search_space_was_exhausted_);
        } else
            if (reason.eql($DEPTH_CUTOFF)) {
                final SubLObject color_val = cb_shop.$$$red;
                html_markup(html_macros.$html_font_head$.getGlobalValue());
                if (NIL != color_val) {
                    html_markup(html_macros.$html_font_color$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_color(color_val));
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ(cb_shop.$str180$Depth_limit_was_reached_);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
                html_markup(html_macros.$html_font_tail$.getGlobalValue());
            } else
                if (reason.eql($NUMBER_CUTOFF)) {
                    final SubLObject color_val = cb_shop.$$$red;
                    html_markup(html_macros.$html_font_head$.getGlobalValue());
                    if (NIL != color_val) {
                        html_markup(html_macros.$html_font_color$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_color(color_val));
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ(cb_shop.$str182$Number_cutoff_was_reached_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                    html_markup(html_macros.$html_font_tail$.getGlobalValue());
                } else
                    if (reason.eql($TIME_CUTOFF)) {
                        final SubLObject color_val = cb_shop.$$$red;
                        html_markup(html_macros.$html_font_head$.getGlobalValue());
                        if (NIL != color_val) {
                            html_markup(html_macros.$html_font_color$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_color(color_val));
                            html_char(CHAR_quotation, UNPROVIDED);
                        }
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ(cb_shop.$str184$Time_cutoff_was_reached_);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                        }
                        html_markup(html_macros.$html_font_tail$.getGlobalValue());
                    }



        return NIL;
    }

    /**
     * Prints in HTML a summary of the last planner execution. Details include number
     * of plans found, timing data, and total counts of inferences and methods used.
     */
    @LispMethod(comment = "Prints in HTML a summary of the last planner execution. Details include number\r\nof plans found, timing data, and total counts of inferences and methods used.\nPrints in HTML a summary of the last planner execution. Details include number\nof plans found, timing data, and total counts of inferences and methods used.")
    public static final SubLObject summarize_last_task_alt(SubLObject planner_thread) {
        if (planner_thread == UNPROVIDED) {
            planner_thread = $cb_shop_planner_thread$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == planner_thread) {
                return NIL;
            }
            {
                SubLObject last_task = shop_basic_planner_thread.shop_planner_thread_task(planner_thread);
                html_newline(UNPROVIDED);
                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                html_princ($str_alt165$Last_task_);
                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                html_newline(UNPROVIDED);
                if ($cb_last_planner_format$.getDynamicValue(thread).equal($CYCL)) {
                    cb_form(last_task, UNPROVIDED, UNPROVIDED);
                } else {
                    {
                        SubLObject _prev_bind_0 = pph_vars.$pph_quantify_varsP$.currentBinding(thread);
                        try {
                            pph_vars.$pph_quantify_varsP$.bind(NIL, thread);
                            pph_html.html_text_with_target(last_task, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        } finally {
                            pph_vars.$pph_quantify_varsP$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
                html_newline(TWO_INTEGER);
                shop_basic_planner_thread.shop_basic_planner_thread_html_stats_method(planner_thread);
                html_newline(TWO_INTEGER);
                com.cyc.cycjava.cycl.cb_shop.cb_planner_display_halt_reason(shop_basic_planner_thread.shop_basic_planner_thread_halt_status_method(planner_thread));
                html_newline(TWO_INTEGER);
                if (NIL != $cb_plan_displayers$.getDynamicValue(thread)) {
                    html_markup(html_macros.$html_strong_head$.getGlobalValue());
                    cb_link($PLAN_EXAMINER, $str_alt175$_Examine_Plans_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                }
                if ($cb_shop_verbosity$.getDynamicValue(thread).numG(ZERO_INTEGER)) {
                    cb_link($PLAN_VERBOSE_EXAMINER, $str_alt176$_Examine_Verbose_Output_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    html_newline(TWO_INTEGER);
                }
                html_hr(UNPROVIDED, UNPROVIDED);
            }
            return NIL;
        }
    }

    /**
     * Prints in HTML a summary of the last planner execution. Details include number
     * of plans found, timing data, and total counts of inferences and methods used.
     */
    @LispMethod(comment = "Prints in HTML a summary of the last planner execution. Details include number\r\nof plans found, timing data, and total counts of inferences and methods used.\nPrints in HTML a summary of the last planner execution. Details include number\nof plans found, timing data, and total counts of inferences and methods used.")
    public static SubLObject summarize_last_task(SubLObject planner_thread) {
        if (planner_thread == UNPROVIDED) {
            planner_thread = cb_shop.$cb_shop_planner_thread$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == planner_thread) {
            return NIL;
        }
        final SubLObject last_task = shop_basic_planner_thread.shop_planner_thread_task(planner_thread);
        html_newline(UNPROVIDED);
        html_markup(html_macros.$html_strong_head$.getGlobalValue());
        html_princ(cb_shop.$str175$Last_task_);
        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        html_newline(UNPROVIDED);
        if (cb_shop.$cb_last_planner_format$.getDynamicValue(thread).equal($CYCL)) {
            cb_form(last_task, UNPROVIDED, UNPROVIDED);
        } else {
            final SubLObject _prev_bind_0 = pph_vars.$pph_quantify_varsP$.currentBinding(thread);
            try {
                pph_vars.$pph_quantify_varsP$.bind(NIL, thread);
                pph_html.html_text_with_target(last_task, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            } finally {
                pph_vars.$pph_quantify_varsP$.rebind(_prev_bind_0, thread);
            }
        }
        html_newline(TWO_INTEGER);
        shop_basic_planner_thread.shop_basic_planner_thread_html_stats_method(planner_thread);
        html_newline(TWO_INTEGER);
        cb_shop.cb_planner_display_halt_reason(shop_basic_planner_thread.shop_basic_planner_thread_halt_status_method(planner_thread));
        html_newline(TWO_INTEGER);
        if (NIL != cb_shop.$cb_plan_displayers$.getDynamicValue(thread)) {
            html_markup(html_macros.$html_strong_head$.getGlobalValue());
            cb_link($PLAN_EXAMINER, cb_shop.$str185$_Examine_Plans_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        }
        if (cb_shop.$cb_shop_verbosity$.getDynamicValue(thread).numG(ZERO_INTEGER)) {
            cb_link(cb_shop.$PLAN_VERBOSE_EXAMINER, cb_shop.$str186$_Examine_Verbose_Output_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            html_newline(TWO_INTEGER);
        }
        html_hr(UNPROVIDED, UNPROVIDED);
        return NIL;
    }

    /**
     *
     *
     * @param ARGS
     * 		;; html arg list
     * 		Gets input for a new task. Assumes the latest format OUT-FORMAT (English or CycL) is default.
     */
    @LispMethod(comment = "@param ARGS\r\n\t\t;; html arg list\r\n\t\tGets input for a new task. Assumes the latest format OUT-FORMAT (English or CycL) is default.")
    public static final SubLObject cb_shop_ask_for_new_task_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject domain_mt = $cb_planner_domain_mt$.getDynamicValue(thread);
                SubLObject task_string = $cb_shop_task_string$.getDynamicValue(thread);
                cb_help_preamble($PLAN_FINDER, NIL, UNPROVIDED);
                if (NIL != com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_checking_enabled()) {
                    html_markup(html_macros.$html_strong_head$.getGlobalValue());
                    html_indent(UNPROVIDED);
                    cb_link($PLAN_CHECKING, $$$Plan_Checking_Enabled, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                }
                html_newline(UNPROVIDED);
                html_complete.html_complete_button($str_alt152$domain_mt, $$$Complete, $$PlanningDomainMicrotheory, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                html_indent(TWO_INTEGER);
                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                html_princ($str_alt181$Mt_);
                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                html_indent(TWO_INTEGER);
                html_text_input($str_alt152$domain_mt, domain_mt, $cb_constant_input_width$.getDynamicValue(thread));
                html_indent(UNPROVIDED);
                html_script_utilities.html_clear_input_button($str_alt152$domain_mt, $$$Clear_Mt);
                html_newline(UNPROVIDED);
                html_complete.html_complete_button($$$task, $$$Complete, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                html_indent(UNPROVIDED);
                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                html_princ($str_alt183$Task_);
                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                html_indent(TWO_INTEGER);
                html_script_utilities.html_clear_input_button($$$task, $$$Clear_Task);
                html_indent(UNPROVIDED);
                html_complete.html_cyclify_button($$$task, UNPROVIDED);
                html_indent($int$24);
                html_submit_input($$$Find_Plans, $str_alt134$find_plans, UNPROVIDED);
                html_newline(UNPROVIDED);
                html_markup(html_macros.$html_textarea_head$.getGlobalValue());
                html_markup(html_macros.$html_textarea_name$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup($$$task);
                html_char(CHAR_quotation, UNPROVIDED);
                if (true) {
                    html_markup(html_macros.$html_textarea_cols$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup($int$80);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_textarea_rows$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(EIGHT_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_markup(html_macros.$html_table_wrap$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup($$$virtual);
                html_char(CHAR_quotation, UNPROVIDED);
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        if (NIL != $cb_planner_task$.getDynamicValue(thread)) {
                            html_princ(format_cycl_expression.format_cycl_expression_to_string(fi.perform_fi_substitutions($cb_planner_task$.getDynamicValue(thread), UNPROVIDED), UNPROVIDED));
                        } else {
                            if (task_string.isString()) {
                                html_princ(task_string);
                            }
                        }
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_textarea_tail$.getGlobalValue());
                html_newline(TWO_INTEGER);
                html_markup(html_macros.$html_table_head$.getGlobalValue());
                if (true) {
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_width$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup($str_alt189$95_);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        if (NIL != html_macros.$html_color_lighter_grey$.getDynamicValue(thread)) {
                            html_markup(html_macros.$html_table_row_bgcolor$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_color_lighter_grey$.getDynamicValue(thread));
                            html_char(CHAR_quotation, UNPROVIDED);
                        }
                        html_char(CHAR_greater, UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_13 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                if (true) {
                                    html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($LEFT));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (true) {
                                    html_markup(html_macros.$html_table_data_width$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup($$$50);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_14 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        if (NIL != $cb_last_planner_show_advanced_settings$.getDynamicValue(thread)) {
                                            html_submit_input($$$Hide, $str_alt135$show_advanced_parameters, UNPROVIDED);
                                        } else {
                                            html_submit_input($$$Show, $str_alt135$show_advanced_parameters, UNPROVIDED);
                                        }
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_14, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_15 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_indent(THREE_INTEGER);
                                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                        html_princ($$$Advanced_Planning_Parameters);
                                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_15, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_13, thread);
                            }
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        if (NIL != $cb_last_planner_show_advanced_settings$.getDynamicValue(thread)) {
                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_16 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                    if (true) {
                                        html_markup(html_macros.$html_table_data_colspan$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(THREE_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_17 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            com.cyc.cycjava.cycl.cb_shop.cb_advanced_planner_settings_table(args);
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_17, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_16, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                            html_source_readability_terpri(UNPROVIDED);
                        }
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_table_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param ARGS
     * 		;; html arg list
     * 		Gets input for a new task. Assumes the latest format OUT-FORMAT (English or CycL) is default.
     */
    @LispMethod(comment = "@param ARGS\r\n\t\t;; html arg list\r\n\t\tGets input for a new task. Assumes the latest format OUT-FORMAT (English or CycL) is default.")
    public static SubLObject cb_shop_ask_for_new_task(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject domain_mt = cb_shop.$cb_planner_domain_mt$.getDynamicValue(thread);
        final SubLObject task_string = cb_shop.$cb_shop_task_string$.getDynamicValue(thread);
        cb_help_preamble($PLAN_FINDER, NIL, UNPROVIDED);
        if (NIL != cb_shop.cb_shop_plan_checking_enabled()) {
            html_markup(html_macros.$html_strong_head$.getGlobalValue());
            html_indent(UNPROVIDED);
            cb_link($PLAN_CHECKING, cb_shop.$$$Plan_Checking_Enabled, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        }
        html_newline(UNPROVIDED);
        html_complete.html_complete_button(cb_shop.$str162$domain_mt, cb_shop.$$$Complete, cb_shop.$$PlanningDomainMicrotheory, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        html_indent(TWO_INTEGER);
        html_markup(html_macros.$html_strong_head$.getGlobalValue());
        html_princ(cb_shop.$str191$Mt_);
        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        html_indent(TWO_INTEGER);
        html_text_input(cb_shop.$str162$domain_mt, domain_mt, $cb_constant_input_width$.getDynamicValue(thread));
        html_indent(UNPROVIDED);
        html_script_utilities.html_clear_input_button(cb_shop.$str162$domain_mt, cb_shop.$$$Clear_Mt, UNPROVIDED);
        html_newline(UNPROVIDED);
        html_complete.html_complete_button(cb_shop.$$$task, cb_shop.$$$Complete, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        html_indent(UNPROVIDED);
        html_markup(html_macros.$html_strong_head$.getGlobalValue());
        html_princ(cb_shop.$str193$Task_);
        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        html_indent(TWO_INTEGER);
        html_script_utilities.html_clear_input_button(cb_shop.$$$task, cb_shop.$$$Clear_Task, UNPROVIDED);
        html_indent(UNPROVIDED);
        html_complete.html_cyclify_button(cb_shop.$$$task, UNPROVIDED, UNPROVIDED);
        html_indent(cb_shop.$int$24);
        html_submit_input(cb_shop.$$$Find_Plans, cb_shop.$str144$find_plans, UNPROVIDED);
        html_newline(UNPROVIDED);
        html_markup(html_macros.$html_textarea_head$.getGlobalValue());
        html_markup(html_macros.$html_textarea_name$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(cb_shop.$$$task);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_textarea_cols$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(cb_shop.$int$80);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_textarea_rows$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(EIGHT_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_wrap$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(cb_shop.$$$virtual);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            if (NIL != cb_shop.$cb_planner_task$.getDynamicValue(thread)) {
                html_princ(format_cycl_expression.format_cycl_expression_to_string(fi.perform_fi_substitutions(cb_shop.$cb_planner_task$.getDynamicValue(thread), UNPROVIDED), UNPROVIDED));
            } else
                if (task_string.isString()) {
                    html_princ(task_string);
                }

        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_textarea_tail$.getGlobalValue());
        html_newline(TWO_INTEGER);
        html_markup(html_macros.$html_table_head$.getGlobalValue());
        html_markup(html_macros.$html_table_border$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_width$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(cb_shop.$str199$95_);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            if (NIL != html_macros.$html_color_lighter_grey$.getGlobalValue()) {
                html_markup(html_macros.$html_table_row_bgcolor$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_macros.$html_color_lighter_grey$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$17 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($LEFT));
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_macros.$html_table_data_width$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(cb_shop.$$$50);
                html_char(CHAR_quotation, UNPROVIDED);
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$18 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    if (NIL != cb_shop.$cb_last_planner_show_advanced_settings$.getDynamicValue(thread)) {
                        html_submit_input(cb_shop.$$$Hide, cb_shop.$str145$show_advanced_parameters, UNPROVIDED);
                    } else {
                        html_submit_input(cb_shop.$$$Show, cb_shop.$str145$show_advanced_parameters, UNPROVIDED);
                    }
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$18, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$19 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_indent(THREE_INTEGER);
                    html_markup(html_macros.$html_strong_head$.getGlobalValue());
                    html_princ(cb_shop.$$$Advanced_Planning_Parameters);
                    html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$19, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$17, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            if (NIL != cb_shop.$cb_last_planner_show_advanced_settings$.getDynamicValue(thread)) {
                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$20 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_data_colspan$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(THREE_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$21 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        cb_shop.cb_advanced_planner_settings_table(args);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$21, thread);
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$20, thread);
                }
                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
            }
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_domain_examiner_alt(SubLObject domain) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject title_var = $$$SHOP_Domain_Examiner;
                {
                    SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
                    try {
                        html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? ((SubLObject) (html_macros.$html_id_space_id_generator$.getDynamicValue(thread))) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
                        html_markup(html_macros.$html_html_head$.getGlobalValue());
                        html_markup(html_macros.$html_head_head$.getGlobalValue());
                        html_macros.html_head_content_type();
                        cb_head_shortcut_icon();
                        html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                        if (NIL != title_var) {
                            html_source_readability_terpri(UNPROVIDED);
                            html_markup(html_macros.$html_title_head$.getGlobalValue());
                            html_princ(title_var);
                            html_markup(html_macros.$html_title_tail$.getGlobalValue());
                        }
                        html_markup(html_macros.$html_head_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_18 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                            try {
                                html_macros.$html_inside_bodyP$.bind(T, thread);
                                html_markup(html_macros.$html_body_head$.getGlobalValue());
                                if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                                    html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (true) {
                                    html_markup(html_macros.$html_body_class$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup($str_alt45$yui_skin_sam);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_19 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        if (NIL != title_var) {
                                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                            html_markup(TWO_INTEGER);
                                            html_char(CHAR_greater, UNPROVIDED);
                                            html_princ(title_var);
                                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                            html_markup(TWO_INTEGER);
                                            html_char(CHAR_greater, UNPROVIDED);
                                        }
                                        cb_link($PLAN_VERBOSE_EXAMINER, $str_alt194$_Verbose_Output_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                        html_hr(UNPROVIDED, UNPROVIDED);
                                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                        html_princ($str_alt195$Go_to_);
                                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                        html_indent(TWO_INTEGER);
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup($str_alt196$_methods);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_20 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_princ($$$Methods);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_20, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_indent(TWO_INTEGER);
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup($str_alt198$_operators);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_21 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_princ($$$Operators);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_21, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_indent(TWO_INTEGER);
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup($str_alt200$_cond_operators);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_22 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_princ($$$Conditional_Ops);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_22, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_indent(TWO_INTEGER);
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup($str_alt202$_sconds);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_23 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_princ($$$Sufficient_Conditions);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_23, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_hr(UNPROVIDED, UNPROVIDED);
                                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                        html_princ($str_alt204$Planning_Mt_);
                                        html_indent(UNPROVIDED);
                                        cb_form(methods.funcall_instance_method_with_0_args(domain, GET_NAME), UNPROVIDED, UNPROVIDED);
                                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                        html_newline(TWO_INTEGER);
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup($$$methods);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_24 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                html_princ($$$Methods);
                                                html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                methods.funcall_instance_method_with_0_args(domain, HTML_DISPLAY_ALL_METHODS);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_24, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup($$$operators);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_25 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                html_princ($$$Operators);
                                                html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                methods.funcall_instance_method_with_0_args(domain, HTML_DISPLAY_ALL_OPERATORS);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_25, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup($str_alt210$cond_operators);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_26 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                html_princ($$$Conditional_Operators);
                                                html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                methods.funcall_instance_method_with_0_args(domain, HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_26, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup($$$sconds);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_27 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                html_princ($$$Sufficient_Conditions);
                                                html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                                html_markup(THREE_INTEGER);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                methods.funcall_instance_method_with_0_args(domain, HTML_DISPLAY_ALL_SCONDS);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_27, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                        html_source_readability_terpri(UNPROVIDED);
                                        html_copyright_notice();
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_19, thread);
                                    }
                                }
                                html_markup(html_macros.$html_body_tail$.getGlobalValue());
                                html_source_readability_terpri(UNPROVIDED);
                            } finally {
                                html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_18, thread);
                            }
                        }
                        html_markup(html_macros.$html_html_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_domain_examiner(final SubLObject domain) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject title_var = cb_shop.$$$SHOP_Domain_Examiner;
        final SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
        try {
            html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? html_macros.$html_id_space_id_generator$.getDynamicValue(thread) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            html_markup(cb_shop.$str44$__DOCTYPE_html_PUBLIC_____W3C__DT);
            if (NIL != html_macros.$html_force_ie_standards_mode$.getDynamicValue(thread)) {
                html_source_readability_terpri(UNPROVIDED);
                html_markup(cb_shop.$str45$_meta_http_equiv__X_UA_Compatible);
            }
            html_source_readability_terpri(UNPROVIDED);
            final SubLObject _prev_bind_0_$22 = cyc_file_dependencies.$html_emitted_file_dependencies$.currentBinding(thread);
            try {
                cyc_file_dependencies.$html_emitted_file_dependencies$.bind(cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread) == $UNINITIALIZED ? list(EMPTY_SUBL_OBJECT_ARRAY) : cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread), thread);
                html_markup(html_macros.$html_html_head$.getGlobalValue());
                html_markup(html_macros.$html_head_head$.getGlobalValue());
                html_macros.html_head_content_type();
                cb_head_shortcut_icon();
                html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                cyc_file_dependencies.css($CB_CYC);
                dhtml_macros.html_basic_cb_scripts();
                if (NIL != title_var) {
                    html_source_readability_terpri(UNPROVIDED);
                    html_markup(html_macros.$html_title_head$.getGlobalValue());
                    html_princ(title_var);
                    html_markup(html_macros.$html_title_tail$.getGlobalValue());
                }
                html_markup(html_macros.$html_head_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
                final SubLObject _prev_bind_0_$23 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                try {
                    html_macros.$html_inside_bodyP$.bind(T, thread);
                    html_markup(html_macros.$html_body_head$.getGlobalValue());
                    if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                        html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_markup(html_macros.$html_body_class$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(cb_shop.$str50$yui_skin_sam);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$24 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_div_head$.getGlobalValue());
                        html_markup(html_macros.$html_attribute_id$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$reloadFrameButton);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$25 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_input_head$.getGlobalValue());
                            html_markup(html_macros.$html_input_type$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$button);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_name$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$reload);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_value$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_princ(cb_shop.$$$Refresh_Frames);
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_input_disabledP$.getDynamicValue(thread)) {
                                html_simple_attribute(html_macros.$html_input_disabled$.getGlobalValue());
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$25, thread);
                        }
                        html_markup(html_macros.$html_div_tail$.getGlobalValue());
                        if (NIL != title_var) {
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(title_var);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                        }
                        cb_link(cb_shop.$PLAN_VERBOSE_EXAMINER, cb_shop.$str204$_Verbose_Output_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        html_hr(UNPROVIDED, UNPROVIDED);
                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                        html_princ(cb_shop.$str205$Go_to_);
                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                        html_indent(TWO_INTEGER);
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$str206$_methods);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$26 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ(cb_shop.$$$Methods);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$26, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_indent(TWO_INTEGER);
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$str208$_operators);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$27 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ(cb_shop.$$$Operators);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$27, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_indent(TWO_INTEGER);
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$str210$_cond_operators);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$28 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ(cb_shop.$$$Conditional_Ops);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$28, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_indent(TWO_INTEGER);
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$str212$_sconds);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$29 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ(cb_shop.$$$Sufficient_Conditions);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$29, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_hr(UNPROVIDED, UNPROVIDED);
                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                        html_princ(cb_shop.$str214$Planning_Mt_);
                        html_indent(UNPROVIDED);
                        cb_form(methods.funcall_instance_method_with_0_args(domain, GET_NAME), UNPROVIDED, UNPROVIDED);
                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                        html_newline(TWO_INTEGER);
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$methods);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$30 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(cb_shop.$$$Methods);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            methods.funcall_instance_method_with_0_args(domain, cb_shop.HTML_DISPLAY_ALL_METHODS);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$30, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$operators);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$31 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(cb_shop.$$$Operators);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            methods.funcall_instance_method_with_0_args(domain, cb_shop.HTML_DISPLAY_ALL_OPERATORS);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$31, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$str220$cond_operators);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$32 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(cb_shop.$$$Conditional_Operators);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            methods.funcall_instance_method_with_0_args(domain, cb_shop.HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$32, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                        html_markup(html_macros.$html_anchor_name$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$sconds);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$33 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(cb_shop.$$$Sufficient_Conditions);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(THREE_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            methods.funcall_instance_method_with_0_args(domain, cb_shop.HTML_DISPLAY_ALL_SCONDS);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$33, thread);
                        }
                        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_copyright_notice();
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$24, thread);
                    }
                    html_markup(html_macros.$html_body_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                } finally {
                    html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_$23, thread);
                }
                html_markup(html_macros.$html_html_tail$.getGlobalValue());
            } finally {
                cyc_file_dependencies.$html_emitted_file_dependencies$.rebind(_prev_bind_0_$22, thread);
            }
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static final SubLObject plan_verbose_examiner_internal_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_html_head$.getGlobalValue());
            html_markup(html_macros.$html_head_head$.getGlobalValue());
            html_macros.html_head_content_type();
            html_source_readability_terpri(UNPROVIDED);
            html_markup(html_macros.$html_title_head$.getGlobalValue());
            html_princ($$$SHOP_Log);
            html_markup(html_macros.$html_title_tail$.getGlobalValue());
            dhtml_with_dom_script();
            dhtml_with_toggle_visibility_support();
            html_markup(html_macros.$html_head_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            {
                SubLObject color_value = html_macros.$html_default_bgcolor$.getDynamicValue(thread);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                    try {
                        html_macros.$html_inside_bodyP$.bind(T, thread);
                        html_markup(html_macros.$html_body_head$.getGlobalValue());
                        if (NIL != color_value) {
                            html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_color(color_value));
                            html_char(CHAR_quotation, UNPROVIDED);
                        }
                        html_char(CHAR_greater, UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_28 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                {
                                    SubLObject thread_29 = $cb_shop_planner_thread$.getDynamicValue(thread);
                                    SubLObject log = instances.get_slot(thread_29, LOG);
                                    SubLObject pw = instances.get_slot(thread_29, P_WRAPPER);
                                    com.cyc.cycjava.cycl.cb_shop.cb_shop_domain_examiner_link(pw);
                                    html_hr(UNPROVIDED, UNPROVIDED);
                                    if (instances.get_slot(log, VERBOSE).numE(ZERO_INTEGER)) {
                                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                        html_princ($str_alt219$No_Verbose_Output__Set_Verbosity_);
                                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                    } else {
                                        {
                                            SubLObject log_30 = instances.get_slot(thread_29, LOG);
                                            methods.funcall_instance_method_with_0_args(log_30, DISPLAY_HTML_LOG);
                                        }
                                    }
                                    html_source_readability_terpri(UNPROVIDED);
                                    html_copyright_notice();
                                }
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_28, thread);
                            }
                        }
                        html_markup(html_macros.$html_body_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_inside_bodyP$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            html_markup(html_macros.$html_html_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            return NIL;
        }
    }

    public static SubLObject plan_verbose_examiner_internal(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(cb_shop.$str44$__DOCTYPE_html_PUBLIC_____W3C__DT);
        if (NIL != html_macros.$html_force_ie_standards_mode$.getDynamicValue(thread)) {
            html_source_readability_terpri(UNPROVIDED);
            html_markup(cb_shop.$str45$_meta_http_equiv__X_UA_Compatible);
        }
        html_source_readability_terpri(UNPROVIDED);
        final SubLObject _prev_bind_0 = cyc_file_dependencies.$html_emitted_file_dependencies$.currentBinding(thread);
        try {
            cyc_file_dependencies.$html_emitted_file_dependencies$.bind(cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread) == $UNINITIALIZED ? list(EMPTY_SUBL_OBJECT_ARRAY) : cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread), thread);
            html_markup(html_macros.$html_html_head$.getGlobalValue());
            html_markup(html_macros.$html_head_head$.getGlobalValue());
            html_macros.html_head_content_type();
            html_source_readability_terpri(UNPROVIDED);
            html_markup(html_macros.$html_title_head$.getGlobalValue());
            html_princ(cb_shop.$$$SHOP_Log);
            html_markup(html_macros.$html_title_tail$.getGlobalValue());
            dhtml_macros.dhtml_with_dom_script();
            dhtml_macros.dhtml_with_toggle_visibility_support();
            html_markup(html_macros.$html_head_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            final SubLObject color_value = html_macros.$html_default_bgcolor$.getDynamicValue(thread);
            final SubLObject _prev_bind_0_$34 = html_macros.$html_inside_bodyP$.currentBinding(thread);
            try {
                html_macros.$html_inside_bodyP$.bind(T, thread);
                html_markup(html_macros.$html_body_head$.getGlobalValue());
                if (NIL != color_value) {
                    html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_color(color_value));
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$35 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    final SubLObject thread_$36 = cb_shop.$cb_shop_planner_thread$.getDynamicValue(thread);
                    final SubLObject log = instances.get_slot(thread_$36, LOG);
                    final SubLObject pw = instances.get_slot(thread_$36, cb_shop.P_WRAPPER);
                    cb_shop.cb_shop_domain_examiner_link(pw);
                    html_hr(UNPROVIDED, UNPROVIDED);
                    if (instances.get_slot(log, VERBOSE).numE(ZERO_INTEGER)) {
                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                        html_princ(cb_shop.$str229$No_Verbose_Output__Set_Verbosity_);
                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                    } else {
                        final SubLObject log_$37 = instances.get_slot(thread_$36, LOG);
                        methods.funcall_instance_method_with_0_args(log_$37, cb_shop.DISPLAY_HTML_LOG);
                    }
                    html_source_readability_terpri(UNPROVIDED);
                    html_copyright_notice();
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$35, thread);
                }
                html_markup(html_macros.$html_body_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
            } finally {
                html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_$34, thread);
            }
            html_markup(html_macros.$html_html_tail$.getGlobalValue());
        } finally {
            cyc_file_dependencies.$html_emitted_file_dependencies$.rebind(_prev_bind_0, thread);
        }
        html_source_readability_terpri(UNPROVIDED);
        return NIL;
    }

    public static final SubLObject plan_examiner_internal_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject title_var = $$$Plan_Examiner;
                {
                    SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
                    try {
                        html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? ((SubLObject) (html_macros.$html_id_space_id_generator$.getDynamicValue(thread))) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
                        html_markup(html_macros.$html_html_head$.getGlobalValue());
                        html_markup(html_macros.$html_head_head$.getGlobalValue());
                        html_macros.html_head_content_type();
                        cb_head_shortcut_icon();
                        html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                        if (NIL != title_var) {
                            html_source_readability_terpri(UNPROVIDED);
                            html_markup(html_macros.$html_title_head$.getGlobalValue());
                            html_princ(title_var);
                            html_markup(html_macros.$html_title_tail$.getGlobalValue());
                        }
                        html_complete.html_complete_script();
                        html_complete.html_autocomplete_css();
                        html_complete.html_autocomplete_scripts();
                        html_markup(html_macros.$html_head_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_31 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                            try {
                                html_macros.$html_inside_bodyP$.bind(T, thread);
                                html_markup(html_macros.$html_body_head$.getGlobalValue());
                                if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                                    html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (true) {
                                    html_markup(html_macros.$html_body_class$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup($str_alt45$yui_skin_sam);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_32 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        if (NIL != title_var) {
                                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                                            html_markup(TWO_INTEGER);
                                            html_char(CHAR_greater, UNPROVIDED);
                                            html_princ(title_var);
                                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                                            html_markup(TWO_INTEGER);
                                            html_char(CHAR_greater, UNPROVIDED);
                                        }
                                        {
                                            SubLObject frame_name_var = cb_frame_name(NIL);
                                            html_markup(html_macros.$html_form_head$.getGlobalValue());
                                            html_markup(html_macros.$html_form_action$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            if (NIL != html_macros.$html_form_method_post$.getGlobalValue()) {
                                                html_markup(html_macros.$html_form_method$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(html_macros.$html_form_method_post$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            if (NIL != frame_name_var) {
                                                html_markup(html_macros.$html_form_target$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(frame_name_var);
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_33 = html_macros.$html_safe_print$.currentBinding(thread);
                                                SubLObject _prev_bind_1 = html_macros.$within_html_form$.currentBinding(thread);
                                                SubLObject _prev_bind_2 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_macros.$within_html_form$.bind(T, thread);
                                                    html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                                                    html_hidden_input($str_alt223$handle_plan_examiner_input, T, UNPROVIDED);
                                                    cb_help_preamble($PLAN_EXAMINER, NIL, UNPROVIDED);
                                                    cb_link($PLANNER, $str_alt130$_New_Plan_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                    html_indent(UNPROVIDED);
                                                    cb_link($PLAN_VERBOSE_EXAMINER, $str_alt194$_Verbose_Output_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                    html_indent(UNPROVIDED);
                                                    {
                                                        SubLObject pw = instances.get_slot($cb_shop_planner_thread$.getDynamicValue(thread), P_WRAPPER);
                                                        com.cyc.cycjava.cycl.cb_shop.cb_shop_domain_examiner_link(pw);
                                                    }
                                                    html_hr(UNPROVIDED, UNPROVIDED);
                                                    com.cyc.cycjava.cycl.cb_shop.cb_plan_examiner_controls();
                                                    {
                                                        SubLObject plans = $cb_plan_displayers$.getDynamicValue(thread);
                                                        SubLObject halt_status = instances.get_slot($cb_shop_planner_thread$.getDynamicValue(thread), HALT_STATUS);
                                                        SubLObject last_task = shop_datastructures.shop_basic_problem_get_tasks_method(instances.get_slot($cb_shop_planner_thread$.getDynamicValue(thread), PROBLEM)).first();
                                                        com.cyc.cycjava.cycl.cb_shop.show_last_task(last_task, plans, halt_status);
                                                    }
                                                    html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                                                } finally {
                                                    html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_2, thread);
                                                    html_macros.$within_html_form$.rebind(_prev_bind_1, thread);
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_33, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_form_tail$.getGlobalValue());
                                        }
                                        html_source_readability_terpri(UNPROVIDED);
                                        html_copyright_notice();
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_32, thread);
                                    }
                                }
                                html_markup(html_macros.$html_body_tail$.getGlobalValue());
                                html_source_readability_terpri(UNPROVIDED);
                            } finally {
                                html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_31, thread);
                            }
                        }
                        html_markup(html_macros.$html_html_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject plan_examiner_internal(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject title_var = cb_shop.$$$Plan_Examiner;
        final SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
        try {
            html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? html_macros.$html_id_space_id_generator$.getDynamicValue(thread) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            html_markup(cb_shop.$str44$__DOCTYPE_html_PUBLIC_____W3C__DT);
            if (NIL != html_macros.$html_force_ie_standards_mode$.getDynamicValue(thread)) {
                html_source_readability_terpri(UNPROVIDED);
                html_markup(cb_shop.$str45$_meta_http_equiv__X_UA_Compatible);
            }
            html_source_readability_terpri(UNPROVIDED);
            final SubLObject _prev_bind_0_$38 = cyc_file_dependencies.$html_emitted_file_dependencies$.currentBinding(thread);
            try {
                cyc_file_dependencies.$html_emitted_file_dependencies$.bind(cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread) == $UNINITIALIZED ? list(EMPTY_SUBL_OBJECT_ARRAY) : cyc_file_dependencies.$html_emitted_file_dependencies$.getDynamicValue(thread), thread);
                html_markup(html_macros.$html_html_head$.getGlobalValue());
                html_markup(html_macros.$html_head_head$.getGlobalValue());
                html_macros.html_head_content_type();
                cb_head_shortcut_icon();
                html_meta_robot_instructions($cb_permit_robots_to_indexP$.getDynamicValue(thread), $cb_permit_robots_to_followP$.getDynamicValue(thread));
                cyc_file_dependencies.css($CB_CYC);
                dhtml_macros.html_basic_cb_scripts();
                if (NIL != title_var) {
                    html_source_readability_terpri(UNPROVIDED);
                    html_markup(html_macros.$html_title_head$.getGlobalValue());
                    html_princ(title_var);
                    html_markup(html_macros.$html_title_tail$.getGlobalValue());
                }
                html_complete.html_complete_script();
                cyc_file_dependencies.css(cb_shop.$SAM_AUTOCOMPLETE_CSS);
                html_markup(html_macros.$html_head_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
                final SubLObject _prev_bind_0_$39 = html_macros.$html_inside_bodyP$.currentBinding(thread);
                try {
                    html_macros.$html_inside_bodyP$.bind(T, thread);
                    html_markup(html_macros.$html_body_head$.getGlobalValue());
                    if (NIL != html_macros.$html_default_bgcolor$.getDynamicValue(thread)) {
                        html_markup(html_macros.$html_body_bgcolor$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_color(html_macros.$html_default_bgcolor$.getDynamicValue(thread)));
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_markup(html_macros.$html_body_class$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(cb_shop.$str50$yui_skin_sam);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$40 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_div_head$.getGlobalValue());
                        html_markup(html_macros.$html_attribute_id$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$reloadFrameButton);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$41 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_input_head$.getGlobalValue());
                            html_markup(html_macros.$html_input_type$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$button);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_name$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(cb_shop.$$$reload);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_value$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_princ(cb_shop.$$$Refresh_Frames);
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_input_disabledP$.getDynamicValue(thread)) {
                                html_simple_attribute(html_macros.$html_input_disabled$.getGlobalValue());
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$41, thread);
                        }
                        html_markup(html_macros.$html_div_tail$.getGlobalValue());
                        if (NIL != title_var) {
                            html_markup(html_macros.$html_heading_head$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                            html_princ(title_var);
                            html_markup(html_macros.$html_heading_tail$.getGlobalValue());
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_greater, UNPROVIDED);
                        }
                        final SubLObject frame_name_var = cb_frame_name(NIL);
                        html_markup(html_macros.$html_form_head$.getGlobalValue());
                        html_markup(html_macros.$html_form_action$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_macros.$html_form_method$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(cb_shop.$$$post);
                        html_char(CHAR_quotation, UNPROVIDED);
                        if (NIL != frame_name_var) {
                            html_markup(html_macros.$html_form_target$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(frame_name_var);
                            html_char(CHAR_quotation, UNPROVIDED);
                        }
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$42 = html_macros.$html_safe_print$.currentBinding(thread);
                        final SubLObject _prev_bind_2 = html_macros.$within_html_form$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_macros.$within_html_form$.bind(T, thread);
                            html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                            html_hidden_input(cb_shop.$str233$handle_plan_examiner_input, T, UNPROVIDED);
                            cb_help_preamble($PLAN_EXAMINER, NIL, UNPROVIDED);
                            cb_link($PLANNER, cb_shop.$str140$_New_Plan_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            html_indent(UNPROVIDED);
                            cb_link(cb_shop.$PLAN_VERBOSE_EXAMINER, cb_shop.$str204$_Verbose_Output_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            html_indent(UNPROVIDED);
                            final SubLObject pw = instances.get_slot(cb_shop.$cb_shop_planner_thread$.getDynamicValue(thread), cb_shop.P_WRAPPER);
                            cb_shop.cb_shop_domain_examiner_link(pw);
                            html_hr(UNPROVIDED, UNPROVIDED);
                            cb_shop.cb_plan_examiner_controls();
                            final SubLObject plans = cb_shop.$cb_plan_displayers$.getDynamicValue(thread);
                            final SubLObject halt_status = instances.get_slot(cb_shop.$cb_shop_planner_thread$.getDynamicValue(thread), HALT_STATUS);
                            final SubLObject last_task = shop_datastructures.shop_basic_problem_get_tasks_method(instances.get_slot(cb_shop.$cb_shop_planner_thread$.getDynamicValue(thread), PROBLEM)).first();
                            cb_shop.show_last_task(last_task, plans, halt_status);
                            html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                        } finally {
                            html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_3, thread);
                            html_macros.$within_html_form$.rebind(_prev_bind_2, thread);
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$42, thread);
                        }
                        html_markup(html_macros.$html_form_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_copyright_notice();
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$40, thread);
                    }
                    html_markup(html_macros.$html_body_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                } finally {
                    html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_$39, thread);
                }
                html_markup(html_macros.$html_html_tail$.getGlobalValue());
            } finally {
                cyc_file_dependencies.$html_emitted_file_dependencies$.rebind(_prev_bind_0_$38, thread);
            }
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static final SubLObject handle_plan_examiner_input_alt(SubLObject args) {
        {
            SubLObject new_format = html_extract_input($$$format, args);
            SubLObject new_structure = html_extract_input($$$structure, args);
            $cb_last_planner_format$.setDynamicValue(new_format.equal($str_alt228$_cycl) ? ((SubLObject) ($CYCL)) : $ENGLISH);
            $cb_last_planner_structure$.setDynamicValue(new_structure.equal($str_alt230$_hierarchical) ? ((SubLObject) ($HIERARCHICAL)) : $PRIMITIVE);
            com.cyc.cycjava.cycl.cb_shop.plan_examiner_internal(args);
            return NIL;
        }
    }

    public static SubLObject handle_plan_examiner_input(final SubLObject args) {
        final SubLObject new_format = html_extract_input(cb_shop.$$$format, args);
        final SubLObject new_structure = html_extract_input(cb_shop.$$$structure, args);
        cb_shop.$cb_last_planner_format$.setDynamicValue(new_format.equal(cb_shop.$str238$_cycl) ? $CYCL : $ENGLISH);
        cb_shop.$cb_last_planner_structure$.setDynamicValue(new_structure.equal(cb_shop.$str240$_hierarchical) ? $HIERARCHICAL : $PRIMITIVE);
        cb_shop.plan_examiner_internal(args);
        return NIL;
    }

    public static final SubLObject cb_plan_examiner_controls_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_submit_input($$$Change_View, $$$submit, UNPROVIDED);
            html_newline(UNPROVIDED);
            html_markup(html_macros.$html_table_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_border$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(ZERO_INTEGER);
                html_char(CHAR_quotation, UNPROVIDED);
            }
            if (true) {
                html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(ZERO_INTEGER);
                html_char(CHAR_quotation, UNPROVIDED);
            }
            if (true) {
                html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(ZERO_INTEGER);
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_34 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_35 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                                    if (true) {
                                        html_markup(html_macros.$html_table_border$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(ZERO_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    if (true) {
                                        html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(ZERO_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    if (true) {
                                        html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(ZERO_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_36 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_37 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_38 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                            html_princ($str_alt235$Format_);
                                                            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_38, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_37, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_39 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_40 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_radio_input($$$format, $str_alt236$_english, equal($cb_last_planner_format$.getDynamicValue(thread), $ENGLISH));
                                                            html_indent(UNPROVIDED);
                                                            html_princ($$$English);
                                                            html_indent(FIVE_INTEGER);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_40, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_39, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_41 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_42 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_radio_input($$$format, $str_alt228$_cycl, equal($cb_last_planner_format$.getDynamicValue(thread), $CYCL));
                                                            html_indent(UNPROVIDED);
                                                            html_princ($$$CycL);
                                                            html_indent(FIVE_INTEGER);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_42, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_41, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_36, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_35, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_43 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                                    if (true) {
                                        html_markup(html_macros.$html_table_border$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(ZERO_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    if (true) {
                                        html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(ZERO_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    if (true) {
                                        html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(ZERO_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_44 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_45 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_46 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                            html_princ($str_alt239$Structure_);
                                                            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_46, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_45, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_47 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_48 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_radio_input($$$structure, $str_alt240$_primitive, equal($cb_last_planner_structure$.getDynamicValue(thread), $PRIMITIVE));
                                                            html_indent(UNPROVIDED);
                                                            html_princ($$$Primitive);
                                                            html_indent(FIVE_INTEGER);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_48, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_47, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_49 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_50 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_radio_input($$$structure, $str_alt230$_hierarchical, equal($cb_last_planner_structure$.getDynamicValue(thread), $HIERARCHICAL));
                                                            html_indent(UNPROVIDED);
                                                            html_princ($$$Hierarchical);
                                                            html_indent(FIVE_INTEGER);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_50, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_49, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_44, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_43, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_34, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_tail$.getGlobalValue());
            html_newline(UNPROVIDED);
            html_hr(UNPROVIDED, UNPROVIDED);
            return NIL;
        }
    }

    public static SubLObject cb_plan_examiner_controls() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_submit_input(cb_shop.$$$Change_View, cb_shop.$$$submit, UNPROVIDED);
        html_newline(UNPROVIDED);
        html_markup(html_macros.$html_table_head$.getGlobalValue());
        html_markup(html_macros.$html_table_border$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$43 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$44 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$45 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$46 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$47 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str245$Format_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$47, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$46, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$48 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$49 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$$$format, cb_shop.$str246$_english, equal(cb_shop.$cb_last_planner_format$.getDynamicValue(thread), $ENGLISH), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$English);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$49, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$48, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$50 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$51 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$$$format, cb_shop.$str238$_cycl, equal(cb_shop.$cb_last_planner_format$.getDynamicValue(thread), $CYCL), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$CycL);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$51, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$50, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$45, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$44, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$52 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$53 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$54 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$55 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str249$Structure_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$55, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$54, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$56 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$57 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$$$structure, cb_shop.$str250$_primitive, equal(cb_shop.$cb_last_planner_structure$.getDynamicValue(thread), $PRIMITIVE), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$Primitive);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$57, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$56, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$58 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$59 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$$$structure, cb_shop.$str240$_hierarchical, equal(cb_shop.$cb_last_planner_structure$.getDynamicValue(thread), $HIERARCHICAL), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$Hierarchical);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$59, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$58, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$53, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$52, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$43, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_tail$.getGlobalValue());
        html_newline(UNPROVIDED);
        html_hr(UNPROVIDED, UNPROVIDED);
        return NIL;
    }

    public static final SubLObject cb_advanced_planner_settings_table_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            cb_link($PLAN_CHECKING, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            {
                SubLObject number_string = html_extract_input($$$number, args);
                SubLObject time_arg = html_extract_input($$$time, args);
                SubLObject time_string = (NIL != time_arg) ? ((SubLObject) (time_arg)) : NIL != $cb_shop_time_cutoff$.getDynamicValue(thread) ? ((SubLObject) (write_to_string($cb_shop_time_cutoff$.getDynamicValue(thread), EMPTY_SUBL_OBJECT_ARRAY))) : $str_alt243$;
                html_newline(TWO_INTEGER);
                html_markup(html_macros.$html_table_head$.getGlobalValue());
                if (true) {
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_51 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_52 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_indent(THREE_INTEGER);
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_52, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_53 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_54 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_55 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_56 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                                html_princ($str_alt244$Number_Cutoff_);
                                                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_56, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_55, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_57 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_58 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt139$cutoff_number, $str_alt245$no_number, makeBoolean(NIL == $cb_last_planner_number$.getDynamicValue(thread)));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$All_plans);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_58, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_57, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_59 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_60 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt139$cutoff_number, $$$number, list_utilities.sublisp_boolean($cb_last_planner_number$.getDynamicValue(thread)));
                                                                html_indent(UNPROVIDED);
                                                                html_text_input($$$number, number_string, THREE_INTEGER);
                                                                html_princ($str_alt247$_plans);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_60, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_59, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_54, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_53, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_61 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_62 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_63 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_64 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                                html_princ($str_alt248$Time_Cutoff_);
                                                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_64, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_63, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_65 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_66 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt141$cutoff_time, $str_alt249$no_time, makeBoolean(NIL == $cb_shop_time_cutoff$.getDynamicValue(thread)));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$Run_forever);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_66, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_65, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_67 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_68 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt141$cutoff_time, $$$time, list_utilities.sublisp_boolean($cb_shop_time_cutoff$.getDynamicValue(thread)));
                                                                html_indent(UNPROVIDED);
                                                                html_text_input($$$time, time_string, THREE_INTEGER);
                                                                html_princ($str_alt251$_seconds);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_68, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_67, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_62, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_61, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_69 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_70 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_71 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_72 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                                html_princ($str_alt252$Depth_Cutoff_);
                                                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_72, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_71, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_73 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_74 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt147$depth_limit_p, $str_alt253$no_depth_limit, sublisp_null($cb_shop_depth_limit$.getDynamicValue(thread)));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$No);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_74, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_73, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_75 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_76 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt147$depth_limit_p, $str_alt148$depth_limit, $cb_shop_depth_limit$.getDynamicValue(thread));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$Yes);
                                                                html_text_input($str_alt148$depth_limit, NIL == $cb_shop_depth_limit$.getDynamicValue(thread) ? ((SubLObject) ($str_alt243$)) : write_to_string($cb_shop_depth_limit$.getDynamicValue(thread), EMPTY_SUBL_OBJECT_ARRAY), FIVE_INTEGER);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_76, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_75, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_70, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_69, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_77 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_78 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_79 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_80 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                                html_princ($str_alt256$Back_Chaining_);
                                                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_80, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_79, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_81 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_82 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt143$do_fi_asks, $$$nil, eq($cb_shop_num_backchains$.getDynamicValue(thread), ZERO_INTEGER));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$No_backchaining);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_82, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_81, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_83 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_84 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt143$do_fi_asks, $str_alt144$do_asks, numGE($cb_shop_num_backchains$.getDynamicValue(thread), ONE_INTEGER));
                                                                html_indent(UNPROVIDED);
                                                                html_text_input($str_alt145$num_backchains, write_to_string($cb_shop_num_backchains$.getDynamicValue(thread), EMPTY_SUBL_OBJECT_ARRAY), THREE_INTEGER);
                                                                html_princ($str_alt259$_backchains);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_84, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_83, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_78, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_77, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_51, thread);
                            }
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_table_tail$.getGlobalValue());
                html_newline(UNPROVIDED);
                html_markup(html_macros.$html_table_head$.getGlobalValue());
                if (true) {
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_85 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_86 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_indent(THREE_INTEGER);
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_86, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_87 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_88 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_89 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_90 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                                html_princ($str_alt260$Cycle_Checking_);
                                                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_90, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_89, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_91 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_92 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt146$cycle_checking, $str_alt261$no_cycle_checking, eq($cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), NIL));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$No);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_92, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_91, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_93 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_94 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt146$cycle_checking, $str_alt146$cycle_checking, eq($cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), T));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$Yes);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_94, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_93, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_88, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_87, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_95 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_table_border$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        if (true) {
                                            html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(ZERO_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_96 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_97 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_98 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                                                html_princ($str_alt262$Log_Process_);
                                                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_98, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_97, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_99 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_100 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt138$verbosity_level, $str_alt151$no_logging, numE($cb_shop_verbosity$.getDynamicValue(thread), ZERO_INTEGER));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$No);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_100, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_99, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_101 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                        html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                                                        html_char(CHAR_greater, UNPROVIDED);
                                                        {
                                                            SubLObject _prev_bind_0_102 = html_macros.$html_safe_print$.currentBinding(thread);
                                                            try {
                                                                html_macros.$html_safe_print$.bind(T, thread);
                                                                html_radio_input($str_alt138$verbosity_level, $$$logging, numG($cb_shop_verbosity$.getDynamicValue(thread), ZERO_INTEGER));
                                                                html_indent(UNPROVIDED);
                                                                html_princ($$$Yes);
                                                                html_indent(FIVE_INTEGER);
                                                            } finally {
                                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_102, thread);
                                                            }
                                                        }
                                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_101, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                                html_source_readability_terpri(UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_96, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_95, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_85, thread);
                            }
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_table_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_advanced_planner_settings_table(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        cb_link($PLAN_CHECKING, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject number_string = html_extract_input(cb_shop.$$$number, args);
        final SubLObject time_arg = html_extract_input(cb_shop.$$$time, args);
        final SubLObject time_string = (NIL != time_arg) ? time_arg : NIL != cb_shop.$cb_shop_time_cutoff$.getDynamicValue(thread) ? write_to_string(cb_shop.$cb_shop_time_cutoff$.getDynamicValue(thread), EMPTY_SUBL_OBJECT_ARRAY) : cb_shop.$str253$;
        html_newline(TWO_INTEGER);
        html_markup(html_macros.$html_table_head$.getGlobalValue());
        html_markup(html_macros.$html_table_border$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$60 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$61 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_indent(THREE_INTEGER);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$61, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$62 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$63 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$64 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$65 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str254$Number_Cutoff_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$65, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$64, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$66 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$67 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str149$cutoff_number, cb_shop.$str255$no_number, makeBoolean(NIL == cb_shop.$cb_last_planner_number$.getDynamicValue(thread)), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$All_plans);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$67, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$66, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$68 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$69 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str149$cutoff_number, cb_shop.$$$number, list_utilities.sublisp_boolean(cb_shop.$cb_last_planner_number$.getDynamicValue(thread)), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_text_input(cb_shop.$$$number, number_string, THREE_INTEGER);
                                html_princ(cb_shop.$$$_plans);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$69, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$68, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$63, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$62, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$70 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$71 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$72 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$73 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str258$Time_Cutoff_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$73, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$72, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$74 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$75 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str151$cutoff_time, cb_shop.$str259$no_time, makeBoolean(NIL == cb_shop.$cb_shop_time_cutoff$.getDynamicValue(thread)), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$Run_forever);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$75, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$74, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$76 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$77 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str151$cutoff_time, cb_shop.$$$time, list_utilities.sublisp_boolean(cb_shop.$cb_shop_time_cutoff$.getDynamicValue(thread)), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_text_input(cb_shop.$$$time, time_string, THREE_INTEGER);
                                html_princ(cb_shop.$$$_seconds);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$77, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$76, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$71, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$70, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$78 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$79 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$80 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$81 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str262$Depth_Cutoff_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$81, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$80, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$82 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$83 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str157$depth_limit_p, cb_shop.$str263$no_depth_limit, sublisp_null(cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread)), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$No);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$83, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$82, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$84 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$85 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str157$depth_limit_p, cb_shop.$str158$depth_limit, cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$Yes);
                                html_text_input(cb_shop.$str158$depth_limit, NIL == cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread) ? cb_shop.$str253$ : write_to_string(cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread), EMPTY_SUBL_OBJECT_ARRAY), FIVE_INTEGER);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$85, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$84, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$79, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$78, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$86 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$87 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$88 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$89 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str266$Back_Chaining_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$89, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$88, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$90 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$91 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str153$do_fi_asks, cb_shop.$$$nil, eq(cb_shop.$cb_shop_num_backchains$.getDynamicValue(thread), ZERO_INTEGER), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$No_backchaining);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$91, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$90, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$92 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$93 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str153$do_fi_asks, cb_shop.$str154$do_asks, numGE(cb_shop.$cb_shop_num_backchains$.getDynamicValue(thread), ONE_INTEGER), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_text_input(cb_shop.$str155$num_backchains, write_to_string(cb_shop.$cb_shop_num_backchains$.getDynamicValue(thread), EMPTY_SUBL_OBJECT_ARRAY), THREE_INTEGER);
                                html_princ(cb_shop.$$$_backchains);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$93, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$92, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$87, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$86, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$60, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_tail$.getGlobalValue());
        html_newline(UNPROVIDED);
        html_markup(html_macros.$html_table_head$.getGlobalValue());
        html_markup(html_macros.$html_table_border$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$94 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$95 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_indent(THREE_INTEGER);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$95, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$96 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$97 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$98 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$99 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str270$Cycle_Checking_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$99, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$98, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$100 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$101 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str156$cycle_checking, cb_shop.$str271$no_cycle_checking, eq(cb_shop.$cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), NIL), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$No);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$101, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$100, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$102 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$103 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str156$cycle_checking, cb_shop.$str156$cycle_checking, eq(cb_shop.$cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), T), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$Yes);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$103, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$102, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$97, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$96, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$104 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_border$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$105 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$106 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$107 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                                html_princ(cb_shop.$str272$Log_Process_);
                                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$107, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$106, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$108 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$109 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str148$verbosity_level, cb_shop.$str161$no_logging, numE(cb_shop.$cb_shop_verbosity$.getDynamicValue(thread), ZERO_INTEGER), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$No);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$109, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$108, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$110 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_simple_attribute(html_macros.$html_table_data_nowrap$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$111 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_radio_input(cb_shop.$str148$verbosity_level, cb_shop.$$$logging, numG(cb_shop.$cb_shop_verbosity$.getDynamicValue(thread), ZERO_INTEGER), UNPROVIDED);
                                html_indent(UNPROVIDED);
                                html_princ(cb_shop.$$$Yes);
                                html_indent(FIVE_INTEGER);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$111, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$110, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$105, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$104, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$94, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject find_keyword_for_search_type_alt(SubLObject search_type) {
        {
            SubLObject v_answer = NIL;
            if (search_type.equal($str_alt263$_all)) {
                v_answer = $ALL;
            } else {
                if (search_type.equal($str_alt264$_first)) {
                    v_answer = $FIRST;
                } else {
                    if (search_type.equal($str_alt266$_shallowest)) {
                        v_answer = $SHALLOWEST;
                    } else {
                        if (search_type.equal($str_alt268$_all_shallowest)) {
                            v_answer = $ALL_SHALLOWEST;
                        } else {
                            if (search_type.equal($str_alt270$_id_first)) {
                                v_answer = $ID_FIRST;
                            } else {
                                if (search_type.equal($str_alt272$_id_all)) {
                                    v_answer = $ID_ALL;
                                } else {
                                    v_answer = $ALL;
                                }
                            }
                        }
                    }
                }
            }
            return v_answer;
        }
    }

    public static SubLObject find_keyword_for_search_type(final SubLObject search_type) {
        SubLObject v_answer = NIL;
        if (search_type.equal(cb_shop.$str273$_all)) {
            v_answer = $ALL;
        } else
            if (search_type.equal(cb_shop.$str274$_first)) {
                v_answer = $FIRST;
            } else
                if (search_type.equal(cb_shop.$str276$_shallowest)) {
                    v_answer = $SHALLOWEST;
                } else
                    if (search_type.equal(cb_shop.$str278$_all_shallowest)) {
                        v_answer = $ALL_SHALLOWEST;
                    } else
                        if (search_type.equal(cb_shop.$str280$_id_first)) {
                            v_answer = $ID_FIRST;
                        } else
                            if (search_type.equal(cb_shop.$str282$_id_all)) {
                                v_answer = $ID_ALL;
                            } else {
                                v_answer = $ALL;
                            }





        return v_answer;
    }

    public static final SubLObject plan_task_examiner_internal_alt(SubLObject args) {
        {
            SubLObject id_string = args.first();
            SubLObject task_displayer = methods.funcall_class_method_with_1_args(HTML_TASK_DISPLAYER, FIND_INSTANCE_BY_ID_STRING, id_string);
            methods.funcall_instance_method_with_1_args(task_displayer, HTML_TASK_PAGE, NIL);
            return NIL;
        }
    }

    public static SubLObject plan_task_examiner_internal(final SubLObject args) {
        final SubLObject id_string = args.first();
        final SubLObject task_displayer = methods.funcall_class_method_with_1_args(cb_shop.HTML_TASK_DISPLAYER, cb_shop.FIND_INSTANCE_BY_ID_STRING, id_string);
        methods.funcall_instance_method_with_1_args(task_displayer, cb_shop.HTML_TASK_PAGE, NIL);
        return NIL;
    }

    /**
     * Displays PLANS in OUT-FORMAT (English or CycL).
     */
    @LispMethod(comment = "Displays PLANS in OUT-FORMAT (English or CycL).")
    public static final SubLObject display_html_plans_alt(SubLObject plans, SubLObject halt_reason) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_strong_head$.getGlobalValue());
            html_princ($str_alt277$Number_of_plans_found__);
            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
            html_princ(length(plans));
            html_newline(TWO_INTEGER);
            com.cyc.cycjava.cycl.cb_shop.cb_planner_display_halt_reason(halt_reason);
            html_newline(TWO_INTEGER);
            html_markup(html_macros.$html_strong_head$.getGlobalValue());
            html_princ($str_alt278$Plans_);
            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
            if (NIL != plans) {
                html_newline(UNPROVIDED);
                {
                    SubLObject j = ONE_INTEGER;
                    SubLObject style_controls = list($cb_last_planner_format$.getDynamicValue(thread), $cb_last_planner_structure$.getDynamicValue(thread), $TASK_EXAMINER_LINKS);
                    SubLObject cdolist_list_var = plans;
                    SubLObject plan = NIL;
                    for (plan = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , plan = cdolist_list_var.first()) {
                        html_markup(html_macros.$html_strong_head$.getGlobalValue());
                        html_princ($str_alt280$Plan_);
                        html_prin1(j);
                        html_princ($str_alt281$_);
                        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                        shop_displayers.html_plan_displayer_html_form_method(plan, style_controls);
                        html_newline(UNPROVIDED);
                        j = add(j, ONE_INTEGER);
                    }
                }
            } else {
                html_indent(UNPROVIDED);
                html_princ($str_alt282$No_plans_found_);
                html_newline(UNPROVIDED);
            }
            return NIL;
        }
    }

    /**
     * Displays PLANS in OUT-FORMAT (English or CycL).
     */
    @LispMethod(comment = "Displays PLANS in OUT-FORMAT (English or CycL).")
    public static SubLObject display_html_plans(final SubLObject plans, final SubLObject halt_reason) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_strong_head$.getGlobalValue());
        html_princ(cb_shop.$str287$Number_of_plans_found__);
        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        html_princ(length(plans));
        html_newline(TWO_INTEGER);
        cb_shop.cb_planner_display_halt_reason(halt_reason);
        html_newline(TWO_INTEGER);
        html_markup(html_macros.$html_strong_head$.getGlobalValue());
        html_princ(cb_shop.$str288$Plans_);
        html_markup(html_macros.$html_strong_tail$.getGlobalValue());
        if (NIL != plans) {
            html_newline(UNPROVIDED);
            SubLObject j = ONE_INTEGER;
            final SubLObject style_controls = list(cb_shop.$cb_last_planner_format$.getDynamicValue(thread), cb_shop.$cb_last_planner_structure$.getDynamicValue(thread), cb_shop.$TASK_EXAMINER_LINKS);
            SubLObject cdolist_list_var = plans;
            SubLObject plan = NIL;
            plan = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                html_princ(cb_shop.$$$Plan_);
                html_prin1(j);
                html_princ(cb_shop.$str291$_);
                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                shop_displayers.html_plan_displayer_html_form_method(plan, style_controls);
                html_newline(UNPROVIDED);
                j = add(j, ONE_INTEGER);
                cdolist_list_var = cdolist_list_var.rest();
                plan = cdolist_list_var.first();
            } 
        } else {
            html_indent(UNPROVIDED);
            html_princ(cb_shop.$str292$No_plans_found_);
            html_newline(UNPROVIDED);
        }
        return NIL;
    }/**
     * Displays PLANS in OUT-FORMAT (English or CycL).
     */


    public static final SubLObject cb_shop_abort_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject pw = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        process_utilities.process_wrapper_process_abort(pw);
                        dictionary.dictionary_remove($cb_shop_processes$.getDynamicValue(thread), process_utilities.process_wrapper_get_id(pw));
                        return com.cyc.cycjava.cycl.cb_shop.plan_finder(NIL);
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_abort_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject pw = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            process_utilities.process_wrapper_process_abort(pw);
            dictionary.dictionary_remove(cb_shop.$cb_shop_processes$.getDynamicValue(thread), process_utilities.process_wrapper_get_id(pw));
            return cb_shop.plan_finder(NIL);
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_abort_link_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), pw);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt289$cb_shop_abort_link_handler, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt290$_Abort_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_abort_link(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), pw);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str299$cb_shop_abort_link_handler, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str300$_Abort_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_pause_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject a = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        process_utilities.process_wrapper_process_pause(a);
                        return com.cyc.cycjava.cycl.cb_shop.plan_finder(NIL);
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_pause_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject a = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            process_utilities.process_wrapper_process_pause(a);
            return cb_shop.plan_finder(NIL);
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_pause_link_alt(SubLObject a) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), a);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt292$cb_shop_pause_link_handler, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt293$_Pause_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_pause_link(final SubLObject a) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), a);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str302$cb_shop_pause_link_handler, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str303$_Pause_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_resume_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject pw = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        process_utilities.process_wrapper_process_resume(pw);
                        return com.cyc.cycjava.cycl.cb_shop.plan_finder(NIL);
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_resume_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject pw = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            process_utilities.process_wrapper_process_resume(pw);
            return cb_shop.plan_finder(NIL);
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_resume_link_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), pw);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt295$cb_shop_resume_link_handler, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt296$_Resume_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_resume_link(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), pw);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str305$cb_shop_resume_link_handler, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str306$_Resume_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_view_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject pw = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_view(pw);
                        return com.cyc.cycjava.cycl.cb_shop.plan_examiner_internal(NIL);
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_view_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject pw = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            cb_shop.cb_shop_plan_process_view(pw);
            return cb_shop.plan_examiner_internal(NIL);
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_view_link_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), pw);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt298$cb_shop_view_link_handler, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt299$_View_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_view_link(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), pw);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str308$cb_shop_view_link_handler, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str309$_View_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_replan_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject pw = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        com.cyc.cycjava.cycl.cb_shop.cb_shop_replan(pw);
                        return com.cyc.cycjava.cycl.cb_shop.plan_finder(NIL);
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_replan_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject pw = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            cb_shop.cb_shop_replan(pw);
            return cb_shop.plan_finder(NIL);
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_replan_link_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), pw);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt301$cb_shop_replan_link_handler, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt302$_Replan_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_replan_link(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), pw);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str311$cb_shop_replan_link_handler, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str312$_Replan_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_verbose_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject pw = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        {
                            SubLObject _prev_bind_0 = $cb_shop_planner_thread$.currentBinding(thread);
                            try {
                                $cb_shop_planner_thread$.bind(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw), thread);
                                com.cyc.cycjava.cycl.cb_shop.plan_verbose_examiner_internal(NIL);
                            } finally {
                                $cb_shop_planner_thread$.rebind(_prev_bind_0, thread);
                            }
                        }
                        return NIL;
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_verbose_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject pw = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            final SubLObject _prev_bind_0 = cb_shop.$cb_shop_planner_thread$.currentBinding(thread);
            try {
                cb_shop.$cb_shop_planner_thread$.bind(cb_shop.cb_shop_plan_process_get_thread(pw), thread);
                cb_shop.plan_verbose_examiner_internal(NIL);
            } finally {
                cb_shop.$cb_shop_planner_thread$.rebind(_prev_bind_0, thread);
            }
            return NIL;
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_verbose_link_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), pw);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt304$cb_shop_verbose_link_handler, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt305$_Log_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_verbose_link(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), pw);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str314$cb_shop_verbose_link_handler, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str315$_Log_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_delete_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject pw = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        if (NIL == process_utilities.process_wrapper_process_completedP(pw)) {
                            process_utilities.process_wrapper_process_abort(pw);
                        }
                        dictionary.dictionary_remove($cb_shop_processes$.getDynamicValue(thread), process_utilities.process_wrapper_get_id(pw));
                        return com.cyc.cycjava.cycl.cb_shop.plan_finder(NIL);
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_delete_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject pw = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            if (NIL == process_utilities.process_wrapper_process_completedP(pw)) {
                process_utilities.process_wrapper_process_abort(pw);
            }
            dictionary.dictionary_remove(cb_shop.$cb_shop_processes$.getDynamicValue(thread), process_utilities.process_wrapper_get_id(pw));
            return cb_shop.plan_finder(NIL);
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_delete_link_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), pw);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt307$cb_shop_delete_link_handler, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt308$_Delete_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_delete_link(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), pw);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str317$cb_shop_delete_link_handler, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str318$_Delete_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject cb_shop_domain_examiner_link_handler_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = args;
                SubLObject current = datum;
                SubLObject id_str = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt286);
                id_str = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject pw = funcall(html_id_links.html_id_lookup_func($cb_shop_processes$.getDynamicValue(thread)), $cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        return com.cyc.cycjava.cycl.cb_shop.cb_shop_domain_examiner(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_domain(pw));
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt286);
                }
            }
            return NIL;
        }
    }

    public static SubLObject cb_shop_domain_examiner_link_handler(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject id_str = NIL;
        destructuring_bind_must_consp(args, args, cb_shop.$list296);
        id_str = args.first();
        final SubLObject current = args.rest();
        if (NIL == current) {
            final SubLObject pw = funcall(html_id_links.html_id_lookup_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), cb_shop.$cb_shop_processes$.getDynamicValue(thread), read_from_string_ignoring_errors(id_str, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            return cb_shop.cb_shop_domain_examiner(cb_shop.cb_shop_plan_process_get_domain(pw));
        }
        cdestructuring_bind_error(args, cb_shop.$list296);
        return NIL;
    }

    public static final SubLObject cb_shop_domain_examiner_link_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = funcall(html_id_links.html_id_id_func($cb_shop_processes$.getDynamicValue(thread)), pw);
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt288$_a__a, $str_alt310$cb_shop_domain_examiner_link_hand, id);
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != frame_name_var) {
                    html_markup(html_macros.$html_anchor_target$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(frame_name_var);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_princ($str_alt311$_Domain_);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                return NIL;
            }
        }
    }

    public static SubLObject cb_shop_domain_examiner_link(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = funcall(html_id_links.html_id_id_func(cb_shop.$cb_shop_processes$.getDynamicValue(thread)), pw);
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        format(html_macros.$html_stream$.getDynamicValue(thread), cb_shop.$str298$_a__a, cb_shop.$str320$cb_shop_domain_examiner_link_hand, id);
        html_char(CHAR_quotation, UNPROVIDED);
        if (NIL != frame_name_var) {
            html_markup(html_macros.$html_anchor_target$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(frame_name_var);
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$str321$_Domain_);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
        return NIL;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return shop-basic-planner-thread-p
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return shop-basic-planner-thread-p")
    public static final SubLObject cb_shop_plan_process_get_thread_alt(SubLObject pw) {
        return process_utilities.process_wrapper_get_property(pw, $THREAD_STRUCTURE, UNPROVIDED);
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return shop-basic-planner-thread-p
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return shop-basic-planner-thread-p")
    public static SubLObject cb_shop_plan_process_get_thread(final SubLObject pw) {
        return process_utilities.process_wrapper_get_property(pw, $THREAD_STRUCTURE, UNPROVIDED);
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @param THREAD
    shop-basic-planner-thread-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@param THREAD\nshop-basic-planner-thread-p")
    public static final SubLObject cb_shop_plan_process_set_planner_alt(SubLObject pw, SubLObject v_planner) {
        return process_utilities.process_wrapper_set_property(pw, $PLANNER, v_planner);
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @param THREAD
    shop-basic-planner-thread-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@param THREAD\nshop-basic-planner-thread-p")
    public static SubLObject cb_shop_plan_process_set_planner(final SubLObject pw, final SubLObject planner) {
        return process_utilities.process_wrapper_set_property(pw, $PLANNER, planner);
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return el-formula-p ;; task given to plan process thread
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return el-formula-p ;; task given to plan process thread")
    public static final SubLObject cb_shop_plan_process_get_task_alt(SubLObject pw) {
        {
            SubLObject task = shop_basic_planner_thread.shop_planner_thread_task(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw));
            if (NIL != task) {
                return task;
            }
            return $$$Initializing;
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return el-formula-p ;; task given to plan process thread
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return el-formula-p ;; task given to plan process thread")
    public static SubLObject cb_shop_plan_process_get_task(final SubLObject pw) {
        final SubLObject task = shop_basic_planner_thread.shop_planner_thread_task(cb_shop.cb_shop_plan_process_get_thread(pw));
        if (NIL != task) {
            return task;
        }
        return cb_shop.$$$Initializing;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return shop-basic-domain-p
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return shop-basic-domain-p")
    public static final SubLObject cb_shop_plan_process_get_domain_alt(SubLObject pw) {
        return methods.funcall_instance_method_with_0_args(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw), GET_DOMAIN);
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return shop-basic-domain-p
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return shop-basic-domain-p")
    public static SubLObject cb_shop_plan_process_get_domain(final SubLObject pw) {
        return methods.funcall_instance_method_with_0_args(cb_shop.cb_shop_plan_process_get_thread(pw), GET_DOMAIN);
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of inferences found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of inferences found so far")
    public static final SubLObject cb_shop_plan_process_num_open_nodes_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            if (NIL != thread) {
                return shop_basic_planner_thread.shop_planner_thread_num_open_nodes(thread);
            } else {
                return ONE_INTEGER;
            }
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of inferences found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of inferences found so far")
    public static SubLObject cb_shop_plan_process_num_open_nodes(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        if (NIL != thread) {
            return shop_basic_planner_thread.shop_planner_thread_num_open_nodes(thread);
        }
        return ONE_INTEGER;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of inferences found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of inferences found so far")
    public static final SubLObject cb_shop_plan_process_num_inferences_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            if (NIL != thread) {
                return shop_basic_planner_thread.shop_planner_thread_num_inferences(thread);
            } else {
                return ZERO_INTEGER;
            }
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of inferences found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of inferences found so far")
    public static SubLObject cb_shop_plan_process_num_inferences(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        if (NIL != thread) {
            return shop_basic_planner_thread.shop_planner_thread_num_inferences(thread);
        }
        return ZERO_INTEGER;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of expansions found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of expansions found so far")
    public static final SubLObject cb_shop_plan_process_num_expansions_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            if (NIL != thread) {
                return shop_basic_planner_thread.shop_planner_thread_nodes_expanded(thread);
            } else {
                return ZERO_INTEGER;
            }
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of expansions found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of expansions found so far")
    public static SubLObject cb_shop_plan_process_num_expansions(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        if (NIL != thread) {
            return shop_basic_planner_thread.shop_planner_thread_nodes_expanded(thread);
        }
        return ZERO_INTEGER;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of plans found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of plans found so far")
    public static final SubLObject cb_shop_plan_process_num_plans_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            if (NIL != thread) {
                return shop_basic_planner_thread.shop_planner_thread_num_plans_found(thread);
            } else {
                return ZERO_INTEGER;
            }
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of plans found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of plans found so far")
    public static SubLObject cb_shop_plan_process_num_plans(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        if (NIL != thread) {
            return shop_basic_planner_thread.shop_planner_thread_num_plans_found(thread);
        }
        return ZERO_INTEGER;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of plans found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of plans found so far")
    public static final SubLObject cb_shop_plan_process_verbosity_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            SubLObject log = instances.get_slot(thread, LOG);
            if (NIL != thread) {
                return instances.get_slot(log, VERBOSE);
            } else {
                return ZERO_INTEGER;
            }
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return integerp ;; the number of plans found so far
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return integerp ;; the number of plans found so far")
    public static SubLObject cb_shop_plan_process_verbosity(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        final SubLObject log = instances.get_slot(thread, LOG);
        if (NIL != thread) {
            return instances.get_slot(log, VERBOSE);
        }
        return ZERO_INTEGER;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return keywordp ;; the reason the planner is paused.
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return keywordp ;; the reason the planner is paused.")
    public static final SubLObject cb_shop_plan_halt_status_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            SubLObject halt_status = instances.get_slot(thread, HALT_STATUS);
            if (NIL != thread) {
                return halt_status;
            } else {
                return $UNKNOWN;
            }
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     * @return keywordp ;; the reason the planner is paused.
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p\r\n\t\t\r\n@return keywordp ;; the reason the planner is paused.")
    public static SubLObject cb_shop_plan_halt_status(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        final SubLObject halt_status = instances.get_slot(thread, HALT_STATUS);
        if (NIL != thread) {
            return halt_status;
        }
        return $UNKNOWN;
    }

    public static final SubLObject cb_shop_plan_process_view_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            com.cyc.cycjava.cycl.cb_shop.clear_plan_displayers();
            com.cyc.cycjava.cycl.cb_shop.init_plan_displayers(thread);
        }
        return NIL;
    }

    public static SubLObject cb_shop_plan_process_view(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        cb_shop.clear_plan_displayers();
        cb_shop.init_plan_displayers(thread);
        return NIL;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static final SubLObject cb_shop_replan_alt(SubLObject pw) {
        {
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_thread(pw);
            SubLObject problem = instances.get_slot(thread, PROBLEM);
            SubLObject tasklist = instances.get_slot(problem, TASKS);
            SubLObject mt = instances.get_slot(problem, TASK_MT);
            if (NIL != list_utilities.singletonP(tasklist)) {
                com.cyc.cycjava.cycl.cb_shop.cb_shop_new_plan_process(tasklist.first(), mt);
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static SubLObject cb_shop_replan(final SubLObject pw) {
        final SubLObject thread = cb_shop.cb_shop_plan_process_get_thread(pw);
        final SubLObject problem = instances.get_slot(thread, PROBLEM);
        final SubLObject tasklist = instances.get_slot(problem, TASKS);
        final SubLObject mt = instances.get_slot(problem, TASK_MT);
        if (NIL != list_utilities.singletonP(tasklist)) {
            cb_shop.cb_shop_new_plan_process(tasklist.first(), mt);
        }
        return NIL;
    }

    public static final SubLObject cb_shop_information_refreshP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents($cb_shop_processes$.getDynamicValue(thread)));
                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject cur_id = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                        SubLObject cur_pw = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (NIL != process_utilities.process_wrapper_process_runningP(cur_pw)) {
                            return T;
                        } else {
                            if (NIL != process_utilities.process_wrapper_process_pausingP(cur_pw)) {
                                return T;
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

    public static SubLObject cb_shop_information_refreshP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(cb_shop.$cb_shop_processes$.getDynamicValue(thread))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject cur_id = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject cur_pw = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != process_utilities.process_wrapper_process_runningP(cur_pw)) {
                return T;
            }
            if (NIL != process_utilities.process_wrapper_process_pausingP(cur_pw)) {
                return T;
            }
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return NIL;
    }

    /**
     * Displays of table of all currently known planner threads.
     * Allows user to view progress, and halt and pause control.
     */
    @LispMethod(comment = "Displays of table of all currently known planner threads.\r\nAllows user to view progress, and halt and pause control.\nDisplays of table of all currently known planner threads.\nAllows user to view progress, and halt and pause control.")
    public static final SubLObject cb_shop_display_processes_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != dictionary.dictionary_empty_p($cb_shop_processes$.getDynamicValue(thread))) {
                html_markup(html_macros.$html_strong_head$.getGlobalValue());
                html_princ($str_alt318$No_Plans_Currently_Available_);
                html_markup(html_macros.$html_strong_tail$.getGlobalValue());
                return NIL;
            }
            html_markup(html_macros.$html_table_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_border$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(ZERO_INTEGER);
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_103 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_104 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_princ($$$Status);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_104, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_display_process_stats_header();
                            html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_105 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_princ($$$Actions);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_105, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_103, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                    {
                        SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents($cb_shop_processes$.getDynamicValue(thread)));
                        while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                            thread.resetMultipleValues();
                            {
                                SubLObject cur_id = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                SubLObject cur_pw = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                com.cyc.cycjava.cycl.cb_shop.cb_shop_display_process(cur_pw);
                                iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                            }
                        } 
                        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                    }
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_tail$.getGlobalValue());
            return NIL;
        }
    }

    @LispMethod(comment = "Displays of table of all currently known planner threads.\r\nAllows user to view progress, and halt and pause control.\nDisplays of table of all currently known planner threads.\nAllows user to view progress, and halt and pause control.")
    public static SubLObject cb_shop_display_processes() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != dictionary.dictionary_empty_p(cb_shop.$cb_shop_processes$.getDynamicValue(thread))) {
            html_markup(html_macros.$html_strong_head$.getGlobalValue());
            html_princ(cb_shop.$str328$No_Plans_Currently_Available_);
            html_markup(html_macros.$html_strong_tail$.getGlobalValue());
            return NIL;
        }
        html_markup(html_macros.$html_table_head$.getGlobalValue());
        html_markup(html_macros.$html_table_border$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$112 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$113 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ(cb_shop.$$$Status);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$113, thread);
                }
                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                cb_shop.cb_shop_display_process_stats_header();
                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$114 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ(cb_shop.$$$Actions);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$114, thread);
                }
                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$112, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            SubLObject iteration_state;
            for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(cb_shop.$cb_shop_processes$.getDynamicValue(thread))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                thread.resetMultipleValues();
                final SubLObject cur_id = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                final SubLObject cur_pw = thread.secondMultipleValue();
                thread.resetMultipleValues();
                cb_shop.cb_shop_display_process(cur_pw);
            }
            dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_tail$.getGlobalValue());
        return NIL;
    }/**
     * Displays of table of all currently known planner threads.
     * Allows user to view progress, and halt and pause control.
     */


    public static final SubLObject cb_shop_pw_func_alt(SubLObject pw, SubLObject task, SubLObject domain_mt, SubLObject verbose, SubLObject number, SubLObject time, SubLObject depth, SubLObject back_chains) {
        {
            SubLObject v_planner = object.object_new_method(CYC_DEVELOPMENT_PLANNER);
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_new_plan_thread(v_planner, verbose);
            instances.set_slot(thread, P_WRAPPER, pw);
            instances.set_slot(thread, NUM_BACKCHAINS, back_chains);
            methods.funcall_instance_method_with_1_args(v_planner, MAKE_DOMAIN_FROM_MT, domain_mt);
            shop_basic_planner_thread.shop_plan_process_set_thread(pw, thread);
            return shop_basic_planner_thread.shop_basic_planner_thread_find_plans_method(thread, task, domain_mt, $ALL, verbose, number, time, depth);
        }
    }

    public static SubLObject cb_shop_pw_func(final SubLObject pw, final SubLObject task, final SubLObject domain_mt, final SubLObject verbose, final SubLObject number, final SubLObject time, final SubLObject depth, final SubLObject back_chains) {
        final SubLObject planner = object.object_new_method(cb_shop.CYC_DEVELOPMENT_PLANNER);
        final SubLObject thread = cb_shop.cb_shop_new_plan_thread(planner, verbose);
        instances.set_slot(thread, cb_shop.P_WRAPPER, pw);
        instances.set_slot(thread, NUM_BACKCHAINS, back_chains);
        methods.funcall_instance_method_with_1_args(planner, cb_shop.MAKE_DOMAIN_FROM_MT, domain_mt);
        shop_basic_planner_thread.shop_plan_process_set_thread(pw, thread);
        return shop_basic_planner_thread.shop_basic_planner_thread_find_plans_method(thread, task, domain_mt, $ALL, verbose, number, time, depth);
    }

    /**
     *
     *
     * @param RULES
     * 		listp;; of proof cheching rules
     */
    @LispMethod(comment = "@param RULES\r\n\t\tlistp;; of proof cheching rules")
    public static final SubLObject cb_shop_pw_proof_checking_func_alt(SubLObject pw, SubLObject task, SubLObject domain_mt, SubLObject rules, SubLObject verbose, SubLObject number, SubLObject time, SubLObject depth, SubLObject back_chains) {
        {
            SubLObject v_planner = object.object_new_method(CYC_DEVELOPMENT_PLANNER);
            SubLObject thread = com.cyc.cycjava.cycl.cb_shop.cb_shop_new_plan_thread(v_planner, verbose);
            instances.set_slot(thread, P_WRAPPER, pw);
            instances.set_slot(thread, NUM_BACKCHAINS, back_chains);
            methods.funcall_instance_method_with_2_args(v_planner, MAKE_DOMAIN_WITH_RULES, rules, domain_mt);
            shop_basic_planner_thread.shop_plan_process_set_thread(pw, thread);
            return shop_basic_planner_thread.shop_basic_planner_thread_find_plans_method(thread, task, domain_mt, $ALL, verbose, number, time, depth);
        }
    }

    /**
     *
     *
     * @param RULES
     * 		listp;; of proof cheching rules
     */
    @LispMethod(comment = "@param RULES\r\n\t\tlistp;; of proof cheching rules")
    public static SubLObject cb_shop_pw_proof_checking_func(final SubLObject pw, final SubLObject task, final SubLObject domain_mt, final SubLObject rules, final SubLObject verbose, final SubLObject number, final SubLObject time, final SubLObject depth, final SubLObject back_chains) {
        final SubLObject planner = object.object_new_method(cb_shop.CYC_DEVELOPMENT_PLANNER);
        final SubLObject thread = cb_shop.cb_shop_new_plan_thread(planner, verbose);
        instances.set_slot(thread, cb_shop.P_WRAPPER, pw);
        instances.set_slot(thread, NUM_BACKCHAINS, back_chains);
        methods.funcall_instance_method_with_2_args(planner, cb_shop.MAKE_DOMAIN_WITH_RULES, rules, domain_mt);
        shop_basic_planner_thread.shop_plan_process_set_thread(pw, thread);
        return shop_basic_planner_thread.shop_basic_planner_thread_find_plans_method(thread, task, domain_mt, $ALL, verbose, number, time, depth);
    }

    /**
     *
     *
     * @param TASK
    el-formula-p
     * 		
     * @param MT
    hlmt-p
     * 		
     */
    @LispMethod(comment = "@param TASK\nel-formula-p\r\n\t\t\r\n@param MT\nhlmt-p")
    public static final SubLObject cb_shop_new_plan_process_alt(SubLObject task, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!((NIL != task) && (NIL != mt))) {
                return NIL;
            }
            if (NIL != com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_checking_enabled()) {
                {
                    SubLObject rules = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_checking_rules();
                    {
                        SubLObject _prev_bind_0 = shop_parameters.$shop_method_cycle_checking_enabledP$.currentBinding(thread);
                        SubLObject _prev_bind_1 = shop_parameters.$shop_default_depth_limit$.currentBinding(thread);
                        try {
                            shop_parameters.$shop_method_cycle_checking_enabledP$.bind($cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), thread);
                            shop_parameters.$shop_default_depth_limit$.bind($cb_shop_depth_limit$.getDynamicValue(thread), thread);
                            {
                                SubLObject pw = process_utilities.new_process_wrapper($cb_shop_process_label$.getGlobalValue(), CB_SHOP_PW_PROOF_CHECKING_FUNC, list(task, mt, rules, $cb_shop_verbosity$.getDynamicValue(thread), $cb_last_planner_number$.getDynamicValue(thread), $cb_shop_time_cutoff$.getDynamicValue(thread), $cb_shop_depth_limit$.getDynamicValue(thread), $cb_shop_num_backchains$.getDynamicValue(thread)));
                                SubLObject id = process_utilities.process_wrapper_get_id(pw);
                                dictionary.dictionary_enter($cb_shop_processes$.getDynamicValue(thread), id, pw);
                            }
                        } finally {
                            shop_parameters.$shop_default_depth_limit$.rebind(_prev_bind_1, thread);
                            shop_parameters.$shop_method_cycle_checking_enabledP$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
            } else {
                {
                    SubLObject _prev_bind_0 = shop_parameters.$shop_method_cycle_checking_enabledP$.currentBinding(thread);
                    SubLObject _prev_bind_1 = shop_parameters.$shop_default_depth_limit$.currentBinding(thread);
                    try {
                        shop_parameters.$shop_method_cycle_checking_enabledP$.bind($cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), thread);
                        shop_parameters.$shop_default_depth_limit$.bind($cb_shop_depth_limit$.getDynamicValue(thread), thread);
                        {
                            SubLObject pw = process_utilities.new_process_wrapper($cb_shop_process_label$.getGlobalValue(), CB_SHOP_PW_FUNC, list(task, mt, $cb_shop_verbosity$.getDynamicValue(thread), $cb_last_planner_number$.getDynamicValue(thread), $cb_shop_time_cutoff$.getDynamicValue(thread), $cb_shop_depth_limit$.getDynamicValue(thread), $cb_shop_num_backchains$.getDynamicValue(thread)));
                            SubLObject id = process_utilities.process_wrapper_get_id(pw);
                            dictionary.dictionary_enter($cb_shop_processes$.getDynamicValue(thread), id, pw);
                        }
                    } finally {
                        shop_parameters.$shop_default_depth_limit$.rebind(_prev_bind_1, thread);
                        shop_parameters.$shop_method_cycle_checking_enabledP$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param TASK
    el-formula-p
     * 		
     * @param MT
    hlmt-p
     * 		
     */
    @LispMethod(comment = "@param TASK\nel-formula-p\r\n\t\t\r\n@param MT\nhlmt-p")
    public static SubLObject cb_shop_new_plan_process(final SubLObject task, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == task) || (NIL == mt)) {
            return NIL;
        }
        if (NIL != cb_shop.cb_shop_plan_checking_enabled()) {
            final SubLObject rules = cb_shop.cb_shop_plan_checking_rules();
            final SubLObject _prev_bind_0 = shop_parameters.$shop_method_cycle_checking_enabledP$.currentBinding(thread);
            final SubLObject _prev_bind_2 = shop_parameters.$shop_default_depth_limit$.currentBinding(thread);
            try {
                shop_parameters.$shop_method_cycle_checking_enabledP$.bind(cb_shop.$cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), thread);
                shop_parameters.$shop_default_depth_limit$.bind(cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread), thread);
                final SubLObject pw = process_utilities.new_process_wrapper(cb_shop.$cb_shop_process_label$.getGlobalValue(), cb_shop.CB_SHOP_PW_PROOF_CHECKING_FUNC, list(task, mt, rules, cb_shop.$cb_shop_verbosity$.getDynamicValue(thread), cb_shop.$cb_last_planner_number$.getDynamicValue(thread), cb_shop.$cb_shop_time_cutoff$.getDynamicValue(thread), cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread), cb_shop.$cb_shop_num_backchains$.getDynamicValue(thread)));
                final SubLObject id = process_utilities.process_wrapper_get_id(pw);
                dictionary.dictionary_enter(cb_shop.$cb_shop_processes$.getDynamicValue(thread), id, pw);
            } finally {
                shop_parameters.$shop_default_depth_limit$.rebind(_prev_bind_2, thread);
                shop_parameters.$shop_method_cycle_checking_enabledP$.rebind(_prev_bind_0, thread);
            }
        } else {
            final SubLObject _prev_bind_3 = shop_parameters.$shop_method_cycle_checking_enabledP$.currentBinding(thread);
            final SubLObject _prev_bind_4 = shop_parameters.$shop_default_depth_limit$.currentBinding(thread);
            try {
                shop_parameters.$shop_method_cycle_checking_enabledP$.bind(cb_shop.$cb_shop_cycle_checking_enabledP$.getDynamicValue(thread), thread);
                shop_parameters.$shop_default_depth_limit$.bind(cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread), thread);
                final SubLObject pw2 = process_utilities.new_process_wrapper(cb_shop.$cb_shop_process_label$.getGlobalValue(), cb_shop.CB_SHOP_PW_FUNC, list(task, mt, cb_shop.$cb_shop_verbosity$.getDynamicValue(thread), cb_shop.$cb_last_planner_number$.getDynamicValue(thread), cb_shop.$cb_shop_time_cutoff$.getDynamicValue(thread), cb_shop.$cb_shop_depth_limit$.getDynamicValue(thread), cb_shop.$cb_shop_num_backchains$.getDynamicValue(thread)));
                final SubLObject id2 = process_utilities.process_wrapper_get_id(pw2);
                dictionary.dictionary_enter(cb_shop.$cb_shop_processes$.getDynamicValue(thread), id2, pw2);
            } finally {
                shop_parameters.$shop_default_depth_limit$.rebind(_prev_bind_4, thread);
                shop_parameters.$shop_method_cycle_checking_enabledP$.rebind(_prev_bind_3, thread);
            }
        }
        return NIL;
    }

    public static final SubLObject cb_shop_display_process_stats_header_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_header_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_data_width$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup($int$30);
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ($$$Task);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_header_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ($$$Open_Nodes);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_header_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ($$$Inferences);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_header_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ($$$Expansions);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_header_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ($$$Plans);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
            return NIL;
        }
    }

    public static SubLObject cb_shop_display_process_stats_header() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_header_head$.getGlobalValue());
        html_markup(html_macros.$html_table_data_width$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(cb_shop.$int$30);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$$$Task);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_header_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$$$Open_Nodes);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_header_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$$$Inferences);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_header_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$$$Expansions);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_header_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.$$$Plans);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
        return NIL;
    }

    /**
     * Displays part of the summary table for each PW.
     * Abstracted out here to aid maintenance and to ensure
     * all stats look the same in table.
     */
    @LispMethod(comment = "Displays part of the summary table for each PW.\r\nAbstracted out here to aid maintenance and to ensure\r\nall stats look the same in table.\nDisplays part of the summary table for each PW.\nAbstracted out here to aid maintenance and to ensure\nall stats look the same in table.")
    public static final SubLObject cb_shop_display_process_stats_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    {
                        SubLObject task = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_get_task(pw);
                        if (task.isString()) {
                            html_princ(task);
                        } else {
                            cb_form(task, UNPROVIDED, UNPROVIDED);
                        }
                    }
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($RIGHT));
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_num_open_nodes(pw));
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($RIGHT));
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_num_inferences(pw));
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($RIGHT));
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_num_expansions(pw));
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($RIGHT));
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ(com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_num_plans(pw));
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            return NIL;
        }
    }

    @LispMethod(comment = "Displays part of the summary table for each PW.\r\nAbstracted out here to aid maintenance and to ensure\r\nall stats look the same in table.\nDisplays part of the summary table for each PW.\nAbstracted out here to aid maintenance and to ensure\nall stats look the same in table.")
    public static SubLObject cb_shop_display_process_stats(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            final SubLObject task = cb_shop.cb_shop_plan_process_get_task(pw);
            if (task.isString()) {
                html_princ(task);
            } else {
                cb_form(task, UNPROVIDED, UNPROVIDED);
            }
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_align($RIGHT));
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.cb_shop_plan_process_num_open_nodes(pw));
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_align($RIGHT));
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.cb_shop_plan_process_num_inferences(pw));
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_align($RIGHT));
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.cb_shop_plan_process_num_expansions(pw));
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_align($RIGHT));
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_princ(cb_shop.cb_shop_plan_process_num_plans(pw));
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        return NIL;
    }/**
     * Displays part of the summary table for each PW.
     * Abstracted out here to aid maintenance and to ensure
     * all stats look the same in table.
     */


    /**
     * Write to the HTML stream a description of the halt
     * status of the SHOP thread running under PW.
     */
    @LispMethod(comment = "Write to the HTML stream a description of the halt\r\nstatus of the SHOP thread running under PW.\nWrite to the HTML stream a description of the halt\nstatus of the SHOP thread running under PW.")
    public static final SubLObject cb_shop_display_process_halt_status_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject color_val = $RED;
                html_markup(html_macros.$html_font_head$.getGlobalValue());
                if (NIL != color_val) {
                    html_markup(html_macros.$html_font_color$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_color(color_val));
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        {
                            SubLObject pcase_var = com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_halt_status(pw);
                            if (pcase_var.eql($DEPTH_CUTOFF)) {
                                html_princ($$$Depth_Cutoff);
                            } else {
                                if (pcase_var.eql($NUMBER_CUTOFF)) {
                                    html_princ($$$Number_Cutoff);
                                } else {
                                    if (pcase_var.eql($TIME_CUTOFF)) {
                                        html_princ($$$Time_Cutoff);
                                    } else {
                                        if (NIL != process_utilities.process_wrapper_process_pausedP(pw)) {
                                            html_princ($$$Paused_By_User);
                                        }
                                    }
                                }
                            }
                        }
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_font_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    @LispMethod(comment = "Write to the HTML stream a description of the halt\r\nstatus of the SHOP thread running under PW.\nWrite to the HTML stream a description of the halt\nstatus of the SHOP thread running under PW.")
    public static SubLObject cb_shop_display_process_halt_status(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject color_val = $RED;
        html_markup(html_macros.$html_font_head$.getGlobalValue());
        if (NIL != color_val) {
            html_markup(html_macros.$html_font_color$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(html_color(color_val));
            html_char(CHAR_quotation, UNPROVIDED);
        }
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            final SubLObject pcase_var = cb_shop.cb_shop_plan_halt_status(pw);
            if (pcase_var.eql($DEPTH_CUTOFF)) {
                html_princ(cb_shop.$$$Depth_Cutoff);
            } else
                if (pcase_var.eql($NUMBER_CUTOFF)) {
                    html_princ(cb_shop.$$$Number_Cutoff);
                } else
                    if (pcase_var.eql($TIME_CUTOFF)) {
                        html_princ(cb_shop.$$$Time_Cutoff);
                    } else
                        if (NIL != process_utilities.process_wrapper_process_pausedP(pw)) {
                            html_princ(cb_shop.$$$Paused_By_User);
                        }



        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_font_tail$.getGlobalValue());
        return NIL;
    }/**
     * Write to the HTML stream a description of the halt
     * status of the SHOP thread running under PW.
     */


    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static final SubLObject cb_shop_display_process_alt(SubLObject pw) {
        if (NIL != process_utilities.process_wrapper_process_runningP(pw)) {
            com.cyc.cycjava.cycl.cb_shop.cb_shop_display_running_process(pw);
        } else {
            if (NIL != process_utilities.process_wrapper_process_pausingP(pw)) {
                com.cyc.cycjava.cycl.cb_shop.cb_shop_display_pausing_process(pw);
            } else {
                if (NIL != process_utilities.process_wrapper_process_pausedP(pw)) {
                    com.cyc.cycjava.cycl.cb_shop.cb_shop_display_paused_process(pw);
                } else {
                    if (NIL != process_utilities.process_wrapper_process_completedP(pw)) {
                        com.cyc.cycjava.cycl.cb_shop.cb_shop_display_completed_process(pw);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static SubLObject cb_shop_display_process(final SubLObject pw) {
        if (NIL != process_utilities.process_wrapper_process_runningP(pw)) {
            cb_shop.cb_shop_display_running_process(pw);
        } else
            if (NIL != process_utilities.process_wrapper_process_pausingP(pw)) {
                cb_shop.cb_shop_display_pausing_process(pw);
            } else
                if (NIL != process_utilities.process_wrapper_process_pausedP(pw)) {
                    cb_shop.cb_shop_display_paused_process(pw);
                } else
                    if (NIL != process_utilities.process_wrapper_process_completedP(pw)) {
                        cb_shop.cb_shop_display_completed_process(pw);
                    }



        return NIL;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static final SubLObject cb_shop_display_completed_process_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_106 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ($$$Completed);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_106, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                    com.cyc.cycjava.cycl.cb_shop.cb_shop_display_process_stats(pw);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_107 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            if (com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_num_plans(pw).numG(ZERO_INTEGER)) {
                                com.cyc.cycjava.cycl.cb_shop.cb_shop_view_link(pw);
                                html_indent(TWO_INTEGER);
                            }
                            if (com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_verbosity(pw).numG(ZERO_INTEGER)) {
                                com.cyc.cycjava.cycl.cb_shop.cb_shop_verbose_link(pw);
                                html_indent(TWO_INTEGER);
                            }
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_delete_link(pw);
                            html_indent(TWO_INTEGER);
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_replan_link(pw);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_107, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            return NIL;
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static SubLObject cb_shop_display_completed_process(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$115 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_princ(cb_shop.$$$Completed);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$115, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            cb_shop.cb_shop_display_process_stats(pw);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$116 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                if (cb_shop.cb_shop_plan_process_num_plans(pw).numG(ZERO_INTEGER)) {
                    cb_shop.cb_shop_view_link(pw);
                    html_indent(TWO_INTEGER);
                }
                if (cb_shop.cb_shop_plan_process_verbosity(pw).numG(ZERO_INTEGER)) {
                    cb_shop.cb_shop_verbose_link(pw);
                    html_indent(TWO_INTEGER);
                }
                cb_shop.cb_shop_delete_link(pw);
                html_indent(TWO_INTEGER);
                cb_shop.cb_shop_replan_link(pw);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$116, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
        html_source_readability_terpri(UNPROVIDED);
        return NIL;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static final SubLObject cb_shop_display_pausing_process_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_108 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ($$$Pausing);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_108, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                    com.cyc.cycjava.cycl.cb_shop.cb_shop_display_process_stats(pw);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_109 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_resume_link(pw);
                            html_indent(TWO_INTEGER);
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_abort_link(pw);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_109, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            return NIL;
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static SubLObject cb_shop_display_pausing_process(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$117 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_princ(cb_shop.$$$Pausing);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$117, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            cb_shop.cb_shop_display_process_stats(pw);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$118 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                cb_shop.cb_shop_resume_link(pw);
                html_indent(TWO_INTEGER);
                cb_shop.cb_shop_abort_link(pw);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$118, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
        html_source_readability_terpri(UNPROVIDED);
        return NIL;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static final SubLObject cb_shop_display_paused_process_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_110 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_display_process_halt_status(pw);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_110, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                    com.cyc.cycjava.cycl.cb_shop.cb_shop_display_process_stats(pw);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_111 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            if (com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_num_plans(pw).numG(ZERO_INTEGER)) {
                                com.cyc.cycjava.cycl.cb_shop.cb_shop_view_link(pw);
                                html_indent(TWO_INTEGER);
                            }
                            if (com.cyc.cycjava.cycl.cb_shop.cb_shop_plan_process_verbosity(pw).numG(ZERO_INTEGER)) {
                                com.cyc.cycjava.cycl.cb_shop.cb_shop_verbose_link(pw);
                                html_indent(TWO_INTEGER);
                            }
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_resume_link(pw);
                            html_indent(TWO_INTEGER);
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_abort_link(pw);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_111, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            return NIL;
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static SubLObject cb_shop_display_paused_process(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$119 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                cb_shop.cb_shop_display_process_halt_status(pw);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$119, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            cb_shop.cb_shop_display_process_stats(pw);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$120 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                if (cb_shop.cb_shop_plan_process_num_plans(pw).numG(ZERO_INTEGER)) {
                    cb_shop.cb_shop_view_link(pw);
                    html_indent(TWO_INTEGER);
                }
                if (cb_shop.cb_shop_plan_process_verbosity(pw).numG(ZERO_INTEGER)) {
                    cb_shop.cb_shop_verbose_link(pw);
                    html_indent(TWO_INTEGER);
                }
                cb_shop.cb_shop_resume_link(pw);
                html_indent(TWO_INTEGER);
                cb_shop.cb_shop_abort_link(pw);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$120, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
        html_source_readability_terpri(UNPROVIDED);
        return NIL;
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static final SubLObject cb_shop_display_running_process_alt(SubLObject pw) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_112 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_princ($$$Running);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_112, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                    com.cyc.cycjava.cycl.cb_shop.cb_shop_display_process_stats(pw);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0_113 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_pause_link(pw);
                            html_indent(TWO_INTEGER);
                            com.cyc.cycjava.cycl.cb_shop.cb_shop_abort_link(pw);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_113, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            return NIL;
        }
    }

    /**
     *
     *
     * @param PW
    process-wrapper-p
     * 		
     */
    @LispMethod(comment = "@param PW\nprocess-wrapper-p")
    public static SubLObject cb_shop_display_running_process(final SubLObject pw) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$121 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_princ(cb_shop.$$$Running);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$121, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            cb_shop.cb_shop_display_process_stats(pw);
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$122 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                cb_shop.cb_shop_pause_link(pw);
                html_indent(TWO_INTEGER);
                cb_shop.cb_shop_abort_link(pw);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$122, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
        html_source_readability_terpri(UNPROVIDED);
        return NIL;
    }

    /**
     * Initializes the interface objects that govern the display of planner
     * results. Creates a HTML-PLAN-DISPLAYER object for each plan found, and
     * a HTML-TASK-DISPLAYER object for each task in each plan.
     */
    @LispMethod(comment = "Initializes the interface objects that govern the display of planner\r\nresults. Creates a HTML-PLAN-DISPLAYER object for each plan found, and\r\na HTML-TASK-DISPLAYER object for each task in each plan.\nInitializes the interface objects that govern the display of planner\nresults. Creates a HTML-PLAN-DISPLAYER object for each plan found, and\na HTML-TASK-DISPLAYER object for each task in each plan.")
    public static final SubLObject init_plan_displayers_alt(SubLObject thread) {
        {
            final SubLThread thread_114 = SubLProcess.currentSubLThread();
            {
                SubLObject plans = methods.funcall_instance_method_with_0_args(thread, CYCL_PLANS);
                SubLObject v_planner = instances.get_slot(thread, PLANNER);
                SubLObject cdolist_list_var = plans;
                SubLObject plan = NIL;
                for (plan = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , plan = cdolist_list_var.first()) {
                    $cb_plan_displayers$.setDynamicValue(cons(methods.funcall_class_method_with_1_args(shop_main.get_shop_displayer_factory(v_planner), CREATE_PLAN_DISPLAYER, plan), $cb_plan_displayers$.getDynamicValue(thread_114)), thread_114);
                }
                $cb_plan_displayers$.setDynamicValue(reverse($cb_plan_displayers$.getDynamicValue(thread_114)), thread_114);
                $cb_shop_planner_thread$.setDynamicValue(thread, thread_114);
                return NIL;
            }
        }
    }

    @LispMethod(comment = "Initializes the interface objects that govern the display of planner\r\nresults. Creates a HTML-PLAN-DISPLAYER object for each plan found, and\r\na HTML-TASK-DISPLAYER object for each task in each plan.\nInitializes the interface objects that govern the display of planner\nresults. Creates a HTML-PLAN-DISPLAYER object for each plan found, and\na HTML-TASK-DISPLAYER object for each task in each plan.")
    public static SubLObject init_plan_displayers(final SubLObject thread) {
        final SubLThread thread_$123 = SubLProcess.currentSubLThread();
        final SubLObject plans = methods.funcall_instance_method_with_0_args(thread, cb_shop.CYCL_PLANS);
        final SubLObject planner = instances.get_slot(thread, PLANNER);
        SubLObject cdolist_list_var = plans;
        SubLObject plan = NIL;
        plan = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            cb_shop.$cb_plan_displayers$.setDynamicValue(cons(methods.funcall_class_method_with_1_args(shop_main.get_shop_displayer_factory(planner), cb_shop.CREATE_PLAN_DISPLAYER, plan), cb_shop.$cb_plan_displayers$.getDynamicValue(thread_$123)), thread_$123);
            cdolist_list_var = cdolist_list_var.rest();
            plan = cdolist_list_var.first();
        } 
        cb_shop.$cb_plan_displayers$.setDynamicValue(reverse(cb_shop.$cb_plan_displayers$.getDynamicValue(thread_$123)), thread_$123);
        cb_shop.$cb_shop_planner_thread$.setDynamicValue(thread, thread_$123);
        return NIL;
    }/**
     * Initializes the interface objects that govern the display of planner
     * results. Creates a HTML-PLAN-DISPLAYER object for each plan found, and
     * a HTML-TASK-DISPLAYER object for each task in each plan.
     */


    /**
     * Used to ensure that pointers to old plan run data do
     * not persist in the interface state when the planner is
     * run again.
     */
    @LispMethod(comment = "Used to ensure that pointers to old plan run data do\r\nnot persist in the interface state when the planner is\r\nrun again.\nUsed to ensure that pointers to old plan run data do\nnot persist in the interface state when the planner is\nrun again.")
    public static final SubLObject clear_plan_displayers_alt() {
        $cb_plan_displayers$.setDynamicValue(NIL);
        return NIL;
    }

    @LispMethod(comment = "Used to ensure that pointers to old plan run data do\r\nnot persist in the interface state when the planner is\r\nrun again.\nUsed to ensure that pointers to old plan run data do\nnot persist in the interface state when the planner is\nrun again.")
    public static SubLObject clear_plan_displayers() {
        cb_shop.$cb_plan_displayers$.setDynamicValue(NIL);
        return NIL;
    }/**
     * Used to ensure that pointers to old plan run data do
     * not persist in the interface state when the planner is
     * run again.
     */


    public static SubLObject declare_cb_shop_file() {
        declareMacro("with_cb_shop_defaults", "WITH-CB-SHOP-DEFAULTS");
        declareFunction("cb_shop_plan_checking_enabled", "CB-SHOP-PLAN-CHECKING-ENABLED", 0, 0, false);
        declareFunction("cb_shop_enable_plan_checking", "CB-SHOP-ENABLE-PLAN-CHECKING", 0, 0, false);
        declareFunction("cb_shop_disable_plan_checking", "CB-SHOP-DISABLE-PLAN-CHECKING", 0, 0, false);
        declareFunction("cb_shop_plan_checking_rules", "CB-SHOP-PLAN-CHECKING-RULES", 0, 0, false);
        declareFunction("cb_shop_add_to_plan_checking_rules", "CB-SHOP-ADD-TO-PLAN-CHECKING-RULES", 1, 0, false);
        declareFunction("cb_shop_remove_from_plan_checking_rules", "CB-SHOP-REMOVE-FROM-PLAN-CHECKING-RULES", 1, 0, false);
        declareFunction("cb_shop_clear_all_plan_checking_rules", "CB-SHOP-CLEAR-ALL-PLAN-CHECKING-RULES", 0, 0, false);
        declareFunction("cb_shop_use_in_plan_checking", "CB-SHOP-USE-IN-PLAN-CHECKING", 1, 0, false);
        declareFunction("cb_link_use_in_plan_checking", "CB-LINK-USE-IN-PLAN-CHECKING", 1, 1, false);
        declareFunction("cb_shop_plan_checking", "CB-SHOP-PLAN-CHECKING", 1, 0, false);
        declareFunction("cb_link_plan_checking", "CB-LINK-PLAN-CHECKING", 0, 1, false);
        declareFunction("cb_shop_gather_simple_action_rules_in_proof_checker", "CB-SHOP-GATHER-SIMPLE-ACTION-RULES-IN-PROOF-CHECKER", 1, 0, false);
        declareFunction("subloop_reserved_initialize_cyc_development_planner_class", "SUBLOOP-RESERVED-INITIALIZE-CYC-DEVELOPMENT-PLANNER-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_cyc_development_planner_instance", "SUBLOOP-RESERVED-INITIALIZE-CYC-DEVELOPMENT-PLANNER-INSTANCE", 1, 0, false);
        declareFunction("cyc_development_planner_p", "CYC-DEVELOPMENT-PLANNER-P", 1, 0, false);
        declareFunction("cyc_development_planner_initialize_method", "CYC-DEVELOPMENT-PLANNER-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("cyc_development_planner_make_domain_with_rules_method", "CYC-DEVELOPMENT-PLANNER-MAKE-DOMAIN-WITH-RULES-METHOD", 3, 0, false);
        declareFunction("cyc_development_planner_make_domain_from_proof_checker_method", "CYC-DEVELOPMENT-PLANNER-MAKE-DOMAIN-FROM-PROOF-CHECKER-METHOD", 2, 0, false);
        declareFunction("plan_finder", "PLAN-FINDER", 0, 1, false);
        declareFunction("plan_examiner", "PLAN-EXAMINER", 0, 1, false);
        declareFunction("plan_verbose_examiner", "PLAN-VERBOSE-EXAMINER", 0, 1, false);
        declareFunction("plan_task_examiner", "PLAN-TASK-EXAMINER", 0, 1, false);
        declareFunction("cb_link_cb_shop_summary", "CB-LINK-CB-SHOP-SUMMARY", 0, 1, false);
        declareFunction("cb_link_cb_shop_controls", "CB-LINK-CB-SHOP-CONTROLS", 0, 1, false);
        declareFunction("cb_link_planner", "CB-LINK-PLANNER", 0, 1, false);
        declareFunction("cb_link_plan_examiner", "CB-LINK-PLAN-EXAMINER", 0, 1, false);
        declareFunction("cb_link_plan_verbose_examiner", "CB-LINK-PLAN-VERBOSE-EXAMINER", 0, 1, false);
        declareFunction("clear_cb_planner_warning_message", "CLEAR-CB-PLANNER-WARNING-MESSAGE", 0, 0, false);
        declareFunction("cb_shop_controls", "CB-SHOP-CONTROLS", 1, 0, false);
        declareFunction("cb_shop_information", "CB-SHOP-INFORMATION", 1, 0, false);
        declareFunction("plan_finder_internal", "PLAN-FINDER-INTERNAL", 1, 0, false);
        declareFunction("extract_settings", "EXTRACT-SETTINGS", 1, 0, false);
        declareFunction("extract_advanced_settings", "EXTRACT-ADVANCED-SETTINGS", 1, 0, false);
        declareFunction("cb_shop_extract_problem", "CB-SHOP-EXTRACT-PROBLEM", 1, 0, false);
        declareFunction("cb_shop_new_plan_thread", "CB-SHOP-NEW-PLAN-THREAD", 2, 0, false);
        declareFunction("initialize_planner_thread", "INITIALIZE-PLANNER-THREAD", 0, 0, false);
        declareFunction("invalid_mt_warning_string", "INVALID-MT-WARNING-STRING", 2, 0, false);
        declareFunction("cb_shop_new_task", "CB-SHOP-NEW-TASK", 1, 0, false);
        declareFunction("plan_finder_continue_task", "PLAN-FINDER-CONTINUE-TASK", 0, 0, false);
        declareFunction("plan_finder_change_advanced_parameters", "PLAN-FINDER-CHANGE-ADVANCED-PARAMETERS", 1, 0, false);
        declareFunction("show_last_task", "SHOW-LAST-TASK", 3, 0, false);
        declareFunction("cb_planner_display_halt_reason", "CB-PLANNER-DISPLAY-HALT-REASON", 1, 0, false);
        declareFunction("summarize_last_task", "SUMMARIZE-LAST-TASK", 0, 1, false);
        declareFunction("cb_shop_ask_for_new_task", "CB-SHOP-ASK-FOR-NEW-TASK", 1, 0, false);
        declareFunction("cb_shop_domain_examiner", "CB-SHOP-DOMAIN-EXAMINER", 1, 0, false);
        declareFunction("plan_verbose_examiner_internal", "PLAN-VERBOSE-EXAMINER-INTERNAL", 1, 0, false);
        declareFunction("plan_examiner_internal", "PLAN-EXAMINER-INTERNAL", 1, 0, false);
        declareFunction("handle_plan_examiner_input", "HANDLE-PLAN-EXAMINER-INPUT", 1, 0, false);
        declareFunction("cb_plan_examiner_controls", "CB-PLAN-EXAMINER-CONTROLS", 0, 0, false);
        declareFunction("cb_advanced_planner_settings_table", "CB-ADVANCED-PLANNER-SETTINGS-TABLE", 1, 0, false);
        declareFunction("find_keyword_for_search_type", "FIND-KEYWORD-FOR-SEARCH-TYPE", 1, 0, false);
        declareFunction("plan_task_examiner_internal", "PLAN-TASK-EXAMINER-INTERNAL", 1, 0, false);
        declareFunction("display_html_plans", "DISPLAY-HTML-PLANS", 2, 0, false);
        declareFunction("cb_shop_abort_link_handler", "CB-SHOP-ABORT-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_abort_link", "CB-SHOP-ABORT-LINK", 1, 0, false);
        declareFunction("cb_shop_pause_link_handler", "CB-SHOP-PAUSE-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_pause_link", "CB-SHOP-PAUSE-LINK", 1, 0, false);
        declareFunction("cb_shop_resume_link_handler", "CB-SHOP-RESUME-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_resume_link", "CB-SHOP-RESUME-LINK", 1, 0, false);
        declareFunction("cb_shop_view_link_handler", "CB-SHOP-VIEW-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_view_link", "CB-SHOP-VIEW-LINK", 1, 0, false);
        declareFunction("cb_shop_replan_link_handler", "CB-SHOP-REPLAN-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_replan_link", "CB-SHOP-REPLAN-LINK", 1, 0, false);
        declareFunction("cb_shop_verbose_link_handler", "CB-SHOP-VERBOSE-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_verbose_link", "CB-SHOP-VERBOSE-LINK", 1, 0, false);
        declareFunction("cb_shop_delete_link_handler", "CB-SHOP-DELETE-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_delete_link", "CB-SHOP-DELETE-LINK", 1, 0, false);
        declareFunction("cb_shop_domain_examiner_link_handler", "CB-SHOP-DOMAIN-EXAMINER-LINK-HANDLER", 1, 0, false);
        declareFunction("cb_shop_domain_examiner_link", "CB-SHOP-DOMAIN-EXAMINER-LINK", 1, 0, false);
        declareFunction("cb_shop_plan_process_get_thread", "CB-SHOP-PLAN-PROCESS-GET-THREAD", 1, 0, false);
        declareFunction("cb_shop_plan_process_set_planner", "CB-SHOP-PLAN-PROCESS-SET-PLANNER", 2, 0, false);
        declareFunction("cb_shop_plan_process_get_task", "CB-SHOP-PLAN-PROCESS-GET-TASK", 1, 0, false);
        declareFunction("cb_shop_plan_process_get_domain", "CB-SHOP-PLAN-PROCESS-GET-DOMAIN", 1, 0, false);
        declareFunction("cb_shop_plan_process_num_open_nodes", "CB-SHOP-PLAN-PROCESS-NUM-OPEN-NODES", 1, 0, false);
        declareFunction("cb_shop_plan_process_num_inferences", "CB-SHOP-PLAN-PROCESS-NUM-INFERENCES", 1, 0, false);
        declareFunction("cb_shop_plan_process_num_expansions", "CB-SHOP-PLAN-PROCESS-NUM-EXPANSIONS", 1, 0, false);
        declareFunction("cb_shop_plan_process_num_plans", "CB-SHOP-PLAN-PROCESS-NUM-PLANS", 1, 0, false);
        declareFunction("cb_shop_plan_process_verbosity", "CB-SHOP-PLAN-PROCESS-VERBOSITY", 1, 0, false);
        declareFunction("cb_shop_plan_halt_status", "CB-SHOP-PLAN-HALT-STATUS", 1, 0, false);
        declareFunction("cb_shop_plan_process_view", "CB-SHOP-PLAN-PROCESS-VIEW", 1, 0, false);
        declareFunction("cb_shop_replan", "CB-SHOP-REPLAN", 1, 0, false);
        declareFunction("cb_shop_information_refreshP", "CB-SHOP-INFORMATION-REFRESH?", 0, 0, false);
        declareFunction("cb_shop_display_processes", "CB-SHOP-DISPLAY-PROCESSES", 0, 0, false);
        declareFunction("cb_shop_pw_func", "CB-SHOP-PW-FUNC", 8, 0, false);
        declareFunction("cb_shop_pw_proof_checking_func", "CB-SHOP-PW-PROOF-CHECKING-FUNC", 9, 0, false);
        declareFunction("cb_shop_new_plan_process", "CB-SHOP-NEW-PLAN-PROCESS", 2, 0, false);
        declareFunction("cb_shop_display_process_stats_header", "CB-SHOP-DISPLAY-PROCESS-STATS-HEADER", 0, 0, false);
        declareFunction("cb_shop_display_process_stats", "CB-SHOP-DISPLAY-PROCESS-STATS", 1, 0, false);
        declareFunction("cb_shop_display_process_halt_status", "CB-SHOP-DISPLAY-PROCESS-HALT-STATUS", 1, 0, false);
        declareFunction("cb_shop_display_process", "CB-SHOP-DISPLAY-PROCESS", 1, 0, false);
        declareFunction("cb_shop_display_completed_process", "CB-SHOP-DISPLAY-COMPLETED-PROCESS", 1, 0, false);
        declareFunction("cb_shop_display_pausing_process", "CB-SHOP-DISPLAY-PAUSING-PROCESS", 1, 0, false);
        declareFunction("cb_shop_display_paused_process", "CB-SHOP-DISPLAY-PAUSED-PROCESS", 1, 0, false);
        declareFunction("cb_shop_display_running_process", "CB-SHOP-DISPLAY-RUNNING-PROCESS", 1, 0, false);
        declareFunction("init_plan_displayers", "INIT-PLAN-DISPLAYERS", 1, 0, false);
        declareFunction("clear_plan_displayers", "CLEAR-PLAN-DISPLAYERS", 0, 0, false);
        return NIL;
    }

    public static SubLObject init_cb_shop_file() {
        defparameter("*CYC-DEVELOPMENT-ENVIRONMENT*", T);
        defparameter("*CB-PLANNER-DEFAULT-DOMAIN-MT*", NIL);
        defparameter("*CB-SHOP-PLANNER*", NIL);
        defparameter("*CB-SHOP-TASK-STRING*", NIL);
        defparameter("*CB-PLANNER-WARNING-MESSAGE*", NIL);
        defparameter("*CB-SHOP-PLANNER-THREAD*", NIL);
        defparameter("*CB-PLANNER-TASK*", NIL);
        defparameter("*CB-PLANNER-DOMAIN-MT*", cb_shop.$cb_planner_default_domain_mt$.getDynamicValue());
        defparameter("*CB-LAST-PLANNER-FORMAT*", $CYCL);
        defparameter("*CB-LAST-PLANNER-STRUCTURE*", $HIERARCHICAL);
        defparameter("*CB-LAST-PLANNER-NUMBER*", NIL);
        defparameter("*CB-SHOP-TIME-CUTOFF*", cb_shop.$int$500);
        defparameter("*CB-SHOP-DEPTH-LIMIT*", cb_shop.$int$75);
        defparameter("*CB-SHOP-NUM-BACKCHAINS*", ZERO_INTEGER);
        defparameter("*CB-LAST-PLANNER-DO-FI-ASKS*", NIL);
        defparameter("*CB-SHOP-CYCLE-CHECKING-ENABLED?*", T);
        defparameter("*CB-LAST-PLANNER-SHOW-ADVANCED-SETTINGS*", NIL);
        defparameter("*CB-PLAN-SEARCH-TYPE*", $ALL);
        defparameter("*CB-SHOP-VERBOSITY*", NINE_INTEGER);
        defparameter("*CB-PLAN-DISPLAYERS*", NIL);
        defparameter("*CB-SHOP-PLAN-CHECKING-ENABLED*", NIL);
        defparameter("*CB-SHOP-PLAN-CHECKING-RULES*", NIL);
        deflexical("*CB-SHOP-REFRESH-RATE*", TWO_INTEGER);
        deflexical("*CB-SHOP-PROCESS-LABEL*", cb_shop.$$$CB_SHOP_PLANNING);
        defparameter("*CB-SHOP-PROCESSES*", dictionary.new_dictionary(symbol_function(EQL), TEN_INTEGER));
        return NIL;
    }

    public static final SubLObject setup_cb_shop_file_alt() {
        register_html_state_variable($cb_shop_task_string$);
        register_html_state_variable($cb_planner_warning_message$);
        register_html_state_variable($cb_shop_planner_thread$);
        register_html_state_variable($cb_planner_task$);
        register_html_state_variable($cb_planner_domain_mt$);
        register_html_state_variable($cb_last_planner_format$);
        register_html_state_variable($cb_last_planner_structure$);
        register_html_state_variable($cb_last_planner_number$);
        register_html_state_variable($cb_shop_time_cutoff$);
        register_html_state_variable($cb_shop_depth_limit$);
        register_html_state_variable($cb_shop_num_backchains$);
        register_html_state_variable($cb_last_planner_do_fi_asks$);
        register_html_state_variable($cb_shop_cycle_checking_enabledP$);
        register_html_state_variable($cb_last_planner_show_advanced_settings$);
        register_html_state_variable($cb_plan_search_type$);
        register_html_state_variable($cb_shop_verbosity$);
        register_html_state_variable($cb_shop_plan_checking_enabled$);
        register_html_state_variable($cb_shop_plan_checking_rules$);
        html_macros.note_html_handler_function(CB_SHOP_USE_IN_PLAN_CHECKING);
        setup_cb_link_method($USE_IN_PLAN_CHECKING, CB_LINK_USE_IN_PLAN_CHECKING, TWO_INTEGER);
        sethash($CB_SHOP_PLAN_CHECKING, $cb_help_definitions$.getGlobalValue(), list($str_alt36$cb_shop_plan_checking_html, NIL));
        html_macros.note_html_handler_function(CB_SHOP_PLAN_CHECKING);
        setup_cb_link_method($PLAN_CHECKING, CB_LINK_PLAN_CHECKING, ONE_INTEGER);
        classes.subloop_new_class(CYC_DEVELOPMENT_PLANNER, SHOP, NIL, NIL, $list_alt66);
        classes.class_set_implements_slot_listeners(CYC_DEVELOPMENT_PLANNER, NIL);
        classes.subloop_note_class_initialization_function(CYC_DEVELOPMENT_PLANNER, SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_CLASS);
        classes.subloop_note_instance_initialization_function(CYC_DEVELOPMENT_PLANNER, SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_INSTANCE);
        com.cyc.cycjava.cycl.cb_shop.subloop_reserved_initialize_cyc_development_planner_class(CYC_DEVELOPMENT_PLANNER);
        methods.methods_incorporate_instance_method(INITIALIZE, CYC_DEVELOPMENT_PLANNER, $list_alt78, NIL, $list_alt79);
        methods.subloop_register_instance_method(CYC_DEVELOPMENT_PLANNER, INITIALIZE, CYC_DEVELOPMENT_PLANNER_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(MAKE_DOMAIN_WITH_RULES, CYC_DEVELOPMENT_PLANNER, $list_alt84, $list_alt85, $list_alt86);
        methods.subloop_register_instance_method(CYC_DEVELOPMENT_PLANNER, MAKE_DOMAIN_WITH_RULES, CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_WITH_RULES_METHOD);
        methods.methods_incorporate_instance_method(MAKE_DOMAIN_FROM_PROOF_CHECKER, CYC_DEVELOPMENT_PLANNER, $list_alt84, $list_alt91, $list_alt92);
        methods.subloop_register_instance_method(CYC_DEVELOPMENT_PLANNER, MAKE_DOMAIN_FROM_PROOF_CHECKER, CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_FROM_PROOF_CHECKER_METHOD);
        html_macros.note_html_handler_function(PLAN_FINDER);
        html_macros.note_html_handler_function(PLAN_EXAMINER);
        html_macros.note_html_handler_function(PLAN_VERBOSE_EXAMINER);
        html_macros.note_html_handler_function(PLAN_TASK_EXAMINER);
        register_cb_frame($CB_SHOP_CONTROLS, $$$controls, $str_alt107$cb_shop_controls, NIL, NIL, T);
        register_cb_frame($CB_SHOP_SUMMARY, $$$info, $str_alt109$cb_shop_information, NIL, NIL, T);
        setup_cb_link_method($CB_SHOP_SUMMARY, CB_LINK_CB_SHOP_SUMMARY, ONE_INTEGER);
        setup_cb_link_method($CB_SHOP_CONTROLS, CB_LINK_CB_SHOP_CONTROLS, ONE_INTEGER);
        setup_cb_link_method($PLANNER, CB_LINK_PLANNER, ONE_INTEGER);
        setup_cb_link_method($PLAN_EXAMINER, CB_LINK_PLAN_EXAMINER, ONE_INTEGER);
        setup_cb_link_method($PLAN_VERBOSE_EXAMINER, CB_LINK_PLAN_VERBOSE_EXAMINER, ONE_INTEGER);
        declare_cb_tool($PLANNER, $$$Planner, $$$Planner, $str_alt127$Simple_Hierarchical_Ordered_Plann);
        html_macros.note_html_handler_function(CB_SHOP_CONTROLS);
        html_macros.note_html_handler_function(CB_SHOP_INFORMATION);
        sethash($PLAN_FINDER, $cb_help_definitions$.getGlobalValue(), list($str_alt178$plan_finder_html, NIL));
        sethash($PLAN_EXAMINER, $cb_help_definitions$.getGlobalValue(), list($str_alt221$plan_examiner_html, NIL));
        html_macros.note_html_handler_function(HANDLE_PLAN_EXAMINER_INPUT);
        html_id_links.register_html_id_index($cb_shop_processes$.getDynamicValue(), DICTIONARY_LOOKUP, PROCESS_WRAPPER_GET_ID);
        html_macros.note_html_handler_function(CB_SHOP_ABORT_LINK_HANDLER);
        html_macros.note_html_handler_function(CB_SHOP_PAUSE_LINK_HANDLER);
        html_macros.note_html_handler_function(CB_SHOP_RESUME_LINK_HANDLER);
        html_macros.note_html_handler_function(CB_SHOP_VIEW_LINK_HANDLER);
        html_macros.note_html_handler_function(CB_SHOP_REPLAN_LINK_HANDLER);
        html_macros.note_html_handler_function(CB_SHOP_VERBOSE_LINK_HANDLER);
        html_macros.note_html_handler_function(CB_SHOP_DELETE_LINK_HANDLER);
        html_macros.note_html_handler_function(CB_SHOP_DOMAIN_EXAMINER_LINK_HANDLER);
        return NIL;
    }

    public static SubLObject setup_cb_shop_file() {
        if (SubLFiles.USE_V1) {
            register_html_state_variable(cb_shop.$cb_shop_task_string$);
            register_html_state_variable(cb_shop.$cb_planner_warning_message$);
            register_html_state_variable(cb_shop.$cb_shop_planner_thread$);
            register_html_state_variable(cb_shop.$cb_planner_task$);
            register_html_state_variable(cb_shop.$cb_planner_domain_mt$);
            register_html_state_variable(cb_shop.$cb_last_planner_format$);
            register_html_state_variable(cb_shop.$cb_last_planner_structure$);
            register_html_state_variable(cb_shop.$cb_last_planner_number$);
            register_html_state_variable(cb_shop.$cb_shop_time_cutoff$);
            register_html_state_variable(cb_shop.$cb_shop_depth_limit$);
            register_html_state_variable(cb_shop.$cb_shop_num_backchains$);
            register_html_state_variable(cb_shop.$cb_last_planner_do_fi_asks$);
            register_html_state_variable(cb_shop.$cb_shop_cycle_checking_enabledP$);
            register_html_state_variable(cb_shop.$cb_last_planner_show_advanced_settings$);
            register_html_state_variable(cb_shop.$cb_plan_search_type$);
            register_html_state_variable(cb_shop.$cb_shop_verbosity$);
            register_html_state_variable(cb_shop.$cb_shop_plan_checking_enabled$);
            register_html_state_variable(cb_shop.$cb_shop_plan_checking_rules$);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_USE_IN_PLAN_CHECKING, $HTML_HANDLER);
            setup_cb_link_method(cb_shop.$USE_IN_PLAN_CHECKING, cb_shop.CB_LINK_USE_IN_PLAN_CHECKING, TWO_INTEGER);
            sethash(cb_shop.$CB_SHOP_PLAN_CHECKING, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list(cb_shop.$str37$cb_shop_plan_checking_html, NIL));
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_PLAN_CHECKING, $HTML_HANDLER);
            setup_cb_link_method($PLAN_CHECKING, cb_shop.CB_LINK_PLAN_CHECKING, ONE_INTEGER);
            classes.subloop_new_class(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.SHOP, NIL, NIL, cb_shop.$list76);
            classes.class_set_implements_slot_listeners(cb_shop.CYC_DEVELOPMENT_PLANNER, NIL);
            classes.subloop_note_class_initialization_function(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_CLASS);
            classes.subloop_note_instance_initialization_function(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_INSTANCE);
            cb_shop.subloop_reserved_initialize_cyc_development_planner_class(cb_shop.CYC_DEVELOPMENT_PLANNER);
            methods.methods_incorporate_instance_method(INITIALIZE, cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.$list88, NIL, cb_shop.$list89);
            methods.subloop_register_instance_method(cb_shop.CYC_DEVELOPMENT_PLANNER, INITIALIZE, cb_shop.CYC_DEVELOPMENT_PLANNER_INITIALIZE_METHOD);
            methods.methods_incorporate_instance_method(cb_shop.MAKE_DOMAIN_WITH_RULES, cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.$list94, cb_shop.$list95, cb_shop.$list96);
            methods.subloop_register_instance_method(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.MAKE_DOMAIN_WITH_RULES, cb_shop.CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_WITH_RULES_METHOD);
            methods.methods_incorporate_instance_method(cb_shop.MAKE_DOMAIN_FROM_PROOF_CHECKER, cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.$list94, cb_shop.$list101, cb_shop.$list102);
            methods.subloop_register_instance_method(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.MAKE_DOMAIN_FROM_PROOF_CHECKER, cb_shop.CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_FROM_PROOF_CHECKER_METHOD);
            html_macros.note_cgi_handler_function(cb_shop.PLAN_FINDER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.PLAN_EXAMINER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.PLAN_VERBOSE_EXAMINER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.PLAN_TASK_EXAMINER, $HTML_HANDLER);
            register_cb_frame(cb_shop.$CB_SHOP_CONTROLS, cb_shop.$$$controls, cb_shop.$str117$cb_shop_controls, NIL, NIL, T);
            register_cb_frame(cb_shop.$CB_SHOP_SUMMARY, cb_shop.$$$info, cb_shop.$str119$cb_shop_information, NIL, NIL, T);
            setup_cb_link_method(cb_shop.$CB_SHOP_SUMMARY, cb_shop.CB_LINK_CB_SHOP_SUMMARY, ONE_INTEGER);
            setup_cb_link_method(cb_shop.$CB_SHOP_CONTROLS, cb_shop.CB_LINK_CB_SHOP_CONTROLS, ONE_INTEGER);
            setup_cb_link_method($PLANNER, cb_shop.CB_LINK_PLANNER, ONE_INTEGER);
            setup_cb_link_method($PLAN_EXAMINER, cb_shop.CB_LINK_PLAN_EXAMINER, ONE_INTEGER);
            setup_cb_link_method(cb_shop.$PLAN_VERBOSE_EXAMINER, cb_shop.CB_LINK_PLAN_VERBOSE_EXAMINER, ONE_INTEGER);
            declare_cb_tool($PLANNER, cb_shop.$$$Planner, cb_shop.$$$Planner, cb_shop.$str137$Simple_Hierarchical_Ordered_Plann);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_CONTROLS, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_INFORMATION, $HTML_HANDLER);
            sethash($PLAN_FINDER, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list(cb_shop.$str188$plan_finder_html, NIL));
            sethash($PLAN_EXAMINER, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list(cb_shop.$str231$plan_examiner_html, NIL));
            html_macros.note_cgi_handler_function(cb_shop.HANDLE_PLAN_EXAMINER_INPUT, $HTML_HANDLER);
            html_id_links.register_html_id_index(cb_shop.$cb_shop_processes$.getDynamicValue(), DICTIONARY_LOOKUP, cb_shop.PROCESS_WRAPPER_GET_ID);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_ABORT_LINK_HANDLER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_PAUSE_LINK_HANDLER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_RESUME_LINK_HANDLER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_VIEW_LINK_HANDLER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_REPLAN_LINK_HANDLER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_VERBOSE_LINK_HANDLER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_DELETE_LINK_HANDLER, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_DOMAIN_EXAMINER_LINK_HANDLER, $HTML_HANDLER);
        }
        if (SubLFiles.USE_V2) {
            html_macros.note_html_handler_function(CB_SHOP_USE_IN_PLAN_CHECKING);
            sethash($CB_SHOP_PLAN_CHECKING, $cb_help_definitions$.getGlobalValue(), list($str_alt36$cb_shop_plan_checking_html, NIL));
            html_macros.note_html_handler_function(CB_SHOP_PLAN_CHECKING);
            classes.subloop_new_class(CYC_DEVELOPMENT_PLANNER, SHOP, NIL, NIL, $list_alt66);
            methods.methods_incorporate_instance_method(INITIALIZE, CYC_DEVELOPMENT_PLANNER, $list_alt78, NIL, $list_alt79);
            methods.methods_incorporate_instance_method(MAKE_DOMAIN_WITH_RULES, CYC_DEVELOPMENT_PLANNER, $list_alt84, $list_alt85, $list_alt86);
            methods.methods_incorporate_instance_method(MAKE_DOMAIN_FROM_PROOF_CHECKER, CYC_DEVELOPMENT_PLANNER, $list_alt84, $list_alt91, $list_alt92);
            html_macros.note_html_handler_function(PLAN_FINDER);
            html_macros.note_html_handler_function(PLAN_EXAMINER);
            html_macros.note_html_handler_function(PLAN_VERBOSE_EXAMINER);
            html_macros.note_html_handler_function(PLAN_TASK_EXAMINER);
            register_cb_frame($CB_SHOP_CONTROLS, $$$controls, $str_alt107$cb_shop_controls, NIL, NIL, T);
            register_cb_frame($CB_SHOP_SUMMARY, $$$info, $str_alt109$cb_shop_information, NIL, NIL, T);
            declare_cb_tool($PLANNER, $$$Planner, $$$Planner, $str_alt127$Simple_Hierarchical_Ordered_Plann);
            html_macros.note_html_handler_function(CB_SHOP_CONTROLS);
            html_macros.note_html_handler_function(CB_SHOP_INFORMATION);
            sethash($PLAN_FINDER, $cb_help_definitions$.getGlobalValue(), list($str_alt178$plan_finder_html, NIL));
            sethash($PLAN_EXAMINER, $cb_help_definitions$.getGlobalValue(), list($str_alt221$plan_examiner_html, NIL));
            html_macros.note_html_handler_function(HANDLE_PLAN_EXAMINER_INPUT);
            html_macros.note_html_handler_function(CB_SHOP_ABORT_LINK_HANDLER);
            html_macros.note_html_handler_function(CB_SHOP_PAUSE_LINK_HANDLER);
            html_macros.note_html_handler_function(CB_SHOP_RESUME_LINK_HANDLER);
            html_macros.note_html_handler_function(CB_SHOP_VIEW_LINK_HANDLER);
            html_macros.note_html_handler_function(CB_SHOP_REPLAN_LINK_HANDLER);
            html_macros.note_html_handler_function(CB_SHOP_VERBOSE_LINK_HANDLER);
            html_macros.note_html_handler_function(CB_SHOP_DELETE_LINK_HANDLER);
            html_macros.note_html_handler_function(CB_SHOP_DOMAIN_EXAMINER_LINK_HANDLER);
        }
        return NIL;
    }

    public static SubLObject setup_cb_shop_file_Previous() {
        register_html_state_variable(cb_shop.$cb_shop_task_string$);
        register_html_state_variable(cb_shop.$cb_planner_warning_message$);
        register_html_state_variable(cb_shop.$cb_shop_planner_thread$);
        register_html_state_variable(cb_shop.$cb_planner_task$);
        register_html_state_variable(cb_shop.$cb_planner_domain_mt$);
        register_html_state_variable(cb_shop.$cb_last_planner_format$);
        register_html_state_variable(cb_shop.$cb_last_planner_structure$);
        register_html_state_variable(cb_shop.$cb_last_planner_number$);
        register_html_state_variable(cb_shop.$cb_shop_time_cutoff$);
        register_html_state_variable(cb_shop.$cb_shop_depth_limit$);
        register_html_state_variable(cb_shop.$cb_shop_num_backchains$);
        register_html_state_variable(cb_shop.$cb_last_planner_do_fi_asks$);
        register_html_state_variable(cb_shop.$cb_shop_cycle_checking_enabledP$);
        register_html_state_variable(cb_shop.$cb_last_planner_show_advanced_settings$);
        register_html_state_variable(cb_shop.$cb_plan_search_type$);
        register_html_state_variable(cb_shop.$cb_shop_verbosity$);
        register_html_state_variable(cb_shop.$cb_shop_plan_checking_enabled$);
        register_html_state_variable(cb_shop.$cb_shop_plan_checking_rules$);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_USE_IN_PLAN_CHECKING, $HTML_HANDLER);
        setup_cb_link_method(cb_shop.$USE_IN_PLAN_CHECKING, cb_shop.CB_LINK_USE_IN_PLAN_CHECKING, TWO_INTEGER);
        sethash(cb_shop.$CB_SHOP_PLAN_CHECKING, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list(cb_shop.$str37$cb_shop_plan_checking_html, NIL));
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_PLAN_CHECKING, $HTML_HANDLER);
        setup_cb_link_method($PLAN_CHECKING, cb_shop.CB_LINK_PLAN_CHECKING, ONE_INTEGER);
        classes.subloop_new_class(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.SHOP, NIL, NIL, cb_shop.$list76);
        classes.class_set_implements_slot_listeners(cb_shop.CYC_DEVELOPMENT_PLANNER, NIL);
        classes.subloop_note_class_initialization_function(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_CLASS);
        classes.subloop_note_instance_initialization_function(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.SUBLOOP_RESERVED_INITIALIZE_CYC_DEVELOPMENT_PLANNER_INSTANCE);
        cb_shop.subloop_reserved_initialize_cyc_development_planner_class(cb_shop.CYC_DEVELOPMENT_PLANNER);
        methods.methods_incorporate_instance_method(INITIALIZE, cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.$list88, NIL, cb_shop.$list89);
        methods.subloop_register_instance_method(cb_shop.CYC_DEVELOPMENT_PLANNER, INITIALIZE, cb_shop.CYC_DEVELOPMENT_PLANNER_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(cb_shop.MAKE_DOMAIN_WITH_RULES, cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.$list94, cb_shop.$list95, cb_shop.$list96);
        methods.subloop_register_instance_method(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.MAKE_DOMAIN_WITH_RULES, cb_shop.CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_WITH_RULES_METHOD);
        methods.methods_incorporate_instance_method(cb_shop.MAKE_DOMAIN_FROM_PROOF_CHECKER, cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.$list94, cb_shop.$list101, cb_shop.$list102);
        methods.subloop_register_instance_method(cb_shop.CYC_DEVELOPMENT_PLANNER, cb_shop.MAKE_DOMAIN_FROM_PROOF_CHECKER, cb_shop.CYC_DEVELOPMENT_PLANNER_MAKE_DOMAIN_FROM_PROOF_CHECKER_METHOD);
        html_macros.note_cgi_handler_function(cb_shop.PLAN_FINDER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.PLAN_EXAMINER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.PLAN_VERBOSE_EXAMINER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.PLAN_TASK_EXAMINER, $HTML_HANDLER);
        register_cb_frame(cb_shop.$CB_SHOP_CONTROLS, cb_shop.$$$controls, cb_shop.$str117$cb_shop_controls, NIL, NIL, T);
        register_cb_frame(cb_shop.$CB_SHOP_SUMMARY, cb_shop.$$$info, cb_shop.$str119$cb_shop_information, NIL, NIL, T);
        setup_cb_link_method(cb_shop.$CB_SHOP_SUMMARY, cb_shop.CB_LINK_CB_SHOP_SUMMARY, ONE_INTEGER);
        setup_cb_link_method(cb_shop.$CB_SHOP_CONTROLS, cb_shop.CB_LINK_CB_SHOP_CONTROLS, ONE_INTEGER);
        setup_cb_link_method($PLANNER, cb_shop.CB_LINK_PLANNER, ONE_INTEGER);
        setup_cb_link_method($PLAN_EXAMINER, cb_shop.CB_LINK_PLAN_EXAMINER, ONE_INTEGER);
        setup_cb_link_method(cb_shop.$PLAN_VERBOSE_EXAMINER, cb_shop.CB_LINK_PLAN_VERBOSE_EXAMINER, ONE_INTEGER);
        declare_cb_tool($PLANNER, cb_shop.$$$Planner, cb_shop.$$$Planner, cb_shop.$str137$Simple_Hierarchical_Ordered_Plann);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_CONTROLS, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_INFORMATION, $HTML_HANDLER);
        sethash($PLAN_FINDER, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list(cb_shop.$str188$plan_finder_html, NIL));
        sethash($PLAN_EXAMINER, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list(cb_shop.$str231$plan_examiner_html, NIL));
        html_macros.note_cgi_handler_function(cb_shop.HANDLE_PLAN_EXAMINER_INPUT, $HTML_HANDLER);
        html_id_links.register_html_id_index(cb_shop.$cb_shop_processes$.getDynamicValue(), DICTIONARY_LOOKUP, cb_shop.PROCESS_WRAPPER_GET_ID);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_ABORT_LINK_HANDLER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_PAUSE_LINK_HANDLER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_RESUME_LINK_HANDLER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_VIEW_LINK_HANDLER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_REPLAN_LINK_HANDLER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_VERBOSE_LINK_HANDLER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_DELETE_LINK_HANDLER, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(cb_shop.CB_SHOP_DOMAIN_EXAMINER_LINK_HANDLER, $HTML_HANDLER);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        cb_shop.declare_cb_shop_file();
    }

    @Override
    public void initializeVariables() {
        cb_shop.init_cb_shop_file();
    }

    @Override
    public void runTopLevelForms() {
        cb_shop.setup_cb_shop_file();
    }

    static {
    }

    static private final SubLList $list_alt22 = list(list(makeSymbol("*SHOP-METHOD-CYCLE-CHECKING-ENABLED?*"), makeSymbol("*CB-SHOP-CYCLE-CHECKING-ENABLED?*")), list(makeSymbol("*SHOP-DEFAULT-DEPTH-LIMIT*"), makeSymbol("*CB-SHOP-DEPTH-LIMIT*")));

    static private final SubLString $str_alt26$Not_a_planning_assertion_ = makeString("Not a planning assertion.");

    static private final SubLString $str_alt27$Rule_has_been_added_to_SHOP_plan_ = makeString("Rule has been added to SHOP plan checking rule set.");

    static private final SubLString $str_alt30$_Use_in_Plan_Checking_ = makeString("[Use in Plan Checking]");

    static private final SubLString $str_alt32$cb_shop_use_in_plan_checking__a = makeString("cb-shop-use-in-plan-checking&~a");

    static private final SubLString $str_alt36$cb_shop_plan_checking_html = makeString("cb-shop-plan-checking.html");

    static private final SubLString $str_alt44$text_javascript = makeString("text/javascript");

    static private final SubLString $str_alt45$yui_skin_sam = makeString("yui-skin-sam");

    static private final SubLString $str_alt46$cb_shop_plan_checking = makeString("cb-shop-plan-checking");

    static private final SubLString $str_alt48$_Back_to_Planner_ = makeString("[Back to Planner]");

    static private final SubLString $str_alt49$Plan_Checking_is_ = makeString("Plan Checking is ");

    static private final SubLString $str_alt50$Enabled_ = makeString("Enabled.");

    static private final SubLString $str_alt51$Disabled_ = makeString("Disabled.");

    static private final SubLString $str_alt56$Choose_Planning_Rules_for_inclusi = makeString("Choose Planning Rules for inclusion:");

    static private final SubLString $str_alt57$No_Rules_in_plan_checking_set_ = makeString("No Rules in plan checking set.");

    static private final SubLString $str_alt60$_Plan_Checking_ = makeString("[Plan Checking]");

    static private final SubLList $list_alt66 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("MAKE-DOMAIN-FROM-PROOF-CHECKER"), NIL, makeKeyword("PUBLIC")));

    static private final SubLList $list_alt78 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list_alt79 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(makeSymbol("CSETQ"), makeSymbol("DISPLAYER-FACTORY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("CYC-DEVELOPMENT-PLAN-DISPLAYER-FACTORY")), list(QUOTE, makeSymbol("CYC-DEVELOPMENT-PLAN-DISPLAYER-FACTORY")))), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym80$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-CYC-DEVELOPMENT-PLANNER-METHOD");

    static private final SubLList $list_alt84 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list_alt85 = list(makeSymbol("RULES"), makeSymbol("TASK-MT"));

    static private final SubLList $list_alt86 = list(list(makeSymbol("CLET"), list(list(makeSymbol("DOMAIN"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-CYC-DOMAIN")), list(QUOTE, makeSymbol("SHOP-CYC-DOMAIN"))))), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT-DOMAIN-WITH-RULES"), makeSymbol("SHOP-CYC-DOMAIN")), makeSymbol("DOMAIN"), makeSymbol("RULES"), makeSymbol("TASK-MT")), list(makeSymbol("CSETF"), list(makeSymbol("GETHASH"), makeSymbol("TASK-MT"), makeSymbol("DOMAIN-TABLE")), makeSymbol("DOMAIN")), list(RET, makeSymbol("SELF"))));

    static private final SubLSymbol $sym87$OUTER_CATCH_FOR_CYC_DEVELOPMENT_PLANNER_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-CYC-DEVELOPMENT-PLANNER-METHOD");

    static private final SubLList $list_alt91 = list(makeSymbol("TASK-MT"));

    static private final SubLList $list_alt92 = list(makeString("@param TASK-MT microtheory-p"), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTIONS"), list(makeSymbol("CB-SHOP-PLAN-CHECKING-RULES")))), list(RET, list(makeSymbol("MAKE-DOMAIN-WITH-RULES"), makeSymbol("SELF"), makeSymbol("ASSERTIONS"), makeSymbol("TASK-MT")))));

    static private final SubLString $str_alt95$Cannot_have_a_FRAMESET_tag_inside = makeString("Cannot have a FRAMESET tag inside a BODY tag.");

    static private final SubLString $str_alt96$400__ = makeString("400,*");

    static private final SubLString $str_alt107$cb_shop_controls = makeString("cb-shop-controls");

    static private final SubLString $str_alt109$cb_shop_information = makeString("cb-shop-information");

    static private final SubLString $str_alt110$_Summary_ = makeString("[Summary]");

    static private final SubLString $str_alt113$_Controls_ = makeString("[Controls]");

    static private final SubLString $str_alt115$_Plan_Finder_ = makeString("[Plan Finder]");

    static private final SubLString $str_alt116$plan_finder = makeString("plan-finder");

    static private final SubLString $str_alt118$_Plan_Examiner_ = makeString("[Plan Examiner]");

    static private final SubLString $str_alt119$plan_examiner = makeString("plan-examiner");

    static private final SubLString $str_alt122$_Plan_Verbose_Examiner_ = makeString("[Plan Verbose Examiner]");

    static private final SubLString $str_alt123$plan_verbose_examiner = makeString("plan-verbose-examiner");

    static private final SubLString $str_alt127$Simple_Hierarchical_Ordered_Plann = makeString("Simple Hierarchical Ordered Planner");

    static private final SubLString $str_alt130$_New_Plan_ = makeString("[New Plan]");

    static private final SubLString $str_alt131$_Full_Screen_ = makeString("[Full Screen]");

    static private final SubLString $str_alt134$find_plans = makeString("find-plans");

    static private final SubLString $str_alt135$show_advanced_parameters = makeString("show-advanced-parameters");

    static private final SubLString $str_alt137$Warning_ = makeString("Warning:");

    static private final SubLString $str_alt138$verbosity_level = makeString("verbosity-level");

    static private final SubLString $str_alt139$cutoff_number = makeString("cutoff-number");

    static private final SubLString $str_alt141$cutoff_time = makeString("cutoff-time");

    static private final SubLString $str_alt143$do_fi_asks = makeString("do-fi-asks");

    static private final SubLString $str_alt144$do_asks = makeString("do-asks");

    static private final SubLString $str_alt145$num_backchains = makeString("num-backchains");

    static private final SubLString $str_alt146$cycle_checking = makeString("cycle-checking");

    static private final SubLString $str_alt147$depth_limit_p = makeString("depth-limit-p");

    static private final SubLString $str_alt148$depth_limit = makeString("depth-limit");

    static private final SubLString $str_alt149$search_type = makeString("search-type");

    static private final SubLString $str_alt151$no_logging = makeString("no-logging");

    static private final SubLString $str_alt152$domain_mt = makeString("domain-mt");

    static private final SubLString $str_alt154$Task_syntax_not_recognized_ = makeString("Task syntax not recognized.");

    static private final SubLString $str_alt157$___s_is_not_a_valid__s_microtheor = makeString("~%~s is not a valid ~s microtheory.");

    static private final SubLString $str_alt161$__Please_enter_an_instance_of__s_ = makeString("~%Please enter an instance of ~s.");

    static private final SubLString $str_alt165$Last_task_ = makeString("Last task:");

    static private final SubLString $str_alt166$Planner_halted_because__ = makeString("Planner halted because: ");

    static private final SubLString $str_alt168$Search_space_was_exhausted_ = makeString("Search space was exhausted.");

    static private final SubLString $str_alt170$Depth_limit_was_reached_ = makeString("Depth limit was reached.");

    static private final SubLString $str_alt172$Number_cutoff_was_reached_ = makeString("Number cutoff was reached.");

    static private final SubLString $str_alt174$Time_cutoff_was_reached_ = makeString("Time cutoff was reached.");

    static private final SubLString $str_alt175$_Examine_Plans_ = makeString("[Examine Plans]");

    static private final SubLString $str_alt176$_Examine_Verbose_Output_ = makeString("[Examine Verbose Output]");

    static private final SubLString $str_alt178$plan_finder_html = makeString("plan-finder.html");

    static private final SubLString $str_alt181$Mt_ = makeString("Mt:");

    static private final SubLString $str_alt183$Task_ = makeString("Task:");

    static private final SubLString $str_alt189$95_ = makeString("95%");

    static private final SubLString $str_alt194$_Verbose_Output_ = makeString("[Verbose Output]");

    static private final SubLString $str_alt195$Go_to_ = makeString("Go to:");

    static private final SubLString $str_alt196$_methods = makeString("#methods");

    static private final SubLString $str_alt198$_operators = makeString("#operators");

    static private final SubLString $str_alt200$_cond_operators = makeString("#cond-operators");

    static private final SubLString $str_alt202$_sconds = makeString("#sconds");

    static private final SubLString $str_alt204$Planning_Mt_ = makeString("Planning Mt:");

    static private final SubLString $str_alt210$cond_operators = makeString("cond-operators");

    static private final SubLString $str_alt219$No_Verbose_Output__Set_Verbosity_ = makeString("No Verbose Output! Set Verbosity to a value higher than 0.");

    static private final SubLString $str_alt221$plan_examiner_html = makeString("plan-examiner.html");

    static private final SubLString $str_alt223$handle_plan_examiner_input = makeString("handle-plan-examiner-input");

    static private final SubLString $str_alt228$_cycl = makeString(":cycl");

    static private final SubLString $str_alt230$_hierarchical = makeString(":hierarchical");

    static private final SubLString $str_alt235$Format_ = makeString("Format:");

    static private final SubLString $str_alt236$_english = makeString(":english");

    static private final SubLString $str_alt239$Structure_ = makeString("Structure:");

    static private final SubLString $str_alt240$_primitive = makeString(":primitive");

    static private final SubLString $str_alt244$Number_Cutoff_ = makeString("Number Cutoff:");

    static private final SubLString $str_alt245$no_number = makeString("no-number");

    static private final SubLString $str_alt247$_plans = makeString(" plans");

    static private final SubLString $str_alt248$Time_Cutoff_ = makeString("Time Cutoff:");

    static private final SubLString $str_alt249$no_time = makeString("no-time");

    static private final SubLString $str_alt251$_seconds = makeString(" seconds");

    static private final SubLString $str_alt252$Depth_Cutoff_ = makeString("Depth Cutoff:");

    static private final SubLString $str_alt253$no_depth_limit = makeString("no-depth-limit");

    static private final SubLString $str_alt256$Back_Chaining_ = makeString("Back Chaining:");

    static private final SubLString $str_alt259$_backchains = makeString(" backchains");

    static private final SubLString $str_alt260$Cycle_Checking_ = makeString("Cycle Checking:");

    static private final SubLString $str_alt261$no_cycle_checking = makeString("no-cycle-checking");

    static private final SubLString $str_alt262$Log_Process_ = makeString("Log Process:");

    static private final SubLString $str_alt263$_all = makeString(":all");

    static private final SubLString $str_alt264$_first = makeString(":first");

    static private final SubLString $str_alt266$_shallowest = makeString(":shallowest");

    static private final SubLString $str_alt268$_all_shallowest = makeString(":all-shallowest");

    static private final SubLString $str_alt270$_id_first = makeString(":id-first");

    static private final SubLString $str_alt272$_id_all = makeString(":id-all");

    static private final SubLString $str_alt277$Number_of_plans_found__ = makeString("Number of plans found: ");

    static private final SubLString $str_alt278$Plans_ = makeString("Plans:");

    static private final SubLString $str_alt280$Plan_ = makeString("Plan ");

    static private final SubLString $str_alt281$_ = makeString(":");

    static private final SubLString $str_alt282$No_plans_found_ = makeString("No plans found!");

    static private final SubLList $list_alt286 = list(makeSymbol("ID-STR"));

    static private final SubLString $str_alt288$_a__a = makeString("~a&~a");

    static private final SubLString $str_alt289$cb_shop_abort_link_handler = makeString("cb-shop-abort-link-handler");

    static private final SubLString $str_alt290$_Abort_ = makeString("[Abort]");

    static private final SubLString $str_alt292$cb_shop_pause_link_handler = makeString("cb-shop-pause-link-handler");

    static private final SubLString $str_alt293$_Pause_ = makeString("[Pause]");

    static private final SubLString $str_alt295$cb_shop_resume_link_handler = makeString("cb-shop-resume-link-handler");

    static private final SubLString $str_alt296$_Resume_ = makeString("[Resume]");

    static private final SubLString $str_alt298$cb_shop_view_link_handler = makeString("cb-shop-view-link-handler");

    static private final SubLString $str_alt299$_View_ = makeString("[View]");

    static private final SubLString $str_alt301$cb_shop_replan_link_handler = makeString("cb-shop-replan-link-handler");

    static private final SubLString $str_alt302$_Replan_ = makeString("[Replan]");

    static private final SubLString $str_alt304$cb_shop_verbose_link_handler = makeString("cb-shop-verbose-link-handler");

    static private final SubLString $str_alt305$_Log_ = makeString("[Log]");

    static private final SubLString $str_alt307$cb_shop_delete_link_handler = makeString("cb-shop-delete-link-handler");

    static private final SubLString $str_alt308$_Delete_ = makeString("[Delete]");

    static private final SubLString $str_alt310$cb_shop_domain_examiner_link_hand = makeString("cb-shop-domain-examiner-link-handler");

    static private final SubLString $str_alt311$_Domain_ = makeString("[Domain]");

    static private final SubLString $str_alt318$No_Plans_Currently_Available_ = makeString("No Plans Currently Available.");
}

/**
 * Total time: 3099 ms
 */
