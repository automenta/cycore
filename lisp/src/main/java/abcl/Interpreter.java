/*
 * Interpreter.java
 *
 * Copyright (C) 2002-2006 Peter Graves
 * $Id$
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */

package abcl;

import cyc.CYC;
import subl.Errors;
import subl.type.symbol.SubLPackage;

import java.io.*;

import static abcl.Lisp.*;
import static cyc.CYC.addUncaught;
import static cyc.CYC.startTimeMillis;

//import org.logicmoo.system.BeanShellCntrl;

public final class Interpreter implements Runnable {
	// There should only be one globalInterpreter.
	static final Interpreter globalInterpreter = new Interpreter();

	public static boolean jlisp;
	private final InputStream inputStream;
	private final OutputStream outputStream;

	public static boolean noinit = false;
	public static boolean nosystem = false;
	private static String RC_FILE = ".abclrc";
	public static boolean noinform = false;
	private static boolean postProcess = true;
	public static boolean help = false;
	private static boolean doubledash = false;

	public static Interpreter getInstance() {
		return globalInterpreter;
	}

	/**
	 * only call once
	 */
	@Deprecated
	public static Interpreter createInstance() {
		_NOINFORM_.setSymbolValue(T);
		initializeLisp();
		postProcessCommandLine(CYC.passedArgs);
		return globalInterpreter;
	}

//	// Interface.
//	public static Interpreter createBasicInstance() {
//		try {
//			synchronized (Interpreter.class) {
//				if (globalInterpreter != null)
//					return globalInterpreter;
//				globalInterpreter = new Interpreter();
//				_NOINFORM_.setSymbolValue(T);
//				initializeLisp();
//				return globalInterpreter;
//			}
//		} finally {
//			// postProcessCommandLine(Main.passedArgs);
//		}
//	}

	public static Interpreter createDefaultInstance(String[] args) {
//		try {
//			synchronized (Interpreter.class) {
//				if (globalInterpreter != null) {
//					return null;
//				}
//				globalInterpreter = new Interpreter();
				initInstance(globalInterpreter, args, false);
//			}
//		} finally {
			postProcessCommandLine(args);
			return globalInterpreter;
//		}
	}

	private static synchronized Interpreter initInstance(Interpreter interp, String[] args, boolean jLisp) {
		//Interpreter.globalInterpreter = interp;
		if (args != null)
			preprocessCommandLineArguments(args);
		if (!noinform) {
			Stream out = getStandardOutput();
			out._writeString(banner());
			out._finishOutput();
		}
		if (help) {
			Stream out = getStandardOutput();
			out._writeString(help());
			out._finishOutput();
			exit(0); // FIXME
		}
		if (noinform)
			_NOINFORM_.setSymbolValue(T);
		else {
			double uptime = (System.currentTimeMillis() - startTimeMillis) / 1000.0;
			getStandardOutput()._writeString("Low-level initialization completed in " + uptime + " seconds.\n");
		}
		if (jLisp || jlisp) {
			initializeJLisp();
		} else {
			initializeLisp();
		}

		initializeTopLevel();

		if (!nosystem) {
			initializeSystem();
			nosystem = true;
		}

		//
		//		if (!Main.noSubLisp) {
		//			/*
		//			 * System.setIn(in); System.setOut(new PrintStream(out));
		//			 * System.setErr(new PrintStream(out));
		//			 */
		//			Main.needABCL = false;
		//			SubLMain.embeddedMain(new String[0], SubLMain.NOTHING_TO_DO);
		//		}

		if (!noinit) {
			processInitializationFile();
			noinit = true;
		}

		if (CYC.subLisp != null)
			SubLPackage.setCurrentPackage("CL-USER");
		//globalInterpreter.eval("(cl:funcall #'top-level::top-level-loop)");
		///StreamsLow.$terminal_io$.getValue().toOutputStream().flush();
		return interp;
	}

