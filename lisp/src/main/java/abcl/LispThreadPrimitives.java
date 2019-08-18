package abcl;

import subl.util.SubLFiles;

import java.io.PrintStream;
import java.util.Iterator;

import static abcl.Lisp.*;

public enum LispThreadPrimitives { ;

	public static final Primitive CURRENT_THREAD = new pf_current_thread();
	public static final Primitive BACKTRACE = new LispThread.pf_backtrace();
	public static final Primitive FRAME_TO_STRING = new LispThread.pf_frame_to_string();
	public static final Primitive FRAME_TO_LIST = new LispThread.pf_frame_to_list();
	public static final SpecialOperator SYNCHRONIZED_ON = new LispThread.so_synchronized_on();
	public static final Primitive OBJECT_WAIT = new LispThread.pf_object_wait();
	public static final Primitive OBJECT_NOTIFY = new LispThread.pf_object_notify();
	public static final Primitive OBJECT_NOTIFY_ALL = new LispThread.pf_object_notify_all();
	@DocString(name = "sleep", args = "seconds", doc = "Causes the invoking thread to sleep for an interveral expressed in SECONDS.\n"
			+ "SECONDS may be specified as a fraction of a second, with intervals\n"
			+ "less than or equal to a nanosecond resulting in a yield of execution\n"
			+ "to other waiting threads rather than an actual sleep.\n"
			+ "A zero value of SECONDS *may* result in the JVM sleeping indefinitely,\n"
			+ "depending on the implementation.")
	private static final Primitive SLEEP = new Primitive("sleep", PACKAGE_CL, true) {
		@Override
		public LispObject execute(LispObject arg) {
			long millis = LispThread.sleepMillisPart(arg);
			int nanos = LispThread.sleepNanosPart(arg);
			boolean zeroArgP = arg.ZEROP() != NIL;

			try {
				if (millis == 0 && nanos == 0) {
					if (zeroArgP) {
						Thread.sleep(0, 0);
					} else {
						Thread.sleep(0, 1);
					}
				} else {
					Thread.sleep(millis, nanos);
				}
			} catch (InterruptedException e) {
				LispThread.currentThread().processThreadInterrupts();
			}
			return NIL;
		}
	};
	@DocString(name = "destroy-thread", args = "thread", doc = "Mark THREAD as destroyed")
	private static final Primitive DESTROY_THREAD = new Primitive("destroy-thread", PACKAGE_THREADS, true) {
		@Override
		public LispObject execute(LispObject arg) {
			final LispThread thread;
			if (arg instanceof LispThread) {
				thread = (LispThread) arg;
			} else {
				return type_error(arg, Symbol.THREAD);
			}
			thread.setDestroyed(true);
			return T;
		}
	};
	// => T
	@DocString(name = "interrupt-thread", args = "thread function &rest args", doc = "Interrupts thread and forces it to apply function to args. When the\n"
			+ "function returns, the thread's original computation continues. If\n"
			+ "multiple interrupts are queued for a thread, they are all run, but the\n" + "order is not guaranteed.")
	private static final Primitive INTERRUPT_THREAD = new Primitive("interrupt-thread", PACKAGE_THREADS, true,
			"thread function &rest args",
			"Interrupts THREAD and forces it to apply FUNCTION to ARGS.\nWhen the function returns, the thread's original computation continues. If  multiple interrupts are queued for a thread, they are all run, but the order is not guaranteed.") {
		@Override
		public LispObject execute(LispObject[] args) {
			if (args.length < 2)
				return error(new WrongNumberOfArgumentsException(this, 2, -1));
			final LispThread thread;
			if (args[0] instanceof LispThread) {
				thread = (LispThread) args[0];
			} else {
				return type_error(args[0], Symbol.THREAD);
			}
			LispObject fun = args[1];
			LispObject funArgs = NIL;
			for (int i = args.length; i-- > 2;)
				funArgs = new Cons(args[i], funArgs);
			thread.interrupt(fun, funArgs);
			return T;
		}
	};
	@DocString(name = "trace-lisp", args = "value &optional function")
	static final Primitive TRACE_LISP = new Primitive("trace-lisp", PACKAGE_EXT, true,
			"value &optional function") {
		@Override
		public LispObject execute(LispObject[] args) {
			LispThread thread = LispThread.currentThread();
			if (args == null || args.length == 0) {
				if (thread.disableTrace) {
					thread.disableTrace = false;
				}
				if (thread.traceSome)
					args = new LispObject[] { T };
			}
			final LispObject arg0 = args[0];
			PrintStream ps = thread.ps;
			LispThread._TRACE_LISP_.setSymbolValue(arg0);
			final boolean traceOn = !arg0.isNil();
			thread.disableTrace = !traceOn;
			if (traceOn) {
				thread.enableTraceAll = arg0 == T;
				if (!thread.enableTraceAll) {
					thread.traceSome = true;
				}
			}

			// if (traceOn)
			// new Throwable("FAKE TRACE_LISP: ").printStackTrace(ps);
			LispThread olt = LispThread.map.get(LispThread.mainThread);

			if (Packages.findPackage("SWANK") != null) {
				Symbol sym = Symbol.PRINT_PPRINT_DISPATCH;
				Symbol symb = (Symbol) Lisp
						.readObjectFromString("swank::*backtrace-pprint-dispatch-table*".toUpperCase());
				ps.println(olt + " " + symb + "=" + symb.symbolValue(olt));
				ps.println(thread + " " + symb + "=" + symb.symbolValue(thread));
				ps.println(olt + " " + sym + "=" + symb.symbolValue(olt));
				ps.println(thread + " " + sym + "=" + symb.symbolValue(thread));
			}

			// Symbol.ERROR.setSymbolFunction(new signal_2());

			return NIL;
		}
	};
	@DocString(name = "make-thread", args = "function &key name :INITIAL-BINDINGS :CSTACK-SIZE :VSTACK-SIZE")
	private static final Primitive MAKE_THREAD = new Primitive("make-thread", PACKAGE_THREADS, true,
			"function &key name :INITIAL-BINDINGS :CSTACK-SIZE :VSTACK-SIZE") {
		@Override
		public LispObject execute(LispObject[] args) {
			final int length = args.length;
			if (length == 0)
				error(new WrongNumberOfArgumentsException(this, 1, -1));
			if (length > 1) {
				if ((length - 1) % 2 != 0)
					program_error("Odd number of keyword arguments.");
			}
			long cstack = 0;
			long vstack = 0;
			LispObject name = SubLFiles.findKeyword(1, args, Keyword.NAME, () -> NIL);
			if (name != NIL) {
				name = name.STRING();
			}
			LispObject initialBindings = SubLFiles.findKeyword(1, args, LispThread.INITIAL_BINDING,
					() -> Symbol._DEFAULT_SPECIAL_BINDINGS_.symbolValue());
			// else
			// program_error("Unrecognized keyword argument "
			// + args[1].princToString() + ".");
			return new LispThread.LispThreadImpl(checkFunction(args[0]), name, initialBindings, vstack, cstack);
		}
	};
	@DocString(name = "threadp", args = "object", doc = "Boolean predicate testing if OBJECT is a thread.")
	private static final Primitive THREADP = new Primitive("threadp", PACKAGE_THREADS, true) {
		@Override
		public LispObject execute(LispObject arg) {
			return arg instanceof LispThread ? T : NIL;
		}
	};
	@DocString(name = "thread-alive-p", args = "thread", doc = "Returns T if THREAD is alive.")
	private static final Primitive THREAD_ALIVE_P = new Primitive("thread-alive-p", PACKAGE_THREADS, true, "thread",
			"Boolean predicate whether THREAD is alive.") {
		@Override
		public LispObject execute(LispObject arg) {
			final LispThread lispThread;
			if (arg instanceof LispThread) {
				lispThread = (LispThread) arg;
			} else {
				return type_error(arg, Symbol.THREAD);
			}
			return lispThread.javaThread.isAlive() ? T : NIL;
		}
	};
	@DocString(name = "thread-active-p", args = "thread", doc = "Returns T if THREAD is alive.")
	private static final Primitive THREAD_ACTIVE_P = new Primitive("thread-alive-p", PACKAGE_THREADS, true, "thread",
			"Boolean predicate whether THREAD is already terminated.") {
		@Override
		public LispObject execute(LispObject arg) {
			final LispThread lispThread;
			if (arg instanceof LispThread) {
				lispThread = (LispThread) arg;
			} else {
				return type_error(arg, Symbol.THREAD);
			}
			return lispThread.javaThread.isAlive() ? T : NIL;
		}
	};
	@DocString(name = "thread-name", args = "thread", doc = "Return the name of THREAD, if it has one.")
	private static final Primitive THREAD_NAME = new Primitive("thread-name", PACKAGE_THREADS, true) {
		@Override
		public LispObject execute(LispObject arg) {
			if (arg instanceof LispThread) {
				return ((LispThread) arg).name;
			}
			return type_error(arg, Symbol.THREAD);
		}
	};
	private static final Primitive THREAD_JOIN = new Primitive("thread-join", PACKAGE_THREADS, true, "thread",
			"Waits for thread to finish.") {
		@Override
		public LispObject execute(LispObject arg) {
			// join the thread, and returns it's value. The second return
			// value is T if the thread finishes normally, NIL if its
			// interrupted.
			if (arg instanceof LispThread) {
				final LispThread joinedThread = (LispThread) arg;
				final LispThread waitingThread = LispThread.currentThread();
				try {
					joinedThread.javaThread.join();
					return waitingThread.setValues(joinedThread.threadValue, T);
				} catch (InterruptedException e) {
					waitingThread.processThreadInterrupts();
					return waitingThread.setValues(joinedThread.threadValue, NIL);
				}
			} else {
				return type_error(arg, Symbol.THREAD);
			}
		}
	};
	@DocString(name = "mapcar-threads", args = "function", doc = "Applies FUNCTION to all existing threads.")
	private static final Primitive MAPCAR_THREADS = new Primitive("mapcar-threads", PACKAGE_THREADS, true) {
		@Override
		public LispObject execute(LispObject arg) {
			Function fun = checkFunction(arg);
			final LispThread thread = LispThread.currentThread();
			LispObject result = NIL;
			Iterator it = LispThread.map.values().iterator();
			while (it.hasNext()) {
				LispObject[] args = new LispObject[1];
				args[0] = (LispThread) it.next();
				result = new Cons(Lisp.funcall(fun, args, thread), result);
			}
			return result;
		}
	};

	@DocString(name = "current-thread", doc = "Returns a reference to invoking thread.")
	static final class pf_current_thread extends Primitive {
		pf_current_thread() {
			super("current-thread", PACKAGE_THREADS, true);
		}

		@Override
		public LispObject execute() {
			return LispThread.currentThread();
		}
	}
}
