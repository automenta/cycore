/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.cb_parameters.$cb_constant_history$;
import static com.cyc.cycjava.cycl.cb_parameters.$cb_nat_history$;
import static com.cyc.cycjava.cycl.cb_parameters.$cb_permit_robots_to_followP$;
import static com.cyc.cycjava.cycl.cb_parameters.$cb_permit_robots_to_indexP$;
import static com.cyc.cycjava.cycl.cb_parameters.$cb_show_enhanced_tables$;
import static com.cyc.cycjava.cycl.cb_parameters.cb_head_shortcut_icon;
import static com.cyc.cycjava.cycl.cb_parameters.cyc_cgi_url_int;
import static com.cyc.cycjava.cycl.cb_utilities.cb_error;
import static com.cyc.cycjava.cycl.cb_utilities.cb_form;
import static com.cyc.cycjava.cycl.cb_utilities.cb_fort_identifier;
import static com.cyc.cycjava.cycl.cb_utilities.cb_frame_name;
import static com.cyc.cycjava.cycl.cb_utilities.cb_guess_fort;
import static com.cyc.cycjava.cycl.cb_utilities.cb_help_preamble;
import static com.cyc.cycjava.cycl.cb_utilities.cb_link;
import static com.cyc.cycjava.cycl.cb_utilities.declare_cb_tool;
import static com.cyc.cycjava.cycl.cb_utilities.declare_deprecated_cb_tool;
import static com.cyc.cycjava.cycl.cb_utilities.html_assertion_marker;
import static com.cyc.cycjava.cycl.cb_utilities.setup_cb_link_method;
import static com.cyc.cycjava.cycl.cyc_file_dependencies.$cb_help_definitions$;
import static com.cyc.cycjava.cycl.cyc_file_dependencies.$cb_icon_definitions$;
import static com.cyc.cycjava.cycl.cyc_file_dependencies.cb_get_icon_alt_string;
import static com.cyc.cycjava.cycl.cyc_file_dependencies.cb_icon_file_path;
import static com.cyc.cycjava.cycl.html_utilities.html_align;
import static com.cyc.cycjava.cycl.html_utilities.html_char;
import static com.cyc.cycjava.cycl.html_utilities.html_checkbox_input;
import static com.cyc.cycjava.cycl.html_utilities.html_color;
import static com.cyc.cycjava.cycl.html_utilities.html_copyright_notice;
import static com.cyc.cycjava.cycl.html_utilities.html_glyph;
import static com.cyc.cycjava.cycl.html_utilities.html_hidden_input;
import static com.cyc.cycjava.cycl.html_utilities.html_indent;
import static com.cyc.cycjava.cycl.html_utilities.html_markup;
import static com.cyc.cycjava.cycl.html_utilities.html_meta_robot_instructions;
import static com.cyc.cycjava.cycl.html_utilities.html_newline;
import static com.cyc.cycjava.cycl.html_utilities.html_princ;
import static com.cyc.cycjava.cycl.html_utilities.html_simple_attribute;
import static com.cyc.cycjava.cycl.html_utilities.html_source_readability_terpri;
import static com.cyc.cycjava.cycl.html_utilities.html_submit_input;
import static com.cyc.cycjava.cycl.html_utilities.html_terpri;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_greater;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_quotation;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.clrhash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryKey;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryValue;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.gethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorNextEntry;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.make_hash_table;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.releaseEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.sethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.multiply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove_duplicates;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;

import java.util.Iterator;
import java.util.Map;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      CB-SIMILARITY
 * source file: /cyc/top/cycl/cb-similarity.lisp
 * created:     2019/07/03 17:38:12
 */
public final class cb_similarity extends SubLTranslatedFile implements V12 {
    static private final SubLString $str_alt78$ = makeString("");

    public static final SubLFile me = new cb_similarity();

 public static final String myName = "com.cyc.cycjava.cycl.cb_similarity";


    static private final SubLString $str1$blue_gif = makeString("blue.gif");

    static private final SubLString $str3$green_gif = makeString("green.gif");

    static private final SubLString $str5$red_gif = makeString("red.gif");

    static private final SubLString $str7$white_gif = makeString("white.gif");

    static private final SubLString $str9$yellow_gif = makeString("yellow.gif");

    private static final SubLSymbol ST_SIMILARITY_ENTRANCE = makeSymbol("ST-SIMILARITY-ENTRANCE");

    private static final SubLString $$$Similarity = makeString("Similarity");

    private static final SubLString $str14$st_similarity_entrance = makeString("st-similarity-entrance");

    private static final SubLSymbol CB_LINK_SIMILARITY = makeSymbol("CB-LINK-SIMILARITY");

    private static final SubLString $$$Sim = makeString("Sim");

    private static final SubLString $$$Similarity_Tool = makeString("Similarity Tool");

    private static final SubLString $str20$st_similarity_html = makeString("st-similarity.html");

    private static final SubLString $$$Similarity_Tool_Choose = makeString("Similarity Tool Choose");

    private static final SubLString $str22$__DOCTYPE_html_PUBLIC_____W3C__DT = makeString("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");

    private static final SubLString $str23$_meta_http_equiv__X_UA_Compatible = makeString("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\" >");

    private static final SubLSymbol $SAM_AUTOCOMPLETE_CSS = makeKeyword("SAM-AUTOCOMPLETE-CSS");

    private static final SubLString $str28$yui_skin_sam = makeString("yui-skin-sam");

    private static final SubLString $$$reloadFrameButton = makeString("reloadFrameButton");

    private static final SubLString $$$button = makeString("button");

    private static final SubLString $$$reload = makeString("reload");

    private static final SubLString $$$Refresh_Frames = makeString("Refresh Frames");

    private static final SubLString $str34$cg_cb_similarity = makeString("cg?cb-similarity");

    private static final SubLString $str36$cb_similarity = makeString("cb-similarity");

    private static final SubLString $$$Run_Similarity_Tool = makeString("Run Similarity Tool");

    private static final SubLString $str38$Please_choose_two_or_more_constan = makeString("Please choose two or more constants.");

    private static final SubLString $str39$Recent_Constants__ = makeString("Recent Constants :");

    private static final SubLString $$$None = makeString("None");

    private static final SubLString $str41$Recent_NATs__ = makeString("Recent NATs :");

    private static final SubLSymbol $sym42$VALID_CONSTANT_ = makeSymbol("VALID-CONSTANT?");

    private static final SubLSymbol $sym43$VALID_NART_ = makeSymbol("VALID-NART?");

    private static final SubLString $$$the_Similarity_Tool_page = makeString("the Similarity Tool page");

    private static final SubLString $str45$Cannot_show_similarity_for_just_o = makeString("Cannot show similarity for just one constant: ~A.");

    private static final SubLString $str47$Check_or_uncheck_the_boxes_to_mak = makeString("Check or uncheck the boxes to make or remove assertions.");

    private static final SubLString $$$cg = makeString("cg");

    private static final SubLString $$$SimilarityForm = makeString("SimilarityForm");

    private static final SubLString $str50$cb_similarity_assert = makeString("cb-similarity-assert");

    private static final SubLString $$$t = makeString("t");

    private static final SubLString $$$Reset = makeString("Reset");

    private static final SubLString $$$Submit = makeString("Submit");

    private static final SubLSymbol CB_SIMILARITY = makeSymbol("CB-SIMILARITY");

    private static final SubLString $str56$_nbsp_ = makeString("&nbsp;");







    private static final SubLString $str61$_ = makeString("(");

    private static final SubLString $str62$_ = makeString(")");

    private static final SubLString $str64$_dddddd = makeString("#dddddd");

    private static final SubLString $str65$_cccccc = makeString("#cccccc");

    private static final SubLString $str66$___ = makeString("|__");

    private static final SubLString $str67$__A_ = makeString("(~A)");

    private static final SubLString $$$BIA_ = makeString("BIA ");

    private static final SubLString $str72$BI__ = makeString("BI_ ");

    private static final SubLString $str73$B_A_ = makeString("B_A ");

    private static final SubLString $str74$B___ = makeString("B__ ");

    private static final SubLString $str75$_IA_ = makeString("_IA ");

    private static final SubLString $str76$_I__ = makeString("_I_ ");

    private static final SubLString $str77$__A_ = makeString("__A ");

    private static final SubLString $str78$____ = makeString("___ ");

    private static final SubLString $str80$cg_cb_af__a = makeString("cg?cb-af&~a");

    private static final SubLString $$$a = makeString("a");

    private static final SubLString $$$x = makeString("x");

    private static final SubLString $$$L_E_G_E_N_D = makeString("L E G E N D");

    private static final SubLString $str85$_ = makeString("*");

    private static final SubLString $str86$ = makeString("");

    private static final SubLString $str87$Asserted_in_KB_ = makeString("Asserted in KB.");

    private static final SubLString $str88$Asserted_in_KB__but_redundant_ = makeString("Asserted in KB, but redundant.");

    private static final SubLString $str90$Shown_as_asserted_in_KB_by_a_forw = makeString("Shown as asserted in KB by a forward rule.");

    private static final SubLString $str91$Not_asserted_in_KB__but_true_ = makeString("Not asserted in KB, but true.");

    private static final SubLString $str92$Neither_asserted_in_KB_nor_true_ = makeString("Neither asserted in KB nor true.");

    private static final SubLSymbol ASSERT = makeSymbol("ASSERT");

    private static final SubLSymbol DO_NOTHING = makeSymbol("DO-NOTHING");

    private static final SubLSymbol UNASSERT = makeSymbol("UNASSERT");

    private static final SubLString $str97$Asserting____A__A__A__in__A = makeString("Asserting: (~A ~A ~A) in ~A");

    private static final SubLString $str98$Result__ = makeString("Result: ");

    private static final SubLString $str99$Unasserting____A__A__A__from__A = makeString("Unasserting: (~A ~A ~A) from ~A");

    private static final SubLSymbol CB_SIMILARITY_ASSERT = makeSymbol("CB-SIMILARITY-ASSERT");

    private static final SubLString $str101$st__A__A__A__A__A_ = makeString("st|~A|~A|~A|~A|~A|");

    private static final SubLString $str102$st__A__A__A__A_ = makeString("st|~A|~A|~A|~A|");

    private static final SubLString $str103$st__A__A__A_ = makeString("st|~A|~A|~A|");

    private static final SubLString $str104$st__A__A_ = makeString("st|~A|~A|");

    private static final SubLString $str105$st__A_ = makeString("st|~A|");

    private static final SubLList $list106 = list(makeString("|"));

    // Definitions
    /**
     * The handler for the Similarity Tool Entrance Page.  Just calls st-similarity-entrance-internal.
     */
    @LispMethod(comment = "The handler for the Similarity Tool Entrance Page.  Just calls st-similarity-entrance-internal.")
    public static final SubLObject st_similarity_entrance_alt(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        return com.cyc.cycjava.cycl.cb_similarity.st_similarity_entrance_internal();
    }

    // Definitions
    /**
     * The handler for the Similarity Tool Entrance Page.  Just calls st-similarity-entrance-internal.
     */
    @LispMethod(comment = "The handler for the Similarity Tool Entrance Page.  Just calls st-similarity-entrance-internal.")
    public static SubLObject st_similarity_entrance(SubLObject args) {
        if (args == UNPROVIDED) {
            args = NIL;
        }
        return st_similarity_entrance_internal();
    }

