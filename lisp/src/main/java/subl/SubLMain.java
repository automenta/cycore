/*
 * java
 *
 * Created on December 13, 2005, 1:41 PM
 */
package subl;

import abcl.Interpreter;
import abcl.Lisp;
import com.cyc.misc.rcycl;
import cyc.CYC;
import org.jpl7.JPL;
import org.jpl7.Query;
import subl.jrtl.translatedCode.sublisp.reader;
import subl.type.core.*;
import subl.type.exception.ResumeException;
import subl.type.operator.SubLFunction;
import subl.type.symbol.SubLNil;
import subl.type.symbol.SubLPackage;
import subl.type.symbol.SubLSymbol;
import subl.util.PatchFileLoader;
import subl.util.SubLFiles;
import subl.util.SubLTranslatedFile;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

// import cyc.CYCEval;
//import org.logicmoo.system.BeanShellCntrl;
//import org.logicmoo.system.SystemCurrent;
// Internal imports
// External imports

/**
 * Typical arguments: -i "/cyc/top/init/jrtl-init.lisp" Typical Java params:
 * -server -Xms256m -Xmx776m -Xss1m -XX:MaxPermSize=128m Typical working
 * directory: /home/<user>/cvs/head/cycorp/cyc/top/
 *
 * @author goolsbey, tbrussea, dmiles
 */
public class SubLMain {
    public static boolean OPENCYC = false;
    public static boolean TINY_KB = false;
    public static boolean Never_REDEFINE = false;
    public static boolean Always_REDEFINE = false;
    public static boolean BOOTY_HACKZ = true;

//    static {
//	SystemCurrent.setupIO();
//    }

    public static final class InitialEmbeddedMain extends SubLProcess {
	private final Runnable runnable;
	private final String[] args;

	public InitialEmbeddedMain(String name, Runnable runnable, String[] args) {
	    super(name);
	    this.runnable = runnable;
	    this.args = args;
	}

	@Override
	public void safeRun() {
	    try {
		if (OPENCYC && false) {
		    doInitialEmbeddedMainOriginal(args);
		} else {
		    doInitialEmbeddedMain(args);
		}
		runnable.run();
	    } catch (RuntimeException e2) {
		System.out.println("Initial Lisp Listener Exiting Now");
		throw e2;
	    } finally {
		System.out.println("Initial Lisp Listener Exiting Now");
	    }
	}
    }

    static void doInitialEmbeddedMainOriginal(String[] args) {
	if (!SubLMain.shouldRunInBackground()) {
	    System.out.println("Starting Cyc.");
	}
	startTime = System.currentTimeMillis();
	try {
	    SubLMain.initializeSubL(args);
	    SubLMain.initializeTranslatedSystems();
	} catch (Exception e) {
	    e.printStackTrace();
	    SubLMain.me.doSystemCleanupAndExit(-1);
	}
	long endTime = System.currentTimeMillis();
	double theTime = (endTime - startTime) / 1000.0D;
	if (!SubLMain.shouldRunInBackground()) {
	    System.out.println("Internal initialization time = " + theTime + " secs.");
	}
	startTime = System.currentTimeMillis();
	SubLMain.handleInits();
	if (!SubLMain.shouldRunInBackground()) {
	    endTime = System.currentTimeMillis();
	    theTime = (endTime - startTime) / 1000.0D;
	    System.out.println("Initialization time = " + theTime + " secs.");
	    System.out.println();
	    SubLMain.writeSystemInfo();
	    Storage.room(SubLNil.NIL);
	    StreamsLow.$terminal_io$.getValue().toOutputStream().flush();
	}
    }

