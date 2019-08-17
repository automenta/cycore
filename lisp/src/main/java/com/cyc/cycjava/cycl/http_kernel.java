/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;

import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.cb_utilities.cb_error;
import static com.cyc.cycjava.cycl.cyc_testing.generic_testing.define_test_case_table_int;
import static com.cyc.cycjava.cycl.file_utilities.get_file_length;
import static com.cyc.cycjava.cycl.file_utilities.relative_filename;
import static com.cyc.cycjava.cycl.html_utilities.html_construct_message_from_multipart_data;
import static com.cyc.cycjava.cycl.html_utilities.html_extract_input;
import static com.cyc.cycjava.cycl.html_utilities.html_parse_multipart_form_data;
import static com.cyc.cycjava.cycl.html_utilities.html_request_arguments_are_multipart_dataP;
import static com.cyc.cycjava.cycl.numeric_date_utilities.encode_universal_date;
import static com.cyc.cycjava.cycl.numeric_date_utilities.this_year;
import static com.cyc.cycjava.cycl.numeric_date_utilities.today;
import static com.cyc.cycjava.cycl.numeric_date_utilities.universal_date_add_days;
import static com.cyc.cycjava.cycl.numeric_date_utilities.universal_time_for_start_of_universal_date;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.cycjava.cycl.subl_macro_promotions.validate_tcp_connection;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_question;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.code_char;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.$package$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.find_package;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.position;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.search;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.subseq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$process_normal_priority$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.current_process;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.process_priority;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.set_process_priority;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.valid_process_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.resetMultipleValues;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.assoc;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.putf;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_readably$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.princ;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.$read_default_float_format$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.$read_eval$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.parse_integer;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.read_from_string;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.close;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.force_output;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.get_output_stream_string;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.interactive_stream_p;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.make_private_string_output_stream;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.read_byte;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.read_char;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.read_line;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_char;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_string;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;
import static com.cyc.tool.subl.util.SubLFiles.defvar;

import org.armedbear.lisp.Debug;
import org.armedbear.lisp.Main;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Environment;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Filesys;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.StreamsLow;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLPackage;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.compatibility;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.stream_macros;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;

/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      HTTP-KERNEL
 * source file: /cyc/top/cycl/http-kernel.lisp
 * created:     2019/07/03 17:38:14
 */