    /**
     * Define a cyc browser link method called :similarity.
     */
    @LispMethod(comment = "Define a cyc browser link method called :similarity.")
    public static final SubLObject cb_link_similarity_alt(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == linktext) {
                linktext = $$$Similarity;
            }
            {
                SubLObject frame_name_var = cb_frame_name($MAIN);
                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                cyc_cgi_url_int();
                html_princ($str_alt13$st_similarity_entrance);
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
     * Define a cyc browser link method called :similarity.
     */
    @LispMethod(comment = "Define a cyc browser link method called :similarity.")
    public static SubLObject cb_link_similarity(SubLObject linktext) {
        if (linktext == UNPROVIDED) {
            linktext = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == linktext) {
            linktext = $$$Similarity;
        }
        final SubLObject frame_name_var = cb_frame_name($MAIN);
        html_markup(html_macros.$html_anchor_head$.getGlobalValue());
        html_markup(html_macros.$html_anchor_href$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        cyc_cgi_url_int();
        html_princ($str14$st_similarity_entrance);
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
     * Show the Similarity Tool Entrance Page.
     */
    @LispMethod(comment = "Show the Similarity Tool Entrance Page.")
    public static final SubLObject st_similarity_entrance_internal_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject title_var = $$$Similarity_Tool_Choose;
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
                                    html_markup($str_alt23$yui_skin_sam);
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
                                        html_markup(html_macros.$html_form_head$.getGlobalValue());
                                        html_markup(html_macros.$html_form_action$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_3 = html_macros.$html_safe_print$.currentBinding(thread);
                                            SubLObject _prev_bind_1 = html_macros.$within_html_form$.currentBinding(thread);
                                            SubLObject _prev_bind_2 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_macros.$within_html_form$.bind(T, thread);
                                                html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                                                cb_help_preamble($ST_SIMILARITY, UNPROVIDED, UNPROVIDED);
                                                cb_link($CLEAR_HISTORY, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                com.cyc.cycjava.cycl.cb_similarity.st_similarity_show_recent_history();
                                                html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                                            } finally {
                                                html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_2, thread);
                                                html_macros.$within_html_form$.rebind(_prev_bind_1, thread);
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_3, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_form_tail$.getGlobalValue());
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
            return NIL;
        }
    }

    /**
     * Show the Similarity Tool Entrance Page.
     */
    @LispMethod(comment = "Show the Similarity Tool Entrance Page.")
    public static SubLObject st_similarity_entrance_internal() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject title_var = $$$Similarity_Tool_Choose;
        final SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
        try {
            html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? html_macros.$html_id_space_id_generator$.getDynamicValue(thread) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            html_markup($str22$__DOCTYPE_html_PUBLIC_____W3C__DT);
            if (NIL != html_macros.$html_force_ie_standards_mode$.getDynamicValue(thread)) {
                html_source_readability_terpri(UNPROVIDED);
                html_markup($str23$_meta_http_equiv__X_UA_Compatible);
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
                    html_markup($str28$yui_skin_sam);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$3 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_div_head$.getGlobalValue());
                        html_markup(html_macros.$html_attribute_id$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup($$$reloadFrameButton);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$4 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_input_head$.getGlobalValue());
                            html_markup(html_macros.$html_input_type$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup($$$button);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_name$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup($$$reload);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_value$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_princ($$$Refresh_Frames);
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
                        html_markup(html_macros.$html_form_head$.getGlobalValue());
                        html_markup(html_macros.$html_form_action$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(system_parameters.$cyc_cgi_program$.getDynamicValue(thread));
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$5 = html_macros.$html_safe_print$.currentBinding(thread);
                        final SubLObject _prev_bind_2 = html_macros.$within_html_form$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_macros.$within_html_form$.bind(T, thread);
                            html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                            cb_help_preamble($ST_SIMILARITY, UNPROVIDED, UNPROVIDED);
                            cb_link($CLEAR_HISTORY, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            st_similarity_show_recent_history();
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

    /**
     * A blatant steal of cb-show-recent-history.  Shows a list of constants and NATs with checkboxes for selecting for the Similarity Tool.
     */
    @LispMethod(comment = "A blatant steal of cb-show-recent-history.  Shows a list of constants and NATs with checkboxes for selecting for the Similarity Tool.")
    public static final SubLObject st_similarity_show_recent_history_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_form_head$.getGlobalValue());
            html_markup(html_macros.$html_form_action$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup($str_alt25$cg_cb_similarity);
            html_char(CHAR_quotation, UNPROVIDED);
            if (true) {
                html_markup(html_macros.$html_form_method$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup($POST);
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                SubLObject _prev_bind_1 = html_macros.$within_html_form$.currentBinding(thread);
                SubLObject _prev_bind_2 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_macros.$within_html_form$.bind(T, thread);
                    html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                    html_hidden_input($str_alt27$cb_similarity, NIL, UNPROVIDED);
                    html_newline(TWO_INTEGER);
                    html_submit_input($$$Run_Similarity_Tool, UNPROVIDED, UNPROVIDED);
                    html_newline(TWO_INTEGER);
                    html_princ($str_alt29$Please_choose_two_or_more_constan);
                    html_newline(TWO_INTEGER);
                    {
                        SubLObject items = com.cyc.cycjava.cycl.cb_similarity.st_similarity_constant_history_items();
                        html_markup(html_macros.$html_big_head$.getGlobalValue());
                        html_princ($str_alt30$Recent_Constants__);
                        html_markup(html_macros.$html_big_tail$.getGlobalValue());
                        html_newline(UNPROVIDED);
                        if (NIL != items) {
                            {
                                SubLObject cdolist_list_var = items;
                                SubLObject item = NIL;
                                for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                                    html_checkbox_input(cb_fort_identifier(item), T, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    html_indent(TWO_INTEGER);
                                    cb_form(item, UNPROVIDED, UNPROVIDED);
                                    html_newline(UNPROVIDED);
                                }
                            }
                        } else {
                            html_princ($$$None);
                        }
                    }
                    html_newline(TWO_INTEGER);
                    html_markup(html_macros.$html_big_head$.getGlobalValue());
                    html_princ($str_alt32$Recent_NATs__);
                    html_markup(html_macros.$html_big_tail$.getGlobalValue());
                    html_newline(UNPROVIDED);
                    {
                        SubLObject items = com.cyc.cycjava.cycl.cb_similarity.st_similarity_nat_history_items();
                        if (NIL != items) {
                            {
                                SubLObject cdolist_list_var = items;
                                SubLObject item = NIL;
                                for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                                    html_checkbox_input(cb_fort_identifier(item), T, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    html_indent(TWO_INTEGER);
                                    cb_form(item, UNPROVIDED, UNPROVIDED);
                                    html_newline(UNPROVIDED);
                                }
                            }
                        } else {
                            html_princ($$$None);
                        }
                    }
                    html_newline(TWO_INTEGER);
                    html_submit_input($$$Run_Similarity_Tool, UNPROVIDED, UNPROVIDED);
                    html_newline(TWO_INTEGER);
                    html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                } finally {
                    html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_2, thread);
                    html_macros.$within_html_form$.rebind(_prev_bind_1, thread);
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_form_tail$.getGlobalValue());
            return NIL;
        }
    }

    /**
     * A blatant steal of cb-show-recent-history.  Shows a list of constants and NATs with checkboxes for selecting for the Similarity Tool.
     */
    @LispMethod(comment = "A blatant steal of cb-show-recent-history.  Shows a list of constants and NATs with checkboxes for selecting for the Similarity Tool.")
    public static SubLObject st_similarity_show_recent_history() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_form_head$.getGlobalValue());
        html_markup(html_macros.$html_form_action$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup($str34$cg_cb_similarity);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_form_method$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup($POST);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        final SubLObject _prev_bind_2 = html_macros.$within_html_form$.currentBinding(thread);
        final SubLObject _prev_bind_3 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_macros.$within_html_form$.bind(T, thread);
            html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
            html_hidden_input($str36$cb_similarity, NIL, UNPROVIDED);
            html_newline(TWO_INTEGER);
            html_submit_input($$$Run_Similarity_Tool, UNPROVIDED, UNPROVIDED);
            html_newline(TWO_INTEGER);
            html_princ($str38$Please_choose_two_or_more_constan);
            html_newline(TWO_INTEGER);
            SubLObject items = st_similarity_constant_history_items();
            html_markup(html_macros.$html_big_head$.getGlobalValue());
            html_princ($str39$Recent_Constants__);
            html_markup(html_macros.$html_big_tail$.getGlobalValue());
            html_newline(UNPROVIDED);
            if (NIL != items) {
                SubLObject cdolist_list_var = items;
                SubLObject item = NIL;
                item = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    html_checkbox_input(cb_fort_identifier(item), T, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    html_indent(TWO_INTEGER);
                    cb_form(item, UNPROVIDED, UNPROVIDED);
                    html_newline(UNPROVIDED);
                    cdolist_list_var = cdolist_list_var.rest();
                    item = cdolist_list_var.first();
                } 
            } else {
                html_princ($$$None);
            }
            html_newline(TWO_INTEGER);
            html_markup(html_macros.$html_big_head$.getGlobalValue());
            html_princ($str41$Recent_NATs__);
            html_markup(html_macros.$html_big_tail$.getGlobalValue());
            html_newline(UNPROVIDED);
            items = st_similarity_nat_history_items();
            if (NIL != items) {
                SubLObject cdolist_list_var = items;
                SubLObject item = NIL;
                item = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    html_checkbox_input(cb_fort_identifier(item), T, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    html_indent(TWO_INTEGER);
                    cb_form(item, UNPROVIDED, UNPROVIDED);
                    html_newline(UNPROVIDED);
                    cdolist_list_var = cdolist_list_var.rest();
                    item = cdolist_list_var.first();
                } 
            } else {
                html_princ($$$None);
            }
            html_newline(TWO_INTEGER);
            html_submit_input($$$Run_Similarity_Tool, UNPROVIDED, UNPROVIDED);
            html_newline(TWO_INTEGER);
            html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
        } finally {
            html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_3, thread);
            html_macros.$within_html_form$.rebind(_prev_bind_2, thread);
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_form_tail$.getGlobalValue());
        return NIL;
    }

    /**
     * A steal of cb-constant-history-items.  Returns a list of constants recently visited.
     */
    @LispMethod(comment = "A steal of cb-constant-history-items.  Returns a list of constants recently visited.")
    public static final SubLObject st_similarity_constant_history_items_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            $cb_constant_history$.setDynamicValue(list_utilities.delete_if_not(symbol_function($sym33$VALID_CONSTANT_), $cb_constant_history$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            return copy_list($cb_constant_history$.getDynamicValue(thread));
        }
    }