    /**
     *
     */
    static void doInitialEmbeddedMain(String[] args) {
	CYC.setSubLisp(true);
	if (!shouldRunInBackground())
	    System.out.println("Starting Cyc.");
	startTime = System.currentTimeMillis();
	try {
	    preInitLisp();
	    initializeSubL(args);
	} catch (Exception e) {
	    e.printStackTrace();
	    me.doSystemCleanupAndExit(-1);
	}
	SubLPackage prevPackage = SubLPackage.getCurrentPackage();
	try {
	    SubLPackage.setCurrentPackage(Lisp.PACKAGE_SYS);
	    long startABCL = System.currentTimeMillis();
	    // Main.setSubLisp(false);
	    Interpreter.createDefaultInstance(new String[] {});
	    startTime += (System.currentTimeMillis() - startABCL);
	} catch (Exception e) {
	    e.printStackTrace();
	    // me.doSystemCleanupAndExit(-1);
	} finally {
	    CYC.setSubLisp(true);
	    SubLPackage.setCurrentPackage(prevPackage);
	}
	if (!noInitCyc)
	    initializeTranslatedSystems();
	long endTime = System.currentTimeMillis();
	double theTime = (endTime - startTime) / 1000.0;
	if (!shouldRunInBackground())
	    System.out.println("Internal initialization time = " + theTime + " secs.");
	startTime = System.currentTimeMillis();
	if (!delayEvalParams)
	    handleInits();
	if (!shouldRunInBackground()) {
	    endTime = System.currentTimeMillis();
	    theTime = (endTime - startTime) / 1000.0;
	    System.out.println("Initialization time = " + theTime + " secs.");
	    System.out.println();
	    writeSystemInfo();
	    Storage.room(SubLNil.NIL);
	}
	StreamsLow.$terminal_io$.getValue().toOutputStream().flush();
	//BeanShellCntrl.registerSelf();
    }

    public static final class RunnableMain implements Runnable {
	@Override
	public void run() {
	    boolean wasSubLisp = CYC.isSubLisp();
	    try {
		boolean startInSubLisp = true;
		Object val = me.argNameToArgValueMap.get("repl");
		if (val != null) {
		    startInSubLisp = !val.toString().endsWith("cl");
		}
		if (startInSubLisp) {
		    SubLPackage.setCurrentPackage(SubLPackage.CYC_PACKAGE);
		    // CycEval.CYC_REPL.execute();
		    //BeanShellCntrl.cyc_repl();
		} else {
		    // SubLPackage.setCurrentPackage(Lisp.PACKAGE_CL_USER);
		    SubLPackage.setCurrentPackage(SubLPackage.CYC_PACKAGE);
		    // CycEval.LISP_REPL.execute();
		    //BeanShellCntrl.lisp_repl();
		}
	    }
//	    } catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		throw new RuntimeException(e);
//	    }
	    finally {
		CYC.setSubLisp(wasSubLisp);
	    }
	}
    }

    public interface Cleanup {
	void cleanup();
    }

    private static class MemoryListener implements NotificationListener {
	// @Override
	@Override
	public void handleNotification(Notification notification, Object handback) {
	    String notifType = notification.getType();
	    if (notifType.equals("java.management.memory.threshold.exceeded") || notifType.equals("java.management.memory.collection.threshold.exceeded"))
		lowMemorySemaphore.release();
	}
    }

    public static InputStream ORIGINAL_IN_STREAM;
    public static PrintStream ORIGINAL_OUT_STREAM;
    public static PrintStream ORIGINAL_ERR_STREAM;
    public static SubLMain me;
    private static List<Cleanup> cleanups;
    private static Set<String> noArgCommandLineArgs;
    private static Set<String> argRequiredCommandLineArgs;
    private static volatile boolean isInitialized;
    private static volatile boolean isFullyInitialized;
    private static ArrayList<SubLFunction> lowMemoryCallbacks;
    private static Semaphore lowMemorySemaphore;
    private static volatile boolean isSubLInitialized;
    public static volatile boolean isSubLInitialized_part0;
    public static Runnable NOTHING_TO_DO;
    protected static boolean noInitLisp = false;
    public static boolean noInitCyc = true;
    protected static boolean delayEvalParams = true;
    protected static boolean noInit;
    public static boolean commonSymbolsOK;
    public static long fistStartTime;
    protected static long startTime;
    public static boolean shouldRunInBackground;
    private static boolean isInitializedTranslatedSystems;
    private static List<String> loadedTranslatedSystems = new ArrayList<>();
    static {
	captureStreams();
	me = new SubLMain();
	cleanups = Collections.synchronizedList(new ArrayList<>(16));
	noArgCommandLineArgs = new HashSet<>();
	argRequiredCommandLineArgs = new HashSet<>();
	isInitialized = false;
	isFullyInitialized = false;
	lowMemoryCallbacks = new ArrayList<>();
	lowMemorySemaphore = new Semaphore(0);
	isSubLInitialized = false;
	noArgCommandLineArgs.add("-gui");
	noArgCommandLineArgs.add("-q");
	noArgCommandLineArgs.add("-b");
	noArgCommandLineArgs.add("-a");
	argRequiredCommandLineArgs.add("-i");
	argRequiredCommandLineArgs.add("-f");
	argRequiredCommandLineArgs.add("-w");
	NOTHING_TO_DO = () -> {
	};
    }