public final class http_kernel extends SubLTranslatedFile implements V12 {
    /**
     * If the given FILENAME is a java web start request, then the first value returned is the filename stripped of the the cyclist, the second value returned is the cyclist and the third value is the project (if present).  Otherwise the first value returned is the FILENAME and the second and third values returned are nil.
     */
    @LispMethod(comment = "If the given FILENAME is a java web start request, then the first value returned is the filename stripped of the the cyclist, the second value returned is the cyclist and the third value is the project (if present).  Otherwise the first value returned is the FILENAME and the second and third values returned are nil.")
    public static final SubLObject separate_args_from_java_web_start_request(SubLObject filename) {
	{
	    SubLObject qm_position = string_utilities.char_position(CHAR_question, filename, UNPROVIDED);
	    if (NIL == qm_position) {
		return values(filename, NIL, NIL);
	    }
	    if ($str_alt36$.equalp(subseq(filename, add(qm_position, ONE_INTEGER), UNPROVIDED))) {
		return values(subseq(filename, ZERO_INTEGER, qm_position), NIL, NIL);
	    }
	    {
		SubLObject just_the_filename = subseq(filename, ZERO_INTEGER, qm_position);
		SubLObject args_string = subseq(filename, add(qm_position, ONE_INTEGER), UNPROVIDED);
		SubLObject args = html_kernel.parse_html_handler_arg_string(args_string, ZERO_INTEGER);
		SubLObject cyclist_string = html_extract_input($$$user, args);
		SubLObject project_string = html_extract_input($$$project, args);
		SubLObject cyclist = czer_main.canonicalize_term(read_from_string(web_utilities.html_url_decode(cyclist_string), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED);
		SubLObject project = (NIL != project_string) ? ((SubLObject) (czer_main.canonicalize_term(read_from_string(web_utilities.html_url_decode(project_string), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED))) : NIL;
		return values(just_the_filename, cyclist, project);
	    }
	}
    }

    public static final SubLObject mime_type_from_handler_function(SubLObject handler) {
	return dictionary.dictionary_lookup(html_macros.$handler_mime_types$.getGlobalValue(), handler, UNPROVIDED);
    }

    /**
     * Fake being an HTTP server just to see what message a client will pass to it.
     */
    @LispMethod(comment = "Fake being an HTTP server just to see what message a client will pass to it.")
    public static final SubLObject http_trace_server_top_level(SubLObject in_stream, SubLObject out_stream) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject _prev_bind_0 = $package$.currentBinding(thread);
		SubLObject _prev_bind_1 = $read_default_float_format$.currentBinding(thread);
		SubLObject _prev_bind_2 = $print_readably$.currentBinding(thread);
		SubLObject _prev_bind_3 = $read_eval$.currentBinding(thread);
		try {
		    $package$.bind(find_package($$$CYC), thread);
		    $read_default_float_format$.bind(DOUBLE_FLOAT, thread);
		    $print_readably$.bind(NIL, thread);
		    $read_eval$.bind(NIL, thread);
		    {
			SubLObject old_priority = process_priority(current_process());
			try {
			    set_process_priority(current_process(), $process_normal_priority$.getGlobalValue());
			    while (true) {
				write_char(read_char(in_stream, UNPROVIDED, UNPROVIDED, UNPROVIDED), StreamsLow.$standard_output$.getDynamicValue(thread));
			    }
			} finally {
			    {
				SubLObject _prev_bind_0_8 = $is_thread_performing_cleanupP$.currentBinding(thread);
				try {
				    $is_thread_performing_cleanupP$.bind(T, thread);
				    set_process_priority(current_process(), old_priority);
				} finally {
				    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_8, thread);
				}
			    }
			}
		    }
		} finally {
		    $read_eval$.rebind(_prev_bind_3, thread);
		    $print_readably$.rebind(_prev_bind_2, thread);
		    $read_default_float_format$.rebind(_prev_bind_1, thread);
		    $package$.rebind(_prev_bind_0, thread);
		}
	    }
	}
    }

    public static final SubLObject http_trace_server_handler(SubLObject in_stream, SubLObject out_stream) {
	return com.cyc.cycjava.cycl.http_kernel.http_trace_server_top_level(in_stream, out_stream);
    }

    /**
     * Echo back the first line from IN-STREAM to OUT-STREAM.
     */
    @LispMethod(comment = "Echo back the first line from IN-STREAM to OUT-STREAM.")
    public static final SubLObject echo_server_top_level(SubLObject in_stream, SubLObject out_stream) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject _prev_bind_0 = $package$.currentBinding(thread);
		SubLObject _prev_bind_1 = $read_default_float_format$.currentBinding(thread);
		SubLObject _prev_bind_2 = $print_readably$.currentBinding(thread);
		SubLObject _prev_bind_3 = $read_eval$.currentBinding(thread);
		try {
		    $package$.bind(find_package($$$CYC), thread);
		    $read_default_float_format$.bind(DOUBLE_FLOAT, thread);
		    $print_readably$.bind(NIL, thread);
		    $read_eval$.bind(NIL, thread);
		    {
			SubLObject old_priority = process_priority(current_process());
			try {
			    set_process_priority(current_process(), $process_normal_priority$.getGlobalValue());
			    write_string(read_line(in_stream, UNPROVIDED, UNPROVIDED, UNPROVIDED), out_stream, UNPROVIDED, UNPROVIDED);
			} finally {
			    {
				SubLObject _prev_bind_0_9 = $is_thread_performing_cleanupP$.currentBinding(thread);
				try {
				    $is_thread_performing_cleanupP$.bind(T, thread);
				    set_process_priority(current_process(), old_priority);
				} finally {
				    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_9, thread);
				}
			    }
			}
		    }
		} finally {
		    $read_eval$.rebind(_prev_bind_3, thread);
		    $print_readably$.rebind(_prev_bind_2, thread);
		    $read_default_float_format$.rebind(_prev_bind_1, thread);
		    $package$.rebind(_prev_bind_0, thread);
		}
	    }
	    return NIL;
	}
    }

    public static final SubLObject echo_server_handler(SubLObject in_stream, SubLObject out_stream) {
	return com.cyc.cycjava.cycl.http_kernel.echo_server_top_level(in_stream, out_stream);
    }

    static private final SubLString $str_alt36$ = makeString("");

    public static final SubLFile me = new http_kernel();

    public static final String myName = "com.cyc.cycjava.cycl.http_kernel";

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $http_cgi_bin_directory$ = makeSymbol("*HTTP-CGI-BIN-DIRECTORY*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $http_cg_url$ = makeSymbol("*HTTP-CG-URL*");

    // deflexical
    @LispMethod(comment = "Any url accessed through here will be treated as if it were a standard\r\ncgi-bin command, where the next thing is the name of the function to\r\nbe called, and all of the arguments follow it.  Note that this differs\r\nfrom things called using just \'cgi-bin\', since that also searches for \'cg?\'\r\nand assumes that the function to be called is really the first argument\r\nto cg, and the second and subsequent arguments are the arguments for the\r\nfunction.\ndeflexical\nAny url accessed through here will be treated as if it were a standard\ncgi-bin command, where the next thing is the name of the function to\nbe called, and all of the arguments follow it.  Note that this differs\nfrom things called using just \'cgi-bin\', since that also searches for \'cg?\'\nand assumes that the function to be called is really the first argument\nto cg, and the second and subsequent arguments are the arguments for the\nfunction.")
    public static final SubLSymbol $http_cgi_bin_ws_directory$ = makeSymbol("*HTTP-CGI-BIN-WS-DIRECTORY*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $http_cyc_ws_directory$ = makeSymbol("*HTTP-CYC-WS-DIRECTORY*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $http_request_logging_stream$ = makeSymbol("*HTTP-REQUEST-LOGGING-STREAM*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $get_http_logging_sequence_generator$ = makeSymbol("*GET-HTTP-LOGGING-SEQUENCE-GENERATOR*");

    // defconstant
    /**
     * If this string is found in a url, the mime-type for the document will be set
     * to whatever is in the url between this string and the next equal-sign
     */
    @LispMethod(comment = "If this string is found in a url, the mime-type for the document will be set\r\nto whatever is in the url between this string and the next equal-sign\ndefconstant\nIf this string is found in a url, the mime-type for the document will be set\nto whatever is in the url between this string and the next equal-sign")
    public static final SubLSymbol $http_return_mime_type_prefix$ = makeSymbol("*HTTP-RETURN-MIME-TYPE-PREFIX*");

    // defvar
    /**
     * When set to NIL, the HTTP server will not add any expires headers to CSS or
     * Javascript files
     */
    @LispMethod(comment = "When set to NIL, the HTTP server will not add any expires headers to CSS or\r\nJavascript files\ndefvar\nWhen set to NIL, the HTTP server will not add any expires headers to CSS or\nJavascript files")
    private static final SubLSymbol $allow_css_js_expires_headers$ = makeSymbol("*ALLOW-CSS-JS-EXPIRES-HEADERS*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $http_content_type_file_extension_table$ = makeSymbol("*HTTP-CONTENT-TYPE-FILE-EXTENSION-TABLE*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLString $str0$_cgi_bin = makeString("/cgi-bin");

    static private final SubLString $str1$_cg_ = makeString("/cg?");

    static private final SubLString $str2$_cgi_bin_ws_ = makeString("/cgi-bin/ws/");

    private static final SubLString $str3$_cyc_ws = makeString("/cyc-ws");

    public static final SubLSymbol $http_cyc_ws_machine$ = makeSymbol("*HTTP-CYC-WS-MACHINE*");

    public static final SubLSymbol $http_cyc_ws_port$ = makeSymbol("*HTTP-CYC-WS-PORT*");

    private static final SubLSymbol HTTP_SERVER_HANDLER = makeSymbol("HTTP-SERVER-HANDLER");

    private static final SubLString $str9$HTTP_Request__6__0D_received_at__ = makeString("HTTP Request ~6,'0D received at ~A~%");

    private static final SubLString $$$CYC = makeString("CYC");

    private static final SubLString $str12$HTTP_Request__6__0D_content___S__ = makeString("HTTP Request ~6,'0D content: ~S~%");

    private static final SubLString $str14$_ = makeString("/");

    private static final SubLString $str16$_cgi_bin_cg_cb_start_redirect = makeString("/cgi-bin/cg?cb-start-redirect");

    private static final SubLSymbol $IGNORE_ERRORS_TARGET = makeKeyword("IGNORE-ERRORS-TARGET");

    private static final SubLSymbol IGNORE_ERRORS_HANDLER = makeSymbol("IGNORE-ERRORS-HANDLER", "SUBLISP");

    private static final SubLString $str20$HTTP_Request__6__0D_returned_at__ = makeString("HTTP Request ~6,'0D returned at ~A (~Ams)~%");

    private static final SubLSymbol HTTP_LOG_REQUESTS_TO_FILE = makeSymbol("HTTP-LOG-REQUESTS-TO-FILE");

    private static final SubLString $str22$Unable_to_write_to__S__ = makeString("Unable to write to ~S~%");

    private static final SubLString $$$GET = makeString("GET");

    private static final SubLString $str27$Content_length = makeString("Content-length");

    private static final SubLString $str28$Remote_Host = makeString("Remote-Host");

    private static final SubLString $$$unknown = makeString("unknown");

    private static final SubLString $str31$Unable_to_initialize__A__ = makeString("Unable to initialize ~A~%");

    private static final SubLString $str32$_ = makeString("&");

    private static final SubLString $str33$return_mime_type_ = makeString("return-mime-type:");

    private static final SubLString $str34$_ = makeString("?");

    private static final SubLString $str35$_ = makeString("|");

    private static final SubLSymbol $sym36$_ = makeSymbol("<");

    private static final SubLString $str37$return_mime_type = makeString("return-mime-type");

    private static final SubLString $str38$_ = makeString("=");

    private static final SubLString $str39$text_html__charset_utf_8 = makeString("text/html; charset=utf-8");

    private static final SubLString $str40$text_html = makeString("text/html");

    private static final SubLString $str41$application_xml = makeString("application/xml");

    private static final SubLString $str42$application_json = makeString("application/json");

    private static final SubLString $str43$Unknown_return_type_for_handler__ = makeString("Unknown return-type for handler ~A.  Assuming 'text/html'");

    private static final SubLList $list44 = cons(makeString("Connection"), makeString("close"));

    private static final SubLString $str45$Content_Type = makeString("Content-Type");

    private static final SubLString $$$POST = makeString("POST");

    private static final SubLSymbol $kw49$KEEP_ALIVE_ = makeKeyword("KEEP-ALIVE?");

    private static final SubLSymbol $kw50$WIDE_NEWLINES_ = makeKeyword("WIDE-NEWLINES?");

    private static final SubLInteger $int$80 = makeInteger(80);

    private static final SubLSymbol $SOAP_ACTION_URI = makeKeyword("SOAP-ACTION-URI");

    private static final SubLList $list63 = cons(makeSymbol("HEADER-KEY"), makeSymbol("VALUE"));

    private static final SubLString $$$Accept = makeString("Accept");

    private static final SubLList $list65 = list(new SubLObject[] { makeString("User-Agent"), makeString("Host"), makeString("Content-Length"), makeString("Connection"), makeString("Keep-Alive"), makeString("Referer"), makeString("Accept-Charset"), makeString("Accept-Encoding"),
	    makeString("Accept-Language"), makeString("Cache-Control"), makeString("Pragma") });

    private static final SubLString $str67$Unable_to_open__S = makeString("Unable to open ~S");

    private static final SubLInteger $int$50 = makeInteger(50);

    private static final SubLInteger $int$51 = makeInteger(51);

    private static final SubLInteger $int$52 = makeInteger(52);

    private static final SubLList $list75 = list(cons(makeString("Connection"), makeString("close")));

    private static final SubLInteger $int$404 = makeInteger(404);

    private static final SubLString $$$File_not_found = makeString("File not found");

    private static final SubLString $str78$ = makeString("");

    private static final SubLString $$$user = makeString("user");

    private static final SubLString $$$project = makeString("project");

    private static final SubLString $str81$_jnlp = makeString(".jnlp");

    private static final SubLSymbol HTTP_FILE_REQUEST_HEADERS_INT = makeSymbol("HTTP-FILE-REQUEST-HEADERS-INT");

    private static final SubLString $str83$Last_Modified = makeString("Last-Modified");

    private static final SubLList $list84 = cons(makeString("Accept-Ranges"), makeString("bytes"));

    private static final SubLString $str85$Content_Length = makeString("Content-Length");

    private static final SubLString $$$Expires = makeString("Expires");

    private static final SubLSymbol $http_file_request_headers_int_caching_state$ = makeSymbol("*HTTP-FILE-REQUEST-HEADERS-INT-CACHING-STATE*");

    private static final SubLInteger $int$200 = makeInteger(200);

    private static final SubLString $str89$image_ = makeString("image/");

    private static final SubLString $str90$text_ = makeString("text/");

    private static final SubLString $$$css = makeString("css");

    private static final SubLString $$$javascript = makeString("javascript");

    private static final SubLList $list93 = list(new SubLObject[] { list(makeString("text/html"), list(makeString("html"), makeString("htm"), makeString("shtml"))), list(makeString("text/plain"), list(makeString("txt"), makeString("text"))), list(makeString("image/gif"), list(makeString("gif"))),
	    list(makeString("image/jpeg"), list(makeString("jpeg"), makeString("jpg"), makeString("jpe"), makeString("jfif"), makeString("pjpeg"), makeString("pjp"))), list(makeString("image/png"), list(makeString("png"))), list(makeString("application/x-java-jnlp-file"), list(makeString("jnlp"))),
	    list(makeString("text/css"), list(makeString("css"))), list(makeString("text/javascript"), list(makeString("js"))), list(makeString("application/json"), list(makeString("json"))), list(makeString("text/xml"), list(makeString("xml"), makeString("xsl"))),
	    list(makeString("audio/ogg"), list(makeString("ogg"))) });

    private static final SubLList $list94 = list(makeSymbol("CONTENT-TYPE"), makeSymbol("EXTENSIONS"));

    private static final SubLString $str96$image_png = makeString("image/png");

    private static final SubLString $str98$image_jpeg = makeString("image/jpeg");

    private static final SubLString $str100$image_gif = makeString("image/gif");

    private static final SubLSymbol HTTP_SERVER_BASE_URL = makeSymbol("HTTP-SERVER-BASE-URL");

    private static final SubLString $str103$http___ = makeString("http://");

    private static final SubLString $str104$_ = makeString(":");

    private static final SubLSymbol HTTP_SERVER_CGI_BASE_URL = makeSymbol("HTTP-SERVER-CGI-BASE-URL");

    private static final SubLSymbol HTTP_CGI_REQUEST_CG_ARGS = makeSymbol("HTTP-CGI-REQUEST-CG-ARGS");

    private static final SubLList $list113 = list(
	    list(list(makeString("/cgi-bin/ws/xml-term-learner-create-new?termString0=hissentient&url=http%3A%2F%2Flocalhost%3A3742%2Fcgi-bin%2Fcg%3Fcb-content-frame%26c3096&title=GAF index for #$CityOfChristchurchNZ&argpos=(1)")),
		    makeString("xml-term-learner-create-new&termString0=hissentient&url=http%3A%2F%2Flocalhost%3A3742%2Fcgi-bin%2Fcg%3Fcb-content-frame%26c3096&title=GAF index for #$CityOfChristchurchNZ&argpos=(1)")),
	    list(list(makeString("/cgi-bin/ws/xml-term-learner-get-followups-for-term&max=1&questionsOnly=true&conceptId=Mx4rvVixTpwpEbGdrcN5Y29ycA")), makeString("xml-term-learner-get-followups-for-term&max=1&questionsOnly=true&conceptId=Mx4rvVixTpwpEbGdrcN5Y29ycA")),
	    list(list(makeString("/cgi-bin/cg?cb-content-frame&c3096")), makeString("cb-content-frame&c3096")));

    public static final SubLObject http_server_handler_alt(SubLObject in_stream, SubLObject out_stream) {
	return com.cyc.cycjava.cycl.http_kernel.http_server_top_level(in_stream, out_stream);
    }

    public static SubLObject http_server_handler(final SubLObject in_stream, final SubLObject out_stream) {
	final boolean wasSubLisp = Main.isSubLisp();
	Main.setSubLisp(true);
	try {
	    final SubLPackage prevPackage = SubLPackage.getCurrentPackage();
	    try {
		SubLPackage.setCurrentPackage("CYC");
		return http_server_top_level(in_stream, out_stream);
	    } finally {
		SubLPackage.setCurrentPackage(prevPackage);
	    }
	} finally {
	    Main.setSubLisp(wasSubLisp);
	}
    }

    /**
     * Handle an HTTP server TCP request across IN-STREAM and OUT-STREAM
     */
    @LispMethod(comment = "Handle an HTTP server TCP request across IN-STREAM and OUT-STREAM")
    public static final SubLObject http_server_top_level_alt(SubLObject in_stream, SubLObject out_stream) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject _prev_bind_0 = $package$.currentBinding(thread);
		SubLObject _prev_bind_1 = $read_default_float_format$.currentBinding(thread);
		SubLObject _prev_bind_2 = $print_readably$.currentBinding(thread);
		SubLObject _prev_bind_3 = $read_eval$.currentBinding(thread);
		try {
		    $package$.bind(find_package($$$CYC), thread);
		    $read_default_float_format$.bind(DOUBLE_FLOAT, thread);
		    $print_readably$.bind(NIL, thread);
		    $read_eval$.bind(NIL, thread);
		    {
			SubLObject old_priority = process_priority(current_process());
			try {
			    set_process_priority(current_process(), $process_normal_priority$.getGlobalValue());
			    thread.resetMultipleValues();
			    {
				SubLObject protocol = com.cyc.cycjava.cycl.http_kernel.http_parse_request(in_stream);
				SubLObject content = thread.secondMultipleValue();
				SubLObject host = thread.thirdMultipleValue();
				thread.resetMultipleValues();
				try {
				    {
					SubLObject pcase_var = protocol;
					if (pcase_var.eql($CGI)) {
					    com.cyc.cycjava.cycl.http_kernel.http_handle_cgi_request(out_stream, host, content);
					} else {
					    if (pcase_var.eql($FILE)) {
						com.cyc.cycjava.cycl.http_kernel.http_handle_file_request(out_stream, host, content);
					    }
					}
				    }
				} finally {
				    {
					SubLObject _prev_bind_0_1 = $is_thread_performing_cleanupP$.currentBinding(thread);
					try {
					    $is_thread_performing_cleanupP$.bind(T, thread);
					    {
						SubLObject ignore_errors_tag = NIL;
						try {
						    {
							SubLObject _prev_bind_0_2 = Errors.$error_handler$.currentBinding(thread);
							try {
							    Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
							    try {
								force_output(out_stream);
							    } catch (Throwable catch_var) {
								Errors.handleThrowable(catch_var, NIL);
							    }
							} finally {
							    Errors.$error_handler$.rebind(_prev_bind_0_2, thread);
							}
						    }
						} catch (Throwable ccatch_env_var) {
						    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
						}
					    }
					} finally {
					    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_1, thread);
					}
				    }
				}
			    }
			} finally {
			    {
				SubLObject _prev_bind_0_3 = $is_thread_performing_cleanupP$.currentBinding(thread);
				try {
				    $is_thread_performing_cleanupP$.bind(T, thread);
				    set_process_priority(current_process(), old_priority);
				} finally {
				    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_3, thread);
				}
			    }
			}
		    }
		} finally {
		    $read_eval$.rebind(_prev_bind_3, thread);
		    $print_readably$.rebind(_prev_bind_2, thread);
		    $read_default_float_format$.rebind(_prev_bind_1, thread);
		    $package$.rebind(_prev_bind_0, thread);
		}
	    }
	    return NIL;
	}
    }

    /**
     * Handle an HTTP server TCP request across IN-STREAM and OUT-STREAM
     */
    @LispMethod(comment = "Handle an HTTP server TCP request across IN-STREAM and OUT-STREAM")
    public static SubLObject http_server_top_level(final SubLObject in_stream, final SubLObject out_stream) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	SubLObject request_id = NIL;
	SubLObject receive_time = NIL;
	if (NIL != $http_request_logging_stream$.getGlobalValue()) {
	    request_id = next_http_request_id();
	    receive_time = numeric_date_utilities.get_universal_millisecond(UNPROVIDED);
	    format($http_request_logging_stream$.getGlobalValue(), $str9$HTTP_Request__6__0D_received_at__, request_id, numeric_date_utilities.timestamp_with_milliseconds(UNPROVIDED, UNPROVIDED));
	}
	final SubLObject _prev_bind_0 = $package$.currentBinding(thread);
	final SubLObject _prev_bind_2 = $read_default_float_format$.currentBinding(thread);
	final SubLObject _prev_bind_3 = $print_readably$.currentBinding(thread);
	final SubLObject _prev_bind_4 = $read_eval$.currentBinding(thread);
	try {
	    $package$.bind(find_package($$$CYC), thread);
	    $read_default_float_format$.bind(DOUBLE_FLOAT, thread);
	    $print_readably$.bind(NIL, thread);
	    $read_eval$.bind(NIL, thread);
	    final SubLObject old_priority = process_priority(current_process());
	    try {
		set_process_priority(current_process(), $process_normal_priority$.getGlobalValue());
		thread.resetMultipleValues();
		SubLObject protocol = http_parse_request(in_stream);
		SubLObject content = thread.secondMultipleValue();
		final SubLObject host = thread.thirdMultipleValue();
		final SubLObject method = thread.fourthMultipleValue();
		final SubLObject header_alist = thread.fifthMultipleValue();
		thread.resetMultipleValues();
		if (NIL != $http_request_logging_stream$.getGlobalValue()) {
		    format($http_request_logging_stream$.getGlobalValue(), $str12$HTTP_Request__6__0D_content___S__, request_id, content);
		}
		if ((protocol == $FILE) && content.equal($str14$_)) {
		    protocol = $CGI;
		    content = $str16$_cgi_bin_cg_cb_start_redirect;
		}
		try {
		    final SubLObject pcase_var = protocol;
		    if (pcase_var.eql($CGI)) {
			http_handle_cgi_request(out_stream, host, content);
		    } else if (pcase_var.eql($FILE)) {
			http_handle_file_request(out_stream, host, content);
		    } else if (pcase_var.eql($WS)) {
			http_handle_ws_request(out_stream, host, content, method, header_alist);
		    }

		} finally {
		    final SubLObject _prev_bind_0_$1 = $is_thread_performing_cleanupP$.currentBinding(thread);
		    try {
			$is_thread_performing_cleanupP$.bind(T, thread);
			final SubLObject _values = getValuesAsVector();
			SubLObject ignore_errors_tag = NIL;
			try {
			    thread.throwStack.push($IGNORE_ERRORS_TARGET);
			    final SubLObject _prev_bind_0_$2 = Errors.$error_handler$.currentBinding(thread);
			    try {
				Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
				try {
				    force_output(out_stream);
				} catch (final Throwable catch_var) {
				    Errors.handleThrowable(catch_var, NIL);
				}
			    } finally {
				Errors.$error_handler$.rebind(_prev_bind_0_$2, thread);
			    }
			} catch (final Throwable ccatch_env_var) {
			    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
			} finally {
			    thread.throwStack.pop();
			}
			restoreValuesFromVector(_values);
		    } finally {
			$is_thread_performing_cleanupP$.rebind(_prev_bind_0_$1, thread);
		    }
		}
	    } finally {
		final SubLObject _prev_bind_0_$3 = $is_thread_performing_cleanupP$.currentBinding(thread);
		try {
		    $is_thread_performing_cleanupP$.bind(T, thread);
		    final SubLObject _values2 = getValuesAsVector();
		    set_process_priority(current_process(), old_priority);
		    restoreValuesFromVector(_values2);
		} finally {
		    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$3, thread);
		}
	    }
	} finally {
	    $read_eval$.rebind(_prev_bind_4, thread);
	    $print_readably$.rebind(_prev_bind_3, thread);
	    $read_default_float_format$.rebind(_prev_bind_2, thread);
	    $package$.rebind(_prev_bind_0, thread);
	}
	if (NIL != $http_request_logging_stream$.getGlobalValue()) {
	    format($http_request_logging_stream$.getGlobalValue(), $str20$HTTP_Request__6__0D_returned_at__,
		    new SubLObject[] { request_id, numeric_date_utilities.timestamp_with_milliseconds(UNPROVIDED, UNPROVIDED), subtract(numeric_date_utilities.get_universal_millisecond(UNPROVIDED), receive_time) });
	}
	return NIL;
    }

    /**
     * Returns the local http-port according to defined system parameters.
     */
    @LispMethod(comment = "Returns the local http-port according to defined system parameters.")
    public static final SubLObject http_port_alt() {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    return add(system_parameters.$base_tcp_port$.getDynamicValue(thread), system_parameters.$http_port_offset$.getDynamicValue(thread));
	}
    }

    /**
     * Returns the local http-port according to defined system parameters.
     */
    @LispMethod(comment = "Returns the local http-port according to defined system parameters.")
    public static SubLObject http_port() {
	final SubLThread thread = SubLProcess.currentSubLThread();
	return system_parameters.$base_tcp_port$.getDynamicValue(thread).isNumber() && system_parameters.$http_port_offset$.getDynamicValue(thread).isNumber() ? add(system_parameters.$base_tcp_port$.getDynamicValue(thread), system_parameters.$http_port_offset$.getDynamicValue(thread)) : NIL;
    }

    public static SubLObject http_log_requests_to_file(final SubLObject filename, SubLObject overwrite) {
	if (overwrite == UNPROVIDED) {
	    overwrite = NIL;
	}
	final SubLThread thread = SubLProcess.currentSubLThread();
	if (NIL == file_utilities.file_valid_for_writing_p(filename)) {
	    Errors.warn($str22$Unable_to_write_to__S__, filename);
	    return $ERROR;
	}
	SubLObject ignore_errors_tag = NIL;
	try {
	    thread.throwStack.push($IGNORE_ERRORS_TARGET);
	    final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
	    try {
		Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
		try {
		    force_output($http_request_logging_stream$.getGlobalValue());
		    if (NIL == interactive_stream_p($http_request_logging_stream$.getGlobalValue())) {
			close($http_request_logging_stream$.getGlobalValue(), UNPROVIDED);
		    }
		} catch (final Throwable catch_var) {
		    Errors.handleThrowable(catch_var, NIL);
		}
	    } finally {
		Errors.$error_handler$.rebind(_prev_bind_0, thread);
	    }
	} catch (final Throwable ccatch_env_var) {
	    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
	} finally {
	    thread.throwStack.pop();
	}
	if (NIL != overwrite) {
	    file_utilities.delete_file_if_exists(filename);
	}
	final SubLObject stream = compatibility.open_text(filename, NIL != overwrite ? $OUTPUT : $APPEND);
	$http_request_logging_stream$.setGlobalValue(stream);
	return filename;
    }

    public static final SubLObject http_parse_request_alt(SubLObject in_stream) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject protocol = NIL;
		SubLObject content = NIL;
		SubLObject host = NIL;
		thread.resetMultipleValues();
		{
		    SubLObject method = web_utilities.http_read_request(in_stream, T);
		    SubLObject url = thread.secondMultipleValue();
		    SubLObject version = thread.thirdMultipleValue();
		    SubLObject header_alist = thread.fourthMultipleValue();
		    thread.resetMultipleValues();
		    host = com.cyc.cycjava.cycl.http_kernel.http_determine_remote_hostname(header_alist, in_stream);
		    if (NIL != string_utilities.starts_with(url, $http_cgi_bin_directory$.getGlobalValue())) {
			protocol = $CGI;
		    } else {
			protocol = $FILE;
		    }
		    if (method.equal($$$GET)) {
			content = url;
		    } else {
			{
			    SubLObject content_stream = NIL;
			    try {
				content_stream = make_private_string_output_stream();
				write_string(url, content_stream, UNPROVIDED, UNPROVIDED);
				write_char(CHAR_question, content_stream);
				{
				    SubLObject content_length = assoc($str_alt13$Content_length, header_alist, symbol_function(EQUALP), UNPROVIDED).rest();
				    if (NIL != content_length) {
					{
					    SubLObject cdotimes_end_var = parse_integer(content_length, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
					    SubLObject i = NIL;
					    for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
						write_char(read_char(in_stream, UNPROVIDED, UNPROVIDED, UNPROVIDED), content_stream);
					    }
					}
				    }
				}
				content = get_output_stream_string(content_stream);
			    } finally {
				{
				    SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
				    try {
					$is_thread_performing_cleanupP$.bind(T, thread);
					close(content_stream, UNPROVIDED);
				    } finally {
					$is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
				    }
				}
			    }
			}
		    }
		}
		return values(protocol, content, host);
	    }
	}
    }

    public static SubLObject http_parse_request(final SubLObject in_stream) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	SubLObject protocol = NIL;
	SubLObject content = NIL;
	SubLObject host = NIL;
	thread.resetMultipleValues();
	final SubLObject method = web_utilities.http_read_request(in_stream, T);
	final SubLObject url = thread.secondMultipleValue();
	final SubLObject version = thread.thirdMultipleValue();
	final SubLObject header_alist = thread.fourthMultipleValue();
	thread.resetMultipleValues();
	if (NIL == method) {
	    return NIL;
	}
	host = http_determine_remote_hostname(header_alist, in_stream);
	if (NIL != string_utilities.starts_with(url, $http_cgi_bin_directory$.getGlobalValue())) {
	    protocol = $CGI;
	} else if (NIL != string_utilities.starts_with(url, $http_cyc_ws_directory$.getGlobalValue())) {
	    protocol = $WS;
	} else {
	    protocol = $FILE;
	}

	if (method.equal($$$GET)) {
	    content = url;
	} else {
	    SubLObject content_stream = NIL;
	    try {
		content_stream = make_private_string_output_stream();
		write_string(url, content_stream, UNPROVIDED, UNPROVIDED);
		write_char(CHAR_question, content_stream);
		final SubLObject content_length = assoc($str27$Content_length, header_alist, symbol_function(EQUALP), UNPROVIDED).rest();
		if (NIL != content_length) {
		    SubLObject cdotimes_end_var;
		    SubLObject i;
		    for (cdotimes_end_var = parse_integer(content_length, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), i = NIL, i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
			write_char(read_char(in_stream, UNPROVIDED, UNPROVIDED, UNPROVIDED), content_stream);
		    }
		}
		content = get_output_stream_string(content_stream);
	    } finally {
		final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
		try {
		    $is_thread_performing_cleanupP$.bind(T, thread);
		    final SubLObject _values = getValuesAsVector();
		    close(content_stream, UNPROVIDED);
		    restoreValuesFromVector(_values);
		} finally {
		    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
		}
	    }
	}
	return values(protocol, content, host, method, header_alist);
    }

    public static final SubLObject http_determine_remote_hostname_alt(SubLObject header_alist, SubLObject stream) {
	{
	    SubLObject provided_host = assoc($str_alt14$Remote_Host, header_alist, symbol_function(EQUALP), UNPROVIDED).rest();
	    if (provided_host.isString()) {
		return provided_host;
	    }
	}
	{
	    SubLObject remote_hostname = subl_promotions.tcp_stream_remote_hostname(stream);
	    if (remote_hostname.isString()) {
		return remote_hostname;
	    }
	}
	{
	    SubLObject remote_address = subl_promotions.tcp_stream_remote_address(stream);
	    if (remote_address.isString()) {
		return remote_address;
	    }
	}
	return $$$unknown;
    }

    public static SubLObject http_determine_remote_hostname(final SubLObject header_alist, final SubLObject stream) {
	final SubLObject provided_host = assoc($str28$Remote_Host, header_alist, symbol_function(EQUALP), UNPROVIDED).rest();
	if (provided_host.isString()) {
	    return provided_host;
	}
	final SubLObject remote_hostname = subl_promotions.tcp_stream_remote_hostname(stream);
	if (remote_hostname.isString()) {
	    return remote_hostname;
	}
	final SubLObject remote_address = subl_promotions.tcp_stream_remote_address(stream);
	if (remote_address.isString()) {
	    return remote_address;
	}
	return $$$unknown;
    }

    public static SubLObject get_http_logging_sequence_generator() {
	final SubLThread thread = SubLProcess.currentSubLThread();
	if ($get_http_logging_sequence_generator$.getDynamicValue(thread) == $UNINITIALIZED) {
	    final SubLObject init_value = integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED);
	    if (init_value == $UNINITIALIZED) {
		Errors.error($str31$Unable_to_initialize__A__, $get_http_logging_sequence_generator$.getDynamicValue(thread));
	    }
	    $get_http_logging_sequence_generator$.setDynamicValue(init_value, thread);
	}
	return $get_http_logging_sequence_generator$.getDynamicValue(thread);
    }

    public static SubLObject next_http_request_id() {
	return integer_sequence_generator.integer_sequence_generator_next(get_http_logging_sequence_generator());
    }

    public static final SubLObject http_handle_cgi_request_alt(SubLObject out_stream, SubLObject host, SubLObject content) {
	{
	    SubLObject success = NIL;
	    SubLObject ignore_errors_tag = NIL;
	    try {
		{
		    SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
		    try {
			bind(Errors.$error_handler$, symbol_function(IGNORE_ERRORS_HANDLER));
			try {
			    {
				SubLObject cg_args = com.cyc.cycjava.cycl.http_kernel.http_cgi_request_cg_args(content, UNPROVIDED);
				if (NIL != html_request_arguments_are_multipart_dataP(cg_args)) {
				    {
					SubLObject contents = html_parse_multipart_form_data(cg_args);
					cg_args = html_construct_message_from_multipart_data(contents);
				    }
				}
				{
				    SubLObject html_message = cconcatenate(host, new SubLObject[] { $str_alt16$_, cg_args });
				    SubLObject handler_function = html_kernel.handler_function_from_html_message(html_message);
				    SubLObject headers = com.cyc.cycjava.cycl.http_kernel.http_cgi_request_headers(content, handler_function);
				    web_utilities.http_send_ok_response(out_stream, headers);
				    html_kernel.handle_html_message_to_stream(html_message, out_stream);
				}
				success = T;
			    }
			} catch (Throwable catch_var) {
			    Errors.handleThrowable(catch_var, NIL);
			}
		    } finally {
			rebind(Errors.$error_handler$, _prev_bind_0);
		    }
		}
	    } catch (Throwable ccatch_env_var) {
		ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
	    }
	    return success;
	}
    }

    public static SubLObject http_handle_cgi_request(final SubLObject out_stream, final SubLObject host, final SubLObject content) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	SubLObject success = NIL;
	SubLObject ignore_errors_tag = NIL;
	try {
	    thread.throwStack.push($IGNORE_ERRORS_TARGET);
	    final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
	    try {
		Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
		try {
		    SubLObject cg_args = http_cgi_request_cg_args(content, UNPROVIDED);
		    if (NIL != html_request_arguments_are_multipart_dataP(cg_args)) {
			final SubLObject contents = html_parse_multipart_form_data(cg_args);
			cg_args = html_construct_message_from_multipart_data(contents);
		    }
		    final SubLObject html_message = cconcatenate(host, new SubLObject[] { $str32$_, cg_args });
		    final SubLObject handler_function = html_kernel.handler_function_from_html_message(html_message);
		    final SubLObject headers = http_cgi_request_headers(content, handler_function);
		    web_utilities.http_send_ok_response(out_stream, headers);
		    html_kernel.handle_html_message_to_stream(html_message, out_stream);
		    success = T;
		} catch (final Throwable catch_var) {
		    Errors.handleThrowable(catch_var, NIL);
		}
	    } finally {
		Errors.$error_handler$.rebind(_prev_bind_0, thread);
	    }
	} catch (final Throwable ccatch_env_var) {
	    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
	} finally {
	    thread.throwStack.pop();
	}
	return success;
    }

    /**
     * Return the position where the Cyc browser relevant part of the URL starts.
     */
    @LispMethod(comment = "Return the position where the Cyc browser relevant part of the URL starts.")
    public static final SubLObject http_cgi_request_cg_args_alt(SubLObject content, SubLObject cgi_start) {
	if (cgi_start == UNPROVIDED) {
	    cgi_start = ZERO_INTEGER;
	}
	{
	    SubLObject wsP = string_utilities.starts_with(content, $http_cgi_bin_ws_directory$.getGlobalValue());
	    SubLObject question_mark_location = search($http_cg_url$.getGlobalValue(), content, symbol_function(EQUAL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, cgi_start, NIL);
	    if (NIL != wsP) {
		{
		    SubLObject args_string = string_utilities.substring(content, length($http_cgi_bin_ws_directory$.getGlobalValue()), UNPROVIDED);
		    SubLObject question_location = search($str_alt18$_, args_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
		    SubLObject ampersand_location = search($str_alt16$_, args_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
		    SubLObject pipe_location = search($str_alt19$_, args_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
		    SubLObject first_separator = list_utilities.extremal(remove(NIL, list(ampersand_location, pipe_location), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), $sym20$_, UNPROVIDED);
		    if (((NIL != question_location) && (NIL != first_separator)) && question_location.numL(first_separator)) {
			args_string = string_utilities.replace_substring_once(args_string, $str_alt18$_, $str_alt16$_, cgi_start);
		    }
		    return args_string;
		}
	    } else {
		if (NIL != subl_promotions.positive_integer_p(question_mark_location)) {
		    return string_utilities.substring(content, add(length($http_cg_url$.getGlobalValue()), question_mark_location), UNPROVIDED);
		} else {
		    return string_utilities.substring(content, number_utilities.f_1X(length($http_cgi_bin_directory$.getGlobalValue())), UNPROVIDED);
		}
	    }
	}
    }

    /**
     * Return the position where the Cyc browser relevant part of the URL starts.
     */
    @LispMethod(comment = "Return the position where the Cyc browser relevant part of the URL starts.")
    public static SubLObject http_cgi_request_cg_args(final SubLObject content, SubLObject cgi_start) {
	if (cgi_start == UNPROVIDED) {
	    cgi_start = ZERO_INTEGER;
	}
	final SubLObject wsP = string_utilities.starts_with(content, $http_cgi_bin_ws_directory$.getGlobalValue());
	final SubLObject question_mark_location = search($http_cg_url$.getGlobalValue(), content, symbol_function(EQUAL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, cgi_start, NIL);
	if (NIL != wsP) {
	    SubLObject args_string = string_utilities.substring(content, length($http_cgi_bin_ws_directory$.getGlobalValue()), UNPROVIDED);
	    final SubLObject question_location = search($str34$_, args_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
	    final SubLObject ampersand_location = search($str32$_, args_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
	    final SubLObject pipe_location = search($str35$_, args_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
	    final SubLObject first_separator = list_utilities.extremal(remove(NIL, list(ampersand_location, pipe_location), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), $sym36$_, UNPROVIDED);
	    if (((NIL != question_location) && (NIL != first_separator)) && question_location.numL(first_separator)) {
		args_string = string_utilities.replace_substring_once(args_string, $str34$_, $str32$_, cgi_start);
	    }
	    return args_string;
	}
	if (NIL != subl_promotions.positive_integer_p(question_mark_location)) {
	    return string_utilities.substring(content, add(length($http_cg_url$.getGlobalValue()), question_mark_location), UNPROVIDED);
	}
	return string_utilities.substring(content, number_utilities.f_1X(length($http_cgi_bin_directory$.getGlobalValue())), UNPROVIDED);
    }

    /**
     *
     *
     * @unknown The content type must be determined from just the URL, because the headers are emitted
    before the content of the page has even begun
    If a mime-type is expressly requested in the URL (by way of a 'return-mime-type:XXX=' argument in the request,
    it will be used.  Next, if the handler was declared with a mime-type (e.g. by using define-xml-handler),
    the declared mime-type will be used.  If all else fails, we'll use html.
     */
    @LispMethod(comment = "@unknown The content type must be determined from just the URL, because the headers are emitted\r\nbefore the content of the page has even begun\r\nIf a mime-type is expressly requested in the URL (by way of a \'return-mime-type:XXX=\' argument in the request,\r\nit will be used.  Next, if the handler was declared with a mime-type (e.g. by using define-xml-handler),\r\nthe declared mime-type will be used.  If all else fails, we\'ll use html.")
    public static final SubLObject http_get_content_type_for_cgi_request_alt(SubLObject raw_content, SubLObject handler_function) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject content = web_utilities.html_url_decode(raw_content);
		SubLObject mime_description = search($str_alt21$return_mime_type, content, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
		if (NIL != mime_description) {
		    {
			SubLObject mime_type_start = add(SEVENTEEN_INTEGER, mime_description);
			SubLObject mime_type_end = search($str_alt22$_, content, EQUAL, IDENTITY, ZERO_INTEGER, NIL, mime_type_start, UNPROVIDED);
			SubLObject mime_type_string = string_utilities.substring(content, mime_type_start, mime_type_end);
			return mime_type_string;
		    }
		} else {
		    if (NIL != com.cyc.cycjava.cycl.http_kernel.mime_type_from_handler_function(handler_function)) {
			return com.cyc.cycjava.cycl.http_kernel.mime_type_from_handler_function(handler_function);
		    } else {
			if (NIL != system_parameters.$permit_utf_8_character_display$.getDynamicValue(thread)) {
			    return $str_alt23$text_html__charset_utf_8;
			} else {
			    return $str_alt24$text_html;
			}
		    }
		}
	    }
	}
    }

    /**
     *
     *
     * @unknown The content type must be determined from just the URL, because the headers are emitted
    before the content of the page has even begun
    If a mime-type is expressly requested in the URL (by way of a 'return-mime-type:XXX=' argument in the request,
    it will be used.  Next, if the handler was declared with a mime-type (e.g. by using define-xml-handler),
    the declared mime-type will be used.  If all else fails, we'll use html.
     */
    @LispMethod(comment = "@unknown The content type must be determined from just the URL, because the headers are emitted\r\nbefore the content of the page has even begun\r\nIf a mime-type is expressly requested in the URL (by way of a \'return-mime-type:XXX=\' argument in the request,\r\nit will be used.  Next, if the handler was declared with a mime-type (e.g. by using define-xml-handler),\r\nthe declared mime-type will be used.  If all else fails, we\'ll use html.")
    public static SubLObject http_get_content_type_for_cgi_request(final SubLObject raw_content, final SubLObject handler_function) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	final SubLObject content = web_utilities.html_url_decode(raw_content);
	final SubLObject mime_description = search($str37$return_mime_type, content, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
	final SubLObject handler_type = html_macros.get_handler_mime_type_property(handler_function);
	if (NIL != mime_description) {
	    final SubLObject mime_type_start = add(SEVENTEEN_INTEGER, mime_description);
	    final SubLObject mime_type_end = search($str38$_, content, EQUAL, IDENTITY, ZERO_INTEGER, NIL, mime_type_start, UNPROVIDED);
	    final SubLObject mime_type_string = string_utilities.substring(content, mime_type_start, mime_type_end);
	    return mime_type_string;
	}
	if (handler_type.eql(html_macros.$html_handler_property$.getGlobalValue()) && (NIL != system_parameters.$permit_utf_8_character_display$.getDynamicValue(thread))) {
	    return $str39$text_html__charset_utf_8;
	}
	if (handler_type.eql(html_macros.$html_handler_property$.getGlobalValue())) {
	    return $str40$text_html;
	}
	if (handler_type.eql(html_macros.$xml_handler_property$.getGlobalValue())) {
	    return $str41$application_xml;
	}
	if (handler_type.eql(html_macros.$json_handler_property$.getGlobalValue())) {
	    return $str42$application_json;
	}
	Errors.warn($str43$Unknown_return_type_for_handler__, handler_function);
	return NIL;
    }

    public static final SubLObject http_cgi_request_headers_alt(SubLObject content, SubLObject handler_function) {
	if (handler_function == UNPROVIDED) {
	    handler_function = NIL;
	}
	{
	    SubLObject mime_type = com.cyc.cycjava.cycl.http_kernel.http_get_content_type_for_cgi_request(content, handler_function);
	    return list($list_alt25, bq_cons($str_alt26$Content_Type, mime_type));
	}
    }

    public static SubLObject http_cgi_request_headers(final SubLObject content, SubLObject handler_function) {
	if (handler_function == UNPROVIDED) {
	    handler_function = NIL;
	}
	final SubLObject mime_type = http_get_content_type_for_cgi_request(content, handler_function);
	return list($list44, bq_cons($str45$Content_Type, mime_type));
    }

    public static SubLObject http_handle_ws_request(final SubLObject out_stream, final SubLObject host, final SubLObject content, SubLObject method, final SubLObject header_alist) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	method = (method.equalp($$$POST)) ? $POST : $GET;
	final SubLObject proxy_content = string_utilities.substring(content, SEVEN_INTEGER, UNPROVIDED);
	final SubLObject header_plist = http_ws_header_conversion(header_alist);
	SubLObject total = ZERO_INTEGER;
	thread.resetMultipleValues();
	final SubLObject url = possibly_split_full_query_url(proxy_content);
	final SubLObject query = thread.secondMultipleValue();
	thread.resetMultipleValues();
	final SubLObject current;
	final SubLObject datum = current = header_plist;
	final SubLObject keep_aliveP_tail = property_list_member($kw49$KEEP_ALIVE_, current);
	final SubLObject keep_aliveP = (NIL != keep_aliveP_tail) ? cadr(keep_aliveP_tail) : T;
	final SubLObject wide_newlinesP_tail = property_list_member($kw50$WIDE_NEWLINES_, current);
	final SubLObject wide_newlinesP = (NIL != wide_newlinesP_tail) ? cadr(wide_newlinesP_tail) : NIL;
	final SubLObject accept_types_tail = property_list_member($ACCEPT_TYPES, current);
	final SubLObject accept_types = (NIL != accept_types_tail) ? cadr(accept_types_tail) : $DEFAULT;
	final SubLObject content_type_tail = property_list_member($CONTENT_TYPE, current);
	final SubLObject content_type = (NIL != content_type_tail) ? cadr(content_type_tail) : $DEFAULT;
	final SubLObject real_port = ($http_cyc_ws_port$.getGlobalValue().eql($DEFAULT)) ? $int$80 : $http_cyc_ws_port$.getGlobalValue();
	SubLObject proxy_stream = NIL;
	try {
	    proxy_stream = subl_promotions.open_tcp_stream_with_timeout($http_cyc_ws_machine$.getGlobalValue(), real_port, NIL, $PRIVATE);
	    if (NIL != validate_tcp_connection(proxy_stream, $http_cyc_ws_machine$.getGlobalValue(), real_port)) {
		web_utilities.send_http_request(proxy_stream, list(new SubLObject[] { $MACHINE, $http_cyc_ws_machine$.getGlobalValue(), $PORT, $http_cyc_ws_port$.getGlobalValue(), $METHOD, method, $URL, url, $QUERY, query, $kw49$KEEP_ALIVE_, NIL, $kw50$WIDE_NEWLINES_, wide_newlinesP, $ACCEPT_TYPES,
			accept_types, $CONTENT_TYPE, content_type, $SOAP_ACTION_URI, NIL }));
		for (SubLObject v_char = read_char(proxy_stream, NIL, NIL, UNPROVIDED); NIL != v_char; v_char = read_char(proxy_stream, NIL, NIL, UNPROVIDED)) {
		    write_char(v_char, out_stream);
		    total = add(total, ONE_INTEGER);
		}
	    }
	} finally {
	    final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
	    try {
		$is_thread_performing_cleanupP$.bind(T, thread);
		final SubLObject _values = getValuesAsVector();
		if (NIL != proxy_stream) {
		    close(proxy_stream, UNPROVIDED);
		}
		restoreValuesFromVector(_values);
	    } finally {
		$is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
	    }
	}
	SubLObject ignore_errors_tag = NIL;
	try {
	    thread.throwStack.push($IGNORE_ERRORS_TARGET);
	    final SubLObject _prev_bind_2 = Errors.$error_handler$.currentBinding(thread);
	    try {
		Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
		try {
		    force_output(out_stream);
		} catch (final Throwable catch_var) {
		    Errors.handleThrowable(catch_var, NIL);
		}
	    } finally {
		Errors.$error_handler$.rebind(_prev_bind_2, thread);
	    }
	} catch (final Throwable ccatch_env_var) {
	    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
	} finally {
	    thread.throwStack.pop();
	}
	return total;
    }

    public static SubLObject possibly_split_full_query_url(final SubLObject full_query_url) {
	assert NIL != stringp(full_query_url) : "! stringp(full_query_url) " + ("Types.stringp(full_query_url) " + "CommonSymbols.NIL != Types.stringp(full_query_url) ") + full_query_url;
	final SubLObject url_end = position(CHAR_question, full_query_url, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
	SubLObject url = full_query_url;
	SubLObject query = NIL;
	if (NIL != url_end) {
	    url = string_utilities.substring(full_query_url, ZERO_INTEGER, url_end);
	    query = string_utilities.substring(full_query_url, number_utilities.f_1X(url_end), UNPROVIDED);
	}
	return values(url, query);
    }

    public static SubLObject http_ws_header_conversion(final SubLObject header_alist) {
	SubLObject result_plist = NIL;
	SubLObject cdolist_list_var = header_alist;
	SubLObject cons = NIL;
	cons = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    SubLObject current;
	    final SubLObject datum = current = cons;
	    SubLObject header_key = NIL;
	    SubLObject value = NIL;
	    destructuring_bind_must_consp(current, datum, $list63);
	    header_key = current.first();
	    current = value = current.rest();
	    if (header_key.equalp($str45$Content_Type)) {
		result_plist = putf(result_plist, $CONTENT_TYPE, value);
	    } else if (header_key.equalp($$$Accept)) {
		result_plist = putf(result_plist, $ACCEPT_TYPES, list(value));
	    } else if (NIL != list_utilities.member_equalpP(header_key, $list65)) {
	    }

	    cdolist_list_var = cdolist_list_var.rest();
	    cons = cdolist_list_var.first();
	}
	return result_plist;
    }

    public static final SubLObject http_handle_file_request_alt(SubLObject out_stream, SubLObject host, SubLObject content) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject whole_filename = com.cyc.cycjava.cycl.http_kernel.http_file_request_filename(content);
		SubLObject success = NIL;
		thread.resetMultipleValues();
		{
		    SubLObject filename = com.cyc.cycjava.cycl.http_kernel.separate_args_from_java_web_start_request(whole_filename);
		    SubLObject cyclist = thread.secondMultipleValue();
		    SubLObject project = thread.thirdMultipleValue();
		    thread.resetMultipleValues();
		    {
			SubLObject ignore_errors_tag = NIL;
			try {
			    {
				SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
				try {
				    Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
				    try {
					if ((NIL != Filesys.probe_file(filename)) && (NIL == Filesys.directory_p(filename))) {
					    {
						SubLObject headers = com.cyc.cycjava.cycl.http_kernel.http_file_request_headers(filename);
						SubLObject java_web_start_fileP = com.cyc.cycjava.cycl.http_kernel.java_web_start_file_p(filename);
						web_utilities.http_send_ok_response(out_stream, headers);
						{
						    SubLObject stream = NIL;
						    try {
							{
							    SubLObject _prev_bind_0_4 = stream_macros.$stream_requires_locking$.currentBinding(thread);
							    try {
								stream_macros.$stream_requires_locking$.bind(NIL, thread);
								stream = compatibility.open_binary(filename, $INPUT, NIL);
							    } finally {
								stream_macros.$stream_requires_locking$.rebind(_prev_bind_0_4, thread);
							    }
							}
							if (!stream.isStream()) {
							    Errors.error($str_alt28$Unable_to_open__S, filename);
							}
							{
							    SubLObject content_stream = stream;
							    SubLObject v_byte = NIL;
							    for (v_byte = read_byte(content_stream, NIL, $EOF); $EOF != v_byte; v_byte = read_byte(content_stream, NIL, $EOF)) {
								if ((NIL != java_web_start_fileP) && (v_byte == $int$35)) {
								    v_byte = read_byte(content_stream, NIL, $EOF);
								    if (v_byte == $int$47) {
									princ(Environment.get_machine_name($$$unknown), out_stream);
									write_char(code_char($int$47), out_stream);
								    } else {
									if (v_byte == $int$49) {
									    princ(Environment.get_machine_name($$$unknown), out_stream);
									} else {
									    if (v_byte == $int$50) {
										princ(system_parameters.$base_tcp_port$.getDynamicValue(thread), out_stream);
									    } else {
										if (v_byte == $int$51) {
										    princ(cyclist, out_stream);
										} else {
										    if (v_byte == $int$52) {
											princ(project, out_stream);
										    } else {
											write_char(code_char($int$35), out_stream);
											write_char(code_char(v_byte), out_stream);
										    }
										}
									    }
									}
								    }
								} else {
								    write_char(code_char(v_byte), out_stream);
								}
							    }
							}
						    } finally {
							{
							    SubLObject _prev_bind_0_5 = $is_thread_performing_cleanupP$.currentBinding(thread);
							    try {
								$is_thread_performing_cleanupP$.bind(T, thread);
								if (stream.isStream()) {
								    close(stream, UNPROVIDED);
								}
							    } finally {
								$is_thread_performing_cleanupP$.rebind(_prev_bind_0_5, thread);
							    }
							}
						    }
						}
						success = T;
					    }
					} else {
					    {
						SubLObject headers = com.cyc.cycjava.cycl.http_kernel.http_cgi_request_headers($str_alt36$, UNPROVIDED);
						web_utilities.http_send_error_response(out_stream, $int$404, headers);
						{
						    SubLObject _prev_bind_0_6 = html_macros.$html_stream$.currentBinding(thread);
						    try {
							html_macros.$html_stream$.bind(out_stream, thread);
							cb_error($$$File_not_found, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
						    } finally {
							html_macros.$html_stream$.rebind(_prev_bind_0_6, thread);
						    }
						}
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
			    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
			}
		    }
		}
		return success;
	    }
	}
    }

    public static SubLObject http_handle_file_request(final SubLObject out_stream, final SubLObject host, final SubLObject content) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	final SubLObject whole_filename = http_file_request_filename(content);
	SubLObject success = NIL;
	thread.resetMultipleValues();
	final SubLObject filename = separate_args_from_file_request(whole_filename);
	final SubLObject cyclist = thread.secondMultipleValue();
	final SubLObject project = thread.thirdMultipleValue();
	thread.resetMultipleValues();
	SubLObject ignore_errors_tag = NIL;
	try {
	    thread.throwStack.push($IGNORE_ERRORS_TARGET);
	    final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
	    try {
		Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
		try {
		    if ((NIL != Filesys.probe_file(filename)) && (NIL == Filesys.directory_p(filename))) {
			final SubLObject headers = http_file_request_headers(filename);
			final SubLObject java_web_start_fileP = java_web_start_file_p(filename);
			web_utilities.http_send_ok_response(out_stream, headers);
			SubLObject stream = NIL;
			try {
			    final SubLObject _prev_bind_0_$4 = stream_macros.$stream_requires_locking$.currentBinding(thread);
			    try {
				stream_macros.$stream_requires_locking$.bind(NIL, thread);
				stream = compatibility.open_binary(filename, $INPUT);
			    } finally {
				stream_macros.$stream_requires_locking$.rebind(_prev_bind_0_$4, thread);
			    }
			    if (!stream.isStream()) {
				Errors.error($str67$Unable_to_open__S, filename);
			    }
			    SubLObject content_stream;
			    SubLObject v_byte;
			    for (content_stream = stream, v_byte = NIL, v_byte = read_byte(content_stream, NIL, $EOF); $EOF != v_byte; v_byte = read_byte(content_stream, NIL, $EOF)) {
				if ((NIL != java_web_start_fileP) && v_byte.eql($int$35)) {
				    v_byte = read_byte(content_stream, NIL, $EOF);
				    if (v_byte.eql($int$47)) {
					princ(Environment.get_machine_name($$$unknown), out_stream);
					write_char(code_char($int$47), out_stream);
				    } else if (v_byte.eql($int$49)) {
					princ(Environment.get_machine_name($$$unknown), out_stream);
				    } else if (v_byte.eql($int$50)) {
					princ(system_parameters.$base_tcp_port$.getDynamicValue(thread), out_stream);
				    } else if (v_byte.eql($int$51)) {
					princ(cyclist, out_stream);
				    } else if (v_byte.eql($int$52)) {
					princ(project, out_stream);
				    } else {
					write_char(code_char($int$35), out_stream);
					write_char(code_char(v_byte), out_stream);
				    }

				} else {
				    write_char(code_char(v_byte), out_stream);
				}
			    }
			} finally {
			    final SubLObject _prev_bind_0_$5 = $is_thread_performing_cleanupP$.currentBinding(thread);
			    try {
				$is_thread_performing_cleanupP$.bind(T, thread);
				final SubLObject _values = getValuesAsVector();
				if (stream.isStream()) {
				    close(stream, UNPROVIDED);
				}
				restoreValuesFromVector(_values);
			    } finally {
				$is_thread_performing_cleanupP$.rebind(_prev_bind_0_$5, thread);
			    }
			}
			success = T;
		    } else {
			final SubLObject headers = $list75;
			web_utilities.http_send_error_response(out_stream, $int$404, headers);
			final SubLObject _prev_bind_0_$6 = html_macros.$html_stream$.currentBinding(thread);
			try {
			    html_macros.$html_stream$.bind(out_stream, thread);
			    cb_error($$$File_not_found, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
			    Debug.trace($$$File_not_found + " " + content);

			} finally {
			    html_macros.$html_stream$.rebind(_prev_bind_0_$6, thread);
			}
		    }
		} catch (final Throwable catch_var) {
		    Errors.handleThrowable(catch_var, NIL);
		}
	    } finally {
		Errors.$error_handler$.rebind(_prev_bind_0, thread);
	    }
	} catch (final Throwable ccatch_env_var) {
	    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
	} finally {
	    thread.throwStack.pop();
	}
	return success;
    }

    public static SubLObject separate_args_from_file_request(final SubLObject filename) {
	final SubLObject qm_position = string_utilities.char_position(CHAR_question, filename, UNPROVIDED);
	if (NIL == qm_position) {
	    return values(filename, NIL, NIL);
	}
	if ($str78$.equalp(subseq(filename, add(qm_position, ONE_INTEGER), UNPROVIDED))) {
	    return values(subseq(filename, ZERO_INTEGER, qm_position), NIL, NIL);
	}
	final SubLObject just_the_filename = subseq(filename, ZERO_INTEGER, qm_position);
	final SubLObject args_string = subseq(filename, add(qm_position, ONE_INTEGER), UNPROVIDED);
	final SubLObject args = html_kernel.parse_html_handler_arg_string(args_string, ZERO_INTEGER);
	final SubLObject cyclist_string = html_extract_input($$$user, args);
	final SubLObject project_string = html_extract_input($$$project, args);
	final SubLObject cyclist = (NIL != cyclist_string) ? czer_main.canonicalize_term(read_from_string(web_utilities.html_url_decode(cyclist_string), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED) : NIL;
	final SubLObject project = (NIL != project_string) ? czer_main.canonicalize_term(read_from_string(web_utilities.html_url_decode(project_string), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED) : NIL;
	return values(just_the_filename, cyclist, project);
    }

    /**
     * Returns true iff the given FILENAME is a Java Web Start filename.
     */
    @LispMethod(comment = "Returns true iff the given FILENAME is a Java Web Start filename.")
    public static final SubLObject java_web_start_file_p_alt(SubLObject filename) {
	return string_utilities.ends_with(filename, $str_alt41$_jnlp, UNPROVIDED);
    }

    /**
     * Returns true iff the given FILENAME is a Java Web Start filename.
     */
    @LispMethod(comment = "Returns true iff the given FILENAME is a Java Web Start filename.")
    public static SubLObject java_web_start_file_p(final SubLObject filename) {
	return string_utilities.ends_with(filename, $str81$_jnlp, UNPROVIDED);
    }

    public static final SubLObject http_file_request_filename_alt(SubLObject content) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    return relative_filename(system_parameters.$http_htdocs_directory$.getDynamicValue(thread), content, UNPROVIDED);
	}
    }

    public static SubLObject http_file_request_filename(final SubLObject content) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	return file_utilities.relative_filename(system_parameters.$http_htdocs_directory$.getDynamicValue(thread), content, UNPROVIDED);
    }

    public static final SubLObject http_file_request_headers_alt(SubLObject filename) {
	{
	    SubLObject last_modified_time = Filesys.file_write_date(filename);
	    SubLObject last_modified_string = web_utilities.http_date_string(last_modified_time);
	    SubLObject content_length = get_file_length(filename);
	    SubLObject content_type = com.cyc.cycjava.cycl.http_kernel.http_file_request_content_type(filename);
	    SubLObject expires = com.cyc.cycjava.cycl.http_kernel.http_file_request_expires(content_type);
	    if (NIL != com.cyc.cycjava.cycl.http_kernel.java_web_start_file_p(filename)) {
		content_length = add(content_length, number_utilities.f_1_(length(Environment.get_machine_name($$$unknown))));
	    }
	    return listS(bq_cons($str_alt42$Last_Modified, last_modified_string),
		    new SubLObject[] { $list_alt43, bq_cons($str_alt44$Content_Length, content_length), $list_alt45, $list_alt46, bq_cons($str_alt26$Content_Type, content_type), append(NIL != expires ? ((SubLObject) (list(bq_cons($$$Expires, expires)))) : NIL, NIL) });
	}
    }

    public static SubLObject http_file_request_headers(final SubLObject filename) {
	return http_file_request_headers_int(filename, Filesys.file_write_date(filename));
    }

    public static SubLObject clear_http_file_request_headers_int() {
	final SubLObject cs = $http_file_request_headers_int_caching_state$.getGlobalValue();
	if (NIL != cs) {
	    memoization_state.caching_state_clear(cs);
	}
	return NIL;
    }

    public static SubLObject remove_http_file_request_headers_int(final SubLObject filename, final SubLObject last_modified_time) {
	return memoization_state.caching_state_remove_function_results_with_args($http_file_request_headers_int_caching_state$.getGlobalValue(), list(filename, last_modified_time), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject http_file_request_headers_int_internal(final SubLObject filename, final SubLObject last_modified_time) {
	final SubLObject last_modified_string = web_utilities.http_date_string(last_modified_time);
	SubLObject content_length = file_utilities.get_file_length(filename);
	final SubLObject content_type = http_file_request_content_type(filename);
	final SubLObject expires = (NIL != last_modified_time) ? NIL : http_file_request_expires(content_type);
	if (NIL != java_web_start_file_p(filename)) {
	    content_length = add(content_length, number_utilities.f_1_(length(Environment.get_machine_name($$$unknown))));
	}
	return listS(bq_cons($str83$Last_Modified, last_modified_string), $list84, bq_cons($str85$Content_Length, content_length), bq_cons($str45$Content_Type, content_type), append(NIL != expires ? list(bq_cons($$$Expires, expires)) : NIL, NIL));
    }

    public static SubLObject http_file_request_headers_int(final SubLObject filename, final SubLObject last_modified_time) {
	SubLObject caching_state = $http_file_request_headers_int_caching_state$.getGlobalValue();
	if (NIL == caching_state) {
	    caching_state = memoization_state.create_global_caching_state_for_name(HTTP_FILE_REQUEST_HEADERS_INT, $http_file_request_headers_int_caching_state$, $int$200, EQUAL, TWO_INTEGER, ZERO_INTEGER);
	}
	final SubLObject sxhash = memoization_state.sxhash_calc_2(filename, last_modified_time);
	final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
	if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
	    SubLObject cdolist_list_var = collisions;
	    SubLObject collision = NIL;
	    collision = cdolist_list_var.first();
	    while (NIL != cdolist_list_var) {
		SubLObject cached_args = collision.first();
		final SubLObject results2 = second(collision);
		if (filename.equal(cached_args.first())) {
		    cached_args = cached_args.rest();
		    if (((NIL != cached_args) && (NIL == cached_args.rest())) && last_modified_time.equal(cached_args.first())) {
			return memoization_state.caching_results(results2);
		    }
		}
		cdolist_list_var = cdolist_list_var.rest();
		collision = cdolist_list_var.first();
	    }
	}
	final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(http_file_request_headers_int_internal(filename, last_modified_time)));
	memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(filename, last_modified_time));
	return memoization_state.caching_results(results3);
    }

    public static final SubLObject http_file_request_expires_alt(SubLObject content_type) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    if (NIL != string_utilities.starts_with(content_type, $str_alt48$image_)) {
		return web_utilities.http_date_string(universal_time_for_start_of_universal_date(encode_universal_date(ONE_INTEGER, ONE_INTEGER, add(TWO_INTEGER, this_year())), UNPROVIDED));
	    } else {
		if (((NIL != $allow_css_js_expires_headers$.getDynamicValue(thread)) && (NIL != string_utilities.starts_with(content_type, $str_alt49$text_)))
			&& ((NIL != string_utilities.ends_with(content_type, $$$css, UNPROVIDED)) || (NIL != string_utilities.ends_with(content_type, $$$javascript, UNPROVIDED)))) {
		    return web_utilities.http_date_string(universal_time_for_start_of_universal_date(universal_date_add_days(today(), THREE_INTEGER), UNPROVIDED));
		} else {
		    return NIL;
		}
	    }
	}
    }

    public static SubLObject http_file_request_expires(final SubLObject content_type) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	if (NIL != string_utilities.starts_with(content_type, $str89$image_)) {
	    return web_utilities.http_date_string(numeric_date_utilities.universal_time_for_start_of_universal_date(numeric_date_utilities.encode_universal_date(ONE_INTEGER, ONE_INTEGER, add(TWO_INTEGER, numeric_date_utilities.this_year())), UNPROVIDED));
	}
	if (((NIL != $allow_css_js_expires_headers$.getDynamicValue(thread)) && (NIL != string_utilities.starts_with(content_type, $str90$text_)))
		&& ((NIL != string_utilities.ends_with(content_type, $$$css, UNPROVIDED)) || (NIL != string_utilities.ends_with(content_type, $$$javascript, UNPROVIDED)))) {
	    return web_utilities.http_date_string(numeric_date_utilities.universal_time_for_start_of_universal_date(numeric_date_utilities.universal_date_add_days(numeric_date_utilities.today(), THREE_INTEGER), UNPROVIDED));
	}
	return NIL;
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLString $str_alt0$_cgi_bin = makeString("/cgi-bin");

    static private final SubLString $str_alt1$_cg_ = makeString("/cg?");

    static private final SubLString $str_alt2$_cgi_bin_ws_ = makeString("/cgi-bin/ws/");

    public static final SubLObject http_file_request_content_type_alt(SubLObject filename) {
	{
	    SubLObject cdolist_list_var = $http_content_type_file_extension_table$.getGlobalValue();
	    SubLObject file_extention_info = NIL;
	    for (file_extention_info = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), file_extention_info = cdolist_list_var.first()) {
		{
		    SubLObject datum = file_extention_info;
		    SubLObject current = datum;
		    SubLObject content_type = NIL;
		    SubLObject extensions = NIL;
		    destructuring_bind_must_consp(current, datum, $list_alt53);
		    content_type = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, $list_alt53);
		    extensions = current.first();
		    current = current.rest();
		    if (NIL == current) {
			{
			    SubLObject cdolist_list_var_7 = extensions;
			    SubLObject extension = NIL;
			    for (extension = cdolist_list_var_7.first(); NIL != cdolist_list_var_7; cdolist_list_var_7 = cdolist_list_var_7.rest(), extension = cdolist_list_var_7.first()) {
				if (NIL != string_utilities.ends_with(filename, extension, symbol_function(EQUALP))) {
				    return content_type;
				}
			    }
			}
		    } else {
			cdestructuring_bind_error(datum, $list_alt53);
		    }
		}
	    }
	}
	return $http_content_type_file_extension_table$.getGlobalValue().first().first();
    }

    public static SubLObject http_file_request_content_type(final SubLObject filename) {
	SubLObject cdolist_list_var = $http_content_type_file_extension_table$.getGlobalValue();
	SubLObject file_extention_info = NIL;
	file_extention_info = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    SubLObject current;
	    final SubLObject datum = current = file_extention_info;
	    SubLObject content_type = NIL;
	    SubLObject extensions = NIL;
	    destructuring_bind_must_consp(current, datum, $list94);
	    content_type = current.first();
	    current = current.rest();
	    destructuring_bind_must_consp(current, datum, $list94);
	    extensions = current.first();
	    current = current.rest();
	    if (NIL == current) {
		SubLObject cdolist_list_var_$7 = extensions;
		SubLObject extension = NIL;
		extension = cdolist_list_var_$7.first();
		while (NIL != cdolist_list_var_$7) {
		    if (NIL != string_utilities.ends_with(filename, extension, symbol_function(EQUALP))) {
			return content_type;
		    }
		    cdolist_list_var_$7 = cdolist_list_var_$7.rest();
		    extension = cdolist_list_var_$7.first();
		}
	    } else {
		cdestructuring_bind_error(datum, $list94);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    file_extention_info = cdolist_list_var.first();
	}
	return $http_content_type_file_extension_table$.getGlobalValue().first().first();
    }

    static private final SubLString $str_alt13$Content_length = makeString("Content-length");

    static private final SubLString $str_alt14$Remote_Host = makeString("Remote-Host");

    static private final SubLString $str_alt16$_ = makeString("&");

    static private final SubLString $str_alt17$return_mime_type_ = makeString("return-mime-type:");

    static private final SubLString $str_alt18$_ = makeString("?");

    static private final SubLString $str_alt19$_ = makeString("|");

    static private final SubLSymbol $sym20$_ = makeSymbol("<");

    static private final SubLString $str_alt21$return_mime_type = makeString("return-mime-type");

    static private final SubLString $str_alt22$_ = makeString("=");

    static private final SubLString $str_alt23$text_html__charset_utf_8 = makeString("text/html; charset=utf-8");

    static private final SubLString $str_alt24$text_html = makeString("text/html");

    static private final SubLList $list_alt25 = cons(makeString("Connection"), makeString("close"));

    static private final SubLString $str_alt26$Content_Type = makeString("Content-Type");

    static private final SubLString $str_alt28$Unable_to_open__S = makeString("Unable to open ~S");

    public static final SubLObject html_determine_mime_type_alt(SubLObject filename, SubLObject type) {
	if (type == UNPROVIDED) {
	    type = NIL;
	}
	{
	    SubLObject pcase_var = type;
	    if (pcase_var.eql($PNG)) {
		return $str_alt55$image_png;
	    } else {
		if (pcase_var.eql($JPEG)) {
		    return $str_alt57$image_jpeg;
		} else {
		    if (pcase_var.eql($GIF)) {
			return $str_alt59$image_gif;
		    } else {
			if (pcase_var.eql(QUOTE) || pcase_var.eql(NIL)) {
			    return com.cyc.cycjava.cycl.http_kernel.http_file_request_content_type(filename);
			} else {
			    return com.cyc.cycjava.cycl.http_kernel.html_determine_mime_type(filename, NIL);
			}
		    }
		}
	    }
	}
    }

    public static SubLObject html_determine_mime_type(final SubLObject filename, SubLObject type) {
	if (type == UNPROVIDED) {
	    type = NIL;
	}
	final SubLObject pcase_var = type;
	if (pcase_var.eql($PNG)) {
	    return $str96$image_png;
	}
	if (pcase_var.eql($JPEG)) {
	    return $str98$image_jpeg;
	}
	if (pcase_var.eql($GIF)) {
	    return $str100$image_gif;
	}
	if (pcase_var.eql(QUOTE) || pcase_var.eql(NIL)) {
	    return http_file_request_content_type(filename);
	}
	return html_determine_mime_type(filename, NIL);
    }

    static private final SubLString $str_alt41$_jnlp = makeString(".jnlp");

    static private final SubLString $str_alt42$Last_Modified = makeString("Last-Modified");

    static private final SubLList $list_alt43 = cons(makeString("Accept-Ranges"), makeString("bytes"));

    /**
     * Return the base URL for this image's http server (if any).
     * This is suitable for use in a <BASE> HTML tag.  This function respects the (global) forwarding settings.  If the option booleanp is set, and there is no HTTP server running, this function will attempt to start it.  If this attempt is unsuccessul, or the option was not set and there is no HTTP server running, then the returned string will suggest using the default HTTP port (80).  This should be taken into account when using port forwarding.
     */
    @LispMethod(comment = "Return the base URL for this image\'s http server (if any).\r\nThis is suitable for use in a <BASE> HTML tag.  This function respects the (global) forwarding settings.  If the option booleanp is set, and there is no HTTP server running, this function will attempt to start it.  If this attempt is unsuccessul, or the option was not set and there is no HTTP server running, then the returned string will suggest using the default HTTP port (80).  This should be taken into account when using port forwarding.\nReturn the base URL for this image\'s http server (if any).\nThis is suitable for use in a <BASE> HTML tag.  This function respects the (global) forwarding settings.  If the option booleanp is set, and there is no HTTP server running, this function will attempt to start it.  If this attempt is unsuccessul, or the option was not set and there is no HTTP server running, then the returned string will suggest using the default HTTP port (80).  This should be taken into account when using port forwarding.")
    public static final SubLObject http_server_base_url_alt(SubLObject start_http_server_if_noneP) {
	if (start_http_server_if_noneP == UNPROVIDED) {
	    start_http_server_if_noneP = NIL;
	}
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject port = NIL;
		if (NIL == port) {
		    {
			SubLObject csome_list_var = tcp_server_utilities.all_tcp_servers();
			SubLObject tcp_server = NIL;
			for (tcp_server = csome_list_var.first(); !((NIL != port) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest(), tcp_server = csome_list_var.first()) {
			    if ($HTTP == tcp_server_utilities.tcp_server_type(tcp_server)) {
				port = tcp_server_utilities.tcp_server_port(tcp_server);
			    }
			}
		    }
		}
		if (!((NIL != port) || (NIL == start_http_server_if_noneP))) {
		    port = com.cyc.cycjava.cycl.http_kernel.http_port();
		    {
			SubLObject tcp_server = tcp_server_utilities.enable_tcp_server($HTTP, port);
			if (NIL == valid_process_p(tcp_server_utilities.tcp_server_process(tcp_server))) {
			    port = NIL;
			}
		    }
		}
		{
		    SubLObject forwarding_machine = cb_uia_forwarding.get_forwarding_machine_name();
		    SubLObject forwarding_port = add(cb_uia_forwarding.get_forwarding_base_port(), system_parameters.$http_port_offset$.getDynamicValue(thread));
		    return cconcatenate($str_alt62$http___,
			    new SubLObject[] { format_nil.format_nil_a_no_copy(forwarding_machine), format_nil.format_nil_a_no_copy(NIL != port ? ((SubLObject) (cconcatenate($str_alt63$_, format_nil.format_nil_a_no_copy(forwarding_port)))) : $str_alt36$), $str_alt64$_ });
		}
	    }
	}
    }

    /**
     * Return the base URL for this image's http server (if any).
     * This is suitable for use in a <BASE> HTML tag.  This function respects the (global) forwarding settings.  If the option booleanp is set, and there is no HTTP server running, this function will attempt to start it.  If this attempt is unsuccessul, or the option was not set and there is no HTTP server running, then the returned string will suggest using the default HTTP port (80).  This should be taken into account when using port forwarding.
     */
    @LispMethod(comment = "Return the base URL for this image\'s http server (if any).\r\nThis is suitable for use in a <BASE> HTML tag.  This function respects the (global) forwarding settings.  If the option booleanp is set, and there is no HTTP server running, this function will attempt to start it.  If this attempt is unsuccessul, or the option was not set and there is no HTTP server running, then the returned string will suggest using the default HTTP port (80).  This should be taken into account when using port forwarding.\nReturn the base URL for this image\'s http server (if any).\nThis is suitable for use in a <BASE> HTML tag.  This function respects the (global) forwarding settings.  If the option booleanp is set, and there is no HTTP server running, this function will attempt to start it.  If this attempt is unsuccessul, or the option was not set and there is no HTTP server running, then the returned string will suggest using the default HTTP port (80).  This should be taken into account when using port forwarding.")
    public static SubLObject http_server_base_url(SubLObject start_http_server_if_noneP) {
	if (start_http_server_if_noneP == UNPROVIDED) {
	    start_http_server_if_noneP = NIL;
	}
	final SubLThread thread = SubLProcess.currentSubLThread();
	SubLObject port = NIL;
	if (NIL == port) {
	    SubLObject csome_list_var = tcp_server_utilities.all_tcp_servers();
	    SubLObject tcp_server = NIL;
	    tcp_server = csome_list_var.first();
	    while ((NIL == port) && (NIL != csome_list_var)) {
		if ($HTTP == tcp_server_utilities.tcp_server_type(tcp_server)) {
		    port = tcp_server_utilities.tcp_server_port(tcp_server);
		}
		csome_list_var = csome_list_var.rest();
		tcp_server = csome_list_var.first();
	    }
	}
	if ((NIL == port) && (NIL != start_http_server_if_noneP)) {
	    port = http_port();
	    final SubLObject tcp_server2 = tcp_server_utilities.enable_tcp_server($HTTP, port);
	    if (NIL == valid_process_p(tcp_server_utilities.tcp_server_process(tcp_server2))) {
		port = NIL;
	    }
	}
	final SubLObject forwarding_machine = cb_uia_forwarding.get_forwarding_machine_name();
	final SubLObject forwarding_port = add(cb_uia_forwarding.get_forwarding_base_port(), system_parameters.$http_port_offset$.getDynamicValue(thread));
	return cconcatenate($str103$http___, new SubLObject[] { format_nil.format_nil_a_no_copy(forwarding_machine), format_nil.format_nil_a_no_copy(NIL != port ? cconcatenate($str104$_, format_nil.format_nil_a_no_copy(forwarding_port)) : $str78$), $str14$_ });
    }

    static private final SubLString $str_alt44$Content_Length = makeString("Content-Length");

    static private final SubLList $list_alt45 = cons(makeString("Keep-Alive"), makeString("timeout=15, max=100"));

    static private final SubLList $list_alt46 = cons(makeString("Connection"), makeString("Keep-Alive"));

    static private final SubLString $str_alt48$image_ = makeString("image/");

    static private final SubLString $str_alt49$text_ = makeString("text/");

    static private final SubLList $list_alt52 = list(list(makeString("text/html"), list(makeString("html"), makeString("htm"), makeString("shtml"))), list(makeString("text/plain"), list(makeString("txt"), makeString("text"))), list(makeString("image/gif"), list(makeString("gif"))),
	    list(makeString("image/jpeg"), list(makeString("jpeg"), makeString("jpg"), makeString("jpe"), makeString("jfif"), makeString("pjpeg"), makeString("pjp"))), list(makeString("image/png"), list(makeString("png"))), list(makeString("application/x-java-jnlp-file"), list(makeString("jnlp"))),
	    list(makeString("text/css"), list(makeString("css"))), list(makeString("text/javascript"), list(makeString("js"))));

    static private final SubLList $list_alt53 = list(makeSymbol("CONTENT-TYPE"), makeSymbol("EXTENSIONS"));

    static private final SubLString $str_alt55$image_png = makeString("image/png");

    static private final SubLString $str_alt57$image_jpeg = makeString("image/jpeg");

    static private final SubLString $str_alt59$image_gif = makeString("image/gif");

    /**
     * Return the base URL for CGI scripts for this image's http server (if any).
     * This is suitable for use in a <BASE> HTML tag.
     */
    @LispMethod(comment = "Return the base URL for CGI scripts for this image\'s http server (if any).\r\nThis is suitable for use in a <BASE> HTML tag.\nReturn the base URL for CGI scripts for this image\'s http server (if any).\nThis is suitable for use in a <BASE> HTML tag.")
    public static final SubLObject http_server_cgi_base_url_alt() {
	{
	    SubLObject base_url = com.cyc.cycjava.cycl.http_kernel.http_server_base_url(UNPROVIDED);
	    if (NIL != base_url) {
		return cconcatenate(format_nil.format_nil_a_no_copy(base_url), new SubLObject[] { format_nil.format_nil_a_no_copy(string_utilities.substring($http_cgi_bin_directory$.getGlobalValue(), ONE_INTEGER, UNPROVIDED)), $str_alt64$_ });
	    }
	}
	return NIL;
    }

    /**
     * Return the base URL for CGI scripts for this image's http server (if any).
     * This is suitable for use in a <BASE> HTML tag.
     */
    @LispMethod(comment = "Return the base URL for CGI scripts for this image\'s http server (if any).\r\nThis is suitable for use in a <BASE> HTML tag.\nReturn the base URL for CGI scripts for this image\'s http server (if any).\nThis is suitable for use in a <BASE> HTML tag.")
    public static SubLObject http_server_cgi_base_url() {
	final SubLObject base_url = http_server_base_url(UNPROVIDED);
	if (NIL != base_url) {
	    return cconcatenate(format_nil.format_nil_a_no_copy(base_url), new SubLObject[] { format_nil.format_nil_a_no_copy(string_utilities.substring($http_cgi_bin_directory$.getGlobalValue(), ONE_INTEGER, UNPROVIDED)), $str14$_ });
	}
	return NIL;
    }

    static private final SubLString $str_alt62$http___ = makeString("http://");

    static private final SubLString $str_alt63$_ = makeString(":");

    static private final SubLString $str_alt64$_ = makeString("/");

    private static final SubLSymbol $HTTP_TRACE = makeKeyword("HTTP-TRACE");

    /**
     * Return the absolute form of a possibly relative URL if possible
     */
    @LispMethod(comment = "Return the absolute form of a possibly relative URL if possible")
    public static final SubLObject get_absolute_url_alt(SubLObject url, SubLObject start_http_server_if_noneP) {
	if (start_http_server_if_noneP == UNPROVIDED) {
	    start_http_server_if_noneP = NIL;
	}
	{
	    SubLObject base_url = com.cyc.cycjava.cycl.http_kernel.http_server_base_url(start_http_server_if_noneP);
	    return web_utilities.absolute_url_from_relative_url_and_base(url, base_url);
	}
    }

    /**
     * Return the absolute form of a possibly relative URL if possible
     */
    @LispMethod(comment = "Return the absolute form of a possibly relative URL if possible")
    public static SubLObject get_absolute_url(final SubLObject url, SubLObject start_http_server_if_noneP) {
	if (start_http_server_if_noneP == UNPROVIDED) {
	    start_http_server_if_noneP = NIL;
	}
	final SubLObject base_url = http_server_base_url(start_http_server_if_noneP);
	return web_utilities.absolute_url_from_relative_url_and_base(url, base_url);
    }

    private static final SubLSymbol HTTP_TRACE_SERVER_HANDLER = makeSymbol("HTTP-TRACE-SERVER-HANDLER");

    private static final SubLSymbol ECHO_SERVER_HANDLER = makeSymbol("ECHO-SERVER-HANDLER");

    public static final SubLObject declare_http_kernel_file_alt() {
	declareFunction("http_server_handler", "HTTP-SERVER-HANDLER", 2, 0, false);
	declareFunction("http_server_top_level", "HTTP-SERVER-TOP-LEVEL", 2, 0, false);
	declareFunction("http_port", "HTTP-PORT", 0, 0, false);
	declareFunction("http_parse_request", "HTTP-PARSE-REQUEST", 1, 0, false);
	declareFunction("http_determine_remote_hostname", "HTTP-DETERMINE-REMOTE-HOSTNAME", 2, 0, false);
	declareFunction("http_handle_cgi_request", "HTTP-HANDLE-CGI-REQUEST", 3, 0, false);
	declareFunction("http_cgi_request_cg_args", "HTTP-CGI-REQUEST-CG-ARGS", 1, 1, false);
	declareFunction("http_get_content_type_for_cgi_request", "HTTP-GET-CONTENT-TYPE-FOR-CGI-REQUEST", 2, 0, false);
	declareFunction("http_cgi_request_headers", "HTTP-CGI-REQUEST-HEADERS", 1, 1, false);
	declareFunction("mime_type_from_handler_function", "MIME-TYPE-FROM-HANDLER-FUNCTION", 1, 0, false);
	declareFunction("http_handle_file_request", "HTTP-HANDLE-FILE-REQUEST", 3, 0, false);
	declareFunction("separate_args_from_java_web_start_request", "SEPARATE-ARGS-FROM-JAVA-WEB-START-REQUEST", 1, 0, false);
	declareFunction("java_web_start_file_p", "JAVA-WEB-START-FILE-P", 1, 0, false);
	declareFunction("http_file_request_filename", "HTTP-FILE-REQUEST-FILENAME", 1, 0, false);
	declareFunction("http_file_request_headers", "HTTP-FILE-REQUEST-HEADERS", 1, 0, false);
	declareFunction("http_file_request_expires", "HTTP-FILE-REQUEST-EXPIRES", 1, 0, false);
	declareFunction("http_file_request_content_type", "HTTP-FILE-REQUEST-CONTENT-TYPE", 1, 0, false);
	declareFunction("html_determine_mime_type", "HTML-DETERMINE-MIME-TYPE", 1, 1, false);
	declareFunction("http_server_base_url", "HTTP-SERVER-BASE-URL", 0, 1, false);
	declareFunction("http_server_cgi_base_url", "HTTP-SERVER-CGI-BASE-URL", 0, 0, false);
	declareFunction("get_absolute_url", "GET-ABSOLUTE-URL", 1, 1, false);
	declareFunction("http_trace_server_handler", "HTTP-TRACE-SERVER-HANDLER", 2, 0, false);
	declareFunction("http_trace_server_top_level", "HTTP-TRACE-SERVER-TOP-LEVEL", 2, 0, false);
	declareFunction("echo_server_handler", "ECHO-SERVER-HANDLER", 2, 0, false);
	declareFunction("echo_server_top_level", "ECHO-SERVER-TOP-LEVEL", 2, 0, false);
	return NIL;
    }

    public static SubLObject declare_http_kernel_file() {
	if (SubLFiles.USE_V1) {
	    declareFunction("http_server_handler", "HTTP-SERVER-HANDLER", 2, 0, false);
	    declareFunction("http_server_top_level", "HTTP-SERVER-TOP-LEVEL", 2, 0, false);
	    declareFunction("http_port", "HTTP-PORT", 0, 0, false);
	    declareFunction("http_log_requests_to_file", "HTTP-LOG-REQUESTS-TO-FILE", 1, 1, false);
	    declareFunction("http_parse_request", "HTTP-PARSE-REQUEST", 1, 0, false);
	    declareFunction("http_determine_remote_hostname", "HTTP-DETERMINE-REMOTE-HOSTNAME", 2, 0, false);
	    declareFunction("get_http_logging_sequence_generator", "GET-HTTP-LOGGING-SEQUENCE-GENERATOR", 0, 0, false);
	    declareFunction("next_http_request_id", "NEXT-HTTP-REQUEST-ID", 0, 0, false);
	    declareFunction("http_handle_cgi_request", "HTTP-HANDLE-CGI-REQUEST", 3, 0, false);
	    declareFunction("http_cgi_request_cg_args", "HTTP-CGI-REQUEST-CG-ARGS", 1, 1, false);
	    declareFunction("http_get_content_type_for_cgi_request", "HTTP-GET-CONTENT-TYPE-FOR-CGI-REQUEST", 2, 0, false);
	    declareFunction("http_cgi_request_headers", "HTTP-CGI-REQUEST-HEADERS", 1, 1, false);
	    declareFunction("http_handle_ws_request", "HTTP-HANDLE-WS-REQUEST", 5, 0, false);
	    declareFunction("possibly_split_full_query_url", "POSSIBLY-SPLIT-FULL-QUERY-URL", 1, 0, false);
	    declareFunction("http_ws_header_conversion", "HTTP-WS-HEADER-CONVERSION", 1, 0, false);
	    declareFunction("http_handle_file_request", "HTTP-HANDLE-FILE-REQUEST", 3, 0, false);
	    declareFunction("separate_args_from_file_request", "SEPARATE-ARGS-FROM-FILE-REQUEST", 1, 0, false);
	    declareFunction("java_web_start_file_p", "JAVA-WEB-START-FILE-P", 1, 0, false);
	    declareFunction("http_file_request_filename", "HTTP-FILE-REQUEST-FILENAME", 1, 0, false);
	    declareFunction("http_file_request_headers", "HTTP-FILE-REQUEST-HEADERS", 1, 0, false);
	    declareFunction("clear_http_file_request_headers_int", "CLEAR-HTTP-FILE-REQUEST-HEADERS-INT", 0, 0, false);
	    declareFunction("remove_http_file_request_headers_int", "REMOVE-HTTP-FILE-REQUEST-HEADERS-INT", 2, 0, false);
	    declareFunction("http_file_request_headers_int_internal", "HTTP-FILE-REQUEST-HEADERS-INT-INTERNAL", 2, 0, false);
	    declareFunction("http_file_request_headers_int", "HTTP-FILE-REQUEST-HEADERS-INT", 2, 0, false);
	    declareFunction("http_file_request_expires", "HTTP-FILE-REQUEST-EXPIRES", 1, 0, false);
	    declareFunction("http_file_request_content_type", "HTTP-FILE-REQUEST-CONTENT-TYPE", 1, 0, false);
	    declareFunction("html_determine_mime_type", "HTML-DETERMINE-MIME-TYPE", 1, 1, false);
	    declareFunction("http_server_base_url", "HTTP-SERVER-BASE-URL", 0, 1, false);
	    declareFunction("http_server_cgi_base_url", "HTTP-SERVER-CGI-BASE-URL", 0, 0, false);
	    declareFunction("get_absolute_url", "GET-ABSOLUTE-URL", 1, 1, false);
	}
	if (SubLFiles.USE_V2) {
	    declareFunction("mime_type_from_handler_function", "MIME-TYPE-FROM-HANDLER-FUNCTION", 1, 0, false);
	    declareFunction("separate_args_from_java_web_start_request", "SEPARATE-ARGS-FROM-JAVA-WEB-START-REQUEST", 1, 0, false);
	    declareFunction("http_trace_server_handler", "HTTP-TRACE-SERVER-HANDLER", 2, 0, false);
	    declareFunction("http_trace_server_top_level", "HTTP-TRACE-SERVER-TOP-LEVEL", 2, 0, false);
	    declareFunction("echo_server_handler", "ECHO-SERVER-HANDLER", 2, 0, false);
	    declareFunction("echo_server_top_level", "ECHO-SERVER-TOP-LEVEL", 2, 0, false);
	}
	return NIL;
    }

    public static SubLObject declare_http_kernel_file_Previous() {
	declareFunction("http_server_handler", "HTTP-SERVER-HANDLER", 2, 0, false);
	declareFunction("http_server_top_level", "HTTP-SERVER-TOP-LEVEL", 2, 0, false);
	declareFunction("http_port", "HTTP-PORT", 0, 0, false);
	declareFunction("http_log_requests_to_file", "HTTP-LOG-REQUESTS-TO-FILE", 1, 1, false);
	declareFunction("http_parse_request", "HTTP-PARSE-REQUEST", 1, 0, false);
	declareFunction("http_determine_remote_hostname", "HTTP-DETERMINE-REMOTE-HOSTNAME", 2, 0, false);
	declareFunction("get_http_logging_sequence_generator", "GET-HTTP-LOGGING-SEQUENCE-GENERATOR", 0, 0, false);
	declareFunction("next_http_request_id", "NEXT-HTTP-REQUEST-ID", 0, 0, false);
	declareFunction("http_handle_cgi_request", "HTTP-HANDLE-CGI-REQUEST", 3, 0, false);
	declareFunction("http_cgi_request_cg_args", "HTTP-CGI-REQUEST-CG-ARGS", 1, 1, false);
	declareFunction("http_get_content_type_for_cgi_request", "HTTP-GET-CONTENT-TYPE-FOR-CGI-REQUEST", 2, 0, false);
	declareFunction("http_cgi_request_headers", "HTTP-CGI-REQUEST-HEADERS", 1, 1, false);
	declareFunction("http_handle_ws_request", "HTTP-HANDLE-WS-REQUEST", 5, 0, false);
	declareFunction("possibly_split_full_query_url", "POSSIBLY-SPLIT-FULL-QUERY-URL", 1, 0, false);
	declareFunction("http_ws_header_conversion", "HTTP-WS-HEADER-CONVERSION", 1, 0, false);
	declareFunction("http_handle_file_request", "HTTP-HANDLE-FILE-REQUEST", 3, 0, false);
	declareFunction("separate_args_from_file_request", "SEPARATE-ARGS-FROM-FILE-REQUEST", 1, 0, false);
	declareFunction("java_web_start_file_p", "JAVA-WEB-START-FILE-P", 1, 0, false);
	declareFunction("http_file_request_filename", "HTTP-FILE-REQUEST-FILENAME", 1, 0, false);
	declareFunction("http_file_request_headers", "HTTP-FILE-REQUEST-HEADERS", 1, 0, false);
	declareFunction("clear_http_file_request_headers_int", "CLEAR-HTTP-FILE-REQUEST-HEADERS-INT", 0, 0, false);
	declareFunction("remove_http_file_request_headers_int", "REMOVE-HTTP-FILE-REQUEST-HEADERS-INT", 2, 0, false);
	declareFunction("http_file_request_headers_int_internal", "HTTP-FILE-REQUEST-HEADERS-INT-INTERNAL", 2, 0, false);
	declareFunction("http_file_request_headers_int", "HTTP-FILE-REQUEST-HEADERS-INT", 2, 0, false);
	declareFunction("http_file_request_expires", "HTTP-FILE-REQUEST-EXPIRES", 1, 0, false);
	declareFunction("http_file_request_content_type", "HTTP-FILE-REQUEST-CONTENT-TYPE", 1, 0, false);
	declareFunction("html_determine_mime_type", "HTML-DETERMINE-MIME-TYPE", 1, 1, false);
	declareFunction("http_server_base_url", "HTTP-SERVER-BASE-URL", 0, 1, false);
	declareFunction("http_server_cgi_base_url", "HTTP-SERVER-CGI-BASE-URL", 0, 0, false);
	declareFunction("get_absolute_url", "GET-ABSOLUTE-URL", 1, 1, false);
	return NIL;
    }

    static private final SubLString $$$daves = makeString("daves");

    static private final SubLList $list_alt78 = list(
	    list(list(makeString("/cgi-bin/ws/xml-term-learner-create-new?termString0=hissentient&url=http%3A%2F%2Flocalhost%3A3742%2Fcgi-bin%2Fcg%3Fcb-content-frame%26c3096&title=GAF index for #$CityOfChristchurchNZ&argpos=(1)")),
		    makeString("xml-term-learner-create-new&termString0=hissentient&url=http%3A%2F%2Flocalhost%3A3742%2Fcgi-bin%2Fcg%3Fcb-content-frame%26c3096&title=GAF index for #$CityOfChristchurchNZ&argpos=(1)")),
	    list(list(makeString("/cgi-bin/ws/xml-term-learner-get-followups-for-term&max=1&questionsOnly=true&conceptId=Mx4rvVixTpwpEbGdrcN5Y29ycA")), makeString("xml-term-learner-get-followups-for-term&max=1&questionsOnly=true&conceptId=Mx4rvVixTpwpEbGdrcN5Y29ycA")),
	    list(list(makeString("/cgi-bin/cg?cb-content-frame&c3096")), makeString("cb-content-frame&c3096")));

    public static final SubLObject init_http_kernel_file_alt() {
	deflexical("*HTTP-CGI-BIN-DIRECTORY*", $str_alt0$_cgi_bin);
	deflexical("*HTTP-CG-URL*", $str_alt1$_cg_);
	deflexical("*HTTP-CGI-BIN-WS-DIRECTORY*", $str_alt2$_cgi_bin_ws_);
	defconstant("*HTTP-RETURN-MIME-TYPE-PREFIX*", $str_alt17$return_mime_type_);
	defvar("*ALLOW-CSS-JS-EXPIRES-HEADERS*", T);
	deflexical("*HTTP-CONTENT-TYPE-FILE-EXTENSION-TABLE*", $list_alt52);
	return NIL;
    }

    public static SubLObject init_http_kernel_file() {
	if (SubLFiles.USE_V1) {
	    deflexical("*HTTP-CGI-BIN-DIRECTORY*", $str0$_cgi_bin);
	    deflexical("*HTTP-CG-URL*", $str1$_cg_);
	    deflexical("*HTTP-CGI-BIN-WS-DIRECTORY*", $str2$_cgi_bin_ws_);
	    deflexical("*HTTP-CYC-WS-DIRECTORY*", $str3$_cyc_ws);
	    deflexical("*HTTP-CYC-WS-MACHINE*", SubLTrampolineFile.maybeDefault($http_cyc_ws_machine$, $http_cyc_ws_machine$, NIL));
	    deflexical("*HTTP-CYC-WS-PORT*", SubLTrampolineFile.maybeDefault($http_cyc_ws_port$, $http_cyc_ws_port$, NIL));
	    deflexical("*HTTP-REQUEST-LOGGING-STREAM*", NIL);
	    defparameter("*GET-HTTP-LOGGING-SEQUENCE-GENERATOR*", $UNINITIALIZED);
	    defconstant("*HTTP-RETURN-MIME-TYPE-PREFIX*", $str33$return_mime_type_);
	    deflexical("*HTTP-FILE-REQUEST-HEADERS-INT-CACHING-STATE*", NIL);
	    defvar("*ALLOW-CSS-JS-EXPIRES-HEADERS*", T);
	    deflexical("*HTTP-CONTENT-TYPE-FILE-EXTENSION-TABLE*", $list93);
	}
	if (SubLFiles.USE_V2) {
	    defconstant("*HTTP-RETURN-MIME-TYPE-PREFIX*", $str_alt17$return_mime_type_);
	    deflexical("*HTTP-CONTENT-TYPE-FILE-EXTENSION-TABLE*", $list_alt52);
	}
	return NIL;
    }

    public static SubLObject init_http_kernel_file_Previous() {
	deflexical("*HTTP-CGI-BIN-DIRECTORY*", $str0$_cgi_bin);
	deflexical("*HTTP-CG-URL*", $str1$_cg_);
	deflexical("*HTTP-CGI-BIN-WS-DIRECTORY*", $str2$_cgi_bin_ws_);
	deflexical("*HTTP-CYC-WS-DIRECTORY*", $str3$_cyc_ws);
	deflexical("*HTTP-CYC-WS-MACHINE*", SubLTrampolineFile.maybeDefault($http_cyc_ws_machine$, $http_cyc_ws_machine$, NIL));
	deflexical("*HTTP-CYC-WS-PORT*", SubLTrampolineFile.maybeDefault($http_cyc_ws_port$, $http_cyc_ws_port$, NIL));
	deflexical("*HTTP-REQUEST-LOGGING-STREAM*", NIL);
	defparameter("*GET-HTTP-LOGGING-SEQUENCE-GENERATOR*", $UNINITIALIZED);
	defconstant("*HTTP-RETURN-MIME-TYPE-PREFIX*", $str33$return_mime_type_);
	deflexical("*HTTP-FILE-REQUEST-HEADERS-INT-CACHING-STATE*", NIL);
	defvar("*ALLOW-CSS-JS-EXPIRES-HEADERS*", T);
	deflexical("*HTTP-CONTENT-TYPE-FILE-EXTENSION-TABLE*", $list93);
	return NIL;
    }

    public static final SubLObject setup_http_kernel_file_alt() {
	tcp_server_utilities.register_tcp_server_type($HTTP, HTTP_SERVER_HANDLER, $TEXT);
	register_external_symbol(HTTP_SERVER_BASE_URL);
	register_external_symbol(HTTP_SERVER_CGI_BASE_URL);
	tcp_server_utilities.register_tcp_server_type($HTTP_TRACE, HTTP_TRACE_SERVER_HANDLER, $TEXT);
	tcp_server_utilities.register_tcp_server_type($ECHO, ECHO_SERVER_HANDLER, $TEXT);
	define_test_case_table_int(HTTP_CGI_REQUEST_CG_ARGS, list(new SubLObject[] { $TEST, EQUAL, $OWNER, $$$daves, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list_alt78);
	return NIL;
    }

    public static SubLObject setup_http_kernel_file() {
	if (SubLFiles.USE_V1) {
	    declare_defglobal($http_cyc_ws_machine$);
	    declare_defglobal($http_cyc_ws_port$);
	    tcp_server_utilities.register_tcp_server_type($HTTP, HTTP_SERVER_HANDLER, $TEXT);
	    register_external_symbol(HTTP_LOG_REQUESTS_TO_FILE);
	    memoization_state.note_globally_cached_function(HTTP_FILE_REQUEST_HEADERS_INT);
	    register_external_symbol(HTTP_SERVER_BASE_URL);
	    register_external_symbol(HTTP_SERVER_CGI_BASE_URL);
	    define_test_case_table_int(HTTP_CGI_REQUEST_CG_ARGS, list(new SubLObject[] { $TEST, EQUAL, $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list113);
	}
	if (SubLFiles.USE_V2) {
	    tcp_server_utilities.register_tcp_server_type($HTTP_TRACE, HTTP_TRACE_SERVER_HANDLER, $TEXT);
	    tcp_server_utilities.register_tcp_server_type($ECHO, ECHO_SERVER_HANDLER, $TEXT);
	    define_test_case_table_int(HTTP_CGI_REQUEST_CG_ARGS, list(new SubLObject[] { $TEST, EQUAL, $OWNER, $$$daves, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list_alt78);
	}
	return NIL;
    }

    public static SubLObject setup_http_kernel_file_Previous() {
	declare_defglobal($http_cyc_ws_machine$);
	declare_defglobal($http_cyc_ws_port$);
	tcp_server_utilities.register_tcp_server_type($HTTP, HTTP_SERVER_HANDLER, $TEXT);
	register_external_symbol(HTTP_LOG_REQUESTS_TO_FILE);
	memoization_state.note_globally_cached_function(HTTP_FILE_REQUEST_HEADERS_INT);
	register_external_symbol(HTTP_SERVER_BASE_URL);
	register_external_symbol(HTTP_SERVER_CGI_BASE_URL);
	define_test_case_table_int(HTTP_CGI_REQUEST_CG_ARGS, list(new SubLObject[] { $TEST, EQUAL, $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list113);
	return NIL;
    }

    @Override
    public void declareFunctions() {
	declare_http_kernel_file();
    }

    @Override
    public void initializeVariables() {
	init_http_kernel_file();
    }

    @Override
    public void runTopLevelForms() {
	setup_http_kernel_file();
    }

    static {
    }
}

/**
 * Total time: 389 ms
 */