    /**
     * A steal of cb-constant-history-items.  Returns a list of constants recently visited.
     */
    @LispMethod(comment = "A steal of cb-constant-history-items.  Returns a list of constants recently visited.")
    public static SubLObject st_similarity_constant_history_items() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        $cb_constant_history$.setDynamicValue(list_utilities.delete_if_not(symbol_function($sym42$VALID_CONSTANT_), $cb_constant_history$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
        return copy_list($cb_constant_history$.getDynamicValue(thread));
    }

    /**
     * A steal of cb-nat-history-items.  Returns a list of NATs recently visited.
     */
    @LispMethod(comment = "A steal of cb-nat-history-items.  Returns a list of NATs recently visited.")
    public static final SubLObject st_similarity_nat_history_items_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            $cb_nat_history$.setDynamicValue(list_utilities.delete_if_not(symbol_function($sym34$VALID_NART_), $cb_nat_history$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            return copy_list($cb_nat_history$.getDynamicValue(thread));
        }
    }

    /**
     * A steal of cb-nat-history-items.  Returns a list of NATs recently visited.
     */
    @LispMethod(comment = "A steal of cb-nat-history-items.  Returns a list of NATs recently visited.")
    public static SubLObject st_similarity_nat_history_items() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        $cb_nat_history$.setDynamicValue(list_utilities.delete_if_not(symbol_function($sym43$VALID_NART_), $cb_nat_history$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
        return copy_list($cb_nat_history$.getDynamicValue(thread));
    }

    /**
     * The handler function for the Similarity Tool.
     * ARGS is a list of constant (in the form 'c:[constant-internal-id]' and NARTs (in the form 'n:[nart-id]'.
     */
    @LispMethod(comment = "The handler function for the Similarity Tool.\r\nARGS is a list of constant (in the form \'c:[constant-internal-id]\' and NARTs (in the form \'n:[nart-id]\'.\nThe handler function for the Similarity Tool.\nARGS is a list of constant (in the form \'c:[constant-internal-id]\' and NARTs (in the form \'n:[nart-id]\'.")
    public static final SubLObject cb_similarity_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != ke.cyclist_is_guest()) {
                cyc_navigator_internals.guest_warn($$$the_Similarity_Tool_page);
                return NIL;
            }
            {
                SubLObject fort_list = NIL;
                SubLObject cdolist_list_var = args;
                SubLObject arg = NIL;
                for (arg = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg = cdolist_list_var.first()) {
                    if (arg.isList()) {
                        {
                            SubLObject fort_spec = arg.first();
                            SubLObject fort = cb_guess_fort(fort_spec, UNPROVIDED);
                            if (NIL != forts.fort_p(fort)) {
                                fort_list = cons(fort, fort_list);
                            }
                        }
                    }
                }
                fort_list = nreverse(remove_duplicates(fort_list, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                if (NIL == fort_list.rest()) {
                    cb_error($str_alt36$Cannot_show_similarity_for_just_o, fort_list.first(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    return NIL;
                }
                {
                    SubLObject title_var = $$$Similarity_Tool;
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
                                SubLObject _prev_bind_0_4 = html_macros.$html_inside_bodyP$.currentBinding(thread);
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
                                        html_markup($str_alt23$yui_skin_sam);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_5 = html_macros.$html_safe_print$.currentBinding(thread);
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
                                            cb_help_preamble($ST_SIMILARITY, NIL, UNPROVIDED);
                                            html_glyph($NBSP, UNPROVIDED);
                                            html_princ($str_alt38$Check_or_uncheck_the_boxes_to_mak);
                                            html_markup(html_macros.$html_form_head$.getGlobalValue());
                                            html_markup(html_macros.$html_form_action$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup($$$cg);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            if (true) {
                                                html_markup(html_macros.$html_form_method$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup($POST);
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            if (true) {
                                                html_markup(html_macros.$html_form_name$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup($$$SimilarityForm);
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_6 = html_macros.$html_safe_print$.currentBinding(thread);
                                                SubLObject _prev_bind_1 = html_macros.$within_html_form$.currentBinding(thread);
                                                SubLObject _prev_bind_2 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_macros.$within_html_form$.bind(T, thread);
                                                    html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                                                    html_hidden_input($str_alt41$cb_similarity_assert, $$$t, UNPROVIDED);
                                                    html_markup(html_macros.$html_input_head$.getGlobalValue());
                                                    html_markup(html_macros.$html_input_type$.getGlobalValue());
                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                    html_markup($$$button);
                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                    if (true) {
                                                        html_markup(html_macros.$html_input_value$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_princ($$$Reset);
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    html_char(CHAR_greater, UNPROVIDED);
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
                                                        SubLObject _prev_bind_0_7 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            com.cyc.cycjava.cycl.cb_similarity.st_html_tree(fort_list, UNPROVIDED, UNPROVIDED);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_7, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                                    html_newline(UNPROVIDED);
                                                    html_submit_input($$$Submit, UNPROVIDED, UNPROVIDED);
                                                    html_newline(TWO_INTEGER);
                                                    com.cyc.cycjava.cycl.cb_similarity.st_html_legend();
                                                    html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                                                } finally {
                                                    html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_2, thread);
                                                    html_macros.$within_html_form$.rebind(_prev_bind_1, thread);
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_6, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_form_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_copyright_notice();
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_5, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_body_tail$.getGlobalValue());
                                    html_source_readability_terpri(UNPROVIDED);
                                } finally {
                                    html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_4, thread);
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

    /**
     * The handler function for the Similarity Tool.
     * ARGS is a list of constant (in the form 'c:[constant-internal-id]' and NARTs (in the form 'n:[nart-id]'.
     */
    @LispMethod(comment = "The handler function for the Similarity Tool.\r\nARGS is a list of constant (in the form \'c:[constant-internal-id]\' and NARTs (in the form \'n:[nart-id]\'.\nThe handler function for the Similarity Tool.\nARGS is a list of constant (in the form \'c:[constant-internal-id]\' and NARTs (in the form \'n:[nart-id]\'.")
    public static SubLObject cb_similarity(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn($$$the_Similarity_Tool_page);
            return NIL;
        }
        SubLObject fort_list = NIL;
        SubLObject cdolist_list_var = args;
        SubLObject arg = NIL;
        arg = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (arg.isList()) {
                final SubLObject fort_spec = arg.first();
                final SubLObject fort = cb_guess_fort(fort_spec, UNPROVIDED);
                if (NIL != forts.fort_p(fort)) {
                    fort_list = cons(fort, fort_list);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            arg = cdolist_list_var.first();
        } 
        fort_list = nreverse(remove_duplicates(fort_list, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        if (NIL == fort_list.rest()) {
            cb_error($str45$Cannot_show_similarity_for_just_o, fort_list.first(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            return NIL;
        }
        final SubLObject title_var = $$$Similarity_Tool;
        final SubLObject _prev_bind_0 = html_macros.$html_id_space_id_generator$.currentBinding(thread);
        try {
            html_macros.$html_id_space_id_generator$.bind(NIL != integer_sequence_generator.integer_sequence_generator_p(html_macros.$html_id_space_id_generator$.getDynamicValue(thread)) ? html_macros.$html_id_space_id_generator$.getDynamicValue(thread) : integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
            html_markup($str22$__DOCTYPE_html_PUBLIC_____W3C__DT);
            if (NIL != html_macros.$html_force_ie_standards_mode$.getDynamicValue(thread)) {
                html_source_readability_terpri(UNPROVIDED);
                html_markup($str23$_meta_http_equiv__X_UA_Compatible);
            }
            html_source_readability_terpri(UNPROVIDED);
            final SubLObject _prev_bind_0_$6 = cyc_file_dependencies.$html_emitted_file_dependencies$.currentBinding(thread);
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
                final SubLObject _prev_bind_0_$7 = html_macros.$html_inside_bodyP$.currentBinding(thread);
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
                    html_markup($str28$yui_skin_sam);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$8 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_div_head$.getGlobalValue());
                        html_markup(html_macros.$html_attribute_id$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup($$$reloadFrameButton);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$9 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_input_head$.getGlobalValue());
                            html_markup(html_macros.$html_input_type$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup($$$button);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_name$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup($$$reload);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_value$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_princ($$$Refresh_Frames);
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_input_disabledP$.getDynamicValue(thread)) {
                                html_simple_attribute(html_macros.$html_input_disabled$.getGlobalValue());
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$9, thread);
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
                        cb_help_preamble($ST_SIMILARITY, NIL, UNPROVIDED);
                        html_glyph($NBSP, UNPROVIDED);
                        html_princ($str47$Check_or_uncheck_the_boxes_to_mak);
                        html_markup(html_macros.$html_form_head$.getGlobalValue());
                        html_markup(html_macros.$html_form_action$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup($$$cg);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_macros.$html_form_method$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup($POST);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_macros.$html_form_name$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup($$$SimilarityForm);
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$10 = html_macros.$html_safe_print$.currentBinding(thread);
                        final SubLObject _prev_bind_2 = html_macros.$within_html_form$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = html_macros.$html_form_field_uniquifier_code$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_macros.$within_html_form$.bind(T, thread);
                            html_macros.$html_form_field_uniquifier_code$.bind(html_macros.next_html_form_field_uniquifier_code(), thread);
                            html_hidden_input($str50$cb_similarity_assert, $$$t, UNPROVIDED);
                            html_markup(html_macros.$html_input_head$.getGlobalValue());
                            html_markup(html_macros.$html_input_type$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup($$$button);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_input_value$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_princ($$$Reset);
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_input_disabledP$.getDynamicValue(thread)) {
                                html_simple_attribute(html_macros.$html_input_disabled$.getGlobalValue());
                            }
                            html_char(CHAR_greater, UNPROVIDED);
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
                            final SubLObject _prev_bind_0_$11 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                st_html_tree(fort_list, UNPROVIDED, UNPROVIDED);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$11, thread);
                            }
                            html_markup(html_macros.$html_table_tail$.getGlobalValue());
                            html_newline(UNPROVIDED);
                            html_submit_input($$$Submit, UNPROVIDED, UNPROVIDED);
                            html_newline(TWO_INTEGER);
                            st_html_legend();
                            html_macros.embed_form_field_code(html_macros.$html_form_field_uniquifier_code$.getDynamicValue(thread));
                        } finally {
                            html_macros.$html_form_field_uniquifier_code$.rebind(_prev_bind_3, thread);
                            html_macros.$within_html_form$.rebind(_prev_bind_2, thread);
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$10, thread);
                        }
                        html_markup(html_macros.$html_form_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_copyright_notice();
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$8, thread);
                    }
                    html_markup(html_macros.$html_body_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                } finally {
                    html_macros.$html_inside_bodyP$.rebind(_prev_bind_0_$7, thread);
                }
                html_markup(html_macros.$html_html_tail$.getGlobalValue());
            } finally {
                cyc_file_dependencies.$html_emitted_file_dependencies$.rebind(_prev_bind_0_$6, thread);
            }
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_id_space_id_generator$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    /**
     * Output N table data cells with pipes in them.  Also adds an empty cell to the front.
     */
    @LispMethod(comment = "Output N table data cells with pipes in them.  Also adds an empty cell to the front.")
    public static final SubLObject st_html_td_pipes_alt(SubLObject n) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            {
                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            {
                SubLObject bgcolor = html_macros.$html_color_light_grey$.getDynamicValue(thread);
                SubLObject grey_toggle = NIL;
                if (n.numG(ZERO_INTEGER)) {
                    {
                        SubLObject x = NIL;
                        for (x = ZERO_INTEGER; x.numL(n); x = add(x, ONE_INTEGER)) {
                            if (NIL != $cb_show_enhanced_tables$.getDynamicValue(thread)) {
                                bgcolor = (NIL != grey_toggle) ? ((SubLObject) (html_macros.$html_color_light_grey$.getDynamicValue(thread))) : html_macros.$html_color_lighter_grey$.getDynamicValue(thread);
                                if (NIL != grey_toggle) {
                                    grey_toggle = NIL;
                                } else {
                                    grey_toggle = T;
                                }
                            }
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            if (true) {
                                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_align($CENTER));
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            if (NIL != bgcolor) {
                                html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(bgcolor);
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_markup($str_alt48$_nbsp_);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        }
                    }
                }
                return NIL;
            }
        }
    }

    /**
     * Output N table data cells with pipes in them.  Also adds an empty cell to the front.
     */
    @LispMethod(comment = "Output N table data cells with pipes in them.  Also adds an empty cell to the front.")
    public static SubLObject st_html_td_pipes(final SubLObject n) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        SubLObject bgcolor = html_macros.$html_color_light_grey$.getGlobalValue();
        SubLObject grey_toggle = NIL;
        if (n.numG(ZERO_INTEGER)) {
            SubLObject x;
            SubLObject _prev_bind_2;
            for (x = NIL, x = ZERO_INTEGER; x.numL(n); x = add(x, ONE_INTEGER)) {
                bgcolor = (NIL != grey_toggle) ? html_macros.$html_color_light_grey$.getGlobalValue() : html_macros.$html_color_lighter_grey$.getGlobalValue();
                if (NIL != grey_toggle) {
                    grey_toggle = NIL;
                } else {
                    grey_toggle = T;
                }
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($CENTER));
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != bgcolor) {
                    html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(bgcolor);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                _prev_bind_2 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup($str56$_nbsp_);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_2, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            }
        }
        return NIL;
    }

    /**
     * Output table rows showing which column correcpond to which constants.
     */
    @LispMethod(comment = "Output table rows showing which column correcpond to which constants.")
    public static final SubLObject st_html_table_top_alt(SubLObject cols) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject width = add(ONE_INTEGER, length(cols));
                SubLObject count = ZERO_INTEGER;
                SubLObject cdolist_list_var = cols;
                SubLObject col = NIL;
                for (col = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , col = cdolist_list_var.first()) {
                    html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                    html_char(CHAR_greater, UNPROVIDED);
                    {
                        SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            com.cyc.cycjava.cycl.cb_similarity.st_html_td_pipes(count);
                            {
                                SubLObject span = subtract(width, count);
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                if (NIL != span) {
                                    html_markup(html_macros.$html_table_data_colspan$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(span);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (true) {
                                    html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($LEFT));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_8 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_font_head$.getGlobalValue());
                                        if (true) {
                                            html_markup(html_macros.$html_font_size$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(FOUR_INTEGER);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_9 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                cb_form(col, UNPROVIDED, UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_9, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_8, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            }
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                        }
                    }
                    html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                    html_source_readability_terpri(UNPROVIDED);
                    count = add(count, ONE_INTEGER);
                }
            }
            return NIL;
        }
    }

    /**
     * Output table rows showing which column correcpond to which constants.
     */
    @LispMethod(comment = "Output table rows showing which column correcpond to which constants.")
    public static SubLObject st_html_table_top(final SubLObject cols) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject width = add(ONE_INTEGER, length(cols));
        SubLObject count = ZERO_INTEGER;
        SubLObject cdolist_list_var = cols;
        SubLObject col = NIL;
        col = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                st_html_td_pipes(count);
                final SubLObject span = subtract(width, count);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                if (NIL != span) {
                    html_markup(html_macros.$html_table_data_colspan$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(span);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($LEFT));
                html_char(CHAR_quotation, UNPROVIDED);
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$12 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_font_head$.getGlobalValue());
                    html_markup(html_macros.$html_font_size$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(FOUR_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$13 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        cb_form(col, UNPROVIDED, UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$13, thread);
                    }
                    html_markup(html_macros.$html_font_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$12, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            count = add(count, ONE_INTEGER);
            cdolist_list_var = cdolist_list_var.rest();
            col = cdolist_list_var.first();
        } 
        return NIL;
    }

    /**
     * Show the similarity tree for COLS.  If PRED is unspecified, we recursively call st-html-tree to show the tree for #$isa and #$genls.  If MT is unspecified, we recursively call st-html-tree to show the tree for the mts relevant to COLS.
     */
    @LispMethod(comment = "Show the similarity tree for COLS.  If PRED is unspecified, we recursively call st-html-tree to show the tree for #$isa and #$genls.  If MT is unspecified, we recursively call st-html-tree to show the tree for the mts relevant to COLS.")
    public static final SubLObject st_html_tree_alt(SubLObject cols, SubLObject pred, SubLObject mt) {
        if (pred == UNPROVIDED) {
            pred = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject span = length(cols);
                if (NIL == pred) {
                    com.cyc.cycjava.cycl.cb_similarity.st_html_table_top(cols);
                    {
                        SubLObject cdolist_list_var = kb_mapping_utilities.pred_values_in_any_mt($$isa, $$genlPreds, TWO_INTEGER, ONE_INTEGER, UNPROVIDED);
                        SubLObject isa_genlpred = NIL;
                        for (isa_genlpred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , isa_genlpred = cdolist_list_var.first()) {
                            com.cyc.cycjava.cycl.cb_similarity.st_html_tree(cols, isa_genlpred, UNPROVIDED);
                        }
                    }
                    com.cyc.cycjava.cycl.cb_similarity.st_html_tree(cols, $$isa, UNPROVIDED);
                    {
                        SubLObject cdolist_list_var = kb_mapping_utilities.pred_values_in_any_mt($$genls, $$genlPreds, TWO_INTEGER, ONE_INTEGER, UNPROVIDED);
                        SubLObject genls_genlpred = NIL;
                        for (genls_genlpred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , genls_genlpred = cdolist_list_var.first()) {
                            com.cyc.cycjava.cycl.cb_similarity.st_html_tree(cols, genls_genlpred, UNPROVIDED);
                        }
                    }
                    com.cyc.cycjava.cycl.cb_similarity.st_html_tree(cols, $$genls, UNPROVIDED);
                    return NIL;
                }
                if (NIL == mt) {
                    {
                        SubLObject mts = similarity.find_relevant_mts(cols, pred);
                        if (NIL != mts) {
                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    com.cyc.cycjava.cycl.cb_similarity.st_html_td_pipes(span);
                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                    if (true) {
                                        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(html_align($CENTER));
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    if (NIL != html_macros.$html_color_light_purple$.getDynamicValue(thread)) {
                                        html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                        html_char(CHAR_quotation, UNPROVIDED);
                                        html_markup(html_macros.$html_color_light_purple$.getDynamicValue(thread));
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_10 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            html_markup(html_macros.$html_font_head$.getGlobalValue());
                                            if (true) {
                                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(TEN_INTEGER);
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_11 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    cb_form(pred, UNPROVIDED, UNPROVIDED);
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_11, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_10, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                            html_source_readability_terpri(UNPROVIDED);
                            {
                                SubLObject cdolist_list_var = mts;
                                SubLObject mt_12 = NIL;
                                for (mt_12 = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , mt_12 = cdolist_list_var.first()) {
                                    com.cyc.cycjava.cycl.cb_similarity.st_html_tree(cols, pred, mt_12);
                                }
                            }
                        }
                    }
                    return NIL;
                }
                clrhash(similarity.$similarity_relation_hash$.getDynamicValue(thread));
                {
                    SubLObject similarity_tree = similarity.make_similarity_tree(cols, pred, mt);
                    if (NIL != similarity_tree) {
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        {
                            SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                com.cyc.cycjava.cycl.cb_similarity.st_html_td_pipes(span);
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                if (true) {
                                    html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($CENTER));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (NIL != html_macros.$html_color_light_blue$.getDynamicValue(thread)) {
                                    html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_macros.$html_color_light_blue$.getDynamicValue(thread));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_13 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_bold_head$.getGlobalValue());
                                        html_princ($str_alt53$_);
                                        cb_form(mt, UNPROVIDED, UNPROVIDED);
                                        html_princ($str_alt54$_);
                                        html_markup(html_macros.$html_bold_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_13, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                            }
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        com.cyc.cycjava.cycl.cb_similarity.st_html_print_tree(similarity_tree, cols, pred, mt);
                    }
                }
                return NIL;
            }
        }
    }

    /**
     * Show the similarity tree for COLS.  If PRED is unspecified, we recursively call st-html-tree to show the tree for #$isa and #$genls.  If MT is unspecified, we recursively call st-html-tree to show the tree for the mts relevant to COLS.
     */
    @LispMethod(comment = "Show the similarity tree for COLS.  If PRED is unspecified, we recursively call st-html-tree to show the tree for #$isa and #$genls.  If MT is unspecified, we recursively call st-html-tree to show the tree for the mts relevant to COLS.")
    public static SubLObject st_html_tree(final SubLObject cols, SubLObject pred, SubLObject mt) {
        if (pred == UNPROVIDED) {
            pred = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject span = length(cols);
        if (NIL == pred) {
            st_html_table_top(cols);
            SubLObject cdolist_list_var = kb_mapping_utilities.pred_values_in_any_mt($$isa, $$genlPreds, TWO_INTEGER, ONE_INTEGER, UNPROVIDED);
            SubLObject isa_genlpred = NIL;
            isa_genlpred = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                st_html_tree(cols, isa_genlpred, UNPROVIDED);
                cdolist_list_var = cdolist_list_var.rest();
                isa_genlpred = cdolist_list_var.first();
            } 
            st_html_tree(cols, $$isa, UNPROVIDED);
            cdolist_list_var = kb_mapping_utilities.pred_values_in_any_mt($$genls, $$genlPreds, TWO_INTEGER, ONE_INTEGER, UNPROVIDED);
            SubLObject genls_genlpred = NIL;
            genls_genlpred = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                st_html_tree(cols, genls_genlpred, UNPROVIDED);
                cdolist_list_var = cdolist_list_var.rest();
                genls_genlpred = cdolist_list_var.first();
            } 
            st_html_tree(cols, $$genls, UNPROVIDED);
            return NIL;
        }
        if (NIL == mt) {
            final SubLObject mts = similarity.find_relevant_mts(cols, pred);
            if (NIL != mts) {
                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    st_html_td_pipes(span);
                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                    html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_align($CENTER));
                    html_char(CHAR_quotation, UNPROVIDED);
                    if (NIL != html_macros.$html_color_light_purple$.getGlobalValue()) {
                        html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(html_macros.$html_color_light_purple$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$14 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_big_head$.getGlobalValue());
                        cb_form(pred, UNPROVIDED, UNPROVIDED);
                        html_markup(html_macros.$html_big_tail$.getGlobalValue());
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$14, thread);
                    }
                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                }
                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                html_source_readability_terpri(UNPROVIDED);
                SubLObject cdolist_list_var2 = mts;
                SubLObject mt_$15 = NIL;
                mt_$15 = cdolist_list_var2.first();
                while (NIL != cdolist_list_var2) {
                    st_html_tree(cols, pred, mt_$15);
                    cdolist_list_var2 = cdolist_list_var2.rest();
                    mt_$15 = cdolist_list_var2.first();
                } 
            }
            return NIL;
        }
        clrhash(similarity.$similarity_relation_hash$.getDynamicValue(thread));
        final SubLObject similarity_tree = similarity.make_similarity_tree(cols, pred, mt);
        if (NIL != similarity_tree) {
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                st_html_td_pipes(span);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($CENTER));
                html_char(CHAR_quotation, UNPROVIDED);
                if (NIL != html_macros.$html_color_light_blue$.getGlobalValue()) {
                    html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(html_macros.$html_color_light_blue$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$15 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_markup(html_macros.$html_bold_head$.getGlobalValue());
                    html_princ($str61$_);
                    cb_form(mt, UNPROVIDED, UNPROVIDED);
                    html_princ($str62$_);
                    html_markup(html_macros.$html_bold_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$15, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            st_html_print_tree(similarity_tree, cols, pred, mt);
        }
        return NIL;
    }

    /**
     * Output the EDGE-LIST in html format.  COLS, PRED, and MT are used for display purposes.
     */
    @LispMethod(comment = "Output the EDGE-LIST in html format.  COLS, PRED, and MT are used for display purposes.")
    public static final SubLObject st_html_print_tree_alt(SubLObject edge_list, SubLObject cols, SubLObject pred, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject node_note_number = make_hash_table($int$32, UNPROVIDED, UNPROVIDED);
                SubLObject count = ZERO_INTEGER;
                SubLObject bgcolor = NIL;
                thread.resetMultipleValues();
                {
                    SubLObject node_ordering = similarity.st_order_nodes_in_dag(edge_list, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    SubLObject forward_links = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL == $cb_show_enhanced_tables$.getDynamicValue(thread)) {
                        bgcolor = $str_alt56$_cccccc;
                    }
                    {
                        SubLObject color_toggle = NIL;
                        SubLObject list_var = NIL;
                        SubLObject node_with_indent = NIL;
                        SubLObject ignore_me = NIL;
                        for (list_var = node_ordering, node_with_indent = list_var.first(), ignore_me = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , node_with_indent = list_var.first() , ignore_me = add(ONE_INTEGER, ignore_me)) {
                            if (NIL != $cb_show_enhanced_tables$.getDynamicValue(thread)) {
                                if (NIL != color_toggle) {
                                    color_toggle = NIL;
                                } else {
                                    color_toggle = T;
                                }
                                bgcolor = (NIL != color_toggle) ? ((SubLObject) ($str_alt57$_dddddd)) : $str_alt56$_cccccc;
                            }
                            {
                                SubLObject node = node_with_indent.first();
                                SubLObject indent = second(node_with_indent);
                                html_terpri(UNPROVIDED);
                                html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_14 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                html_glyph($NBSP, UNPROVIDED);
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_14, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                        com.cyc.cycjava.cycl.cb_similarity.st_html_print_node(node, cols, pred, mt);
                                        html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                        if (NIL != bgcolor) {
                                            html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                            html_char(CHAR_quotation, UNPROVIDED);
                                            html_markup(bgcolor);
                                            html_char(CHAR_quotation, UNPROVIDED);
                                        }
                                        html_char(CHAR_greater, UNPROVIDED);
                                        {
                                            SubLObject _prev_bind_0_15 = html_macros.$html_safe_print$.currentBinding(thread);
                                            try {
                                                html_macros.$html_safe_print$.bind(T, thread);
                                                if (!indent.numE(ZERO_INTEGER)) {
                                                    html_glyph($NBSP, TWO_INTEGER);
                                                    {
                                                        SubLObject cdotimes_end_var = multiply(FOUR_INTEGER, subtract(indent, ONE_INTEGER));
                                                        SubLObject i = NIL;
                                                        for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                                                            html_glyph($NBSP, TWO_INTEGER);
                                                        }
                                                    }
                                                    html_princ($str_alt58$___);
                                                }
                                                html_glyph($NBSP, TWO_INTEGER);
                                                {
                                                    SubLObject cdolist_list_var = similarity.edge_list_node_parents(forward_links, node);
                                                    SubLObject parent = NIL;
                                                    for (parent = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parent = cdolist_list_var.first()) {
                                                        format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt59$__A_, gethash(parent, node_note_number, UNPROVIDED));
                                                        html_glyph($NBSP, UNPROVIDED);
                                                    }
                                                }
                                                cb_form(node, UNPROVIDED, UNPROVIDED);
                                                if (NIL != similarity.edge_list_node_children(forward_links, node)) {
                                                    count = add(count, ONE_INTEGER);
                                                    sethash(node, node_note_number, count);
                                                    html_glyph($NBSP, UNPROVIDED);
                                                    format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt59$__A_, count);
                                                }
                                            } finally {
                                                html_macros.$html_safe_print$.rebind(_prev_bind_0_15, thread);
                                            }
                                        }
                                        html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                html_source_readability_terpri(UNPROVIDED);
                            }
                        }
                    }
                }
            }
            return NIL;
        }
    }

    /**
     * Output the EDGE-LIST in html format.  COLS, PRED, and MT are used for display purposes.
     */
    @LispMethod(comment = "Output the EDGE-LIST in html format.  COLS, PRED, and MT are used for display purposes.")
    public static SubLObject st_html_print_tree(final SubLObject edge_list, final SubLObject cols, final SubLObject pred, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject node_note_number = make_hash_table($int$32, UNPROVIDED, UNPROVIDED);
        SubLObject count = ZERO_INTEGER;
        SubLObject bgcolor = NIL;
        thread.resetMultipleValues();
        final SubLObject node_ordering = similarity.st_order_nodes_in_dag(edge_list, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject forward_links = thread.secondMultipleValue();
        thread.resetMultipleValues();
        SubLObject color_toggle = NIL;
        SubLObject list_var = NIL;
        SubLObject node_with_indent = NIL;
        SubLObject ignore_me = NIL;
        list_var = node_ordering;
        node_with_indent = list_var.first();
        for (ignore_me = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , node_with_indent = list_var.first() , ignore_me = add(ONE_INTEGER, ignore_me)) {
            if (NIL != color_toggle) {
                color_toggle = NIL;
            } else {
                color_toggle = T;
            }
            bgcolor = (NIL != color_toggle) ? $str64$_dddddd : $str65$_cccccc;
            final SubLObject node = node_with_indent.first();
            final SubLObject indent = second(node_with_indent);
            html_terpri(UNPROVIDED);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$17 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_glyph($NBSP, UNPROVIDED);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$17, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                st_html_print_node(node, cols, pred, mt);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                if (NIL != bgcolor) {
                    html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(bgcolor);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$18 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    if (!indent.numE(ZERO_INTEGER)) {
                        html_glyph($NBSP, TWO_INTEGER);
                        SubLObject cdotimes_end_var;
                        SubLObject i;
                        for (cdotimes_end_var = multiply(FOUR_INTEGER, subtract(indent, ONE_INTEGER)), i = NIL, i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                            html_glyph($NBSP, TWO_INTEGER);
                        }
                        html_princ($str66$___);
                    }
                    html_glyph($NBSP, TWO_INTEGER);
                    SubLObject cdolist_list_var = similarity.edge_list_node_parents(forward_links, node);
                    SubLObject parent = NIL;
                    parent = cdolist_list_var.first();
                    while (NIL != cdolist_list_var) {
                        format(html_macros.$html_stream$.getDynamicValue(thread), $str67$__A_, gethash(parent, node_note_number, UNPROVIDED));
                        html_glyph($NBSP, UNPROVIDED);
                        cdolist_list_var = cdolist_list_var.rest();
                        parent = cdolist_list_var.first();
                    } 
                    cb_form(node, UNPROVIDED, UNPROVIDED);
                    if (NIL != similarity.edge_list_node_children(forward_links, node)) {
                        count = add(count, ONE_INTEGER);
                        sethash(node, node_note_number, count);
                        html_glyph($NBSP, UNPROVIDED);
                        format(html_macros.$html_stream$.getDynamicValue(thread), $str67$__A_, count);
                    }
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$18, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        }
        return NIL;
    }

    /**
     * Output a table row for the constant NODE.  COLS, PRED, and MT are used for display purposes.
     */
    @LispMethod(comment = "Output a table row for the constant NODE.  COLS, PRED, and MT are used for display purposes.")
    public static final SubLObject st_html_print_node_alt(SubLObject node, SubLObject cols, SubLObject pred, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject temp_string = NIL;
                SubLObject bgcolor = NIL;
                if (NIL == $cb_show_enhanced_tables$.getDynamicValue(thread)) {
                    bgcolor = $str_alt56$_cccccc;
                }
                {
                    SubLObject color_toggle = NIL;
                    SubLObject list_var = NIL;
                    SubLObject c = NIL;
                    SubLObject ignore_me = NIL;
                    for (list_var = cols, c = list_var.first(), ignore_me = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , c = list_var.first() , ignore_me = add(ONE_INTEGER, ignore_me)) {
                        if (NIL != $cb_show_enhanced_tables$.getDynamicValue(thread)) {
                            if (NIL != color_toggle) {
                                color_toggle = NIL;
                            } else {
                                color_toggle = T;
                            }
                            bgcolor = (NIL != color_toggle) ? ((SubLObject) ($str_alt57$_dddddd)) : $str_alt56$_cccccc;
                        }
                        {
                            SubLObject nodepair = list(c, node);
                            if (NIL != similarity.sim_check_relation(nodepair, $BASIS)) {
                                if (NIL != similarity.sim_check_relation(nodepair, $IMPLIED)) {
                                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                                        temp_string = format(NIL, $str_alt63$BIA_);
                                    } else {
                                        temp_string = format(NIL, $str_alt64$BI__);
                                    }
                                } else {
                                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                                        temp_string = format(NIL, $str_alt65$B_A_);
                                    } else {
                                        temp_string = format(NIL, $str_alt66$B___);
                                    }
                                }
                            } else {
                                if (NIL != similarity.sim_check_relation(nodepair, $IMPLIED)) {
                                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                                        temp_string = format(NIL, $str_alt67$_IA_);
                                    } else {
                                        temp_string = format(NIL, $str_alt68$_I__);
                                    }
                                } else {
                                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                                        temp_string = format(NIL, $str_alt69$__A_);
                                    } else {
                                        temp_string = format(NIL, $str_alt70$____);
                                    }
                                }
                            }
                            {
                                SubLObject assertedP = similarity.sim_check_relation(nodepair, $ASSERTED);
                                SubLObject impliedP = similarity.sim_check_relation(nodepair, $IMPLIED);
                                SubLObject basisP = similarity.sim_check_relation(nodepair, $BASIS);
                                if (NIL != assertedP) {
                                    if (NIL != basisP) {
                                        bgcolor = html_macros.$html_color_light_yellow$.getDynamicValue(thread);
                                    } else {
                                        bgcolor = html_macros.$html_color_light_red$.getDynamicValue(thread);
                                    }
                                } else {
                                    if (NIL != impliedP) {
                                        bgcolor = html_macros.$html_color_light_green$.getDynamicValue(thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                if (true) {
                                    html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($RIGHT));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (NIL != bgcolor) {
                                    html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(bgcolor);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        {
                                            SubLObject assertion = kb_indexing.find_gaf(list(pred, c, node), mt);
                                            if (NIL != assertion) {
                                                html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                                                html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt72$cg_cb_af__a, assertion_handles.assertion_id(assertion));
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_char(CHAR_greater, UNPROVIDED);
                                                {
                                                    SubLObject _prev_bind_0_16 = html_macros.$html_safe_print$.currentBinding(thread);
                                                    try {
                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                        html_assertion_marker(assertion, $BOTTOM);
                                                    } finally {
                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_16, thread);
                                                    }
                                                }
                                                html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                                            }
                                            if (NIL != assertedP) {
                                                html_hidden_input(com.cyc.cycjava.cycl.cb_similarity.st_make_a_string(pred, mt, node, c, UNPROVIDED), $$$a, UNPROVIDED);
                                            }
                                            html_checkbox_input(com.cyc.cycjava.cycl.cb_similarity.st_make_a_string(pred, mt, node, c, UNPROVIDED), $$$x, assertedP, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                        }
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            }
                        }
                    }
                }
            }
            return NIL;
        }
    }

    /**
     * Output a table row for the constant NODE.  COLS, PRED, and MT are used for display purposes.
     */
    @LispMethod(comment = "Output a table row for the constant NODE.  COLS, PRED, and MT are used for display purposes.")
    public static SubLObject st_html_print_node(final SubLObject node, final SubLObject cols, final SubLObject pred, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject temp_string = NIL;
        SubLObject bgcolor = NIL;
        SubLObject color_toggle = NIL;
        SubLObject list_var = NIL;
        SubLObject c = NIL;
        SubLObject ignore_me = NIL;
        list_var = cols;
        c = list_var.first();
        for (ignore_me = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , c = list_var.first() , ignore_me = add(ONE_INTEGER, ignore_me)) {
            if (NIL != color_toggle) {
                color_toggle = NIL;
            } else {
                color_toggle = T;
            }
            bgcolor = (NIL != color_toggle) ? $str64$_dddddd : $str65$_cccccc;
            final SubLObject nodepair = list(c, node);
            if (NIL != similarity.sim_check_relation(nodepair, $BASIS)) {
                if (NIL != similarity.sim_check_relation(nodepair, $IMPLIED)) {
                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                        temp_string = format(NIL, $$$BIA_);
                    } else {
                        temp_string = format(NIL, $str72$BI__);
                    }
                } else
                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                        temp_string = format(NIL, $str73$B_A_);
                    } else {
                        temp_string = format(NIL, $str74$B___);
                    }

            } else
                if (NIL != similarity.sim_check_relation(nodepair, $IMPLIED)) {
                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                        temp_string = format(NIL, $str75$_IA_);
                    } else {
                        temp_string = format(NIL, $str76$_I__);
                    }
                } else
                    if (NIL != similarity.sim_check_relation(nodepair, $ASSERTED)) {
                        temp_string = format(NIL, $str77$__A_);
                    } else {
                        temp_string = format(NIL, $str78$____);
                    }


            final SubLObject assertedP = similarity.sim_check_relation(nodepair, $ASSERTED);
            final SubLObject impliedP = similarity.sim_check_relation(nodepair, $IMPLIED);
            final SubLObject basisP = similarity.sim_check_relation(nodepair, $BASIS);
            if (NIL != assertedP) {
                if (NIL != basisP) {
                    bgcolor = html_macros.$html_color_light_yellow$.getGlobalValue();
                } else {
                    bgcolor = html_macros.$html_color_light_red$.getGlobalValue();
                }
            } else
                if (NIL != impliedP) {
                    bgcolor = html_macros.$html_color_light_green$.getGlobalValue();
                }

            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
            html_markup(html_macros.$html_table_data_align$.getGlobalValue());
            html_char(CHAR_quotation, UNPROVIDED);
            html_markup(html_align($RIGHT));
            html_char(CHAR_quotation, UNPROVIDED);
            if (NIL != bgcolor) {
                html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(bgcolor);
                html_char(CHAR_quotation, UNPROVIDED);
            }
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                final SubLObject assertion = kb_indexing.find_gaf(list(pred, c, node), mt);
                if (NIL != assertion) {
                    html_markup(html_macros.$html_anchor_head$.getGlobalValue());
                    html_markup(html_macros.$html_anchor_href$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    format(html_macros.$html_stream$.getDynamicValue(thread), $str80$cg_cb_af__a, assertion_handles.assertion_id(assertion));
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$19 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_assertion_marker(assertion, $BOTTOM);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$19, thread);
                    }
                    html_markup(html_macros.$html_anchor_tail$.getGlobalValue());
                }
                if (NIL != assertedP) {
                    html_hidden_input(st_make_a_string(pred, mt, node, c, UNPROVIDED), $$$a, UNPROVIDED);
                }
                html_checkbox_input(st_make_a_string(pred, mt, node, c, UNPROVIDED), $$$x, assertedP, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
            }
            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
        }
        return NIL;
    }/**
     * Output a table row for the constant NODE.  COLS, PRED, and MT are used for display purposes.
     */