    public static void deregisterLowMemoryCallback(SubLFunction func) {
	if (func == null)
	    Errors.error("Unable to deregister low memory callback for null function.");
	lowMemoryCallbacks.remove(func);
    }

    // sometimes called when this was started from a interface like telnet
    public static void captureStreams() {
	ORIGINAL_IN_STREAM = System.in;
	ORIGINAL_OUT_STREAM = System.out;
	ORIGINAL_ERR_STREAM = System.err;
    }

    public static void embeddedMain(String[] args) {
	embeddedMain(args, NOTHING_TO_DO);
    }

    public static void embeddedMain(final String[] args, final Runnable runnable) {
	me.processCommandLineArgs(args);
	try {
	    SubLProcess subLProcess = new InitialEmbeddedMain("Initial Lisp Listener", runnable, args);
	    SubLThreadPool.getDefaultPool().execute(subLProcess);
	} catch (Exception e) {
	    Errors.handleError(e);
	}
    }

    public static SubLObject get_red_object() {
	return Errors.unimplementedMethod("get_red_object()");
    }

    public static String getCommandLineArg(String argName) {
	return (String) me.argNameToArgValueMap.get(argName);
    }

    public static SubLString getInitializationFileName() {
	String fileName = (String) me.argNameToArgValueMap.get("-i");
	return fileName == null ? null : SubLObjectFactory.makeString(fileName);
    }

    public static String getInitializationForm() {
	return (String) me.argNameToArgValueMap.get("-f");
    }

    static ThreadLocal<SubLReader> mainReader = new ThreadLocal<>();
    private static SubLReader trueMainReader;
    private static boolean didHandleInits;
    private static boolean didInitFileRunning;
    private static boolean didAllegroCompatiblityMode;
    private static boolean didInitFormRunning;

    public static SubLReader getMainReader() {
	SubLReader locally = mainReader.get();
	if (locally != null)
	    return locally;
	return null;
    }

    public static SubLString getWorldFileName() {
	String fileName = (String) me.argNameToArgValueMap.get("-w");
	return fileName == null ? null : SubLObjectFactory.makeString(fileName);
    }

    private static void handleAllegroCompatiblityMode() {
	if (didAllegroCompatiblityMode)
	    return;
	SubLMain.didAllegroCompatiblityMode = true;
	if (isInAllegroCompatibilityMode())
	    SubLProcess.setAllegoSingleThreadedThread(SubLProcess.currentSubLThread());
    }

    private static void handleInitFileRunning() {
	if (didInitFileRunning)
	    return;
	SubLMain.didInitFileRunning = true;
	SubLString initFile = getInitializationFileName();
	if (initFile != null)
	    try {
		Eval.load(initFile);
	    } catch (Exception e) {
		Errors.handleError("Failed to load initialization file: " + initFile, e);
	    }
    }