	/**
	 * @param jLisp
	 */
	private static void postProcessCommandLine(String[] passedArgs) {

		synchronized (Interpreter.class) {
			if (CYC.passedArgs == passedArgs)
				CYC.passedArgs = null;
		}
		//BeanShellCntrl.scanForExports(//BeanShellCntrl.class);
		if (passedArgs != null) {
			if (postProcess) {
				postProcess = false;
				//if (Main.subLisp) SubLPackage.setCurrentPackage("CL-USER");
				doubledash = false;

				Interpreter.postprocessCommandLineArguments(passedArgs);
			}
		} else {
			//SubLPackage.setCurrentPackage("CL-USER");
			//globalInterpreter.eval("(cl:load \"d.lisp\")");
		}
	}

//	public static Interpreter createJLispInstance(InputStream in, OutputStream out, String initialDirectory, String version) {
//		try {
//			synchronized (Interpreter.class) {
//				if (Interpreter.globalInterpreter != null) {
//					return null;
//				}
//				globalInterpreter = new Interpreter(in, out, initialDirectory);
//				return initInstance(globalInterpreter, new String[0], true);
//			}
//		} finally {
//			postProcessCommandLine(CYC.passedArgs);
//		}
//	}

//	public static Interpreter createNewLispInstance(InputStream in, OutputStream out, String initialDirectory, String version, boolean redoTodo) {
//		String[] todo = null;
//		try {
//			synchronized (Interpreter.class) {
//				todo = CYC.passedArgs;
//				CYC.passedArgs = null;
//				Interpreter interp = new Interpreter(in, out, initialDirectory);
//				if (globalInterpreter == null) {
//					globalInterpreter = interp;
//					redoTodo = true;
//				}
//				return initInstance(interp, new String[0], false);
//			}
//		} finally {
//			if (todo != null && redoTodo) {
//				postProcessCommandLine(todo);
//			}
//		}
//	}

	public static boolean initialized() {
		synchronized (Interpreter.class) {
			return initialized;
		}
	}

	public Interpreter() {
		CYC.lispInstances++;
		//jlisp = false;
		inputStream = null;
		outputStream = null;
		//if(!Main.noBSH)//BeanShellCntrl.setSingleton(this);

	}

	private Interpreter(InputStream inputStream, OutputStream outputStream, String initialDirectory) {
		CYC.lispInstances++;
		//if(!Main.noBSH)//BeanShellCntrl.setSingleton(this);
		jlisp = true;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		Lisp.setIO(inputStream, outputStream);
		if (!initialDirectory.endsWith(File.separator))
			initialDirectory = initialDirectory.concat(File.separator);
		DEFAULT_PATHNAME_DEFAULTS.setSymbolValue(new Pathname(initialDirectory));
	}

	// Interface.
	public LispObject eval(String s) {
		return Lisp.eval(new StringInputStream(s).read(true, NIL, false, LispThread.currentThread(), Stream.currentReadtable));
	}

	private static void initializeLisp() {
		synchronized (Interpreter.class) {
			if (!initialized) {
//		if (globalInterpreter == null) {
//		    globalInterpreter = new Interpreter();
//		    _NOINFORM_.setSymbolValue(T);
//		}
				Load.loadSystemFile("boot.lisp", false, false, false);
				initialized = true;
			}
		}
	}

	public static void initializeJLisp() {
		synchronized (Interpreter.class) {
			if (!initialized) {
				addFeature("J");
				Load.loadSystemFile("boot.lisp", false, false, false);
				try {

					try {
						Class.forName("abcl.j.LispAPI");
					} catch (ClassNotFoundException e) {
					} // FIXME: what to do?
					try {
						Load.loadSystemFile("j.lisp", false); // not being autoloaded
					} catch (Throwable e) {
						e.printStackTrace();
					} // FIXME: what to do?
					// Load.loadSystemFile("emacs.lisp", true); // not being autoloaded
				} catch (Throwable e) {
					e.printStackTrace();
				} // FIXME: what to do?
				initialized = true;
			}
		}
	}

	static boolean topLevelInitialized;

	private static void initializeTopLevel() {
		synchronized (Interpreter.class) {
			if (!topLevelInitialized) {
				// Resolve top-level-loop autoload.
				Symbol TOP_LEVEL_LOOP = intern("TOP-LEVEL-LOOP", PACKAGE_TPL);
				LispObject tplFun = TOP_LEVEL_LOOP.getSymbolFunction();
				if (tplFun instanceof Autoload) {
					Autoload autoload = (Autoload) tplFun;
					autoload.load();
				}

				topLevelInitialized = true;
			}
		}
	}