    /**
     * Print a legend explaining the symbols used on the Similarity Tool page.
     */
    @LispMethod(comment = "Print a legend explaining the symbols used on the Similarity Tool page.")
    public static final SubLObject st_html_legend_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            html_markup(html_macros.$html_table_head$.getGlobalValue());
            if (true) {
                html_markup(html_macros.$html_table_border$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(ONE_INTEGER);
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
                        SubLObject _prev_bind_0_17 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            {
                                SubLObject _prev_bind_0_18 = html_macros.$html_safe_print$.currentBinding(thread);
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
                                        html_markup(THREE_INTEGER);
                                        html_char(CHAR_quotation, UNPROVIDED);
                                    }
                                    html_char(CHAR_greater, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_19 = html_macros.$html_safe_print$.currentBinding(thread);
                                        try {
                                            html_macros.$html_safe_print$.bind(T, thread);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_20 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    if (true) {
                                                        html_markup(html_macros.$html_table_data_colspan$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(TWO_INTEGER);
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    if (true) {
                                                        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_align($CENTER));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_21 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_markup(html_macros.$html_font_head$.getGlobalValue());
                                                            if (true) {
                                                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                                html_markup(FOUR_INTEGER);
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                            }
                                                            html_char(CHAR_greater, UNPROVIDED);
                                                            {
                                                                SubLObject _prev_bind_0_22 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                try {
                                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                                    html_princ($$$L_E_G_E_N_D);
                                                                } finally {
                                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_22, thread);
                                                                }
                                                            }
                                                            html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_21, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_20, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_23 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    if (true) {
                                                        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_align($RIGHT));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    if (NIL != html_macros.$html_color_light_yellow$.getDynamicValue(thread)) {
                                                        html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_macros.$html_color_light_yellow$.getDynamicValue(thread));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_24 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_markup(html_macros.$html_subscript_head$.getGlobalValue());
                                                            html_markup(html_macros.$html_font_head$.getGlobalValue());
                                                            if (true) {
                                                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                                html_markup(EIGHT_INTEGER);
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                            }
                                                            html_char(CHAR_greater, UNPROVIDED);
                                                            {
                                                                SubLObject _prev_bind_0_25 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                try {
                                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                                    html_princ($str_alt77$_);
                                                                } finally {
                                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_25, thread);
                                                                }
                                                            }
                                                            html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                                            html_markup(html_macros.$html_subscript_tail$.getGlobalValue());
                                                            html_checkbox_input($str_alt78$, $str_alt78$, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_24, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_26 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_princ($str_alt79$Asserted_in_KB_);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_26, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_23, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_27 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    if (true) {
                                                        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_align($RIGHT));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    if (NIL != html_macros.$html_color_light_red$.getDynamicValue(thread)) {
                                                        html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_macros.$html_color_light_red$.getDynamicValue(thread));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_28 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_markup(html_macros.$html_subscript_head$.getGlobalValue());
                                                            html_markup(html_macros.$html_font_head$.getGlobalValue());
                                                            if (true) {
                                                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                                html_markup(EIGHT_INTEGER);
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                            }
                                                            html_char(CHAR_greater, UNPROVIDED);
                                                            {
                                                                SubLObject _prev_bind_0_29 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                try {
                                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                                    html_princ($str_alt77$_);
                                                                } finally {
                                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_29, thread);
                                                                }
                                                            }
                                                            html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                                            html_markup(html_macros.$html_subscript_tail$.getGlobalValue());
                                                            html_checkbox_input($str_alt78$, $str_alt78$, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_28, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_30 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_princ($str_alt80$Asserted_in_KB__but_redundant_);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_30, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_27, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_31 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    if (true) {
                                                        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_align($RIGHT));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    if (NIL != html_macros.$html_color_light_green$.getDynamicValue(thread)) {
                                                        html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_macros.$html_color_light_green$.getDynamicValue(thread));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_32 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            {
                                                                SubLObject image_src = cb_icon_file_path($GREEN_BALL);
                                                                SubLObject align = $TOP;
                                                                SubLObject alt = cb_get_icon_alt_string($GREEN_BALL);
                                                                SubLObject border = ZERO_INTEGER;
                                                                html_markup(html_macros.$html_image_head$.getGlobalValue());
                                                                html_markup(html_macros.$html_image_src$.getGlobalValue());
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                                html_markup(image_src);
                                                                html_char(CHAR_quotation, UNPROVIDED);
                                                                if (NIL != alt) {
                                                                    html_markup(html_macros.$html_image_alt$.getGlobalValue());
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                    html_markup(alt);
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                }
                                                                if (NIL != align) {
                                                                    html_markup(html_macros.$html_image_align$.getGlobalValue());
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                    html_markup(html_align(align));
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                }
                                                                if (NIL != border) {
                                                                    html_markup(html_macros.$html_image_border$.getGlobalValue());
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                    html_markup(border);
                                                                    html_char(CHAR_quotation, UNPROVIDED);
                                                                }
                                                                html_char(CHAR_greater, UNPROVIDED);
                                                                {
                                                                    SubLObject _prev_bind_0_33 = html_macros.$html_safe_print$.currentBinding(thread);
                                                                    try {
                                                                        html_macros.$html_safe_print$.bind(T, thread);
                                                                    } finally {
                                                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_33, thread);
                                                                    }
                                                                }
                                                                html_markup(html_macros.$html_image_tail$.getGlobalValue());
                                                                html_checkbox_input($str_alt78$, $str_alt78$, NIL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                            }
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_32, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_34 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_princ($str_alt82$Shown_as_asserted_in_KB_by_a_forw);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_34, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_31, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_35 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    if (true) {
                                                        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_align($RIGHT));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    if (NIL != html_macros.$html_color_light_green$.getDynamicValue(thread)) {
                                                        html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_macros.$html_color_light_green$.getDynamicValue(thread));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_36 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_checkbox_input($str_alt78$, $str_alt78$, NIL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_36, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_37 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_princ($str_alt83$Not_asserted_in_KB__but_true_);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_37, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_35, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_38 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    if (true) {
                                                        html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_align($RIGHT));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    if (NIL != html_macros.$html_color_lighter_grey$.getDynamicValue(thread)) {
                                                        html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                        html_markup(html_macros.$html_color_lighter_grey$.getDynamicValue(thread));
                                                        html_char(CHAR_quotation, UNPROVIDED);
                                                    }
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_39 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_checkbox_input($str_alt78$, $str_alt78$, NIL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_39, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                    html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                                    html_char(CHAR_greater, UNPROVIDED);
                                                    {
                                                        SubLObject _prev_bind_0_40 = html_macros.$html_safe_print$.currentBinding(thread);
                                                        try {
                                                            html_macros.$html_safe_print$.bind(T, thread);
                                                            html_princ($str_alt84$Neither_asserted_in_KB_nor_true_);
                                                        } finally {
                                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_40, thread);
                                                        }
                                                    }
                                                    html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_38, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                                            html_source_readability_terpri(UNPROVIDED);
                                        } finally {
                                            html_macros.$html_safe_print$.rebind(_prev_bind_0_19, thread);
                                        }
                                    }
                                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_18, thread);
                                }
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_17, thread);
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

    /**
     * Print a legend explaining the symbols used on the Similarity Tool page.
     */
    @LispMethod(comment = "Print a legend explaining the symbols used on the Similarity Tool page.")
    public static SubLObject st_html_legend() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        html_markup(html_macros.$html_table_head$.getGlobalValue());
        html_markup(html_macros.$html_table_border$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ONE_INTEGER);
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
            final SubLObject _prev_bind_0_$20 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$21 = html_macros.$html_safe_print$.currentBinding(thread);
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
                    html_markup(THREE_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$22 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$23 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_colspan$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(TWO_INTEGER);
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_align($CENTER));
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$24 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_font_head$.getGlobalValue());
                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(FOUR_INTEGER);
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_char(CHAR_greater, UNPROVIDED);
                                final SubLObject _prev_bind_0_$25 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_princ($$$L_E_G_E_N_D);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$25, thread);
                                }
                                html_markup(html_macros.$html_font_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$24, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$23, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$26 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_align($RIGHT));
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_color_light_yellow$.getGlobalValue()) {
                                html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_macros.$html_color_light_yellow$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$27 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_subscript_head$.getGlobalValue());
                                html_markup(html_macros.$html_font_head$.getGlobalValue());
                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(EIGHT_INTEGER);
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_char(CHAR_greater, UNPROVIDED);
                                final SubLObject _prev_bind_0_$28 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_princ($str85$_);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$28, thread);
                                }
                                html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                html_markup(html_macros.$html_subscript_tail$.getGlobalValue());
                                html_checkbox_input($str86$, $str86$, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$27, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$29 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_princ($str87$Asserted_in_KB_);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$29, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$26, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$30 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_align($RIGHT));
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_color_light_red$.getGlobalValue()) {
                                html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_macros.$html_color_light_red$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$31 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_subscript_head$.getGlobalValue());
                                html_markup(html_macros.$html_font_head$.getGlobalValue());
                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(EIGHT_INTEGER);
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_char(CHAR_greater, UNPROVIDED);
                                final SubLObject _prev_bind_0_$32 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                    html_princ($str85$_);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$32, thread);
                                }
                                html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                html_markup(html_macros.$html_subscript_tail$.getGlobalValue());
                                html_checkbox_input($str86$, $str86$, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$31, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$33 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_princ($str88$Asserted_in_KB__but_redundant_);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$33, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$30, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$34 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_align($RIGHT));
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_color_light_green$.getGlobalValue()) {
                                html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_macros.$html_color_light_green$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$35 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                final SubLObject image_src = cyc_file_dependencies.cb_icon_file_path($GREEN_BALL);
                                final SubLObject align = $TOP;
                                final SubLObject alt = cyc_file_dependencies.cb_get_icon_alt_string($GREEN_BALL);
                                final SubLObject border = ZERO_INTEGER;
                                html_markup(html_macros.$html_image_head$.getGlobalValue());
                                html_markup(html_macros.$html_image_src$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(image_src);
                                html_char(CHAR_quotation, UNPROVIDED);
                                if (NIL != alt) {
                                    html_markup(html_macros.$html_image_alt$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(alt);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (NIL != align) {
                                    html_markup(html_macros.$html_image_align$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align(align));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                if (NIL != border) {
                                    html_markup(html_macros.$html_image_border$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(border);
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                final SubLObject _prev_bind_0_$36 = html_macros.$html_safe_print$.currentBinding(thread);
                                try {
                                    html_macros.$html_safe_print$.bind(T, thread);
                                } finally {
                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$36, thread);
                                }
                                html_markup(html_macros.$html_image_tail$.getGlobalValue());
                                html_checkbox_input($str86$, $str86$, NIL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$35, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$37 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_princ($str90$Shown_as_asserted_in_KB_by_a_forw);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$37, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$34, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$38 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_align($RIGHT));
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_color_light_green$.getGlobalValue()) {
                                html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_macros.$html_color_light_green$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$39 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_checkbox_input($str86$, $str86$, NIL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$39, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$40 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_princ($str91$Not_asserted_in_KB__but_true_);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$40, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$38, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        final SubLObject _prev_bind_0_$41 = html_macros.$html_safe_print$.currentBinding(thread);
                        try {
                            html_macros.$html_safe_print$.bind(T, thread);
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                            html_char(CHAR_quotation, UNPROVIDED);
                            html_markup(html_align($RIGHT));
                            html_char(CHAR_quotation, UNPROVIDED);
                            if (NIL != html_macros.$html_color_lighter_grey$.getGlobalValue()) {
                                html_markup(html_macros.$html_table_data_bgcolor$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                                html_markup(html_macros.$html_color_lighter_grey$.getGlobalValue());
                                html_char(CHAR_quotation, UNPROVIDED);
                            }
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$42 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_checkbox_input($str86$, $str86$, NIL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$42, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                            html_char(CHAR_greater, UNPROVIDED);
                            final SubLObject _prev_bind_0_$43 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_princ($str92$Neither_asserted_in_KB_nor_true_);
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_$43, thread);
                            }
                            html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                        } finally {
                            html_macros.$html_safe_print$.rebind(_prev_bind_0_$41, thread);
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$22, thread);
                    }
                    html_markup(html_macros.$html_table_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$21, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$20, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_tail$.getGlobalValue());
        return NIL;
    }

    /**
     * Process the checkboxed on the Similarity Tool page to figure out what assertions to make/unmake.  Present the choices to the user for confirmation.
     */
    @LispMethod(comment = "Process the checkboxed on the Similarity Tool page to figure out what assertions to make/unmake.  Present the choices to the user for confirmation.")
    public static final SubLObject cb_similarity_assert_alt(SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject assertion_hash = make_hash_table($int$64, EQUAL, UNPROVIDED);
                if (NIL != ke.cyclist_is_guest()) {
                    cyc_navigator_internals.guest_warn($$$the_Similarity_Tool_page);
                    return NIL;
                }
                {
                    SubLObject cdolist_list_var = args;
                    SubLObject arg = NIL;
                    for (arg = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg = cdolist_list_var.first()) {
                        if (arg.isList()) {
                            {
                                SubLObject assertion = arg.first();
                                SubLObject indicator = second(arg);
                                SubLObject cur_state = gethash(assertion, assertion_hash, UNPROVIDED);
                                if (indicator.equal($$$x)) {
                                    if (NIL == cur_state) {
                                        sethash(assertion, assertion_hash, ASSERT);
                                    } else {
                                        sethash(assertion, assertion_hash, DO_NOTHING);
                                    }
                                }
                                if (indicator.equal($$$a)) {
                                    if (NIL == cur_state) {
                                        sethash(assertion, assertion_hash, UNASSERT);
                                    } else {
                                        sethash(assertion, assertion_hash, DO_NOTHING);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject k = NIL;
                    SubLObject v = NIL;
                    {
                        final Iterator cdohash_iterator = getEntrySetIterator(assertion_hash);
                        try {
                            while (iteratorHasNext(cdohash_iterator)) {
                                final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                                k = getEntryKey(cdohash_entry);
                                v = getEntryValue(cdohash_entry);
                                thread.resetMultipleValues();
                                {
                                    SubLObject pred = com.cyc.cycjava.cycl.cb_similarity.st_break_a_string(k);
                                    SubLObject mt = thread.secondMultipleValue();
                                    SubLObject col = thread.thirdMultipleValue();
                                    SubLObject v_const = thread.fourthMultipleValue();
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject pcase_var = v;
                                        if (pcase_var.eql(ASSERT)) {
                                            format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt89$Asserting____A__A__A__in__A, new SubLObject[]{ pred, v_const, col, mt });
                                            html_newline(UNPROVIDED);
                                            html_princ($str_alt90$Result__);
                                            cb_form(ke.ke_assert_now(list(pred, v_const, col), mt, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                                            html_newline(TWO_INTEGER);
                                        } else {
                                            if (pcase_var.eql(UNASSERT)) {
                                                format(html_macros.$html_stream$.getDynamicValue(thread), $str_alt91$Unasserting____A__A__A__from__A, new SubLObject[]{ pred, v_const, col, mt });
                                                html_newline(UNPROVIDED);
                                                html_princ($str_alt90$Result__);
                                                cb_form(ke.ke_unassert_now(list(pred, v_const, col), mt), UNPROVIDED, UNPROVIDED);
                                                html_newline(TWO_INTEGER);
                                            }
                                        }
                                    }
                                }
                            } 
                        } finally {
                            releaseEntrySetIterator(cdohash_iterator);
                        }
                    }
                }
                com.cyc.cycjava.cycl.cb_similarity.st_similarity_entrance(NIL);
            }
            return NIL;
        }
    }

    /**
     * Process the checkboxed on the Similarity Tool page to figure out what assertions to make/unmake.  Present the choices to the user for confirmation.
     */
    @LispMethod(comment = "Process the checkboxed on the Similarity Tool page to figure out what assertions to make/unmake.  Present the choices to the user for confirmation.")
    public static SubLObject cb_similarity_assert(final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject assertion_hash = make_hash_table($int$64, EQUAL, UNPROVIDED);
        if (NIL != ke.cyclist_is_guest()) {
            cyc_navigator_internals.guest_warn($$$the_Similarity_Tool_page);
            return NIL;
        }
        SubLObject cdolist_list_var = args;
        SubLObject arg = NIL;
        arg = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (arg.isList()) {
                final SubLObject assertion = arg.first();
                final SubLObject indicator = second(arg);
                final SubLObject cur_state = gethash(assertion, assertion_hash, UNPROVIDED);
                if (indicator.equal($$$x)) {
                    if (NIL == cur_state) {
                        sethash(assertion, assertion_hash, ASSERT);
                    } else {
                        sethash(assertion, assertion_hash, DO_NOTHING);
                    }
                }
                if (indicator.equal($$$a)) {
                    if (NIL == cur_state) {
                        sethash(assertion, assertion_hash, UNASSERT);
                    } else {
                        sethash(assertion, assertion_hash, DO_NOTHING);
                    }
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            arg = cdolist_list_var.first();
        } 
        SubLObject k = NIL;
        SubLObject v = NIL;
        final Iterator cdohash_iterator = getEntrySetIterator(assertion_hash);
        try {
            while (iteratorHasNext(cdohash_iterator)) {
                final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                k = getEntryKey(cdohash_entry);
                v = getEntryValue(cdohash_entry);
                thread.resetMultipleValues();
                final SubLObject pred = st_break_a_string(k);
                final SubLObject mt = thread.secondMultipleValue();
                final SubLObject col = thread.thirdMultipleValue();
                final SubLObject v_const = thread.fourthMultipleValue();
                thread.resetMultipleValues();
                final SubLObject pcase_var = v;
                if (pcase_var.eql(ASSERT)) {
                    format(html_macros.$html_stream$.getDynamicValue(thread), $str97$Asserting____A__A__A__in__A, new SubLObject[]{ pred, v_const, col, mt });
                    html_newline(UNPROVIDED);
                    html_princ($str98$Result__);
                    cb_form(ke.ke_assert_now(list(pred, v_const, col), mt, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                    html_newline(TWO_INTEGER);
                } else {
                    if (!pcase_var.eql(UNASSERT)) {
                        continue;
                    }
                    format(html_macros.$html_stream$.getDynamicValue(thread), $str99$Unasserting____A__A__A__from__A, new SubLObject[]{ pred, v_const, col, mt });
                    html_newline(UNPROVIDED);
                    html_princ($str98$Result__);
                    cb_form(ke.ke_unassert_now(list(pred, v_const, col), mt), UNPROVIDED, UNPROVIDED);
                    html_newline(TWO_INTEGER);
                }
            } 
        } finally {
            releaseEntrySetIterator(cdohash_iterator);
        }
        st_similarity_entrance(NIL);
        return NIL;
    }/**
     * Process the checkboxed on the Similarity Tool page to figure out what assertions to make/unmake.  Present the choices to the user for confirmation.
     */


    /**
     * Returns a string encoding the parameters.  For use in labelling checkboxes.
     */
    @LispMethod(comment = "Returns a string encoding the parameters.  For use in labelling checkboxes.")
    public static final SubLObject st_make_a_string_alt(SubLObject pred, SubLObject mt, SubLObject col, SubLObject v_const, SubLObject truth) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        if (col == UNPROVIDED) {
            col = NIL;
        }
        if (v_const == UNPROVIDED) {
            v_const = NIL;
        }
        if (truth == UNPROVIDED) {
            truth = NIL;
        }
        {
            SubLObject pred_id = cb_fort_identifier(pred);
            SubLObject mt_id = cb_fort_identifier(mt);
            SubLObject col_id = cb_fort_identifier(col);
            SubLObject const_id = cb_fort_identifier(v_const);
            if (NIL != pred) {
                if (NIL != mt) {
                    if (NIL != col) {
                        if (NIL != v_const) {
                            if (NIL != truth) {
                                return format(NIL, $str_alt93$st__A__A__A__A__A_, new SubLObject[]{ pred_id, mt_id, col_id, const_id, truth });
                            }
                            return format(NIL, $str_alt94$st__A__A__A__A_, new SubLObject[]{ pred_id, mt_id, col_id, const_id });
                        }
                        return format(NIL, $str_alt95$st__A__A__A_, new SubLObject[]{ pred_id, mt_id, col_id });
                    }
                    return format(NIL, $str_alt96$st__A__A_, pred_id, mt_id);
                }
                return format(NIL, $str_alt97$st__A_, pred_id);
            }
            return NIL;
        }
    }

    @LispMethod(comment = "Returns a string encoding the parameters.  For use in labelling checkboxes.")
    public static SubLObject st_make_a_string(final SubLObject pred, SubLObject mt, SubLObject col, SubLObject v_const, SubLObject truth) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        if (col == UNPROVIDED) {
            col = NIL;
        }
        if (v_const == UNPROVIDED) {
            v_const = NIL;
        }
        if (truth == UNPROVIDED) {
            truth = NIL;
        }
        final SubLObject pred_id = cb_fort_identifier(pred);
        final SubLObject mt_id = cb_fort_identifier(mt);
        final SubLObject col_id = cb_fort_identifier(col);
        final SubLObject const_id = cb_fort_identifier(v_const);
        if (NIL == pred) {
            return NIL;
        }
        if (NIL == mt) {
            return format(NIL, $str105$st__A_, pred_id);
        }
        if (NIL == col) {
            return format(NIL, $str104$st__A__A_, pred_id, mt_id);
        }
        if (NIL == v_const) {
            return format(NIL, $str103$st__A__A__A_, new SubLObject[]{ pred_id, mt_id, col_id });
        }
        if (NIL != truth) {
            return format(NIL, $str101$st__A__A__A__A__A_, new SubLObject[]{ pred_id, mt_id, col_id, const_id, truth });
        }
        return format(NIL, $str102$st__A__A__A__A_, new SubLObject[]{ pred_id, mt_id, col_id, const_id });
    }/**
     * Returns a string encoding the parameters.  For use in labelling checkboxes.
     */


    /**
     * Breaks string s up into the component pred, mt, col, const, and truth.
     */
    @LispMethod(comment = "Breaks string s up into the component pred, mt, col, const, and truth.")
    public static final SubLObject st_break_a_string_alt(SubLObject s) {
        {
            SubLObject s_list = string_utilities.string_tokenize(s, $list_alt98, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject pred = cb_guess_fort(nth(ONE_INTEGER, s_list), UNPROVIDED);
            SubLObject mt = cb_guess_fort(nth(TWO_INTEGER, s_list), UNPROVIDED);
            SubLObject col = cb_guess_fort(nth(THREE_INTEGER, s_list), UNPROVIDED);
            SubLObject v_const = cb_guess_fort(nth(FOUR_INTEGER, s_list), UNPROVIDED);
            return values(pred, mt, col, v_const);
        }
    }

    @LispMethod(comment = "Breaks string s up into the component pred, mt, col, const, and truth.")
    public static SubLObject st_break_a_string(final SubLObject s) {
        final SubLObject s_list = string_utilities.string_tokenize(s, $list106, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject pred = cb_guess_fort(nth(ONE_INTEGER, s_list), UNPROVIDED);
        final SubLObject mt = cb_guess_fort(nth(TWO_INTEGER, s_list), UNPROVIDED);
        final SubLObject col = cb_guess_fort(nth(THREE_INTEGER, s_list), UNPROVIDED);
        final SubLObject v_const = cb_guess_fort(nth(FOUR_INTEGER, s_list), UNPROVIDED);
        return values(pred, mt, col, v_const);
    }/**
     * Breaks string s up into the component pred, mt, col, const, and truth.
     */


    public static SubLObject declare_cb_similarity_file() {
        declareFunction("st_similarity_entrance", "ST-SIMILARITY-ENTRANCE", 0, 1, false);
        declareFunction("cb_link_similarity", "CB-LINK-SIMILARITY", 0, 1, false);
        declareFunction("st_similarity_entrance_internal", "ST-SIMILARITY-ENTRANCE-INTERNAL", 0, 0, false);
        declareFunction("st_similarity_show_recent_history", "ST-SIMILARITY-SHOW-RECENT-HISTORY", 0, 0, false);
        declareFunction("st_similarity_constant_history_items", "ST-SIMILARITY-CONSTANT-HISTORY-ITEMS", 0, 0, false);
        declareFunction("st_similarity_nat_history_items", "ST-SIMILARITY-NAT-HISTORY-ITEMS", 0, 0, false);
        declareFunction("cb_similarity", "CB-SIMILARITY", 1, 0, false);
        declareFunction("st_html_td_pipes", "ST-HTML-TD-PIPES", 1, 0, false);
        declareFunction("st_html_table_top", "ST-HTML-TABLE-TOP", 1, 0, false);
        declareFunction("st_html_tree", "ST-HTML-TREE", 1, 2, false);
        declareFunction("st_html_print_tree", "ST-HTML-PRINT-TREE", 4, 0, false);
        declareFunction("st_html_print_node", "ST-HTML-PRINT-NODE", 4, 0, false);
        declareFunction("st_html_legend", "ST-HTML-LEGEND", 0, 0, false);
        declareFunction("cb_similarity_assert", "CB-SIMILARITY-ASSERT", 1, 0, false);
        declareFunction("st_make_a_string", "ST-MAKE-A-STRING", 1, 4, false);
        declareFunction("st_break_a_string", "ST-BREAK-A-STRING", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_cb_similarity_file() {
        return NIL;
    }

    public static final SubLObject setup_cb_similarity_file_alt() {
        sethash($BLUE_BALL, $cb_icon_definitions$.getGlobalValue(), list($str_alt1$blue_gif, NIL));
        sethash($GREEN_BALL, $cb_icon_definitions$.getGlobalValue(), list($str_alt3$green_gif, NIL));
        sethash($RED_BALL, $cb_icon_definitions$.getGlobalValue(), list($str_alt5$red_gif, NIL));
        sethash($WHITE_BALL, $cb_icon_definitions$.getGlobalValue(), list($str_alt7$white_gif, NIL));
        sethash($YELLOW_BALL, $cb_icon_definitions$.getGlobalValue(), list($str_alt9$yellow_gif, NIL));
        html_macros.note_html_handler_function(ST_SIMILARITY_ENTRANCE);
        setup_cb_link_method($SIMILARITY, CB_LINK_SIMILARITY, ONE_INTEGER);
        declare_cb_tool($SIMILARITY, $$$Similarity, $$$Sim, $$$Similarity_Tool);
        sethash($ST_SIMILARITY, $cb_help_definitions$.getGlobalValue(), list($str_alt19$st_similarity_html, NIL));
        html_macros.note_html_handler_function(CB_SIMILARITY);
        html_macros.note_html_handler_function(CB_SIMILARITY_ASSERT);
        return NIL;
    }

    public static SubLObject setup_cb_similarity_file() {
        if (SubLFiles.USE_V1) {
            sethash($BLUE_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str1$blue_gif, NIL));
            sethash($GREEN_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str3$green_gif, NIL));
            sethash($RED_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str5$red_gif, NIL));
            sethash($WHITE_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str7$white_gif, NIL));
            sethash($YELLOW_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str9$yellow_gif, NIL));
            html_macros.note_cgi_handler_function(ST_SIMILARITY_ENTRANCE, $HTML_HANDLER);
            setup_cb_link_method($SIMILARITY, CB_LINK_SIMILARITY, ONE_INTEGER);
            declare_deprecated_cb_tool($SIMILARITY, $$$Similarity, $$$Sim, $$$Similarity_Tool);
            sethash($ST_SIMILARITY, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list($str20$st_similarity_html, NIL));
            html_macros.note_cgi_handler_function(CB_SIMILARITY, $HTML_HANDLER);
            html_macros.note_cgi_handler_function(CB_SIMILARITY_ASSERT, $HTML_HANDLER);
        }
        if (SubLFiles.USE_V2) {
            html_macros.note_html_handler_function(ST_SIMILARITY_ENTRANCE);
            declare_cb_tool($SIMILARITY, $$$Similarity, $$$Sim, $$$Similarity_Tool);
            sethash($ST_SIMILARITY, $cb_help_definitions$.getGlobalValue(), list($str_alt19$st_similarity_html, NIL));
            html_macros.note_html_handler_function(CB_SIMILARITY);
            html_macros.note_html_handler_function(CB_SIMILARITY_ASSERT);
        }
        return NIL;
    }

    public static SubLObject setup_cb_similarity_file_Previous() {
        sethash($BLUE_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str1$blue_gif, NIL));
        sethash($GREEN_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str3$green_gif, NIL));
        sethash($RED_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str5$red_gif, NIL));
        sethash($WHITE_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str7$white_gif, NIL));
        sethash($YELLOW_BALL, cyc_file_dependencies.$cb_icon_definitions$.getGlobalValue(), list($str9$yellow_gif, NIL));
        html_macros.note_cgi_handler_function(ST_SIMILARITY_ENTRANCE, $HTML_HANDLER);
        setup_cb_link_method($SIMILARITY, CB_LINK_SIMILARITY, ONE_INTEGER);
        declare_deprecated_cb_tool($SIMILARITY, $$$Similarity, $$$Sim, $$$Similarity_Tool);
        sethash($ST_SIMILARITY, cyc_file_dependencies.$cb_help_definitions$.getGlobalValue(), list($str20$st_similarity_html, NIL));
        html_macros.note_cgi_handler_function(CB_SIMILARITY, $HTML_HANDLER);
        html_macros.note_cgi_handler_function(CB_SIMILARITY_ASSERT, $HTML_HANDLER);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_cb_similarity_file();
    }

    @Override
    public void initializeVariables() {
        init_cb_similarity_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_cb_similarity_file();
    }

    static {
    }

    static private final SubLString $str_alt1$blue_gif = makeString("blue.gif");

    static private final SubLString $str_alt3$green_gif = makeString("green.gif");

    static private final SubLString $str_alt5$red_gif = makeString("red.gif");

    static private final SubLString $str_alt7$white_gif = makeString("white.gif");

    static private final SubLString $str_alt9$yellow_gif = makeString("yellow.gif");

    static private final SubLString $str_alt13$st_similarity_entrance = makeString("st-similarity-entrance");

    static private final SubLString $str_alt19$st_similarity_html = makeString("st-similarity.html");

    static private final SubLString $str_alt22$text_javascript = makeString("text/javascript");

    static private final SubLString $str_alt23$yui_skin_sam = makeString("yui-skin-sam");

    static private final SubLString $str_alt25$cg_cb_similarity = makeString("cg?cb-similarity");

    static private final SubLString $str_alt27$cb_similarity = makeString("cb-similarity");

    static private final SubLString $str_alt29$Please_choose_two_or_more_constan = makeString("Please choose two or more constants.");

    static private final SubLString $str_alt30$Recent_Constants__ = makeString("Recent Constants :");

    static private final SubLString $str_alt32$Recent_NATs__ = makeString("Recent NATs :");

    static private final SubLSymbol $sym33$VALID_CONSTANT_ = makeSymbol("VALID-CONSTANT?");

    static private final SubLSymbol $sym34$VALID_NART_ = makeSymbol("VALID-NART?");

    static private final SubLString $str_alt36$Cannot_show_similarity_for_just_o = makeString("Cannot show similarity for just one constant: ~A.");

    static private final SubLString $str_alt38$Check_or_uncheck_the_boxes_to_mak = makeString("Check or uncheck the boxes to make or remove assertions.");

    static private final SubLString $str_alt41$cb_similarity_assert = makeString("cb-similarity-assert");

    static private final SubLString $str_alt48$_nbsp_ = makeString("&nbsp;");

    static private final SubLString $str_alt53$_ = makeString("(");

    static private final SubLString $str_alt54$_ = makeString(")");

    static private final SubLString $str_alt56$_cccccc = makeString("#cccccc");

    static private final SubLString $str_alt57$_dddddd = makeString("#dddddd");

    static private final SubLString $str_alt58$___ = makeString("|__");

    static private final SubLString $str_alt59$__A_ = makeString("(~A)");

    static private final SubLString $str_alt63$BIA_ = makeString("BIA ");

    static private final SubLString $str_alt64$BI__ = makeString("BI_ ");

    static private final SubLString $str_alt65$B_A_ = makeString("B_A ");

    static private final SubLString $str_alt66$B___ = makeString("B__ ");

    static private final SubLString $str_alt67$_IA_ = makeString("_IA ");

    static private final SubLString $str_alt68$_I__ = makeString("_I_ ");

    static private final SubLString $str_alt69$__A_ = makeString("__A ");

    static private final SubLString $str_alt70$____ = makeString("___ ");

    static private final SubLString $str_alt72$cg_cb_af__a = makeString("cg?cb-af&~a");

    static private final SubLString $str_alt77$_ = makeString("*");

    static private final SubLString $str_alt79$Asserted_in_KB_ = makeString("Asserted in KB.");

    static private final SubLString $str_alt80$Asserted_in_KB__but_redundant_ = makeString("Asserted in KB, but redundant.");

    static private final SubLString $str_alt82$Shown_as_asserted_in_KB_by_a_forw = makeString("Shown as asserted in KB by a forward rule.");

    static private final SubLString $str_alt83$Not_asserted_in_KB__but_true_ = makeString("Not asserted in KB, but true.");

    static private final SubLString $str_alt84$Neither_asserted_in_KB_nor_true_ = makeString("Neither asserted in KB nor true.");

    static private final SubLString $str_alt89$Asserting____A__A__A__in__A = makeString("Asserting: (~A ~A ~A) in ~A");

    static private final SubLString $str_alt90$Result__ = makeString("Result: ");

    static private final SubLString $str_alt91$Unasserting____A__A__A__from__A = makeString("Unasserting: (~A ~A ~A) from ~A");

    static private final SubLString $str_alt93$st__A__A__A__A__A_ = makeString("st|~A|~A|~A|~A|~A|");

    static private final SubLString $str_alt94$st__A__A__A__A_ = makeString("st|~A|~A|~A|~A|");

    static private final SubLString $str_alt95$st__A__A__A_ = makeString("st|~A|~A|~A|");

    static private final SubLString $str_alt96$st__A__A_ = makeString("st|~A|~A|");

    static private final SubLString $str_alt97$st__A_ = makeString("st|~A|");

    static private final SubLList $list_alt98 = list(makeString("|"));
}

/**
 * Total time: 772 ms
 */