    private static void handleInitFormRunning() {
	if (didInitFormRunning)
	    return;
	SubLMain.didInitFormRunning = true;
	boolean exit_with_error = false;
	try {
	    String initForm = getInitializationForm();
	    if (initForm != null)
		try {
		    SubLString initFormString = SubLObjectFactory.makeString(initForm);
		    SubLObject form = reader.read_from_string(initFormString, CommonSymbols.T, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED);
		    form.eval(SubLEnvironment.currentEnvironment());
		} catch (ResumeException re) {
		    System.out.println("[ Resuming via jump to top level read loop... ]");
		} catch (SubLProcess.TerminationRequest tr) {
			// ignore
		} catch (Throwable t) {
		    try {
			Errors.handleThrowable(t, SubLNil.NIL);
		    } catch (ResumeException re2) {
			System.out.println("[ Resuming via jump to top level read loop... ]");
		    } catch (SubLProcess.TerminationRequest tr2) {
		    } catch (Throwable th) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Error processing initialization form: '" + initForm + "'", th);
			exit_with_error = true;
		    }
		}
	} finally {
	    if (shouldQuitAfterExecutingInitializationForm())
		if (me != null)
		    me.doSystemCleanupAndExit(exit_with_error ? 1 : 0);
		else {
			//BeanShellCntrl.exit(exit_with_error ? 1 : 0);
		}
	}
    }

    public static synchronized void handleInits() {
	if (didHandleInits)
	    return;
	SubLMain.didHandleInits = true;
	SubLString worldFile = getWorldFileName();
	// @todo do something with worldFile if not null here -APB
	setIsInitialized();
	try {
	    handleInitFileRunning();
	    handleInitFormRunning();
	    handleAllegroCompatiblityMode();
	    if (shouldQuitAfterExecutingInitializationForm())
		me.doSystemCleanupAndExit(0);
	} finally {
	    setIsFullyInitialized();
	}
    }

    public static void handlePatches() {
	// placeholder
    }

    static boolean initializedLMD = false;

    private static void initializeLowMemoryDetection() {
	if (initializedLMD)
	    return;
	initializedLMD = true;
	MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
	NotificationEmitter emitter = (NotificationEmitter) mbean;
	MemoryListener listener = new MemoryListener();
	emitter.addNotificationListener(listener, null, null);
	List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
	for (MemoryPoolMXBean pool : pools) {
	    // @todo other garbage collector may use diffeerent names
	    MemoryType curPoolType = pool.getType();
	    if (pool.getName().contains("Tenured") || pool.getName().contains("Old")) {
		long max = pool.getUsage().getMax();
		long percent90 = (long) (max * 0.90001);
		Runtime rt = Runtime.getRuntime();
		long threshold = (long) (max - 1.073741824E9);
		if (threshold < percent90)
		    threshold = percent90;
		if (pool.isCollectionUsageThresholdSupported()) {
		    System.out.println("Low memory situations will be triggered when post-gc usage exceeds " + (int) (threshold / 1048576.0) + "MB(" + (int) (100L * threshold / max) + "% of " + (int) (max / 1048576.0) + "MB) for " + curPoolType + " pool " + pool.getName());
		    pool.setCollectionUsageThreshold(threshold);
		} else if (pool.isUsageThresholdSupported()) {
		    System.out.println("Low memory situations will be triggered when usage exceeds " + (int) (threshold / 1048576.0) + "MB(" + (int) (100L * threshold / max) + "% of " + (int) (max / 1048576.0) + "MB) for " + curPoolType + " pool " + pool.getName());
		    pool.setUsageThreshold(threshold);
		} else
		    Errors.warn("Unable to detect low memory situations.");
	    }
	}
	SubLObjectFactory.makeProcess(SubLObjectFactory.makeString("Low Memory Scavenger"), () -> {
	while (true) {
		lowMemorySemaphore.acquireUninterruptibly();
		Runtime rt = Runtime.getRuntime();
		long totalMemory = rt.totalMemory();
		long freeMemory = rt.freeMemory();
		long usedMemory = totalMemory - freeMemory;
		long eightyPercentMemory = (long) (totalMemory * 0.8);
		if (usedMemory >= eightyPercentMemory)
		lowMemorySituation();
		lowMemorySemaphore.drainPermits();
	}
	});
    }

    public static synchronized void initializeSubL(String[] argsIgnored) {
	if (isSubLInitialized || isSubLInitialized_part0)
	    return;
	isSubLInitialized_part0 = true;
	preInitLisp();
	try {
	    PatchFileLoader.PATCH_FILE_LOADER.loadClass("subl.type.symbol.SubLPackage");
	} catch (Exception e) {
	    e.printStackTrace(); // @hack
	}
	SubLPackage.initPackages();
	commonSymbolsOK = true;
	try {
	    Class c = PatchFileLoader.PATCH_FILE_LOADER.loadClass("subl.CommonSymbols");
	    PatchFileLoader.PATCH_FILE_LOADER.loadClass("subl.CommonSymbols_SYM");
	    // , true);
	    SubLSymbol sym2 = CommonSymbols_SYM.OBJECT_ID; // initialized first
	    SubLSymbol sym3 = CommonSymbols_SYM.OBJECT_MONITOR; // initialized first
	    SubLSymbol sym4 = CommonSymbols_SYM.ARGLIST; // initialized first
	    SubLSymbol sym = CommonSymbols.EQ; // @hack to make sure this get
	} catch (Exception e) {
	    e.printStackTrace(); // @hack
	}
	SubLPackage.setCurrentPackage("SUBLISP");
	assert CommonSymbols.EQ.getPackage() == SubLPackage.getCurrentPackage();
	SubLFiles.initialize("subl.Packages");
	SubLFiles.completePass();
	SubLFiles.initialize("subl.DiskDumper");
	SubLFiles.initialize("subl.LispSync");
	SubLFiles.initialize("subl.PrologSync");
	SubLPackage.setCurrentPackage(SubLPackage.SUBLISP_PACKAGE.toPackage());
	SubLFiles.initialize("subl.Equality");
	SubLFiles.initialize("subl.SubLSpecialOperatorDeclarations");
	SubLFiles.initialize("subl.Numbers");
	SubLFiles.initialize("subl.Strings");
	SubLFiles.initialize("subl.Types");
	SubLFiles.initialize("subl.ConsesLow");
	SubLFiles.initialize("subl.Alien");
	SubLFiles.initialize("subl.Values");
	SubLFiles.initialize("subl.Characters");
	BinaryFunction.initialize(); // this must come after Equality -APB
	SubLFiles.initialize("subl.Semaphores");
	SubLFiles.initialize("subl.Dynamic");
	SubLFiles.initialize("subl.Errors");
	SubLFiles.initialize("subl.Environment");
	SubLFiles.initialize("subl.Eval");
	SubLFiles.initialize("subl.Filesys");
	SubLFiles.initialize("subl.Functions");
	SubLFiles.initialize("subl.Guids");
	SubLFiles.initialize("subl.Hashtables");
	SubLFiles.initialize("subl.JavaLink");
	SubLFiles.completePass();
	// SubLFiles.initialize("Keyhashes");
	SubLFiles.initialize("subl.Locks");
	SubLFiles.initialize("subl.ReadWriteLocks");
	SubLFiles.initialize("subl.Mapper");
	SubLFiles.initialize("subl.Mapping");
	SubLFiles.initialize("subl.PrintLow");
	SubLFiles.initialize("subl.Processes");
	try {
	    SubLPackage.setCurrentPackage(SubLPackage.CYC_PACKAGE);
	    SubLFiles.initialize("subl.Regex");
	} finally {
	    SubLPackage.setCurrentPackage(SubLPackage.SUBLISP_PACKAGE.toPackage());
	}
	SubLFiles.initialize("subl.Sequences");
	SubLFiles.initialize("subl.Sort");
	SubLFiles.initialize("subl.Storage");
	SubLFiles.initialize("subl.StreamsLow");
	SubLFiles.initialize("subl.Structures");
	SubLFiles.initialize("subl.Sxhash");
	SubLFiles.initialize("subl.Symbols");
	SubLFiles.initialize("subl.SystemInfo");
	SubLFiles.initialize("subl.Tcp");
	SubLFiles.initialize("subl.Threads");
	SubLFiles.initialize("subl.Time");
	SubLFiles.initialize("subl.UserIO");
	SubLFiles.initialize("subl.Vectors");
	// SubLFiles.initialize("SubL");
	// translated RTL extensions
	// these are in the order they are initialized in the C RTL
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.print_high");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.streams_high");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.stream_macros");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.print_macros");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.print_functions");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.conses_high");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.hashtables_high");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.bytes");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.environment");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.foreign");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.format");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.visitation");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.reader");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.random");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.cdestructuring_bind");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.complex_special_forms");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.character_names");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.math_utilities");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.compatibility");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.time_high");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.condition_macros");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.thread_macros");
	SubLFiles.initialize("subl.jrtl.translatedCode.sublisp.subl_benchmarks");
	SubLFiles.completePass();
	Errors.isReady = true;
	ZeroArityFunction.initialize(); // this must come after ConsesLow -APB
	UnaryFunction.initialize(); // this must come after ConsesLow -APB
	Lisp.addFeature("CYC-LARKC");
	if (OPENCYC) {
	    Lisp.addFeature("MAIN-OPENCYC");
	} else {
	    Lisp.addFeature("MAIN-RCYC");
	}
	AbstractSubLSequence.init();
	if (!shouldRunInBackground()) {
	    setMainReader(new SubLReader());
	    getMainReader().setThread(SubLProcess.currentSubLThread());
	}
	isSubLInitialized_part0 = true;
    }

    public synchronized static void initializeTranslatedSystems() {
	if (isInitializedTranslatedSystems)
	    return;
	boolean completed = false;
	try {
	    isInitializedTranslatedSystems = true;
	    if (SubLMain.OPENCYC)
		initialize1TranslatedSystem("com.cyc.cycjava.cycl.cycl");
	    if (!SubLMain.OPENCYC) {
		//initialize1TranslatedSystem("com.cyc.cycjava_2.cycl.cycl");
		boolean was = SubLMain.Always_REDEFINE;
		SubLFiles.actuallyReInit = false;
		try {
		    SubLMain.Always_REDEFINE = true;
		    SubLTranslatedFile.transferOverwrite = false;
		    SubLFiles.USE_V1 = true;
		    SubLFiles.USE_V2 = false;
		    SubLMain.BOOTY_HACKZ = false;
		    initialize1TranslatedSystem(rcycl.class.getName());
		    
		} finally {
		    SubLMain.Always_REDEFINE = was;
		}
		//initialize1TranslatedSystem("com.cyc.cycjava_3.cycl.cycl");
		SubLFiles.completePass();
		//initialize1TranslatedSystem(com.cyc.cycjava_0.cycl.cycl.class.getName());
		//initialize1TranslatedSystem(com.cyc.cycjava_3.cycl.cycl.class.getName());
	    }

	    if (!SubLMain.OPENCYC)
		initialize1TranslatedSystem("org.logicmoo.system.export");
	    if (SubLMain.TINY_KB)
		initialize1TranslatedSystem("com.cyc.cycjava_1.cycl.dumper_larkc");

	    isSubLInitialized = true;
	    completed = true;
	    SubLFiles.actuallyReInit = true;
	    PrintLow.registerJRTLPrintMethods();

	} finally {
	    isInitializedTranslatedSystems = completed;
	}
    }

    // @todo make this more flexible once we translate multiple systems
    // or want to ship it without the dependency on cyc
    public synchronized static void initialize1TranslatedSystem(String str) {
	if (loadedTranslatedSystems.contains(str)) {
	    return;
	}
	SubLPackage currentPackage = SubLPackage.getCurrentPackage();
	boolean completed = false;
	try {
	    loadedTranslatedSystems.add(str);
	    if (loadedTranslatedSystems.size() > 1) {

	    }
	    SubLPackage.setCurrentPackage(SubLPackage.CYC_PACKAGE);
	    try {
		Eval.initialize_subl_interface_file(SubLObjectFactory.makeString(str));
		completed = true;
	    } catch (Exception e) {
		e.printStackTrace();
		// Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(),
		// e);
		// ignore
	    } finally {
		try {
		    initializeLowMemoryDetection();
		} catch (Exception e2) {
		    e2.printStackTrace();
		}
	    }
	    isSubLInitialized = completed;
	} finally {
	    if (!completed)
		loadedTranslatedSystems.remove(str);
	    SubLPackage.setCurrentPackage(currentPackage);
	}
    }

    public static boolean isFullyInitialized() {
	return isFullyInitialized;
    }

    public static boolean isInAllegroCompatibilityMode() {
	Boolean value = (Boolean) me.argNameToArgValueMap.get("-a");
	return value == Boolean.TRUE;
    }

    public static boolean isInitialized() {
	return isInitialized;
    }

    public static boolean isInternalInitializationDone() {
	return isSubLInitialized;
    }

    public static SubLObject lowMemoryCallbacks() {
	return SubLObjectFactory.makeList(lowMemoryCallbacks);
    }

    public static void lowMemorySituation() {
	System.out.println("Low memory situation detected; calling registered callbacks:");
	for (SubLFunction func : lowMemoryCallbacks)
	    try {
		System.out.println("  Calling: " + func);
		Functions.funcall(func);
	    } catch (Exception e) {
		Errors.warn("Warning: low memory callback function error for " + func);
	    }
	System.out.println("Done calling low memory situation callbacks.");
	Storage.room(CommonSymbols.UNPROVIDED);
    }

    public static void main(String[] argsIn) {
	SubLMain me = SubLMain.me;
	CYC.setSubLisp(true);
	CYC.isSubLispBindingMode = true;
	noInitCyc = false;
	delayEvalParams = false;
	commonSymbolsOK = true;
	if (argsIn == null || argsIn.length == 0) {
	    argsIn = new String[] { "--opencyc" };
	}
	final String[] args = CYC.extractOptions(argsIn);
	Interpreter.nosystem = true;
	preInitLisp();
	Interpreter.nosystem = true;
	SubLPackage.initPackages();
	trueMainReader = new SubLReader();
	setMainReader(trueMainReader);
	if (argsIn.length > 0) {
	    final String argsIn0 = argsIn[0];
	    if (argsIn0.equalsIgnoreCase("--nosubl")) {
		JPL.init();
		Query.oneSolution("ensure_loaded(library(jpl))");
		Query.oneSolution("interactor");
		return;
	    } else if (argsIn0.equalsIgnoreCase("--opencyc")) {
		OPENCYC = true;
	    } else if (argsIn0.equalsIgnoreCase("--moo")) {
		JPL.init();
		Query.oneSolution("ensure_loaded(library(jpl))");
		/// Query.oneSolution("jcall('subl.SubLMain',mainFromProlog,[])");
		Query.oneSolution("interactor");
		embeddedMain(new String[] { "-f", "(progn (load \"init/jrtl-release-init.lisp\")  (load \"init/port-init.lisp\"))" });
		return;
	    }
	}
	handlePatches();
	// Interpreter.createInstance();
	SubLMain.commonSymbolsOK = true;
	final RunnableMain runnable = new RunnableMain();
	embeddedMain(args, runnable);
    }

    public static void preInitLisp() {
	boolean wasSubLisp = CYC.isSubLisp();
	try {
	    CYC.setSubLisp(false);
	    // Interpreter.nosystem = true;
	    Object o = Lisp._AUTOLOAD_VERBOSE_;
	    Lisp.initLisp();
	} finally {
	    CYC.setSubLisp(wasSubLisp);
	}
    }

    public static void registerCleanup(Cleanup cleanup) {
	cleanups.add(cleanup);
    }

    public static void registerLowMemoryCallback(SubLFunction func) {
	if (func == null)
	    Errors.cerror("skip registerLowMemoryCallback", "Unable to register low memory callback for null function.");
	else
	    lowMemoryCallbacks.add(func);
    }

    public static void setIsFullyInitialized() {
	isFullyInitialized = true;
    }

    public static void setIsInitialized() {
	isInitialized = true;
    }

    public static void setMainReader(SubLReader reader) {
	mainReader.set(reader);
	// if (trueMainReader == null) trueMainReader = reader;
    }

    public static boolean shouldQuitAfterExecutingInitializationForm() {
	Boolean value = (Boolean) me.argNameToArgValueMap.get("-q");
	return value == Boolean.TRUE;
    }

    public static boolean shouldRunInBackground() {
	if (shouldRunInBackground)
	    return true;
	Boolean value = (Boolean) me.argNameToArgValueMap.get("-b");
	return value == Boolean.TRUE;
    }

    public static boolean shouldRunReadloopInGUI() {
	Boolean value = (Boolean) me.argNameToArgValueMap.get("-gui");
	return value == Boolean.TRUE;
    }

    private static void writeSystemInfo() {
	System.out.println("Start time: " + new Date());
	System.out.println("Lisp implementation: " + Environment.lisp_implementation_type().getStringValue());
	System.out.println("JVM: " + System.getProperty("java.vm.vendor") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.version") + " (" + System.getProperty("java.vm.version") + ")");
	try {
	    // use reader to avoid direct code dependency on cycl
	    System.out.println("Current KB: " + Eval.eval(reader.read_from_string(SubLObjectFactory.makeString("(clet ((result \"<none>\")) (ignore-errors (pwhen (fboundp 'KB-VERSION-STRING) (csetq result (format nil \"~A\" (kb-version-string))))) result)"), CommonSymbols.UNPROVIDED,
		    CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED)).getStringValue());
	} catch (Exception e) {
	    System.out.println("KB: <none>");
	}
	try {
	    // use reader to avoid direct code dependency on cycl
	    System.out.println("Patch Level: " + Eval.eval(reader.read_from_string(SubLObjectFactory.makeString("(clet ((result \"<unknown>\")) (ignore-errors (pwhen (fboundp 'CYC-REVISION-STRING) (csetq result (format nil \"~A\" (cyc-revision-string))))) result)"), CommonSymbols.UNPROVIDED,
		    CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED)).getStringValue());
	} catch (Exception e) {
	    System.out.println("Patch level: <unknown>");
	}
	try {
	    System.out.println("Working Directory: "
		    + Eval.eval(reader.read_from_string(SubLObjectFactory.makeString("(clet ((result \"" + System.getProperty("user.dir").replace('\\', '/') + "\")) " + "(ignore-errors (pwhen (fboundp 'CANONICAL-CYC-WORKING-DIRECTORY) (csetq result (canonical-cyc-working-directory))) result))"),
			    CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED, CommonSymbols.UNPROVIDED)).getStringValue());
	} catch (Exception e) {
	    System.out.println("Working directory (Java): " + System.getProperty("user.dir"));
	}
	System.out.println("Running on: " + Environment.machine_instance().getStringValue());
	System.out.println("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
    }

    // private SubLReader mainReader;
    private Map<String, Object> argNameToArgValueMap;

    private SubLMain() {
	// this.mainReader = null;
	this.argNameToArgValueMap = new HashMap<>();
    }

    public synchronized void doSystemCleanupAndExit(int code) {
		for (Cleanup cleanup : cleanups)
			try {
				cleanup.cleanup();
			} catch (Exception ex) {
			}
	StreamsLow.$terminal_io$.getValue().toOutputStream().flush();
	//BeanShellCntrl.exit(code);
    }

    public void processCommandLineArgs(String[] argsIn) {
	String[] args = CYC.extractOptions(argsIn);
	for (int i = 0, size = args.length; i < size; ++i) {
	    String arg = args[i];
	    if (noArgCommandLineArgs.contains(arg))
		this.argNameToArgValueMap.put(arg, Boolean.TRUE);
	    else if (argRequiredCommandLineArgs.contains(args[i])) {
		if (i == size)
		    Errors.error("Not enough command line arguments given for: " + arg);
		this.argNameToArgValueMap.put(arg, args[++i]);
	    } else {
		Errors.error("Got invalid command line argument: " + args[i]);
	    }
	}
    }
}