	private static void processInitializationFile() {
		synchronized (Interpreter.class) {
			try {
				Interpreter.noinit = true;
				// checks local directory firsts
				File file = new File(RC_FILE);
				if (!file.isFile() && file.canRead()) {
					String userHome = Pathname.guessHomeDir();
					file = new File(userHome, RC_FILE);
				}
				if (file.isFile()) {
					final double startLoad = System.currentTimeMillis();
					final String canonicalPath = file.getCanonicalPath();
					Load.load(canonicalPath);
					if (!noinform) {
						final double loadtime = (System.currentTimeMillis() - startLoad) / 1000.0;
						getStandardOutput()._writeString("Loading " + canonicalPath + " completed in " + loadtime + " seconds.\n");
					}
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
				Interpreter.noinit = false;
			}
		}
	}

	private static void initializeSystem() {
		synchronized (Interpreter.class) {
			Load.loadSystemFile("system", false); // not being autoloaded
		}
	}

	// Check for --noinit; verify that arguments are supplied for --load and
	// --eval options.  Copy all unrecognized arguments into
	// ext:*command-line-argument-list*
	private static void preprocessCommandLineArguments(String[] args) {
		synchronized (Interpreter.class) {
			LispObject arglist = NIL;

			if (args != null) {
				for (int i = 0; i < args.length; ++i) {
					String arg = args[i];
					if (doubledash) {
						arglist = new Cons(args[i], arglist);
					} else if (arg.equals("--")) {
						doubledash = true;
					} else if (arg.equals("--noinit")) {
						noinit = true;
					} else if (arg.equals("--j")) {
						jlisp = true;
					} else if (arg.equals("--noj")) {
						jlisp = false;
					} else if (arg.equals("--nosystem")) {
						nosystem = true;
					} else if (arg.equals("--noinform")) {
						noinform = true;
					} else if (arg.equals("--help")) {
						help = true;
					} else if (arg.equals("--batch")) {
						_BATCH_MODE_.setSymbolValue(T);
					} else if (arg.equals("--compile-system")) {
						nosystem = true;
						noinform = true;
					} else if (arg.equals("--eval")) {
						if (i + 1 < args.length) {
							++i;
						} else {
							System.err.println("No argument supplied to --eval");
							exit(1); // FIXME
						}
					} else if (arg.equals("--load") || arg.equals("--load-system-file")) {
						if (i + 1 < args.length) {
							++i;
						} else {
							System.err.println("No argument supplied to --load");
							exit(1); // FIXME
						}
					} else {
						arglist = new Cons(args[i], arglist);
					}
				}
			}
			arglist.nreverse();

			_COMMAND_LINE_ARGUMENT_LIST_.setSymbolValue(arglist);
		}
	}

	// Do the --load and --eval actions.
	private static void postprocessCommandLineArguments(String[] args) {
		if (args != null) {
			for (int i = 0; i < args.length; ++i) {
				String arg = args[i];
				if (doubledash) {
					continue;
				} else if (arg.equals("--")) {
					doubledash = true;
				} else if (arg.equals("--eval")) {
					if (i + 1 < args.length) {
						try {
							evaluate(args[i + 1]);
						} catch (UnhandledCondition c) {
							addUncaught(c);
							final String separator = System.getProperty("line.separator");
							StringBuilder sb = new StringBuilder();
							sb.append(separator);
							sb.append("Caught ");
							final LispObject condition = c.getCondition();
							sb.append(condition.typeOf().princToString());
							sb.append(" while processing --eval option \"" + args[i + 1] + "\":");
							sb.append(separator);
							sb.append("  ");
							final LispThread thread = LispThread.currentThread();
							thread.bindSpecial(Symbol.PRINT_ESCAPE, NIL);
							sb.append(condition.princToString());
							sb.append(separator);
							System.err.print(sb.toString());
							exit(2); // FIXME
						}
						++i;
					} else {
						// Shouldn't happen.
						System.err.println("No argument supplied to --eval");
						exit(1); // FIXME
					}
				} else if (arg.equals("--compile-system")) {
					{
						try {
							evaluate("(setf *load-verbose* t)");
							evaluate("(setf trace-lisp 10)");
							evaluate("(handler-case (compile-system :zip nil :quit t :output-path \"build/classes/\") " + "(t (x) (progn (format t \"~A: ~A~%\" (type-of x) x) (exit :status -1))))");
						} catch (UnhandledCondition c) {
							addUncaught(c);
							final String separator = System.getProperty("line.separator");
							StringBuilder sb = new StringBuilder();
							sb.append(separator);
							sb.append("Caught ");
							sb.append(c.getCondition().typeOf().printObject());
							sb.append(" while processing --compile-system");
							sb.append(separator);
							sb.append("  ");
							final LispThread thread = LispThread.currentThread();
							thread.bindSpecial(Symbol.PRINT_ESCAPE, NIL);
							sb.append(c.getCondition().princToString());
							sb.append(separator);
							System.err.print(sb.toString());
							exit(2); // FIXME
						}
						++i;
					}
				} else if (arg.equals("--nodebug"))
					debug = false;
				else if (arg.equals("--debug"))
					debug = true;
				else if (arg.equals("--nocheck"))
					checkCallers = false;
				else if (arg.equals("--check"))
					checkCallers = true;
				else if (arg.equals("--notrace"))
					evaluate("(trace-lisp nil)");
				else if (arg.equals("--trace"))
					evaluate("(trace-lisp t)");
				else if (arg.equals("--junicode"))
					LISP_NOT_JAVA = false;
				else if (arg.equals("--load") || arg.equals("--load-system-file")) {
					if (i + 1 < args.length) {
						if (arg.equals("--load"))
							Load.load(Pathname.mergePathnames(new Pathname(args[i + 1]), checkPathname(DEFAULT_PATHNAME_DEFAULTS.getSymbolValue())), false, false, true);

						else
							Load.loadSystemFile(args[i + 1], false); // not being autoloaded
						++i;
					} else {
						// Shouldn't happen.
						System.err.println("No argument supplied to --load");
						exit(1); // FIXME
					}
				}
			}
		}
		if (_BATCH_MODE_.getSymbolValue() == T) {
			exit(0); // FIXME
		}
	}

	@Override
	@SuppressWarnings("CallToThreadDumpStack")
	public void run() {
		final LispThread thread = LispThread.currentThread();
		try {
			initializeTopLevel();
			Symbol TOP_LEVEL_LOOP = intern("TOP-LEVEL-LOOP", PACKAGE_TPL);
			LispObject tplFun = TOP_LEVEL_LOOP.getSymbolFunction();
			if (tplFun instanceof Function) {
				thread.execute(tplFun);
				return;
			}
		} catch (ProcessingTerminated e) {
			throw e;
		} catch (IntegrityError e) {
			addUncaught(e);
			return;
		} catch (Throwable t) {
			addUncaught(t);
			t.printStackTrace();
			return;
		}
		// We only arrive here if something went wrong and we weren't able
		// to load top-level.lisp and run the normal top-level loop.
		run2(false);
	}

	@SuppressWarnings("CallToThreadDumpStack")
	void run2(boolean secondChance) {
		final LispThread thread = LispThread.currentThread();
		// We only arrive here if something went wrong and we weren't able
		// to load top-level.lisp and run the normal top-level loop.
		Stream standardOut = getStandardOutput();
		Stream standardInput = getStandardInput();
		while (true) {
			try {
				thread.NO_STACK_FRAMES = true;
				thread.resetStack();
				thread.clearSpecialBindings();
				standardOut._writeString("* ");
				standardOut._finishOutput();

				LispObject object = standardInput.read(false, EOF, false, thread, Stream.currentReadtable);
				if (object == EOF) {
					if (!secondChance)
						break;
				}
				standardOut.setCharPos(0);
				Symbol.MINUS.setSymbolValue(object);
				LispObject result = Lisp.eval(object, Environment.newEnvironment(), thread);
				Debug.assertTrue(result != null);
				Symbol.STAR_STAR_STAR.setSymbolValue(Symbol.STAR_STAR.symbolValue());
				Symbol.STAR_STAR.setSymbolValue(Symbol.STAR.symbolValue());
				Symbol.STAR.setSymbolValue(result);
				Symbol.PLUS_PLUS_PLUS.setSymbolValue(Symbol.PLUS_PLUS.symbolValue());
				Symbol.PLUS_PLUS.setSymbolValue(Symbol.PLUS.symbolValue());
				Symbol.PLUS.setSymbolValue(Symbol.MINUS.symbolValue());
				standardOut = getStandardOutput();
				LispObject[] values = thread.getValues();
				Symbol.SLASH_SLASH_SLASH.setSymbolValue(Symbol.SLASH_SLASH.symbolValue());
				Symbol.SLASH_SLASH.setSymbolValue(Symbol.SLASH.symbolValue());
				if (values != null) {
					LispObject slash = NIL;
					for (int i = values.length; i-- > 0; )
						slash = new Cons(values[i], slash);
					Symbol.SLASH.setSymbolValue(slash);
					for (int i = 0; i < values.length; i++)
						standardOut._writeLine(values[i].printObject());
				} else {
					Symbol.SLASH.setSymbolValue(new Cons(result));
					standardOut._writeLine(result.printObject());
				}
				standardOut._finishOutput();
			} catch (StackOverflowError e) {
				addUncaught(e);
				standardInput.clearInput();
				standardOut._writeLine("Stack overflow");
			} catch (ControlTransfer c) {
				// We're on the toplevel, if this occurs,
				// we're toast...
				addUncaught(c);
				reportError(c, thread);
			} catch (ProcessingTerminated e) {
				throw e;
			} catch (IntegrityError e) {
				addUncaught(e);
				return;
			} catch (Throwable t) {
				addUncaught(t);
				standardInput.clearInput();
				standardOut.printStackTrace(t);
				thread.printBacktrace();
			}
		}
	}

	private static void reportError(ControlTransfer c, LispThread thread) {
		getStandardInput().clearInput();
		Stream out = getStandardOutput();
		out.FRESH_LINE();
		LispObject condition = c.getCondition();
		out._writeLine("Error: unhandled condition: " + condition.princToString());
		if (thread != null)
			thread.printBacktrace();
	}

	private static void reportError(UnhandledCondition c, LispThread thread) {
		getStandardInput().clearInput();
		Stream out = getStandardOutput();
		out.FRESH_LINE();
		LispObject condition = c.getCondition();
		out._writeLine("Error: unhandled condition: " + condition.princToString());
		if (thread != null)
			thread.printBacktrace();
	}

	public void kill(int status) {
		if (jlisp) {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (Throwable e) {
				Debug.trace(e);
			}
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (Throwable e) {
				Debug.trace(e);
			}
		} else {
			flushOutputStreams();
			//BeanShellCntrl.exit(status);
		}
	}

	public synchronized void dispose() {
		Debug.trace("Interpreter.dispose");
		Debug.assertTrue(globalInterpreter == this);

//	globalInterpreter = null;
	}

	@Override
	protected void finalize() throws Throwable {
		System.err.println("Interpreter.finalize");
	}

	public static final class UnhandledCondition extends ConditionThrowable {
		//LispObject condition;

		UnhandledCondition(LispObject condition) {
			this.condition = condition;
		}

		//        public LispObject getCondition() {
		//            return condition;
		//        }

		@Override
		public String getMessage() {
			String conditionText;
			LispThread thread = LispThread.currentThread();
			SpecialBindingsMark mark = thread.markSpecialBindings();
			thread.bindSpecial(Symbol.PRINT_ESCAPE, NIL);
			try {
				final LispObject condition = getCondition();
				conditionText = condition.princToString();
			} catch (Throwable t) {
				conditionText = "<error printing Lisp condition>";
			} finally {
				thread.resetSpecialBindings(mark);
			}

			return "Unhandled lisp condition: " + conditionText;
		}

	}

	;

	private static final Primitive _DEBUGGER_HOOK_FUNCTION = new Primitive("%debugger-hook-function", PACKAGE_SYS, false) {
		@Override
		public LispObject execute(LispObject first, LispObject second) {
			final LispObject condition = first;
			if (globalInterpreter == null) {
				final LispThread thread = LispThread.currentThread();
				final SpecialBindingsMark mark = thread.markSpecialBindings();
				thread.bindSpecial(Symbol.PRINT_ESCAPE, NIL);
				try {
					final LispObject truename = LOAD_TRUENAME.symbolValue(thread);
					if (truename != NIL) {
						final LispObject stream = _LOAD_STREAM_.symbolValue(thread);
						if (stream instanceof Stream) {
							final int lineNumber = ((Stream) stream).getLineNumber() + 1;
							final int offset = ((Stream) stream).getOffset();
							Debug.trace("Error loading " + truename.princToString() + " at line " + lineNumber + " (offset " + offset + ")");
						}
					}
					Debug.trace("Encountered unhandled condition of type " + condition.typeOf().princToString() + ':');
					Debug.trace("  " + condition.princToString());
				} catch (Throwable t) {
				} // catch any exception to throw below
				finally {
					thread.resetSpecialBindings(mark);
				}
			}
			UnhandledCondition uc = new UnhandledCondition(condition);
			if (condition.typep(Symbol.JAVA_EXCEPTION) != NIL)
				uc.initCause((Throwable) JavaException.JAVA_EXCEPTION_CAUSE.execute(condition).javaInstance());
			throw uc;
		}
	};

	public static final LispObject readFromString(String s) {
		return new StringInputStream(s).read(true, NIL, false, LispThread.currentThread(), Stream.currentReadtable);
	}

	// For j.

	/**
	 * Runs its input string through the lisp reader and evaluates the result.
	 *
	 * @param s A string with a valid Common Lisp expression
	 * @return The result of the evaluation
	 * @throws UnhandledCondition in case the an error occurs which
	 *                            should be passed to the Lisp debugger
	 */
	public static LispObject evaluate(String s) {
		if (!initialized)
			initializeJLisp();
		StringInputStream stream = new StringInputStream(s);
		final LispThread thread = LispThread.currentThread();
		LispObject obj = null;

		final SpecialBindingsMark mark0 = thread.markSpecialBindings();
		thread.bindSpecial(Symbol.DEBUGGER_HOOK, _DEBUGGER_HOOK_FUNCTION);
		try { // catch possible errors from use of SHARPSIGN_DOT macros in --eval stanzas
			obj = stream.read(false, EOF, false, thread, Stream.currentReadtable);
		} finally {
			thread.resetSpecialBindings(mark0);
		}
		if (obj == EOF)
			return error(new EndOfFile(stream));

		final SpecialBindingsMark mark = thread.markSpecialBindings();
		thread.bindSpecial(Symbol.DEBUGGER_HOOK, _DEBUGGER_HOOK_FUNCTION);
		try {
			return Lisp.eval(obj, Environment.newEnvironment(), thread);
		} finally {
			thread.resetSpecialBindings(mark);
		}
	}

	private static final String build;

	static {
		String s = null;
		InputStream in = Interpreter.class.getResourceAsStream("build");
		if (in != null) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				s = reader.readLine();
				reader.close();
			} catch (IOException e) {
			}
		}
		build = s;
	}

	private static String banner() {
		final String sep = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("Armed Bear Common Lisp ");
		sb.append(Version.getVersion());
		if (build != null) {
			sb.append(" (built ");
			sb.append(build);
			sb.append(')');
		}
		sb.append(sep);
		sb.append("Java ");
		sb.append(System.getProperty("java.version"));
		sb.append(' ');
		sb.append(System.getProperty("java.vendor"));
		sb.append(sep);
		String vm = System.getProperty("java.vm.name");
		if (vm != null) {
			sb.append(vm);
			sb.append(sep);
		}
		return sb.toString();
	}

	private static String help() {
		final String sep = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("Parameters:");
		sb.append(sep);
		sb.append("--help").append(sep).append("    Displays this message.");
		sb.append(sep);
		sb.append("--noinform").append(sep).append("    Suppresses the printing of startup information and banner.");
		sb.append(sep);
		sb.append("--noinit").append(sep).append("    Suppresses the loading of the '~/.abclrc' startup file.");
		sb.append(sep);
		sb.append("--nosystem").append(sep).append("    Suppresses loading the 'system.lisp' customization file. ");
		sb.append(sep);
		sb.append("--eval <FORM>").append(sep).append("    Evaluates the <FORM> before initializing REPL.");
		sb.append(sep);
		sb.append("--load <FILE>").append(sep).append("    Loads the file <FILE> before initializing REPL.");
		sb.append(sep);
		sb.append("--load-system-file <FILE>").append(sep).append("    Loads the system file <FILE> before initializing REPL.");
		sb.append(sep);
		sb.append("--batch").append(sep).append("    The process evaluates forms specified by arguments and possibly by those").append(sep).append("    by those in the intialization file '~/.abcl', and then exits.");
		sb.append(sep);
		sb.append(sep);
		sb.append("The occurance of '--' copies the remaining arguments, unprocessed, into").append(sep).append("the variable EXTENSIONS:*COMMAND-LINE-ARGUMENT-LIST*.");
		sb.append(sep);

		return sb.toString();
	}

	public void kill() {
		// TODO Auto-generated method stub
		if (true)
			Errors.warn("Auto-generated method stub:  Interpreter.kill");

	}

	static public void exit(int i) {
		System.exit(i);
	}

	public void setIO(InputStream in, OutputStream out) {
		Lisp.setIO(in, out);
	}

}
