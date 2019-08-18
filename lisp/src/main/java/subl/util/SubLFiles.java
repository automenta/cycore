/* For LarKC */
package subl.util;

import abcl.Package;
import abcl.*;
import subl.CommonSymbols;
import subl.Errors;
import subl.Loader;
import subl.Packages;
import subl.type.core.SubLObject;
import subl.type.core.SubLObjectFactory;
import subl.type.operator.SubLFunction;
import subl.type.operator.SubLMacro;
import subl.type.symbol.SubLPackage;
import subl.type.symbol.SubLSymbol;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.*;
import java.util.function.Supplier;

public class SubLFiles {
	public static boolean INEXACT = false;
//	public static boolean DO_STATIC_CL_INIT = true;
	public static boolean actuallyReInit = true;
	public static String currentClassName = null;
	public static String originalFileClassName;
	public static boolean USE_V1 = true;
	public static boolean USE_V2 = false;

	/**
	 * @author Administrator
	 */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface LispMethod {
		// Guess based on method name
		String prologName() default "";

		String symbolName() default "";

		String packageName() default "";

		boolean exported() default true;

		// use false is symbol macro
		boolean evalArgs() default true;

		// Arg has method name
		boolean popFront() default false;

		int requiredArgs() default -1;

		int optionalArgs() default -1;

		boolean allowsRest() default false;

		String comment() default "";

		String signature() default "";

		String jsource() default "";

		String lsource() default "";

	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface LispSlot {
	}

	public enum VariableAccessMode {
		CONSTANT(CommonSymbols.CONSTANT_KEYWORD),
		LEXICAL(CommonSymbols.LEXICAL_KEYWORD),
		DYNAMIC(CommonSymbols.DYNAMIC_KEYWORD),
		UNDECLARED(CommonSymbols.UNDECLARED_KEYWORD),
		GLOBAL_TOP_LEVEL(Keyword.TOP_LEVEL),
		PROCESS_LOCAL(Keyword.TOP_LEVEL),
		INHERITED_ENV(Keyword.INHERITED) // Process Local/Iherited from parent
		;

		public final String name;
		public final SubLSymbol sym;

		VariableAccessMode(SubLSymbol sym) {
			this.name = name();
			this.sym = sym;
		}

		@Override
		public final String toString() { return name; }

	}

	//// Public Area

	/**
	 * @deprecated Use {@link #declareFunction(String, String, int, int, boolean)} instead
	 */
	@Deprecated
	public static void declareFunction(String className, String methodName, String functionName, int requiredArgCount, int optionalArgCount, boolean allowsRest) {
		declareFunction(methodName, functionName, requiredArgCount, optionalArgCount, allowsRest);
	}

	public static void declareFunction(String methodName, String functionName, int requiredArgCount, int optionalArgCount, boolean allowsRest) {
		Loader.declareFunction(null, currentClassName, methodName, functionName, requiredArgCount, optionalArgCount, allowsRest);
	}

	/**
	 * implicitly dependent on the current package, @todo extend to support
	 * non-sublisp functions. We need this helper function to be non-static so that
	 * we can access this.getClass() without having to pass it as an argument
	 */
	public static void declareFunction(SubLFile file, String methodName, String functionName, int requiredArgCount, int optionalArgCount, boolean allowsRest) {
		Loader.declareFunction(file.getClass(), null, methodName, functionName, requiredArgCount, optionalArgCount, allowsRest);
	}

	/**
	 * @deprecated Use {@link #declareMacro(String, String)} instead
	 */
	@Deprecated
	public static void declareMacro(String className, String methodName, String functionName) {
		String wasCN = SubLFiles.currentClassName;
		try {
			currentClassName = className;
			declareMacro(methodName, functionName);
		} finally {
			currentClassName = wasCN;
		}
	}

	public static void declareMacro(String methodName, String functionName) {
		try {
			if (methodName.contains("::")) {
				throw new NoSuchMethodError("methodName.contains(\"::\"): " + methodName);
			}
			//Class[] parameterArray = { SubLObject.class, SubLObject.class };
			SubLSymbol functionSymbol = SubLObjectFactory.makeSymbol(functionName);
			SubLFunction macroExpander = Loader.declareFunction(null, currentClassName, methodName, functionName, 2, 0, false);
			//SubLCompiledFunction macroExpander = SubLObjectFactory.makeCompiledFunction(className, methodName, parameterArray, SubLObject.class, functionSymbol, 2, 0, false);
			SubLMacro macro = SubLObjectFactory.makeMacro(macroExpander);
			functionSymbol.setFunction(macro);
		} catch (Exception e) {
			Errors.cerror("Continue.", "Error while declaring macro: " + functionName, e);
		}
	}

	public static void declareMacro(SubLFile file, String methodName, String functionName) {
		try {
			//Class[] parameterArray = { SubLObject.class, SubLObject.class };
			//Method meth = file.getClass().getMethod(methodName, parameterArray);
			SubLSymbol functionSymbol = SubLObjectFactory.makeSymbol(functionName);
			SubLFunction macroExpander = Loader.declareFunction(file.getClass(), null, methodName, functionName, 2, 0, false);
			//SubLObjectFactory.makeCompiledFunction(meth, functionSymbol, 2, 0, false);
			SubLMacro macro = SubLObjectFactory.makeMacro(macroExpander);
			functionSymbol.setFunction(macro);
		} catch (Exception e) {
			Errors.cerror("Continue.", "Error while declaring macro: " + functionName, e);
		}
	}

	private static SubLSymbol declareSymbol(Object variableName, SubLObject initialValue, VariableAccessMode accessMode, boolean isReinitialized) {
		final SubLPackage dynamicValue = (SubLPackage) Packages.$package$.getDynamicValue();
		return declareSymbol(variableName, false, () -> initialValue, accessMode, isReinitialized, dynamicValue);
	}

	private static SubLSymbol declareSymbol(Object variableName, Supplier<SubLObject> initialValue, VariableAccessMode accessMode, boolean isReinitialized) {
		final SubLPackage dynamicValue = (SubLPackage) Packages.$package$.getDynamicValue();
		return declareSymbol(variableName, true, initialValue, accessMode, isReinitialized, dynamicValue);
	}

	private static SubLSymbol declareSymbol(Object variableName, boolean wasSupplierInit, Supplier<SubLObject> initialValue, VariableAccessMode accessMode, boolean isReinitialized, SubLPackage thePackage) {
		try {
			SubLSymbol symbol = asSymbol(variableName, thePackage.toPackage());
			symbol.setAccessMode(accessMode);
			// perform symbol annotation -- for initialization type
			SubLSymbol initializationType = isReinitialized ? CommonSymbols.INITIALIZER_KEYWORD : CommonSymbols.WORLD_KEYWORD;
			symbol.setProperty(CommonSymbols.INITIALIZATION_TYPE_KEYWORD, initializationType);
			// symbol annotation -- for binding type
			symbol.setProperty(CommonSymbols.BINDING_TYPE_KEYWORD, accessMode.sym);
			synchronized (symbolInitializationOrder) {
				symbolInitializationOrder.add(symbol);
			}
			SubLObject v = initialValue.get();
			if (!symbol.boundp()) {
				symbol.setGlobalUnmergedForcedValue(v, true, false, false);
			} else {
				if (!wasSupplierInit && isReinitialized) {
					symbol.setGlobalUnmergedForcedValue(v, true, false, true);
				} else {
					symbol.setGlobalUnmergedForcedValue(v, true, false, false);
				}
			}
			return symbol;
		} catch (Exception e) {
			Errors.cerror("Continue.", "Error while declaring variable: " + variableName, e);
		}
		return null;
	}

	public static LispObject findKeyword(int start, LispObject args[], Symbol kw, Supplier<LispObject> otherwise) {
		for (int i = start; i < args.length; i++) {
			if (args[i].equal(kw)) {
				return args[i + 1];
			}
		}
		return otherwise.get();
	}

	private static SubLSymbol asSymbol(Object variableName, SubLPackage package1) {
		if (variableName instanceof SubLSymbol)
			return (SubLSymbol) variableName;
		if (variableName instanceof SubLObject) {
			variableName = ((SubLObject) variableName).princToString();
		}
		final String string = variableName.toString();
		return SubLObjectFactory.makeSymbol(string, package1);
	}

	public static SubLSymbol defconstant(String variableName, SubLObject initialValue) {
		/* SubL defconstant : constant lexical global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.CONSTANT, true);
	}

	public static SubLSymbol defconstant(Object variableName, SubLObject initialValue) {
		/* SubL defconstant : constant lexical global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.CONSTANT, true);
	}

	public static SubLSymbol defconstant(Object variableName, Supplier<SubLObject> initialValue) {
		/* SubL defconstant : constant lexical global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.CONSTANT, true);
	}

	// @todo get rid of references to file -APB
	public static SubLSymbol defconstant(SubLFile file, Object variableName, SubLObject initialValue) {
		/* SubL defconstant : constant lexical global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.CONSTANT, true);
	}

	// @todo get rid of references to file -APB
	public static SubLSymbol defconstant(SubLFile file, Object variableName, SubLObject initialValue, SubLPackage thePackage) {
		/* SubL defconstant : constant lexical global */
		return declareSymbol(variableName, false, () -> initialValue, VariableAccessMode.CONSTANT, true, thePackage);
	}

	public static SubLSymbol defglobal(Object variableName, SubLObject initialValue) {
		/*
		 * SubL defglobal : mutable lexical global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.LEXICAL, false);
	}

	public static SubLSymbol defglobal(String variableName, SubLObject initialValue) {
		/*
		 * SubL defglobal : mutable lexical global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.LEXICAL, false);
	}

	// @todo get rid of references to file -APB
	public static SubLSymbol defglobal(SubLFile file, Object variableName, SubLObject initialValue) {
		/*
		 * SubL defglobal : mutable lexical global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.LEXICAL, false);
	}

	public static SubLSymbol defglobal(SubLFile file, Object variableName, SubLObject initialValue, SubLPackage thePackage) {
		/*
		 * SubL defglobal : mutable lexical global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, false, () -> initialValue, VariableAccessMode.LEXICAL, false, thePackage);
	}

	public static SubLSymbol deflexical(Object variableName, SubLObject initialValue) {
		/* SubL deflexical : mutable lexical global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.LEXICAL, false);
	}

	public static SubLSymbol deflexical(String variableName, Supplier<SubLObject> initialValue) {
		/*
		 * SubL defglobal : mutable lexical global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.LEXICAL, false);
	}

	// @todo get rid of references to file -APB
	public static SubLSymbol deflexical(SubLFile file, Object variableName, SubLObject initialValue) {
		/* SubL deflexical : mutable lexical global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.LEXICAL, true);
	}

	public static SubLSymbol deflexical(SubLFile file, Object variableName, SubLObject initialValue, SubLPackage thePackage) {
		/* SubL deflexical : mutable lexical global */
		return declareSymbol(variableName, false, () -> initialValue, VariableAccessMode.LEXICAL, true, thePackage);
	}

	public static SubLSymbol defparameter(String variableName, Supplier<SubLObject> initialValue) {
		/* SubL defparameter : mutable dynamic global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, true);
	}

	public static SubLSymbol defparameter(String variableName, SubLObject initialValue) {
		/* SubL defparameter : mutable dynamic global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, true);
	}

	public static SubLSymbol defparameter(Object variableName, SubLObject initialValue) {
		/* SubL defparameter : mutable dynamic global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, true);
	}

	// @todo get rid of references to file -APB    Noe sorry we need unloaders - dmiles
	public static SubLSymbol defparameter(SubLFile file, Object variableName, SubLObject initialValue) {
		/* SubL defparameter : mutable dynamic global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, true);
	}

	// @todo get rid of references to file -APB    Noe sorry we need unloaders - dmiles
	public static SubLSymbol defparameter(SubLFile file, Object variableName, Supplier<SubLObject> initialValue) {
		/* SubL defparameter : mutable dynamic global */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, true);
	}

	public static SubLSymbol defparameter(SubLFile file, Object variableName, SubLObject initialValue, SubLPackage thePackage) {
		/* SubL defparameter : mutable dynamic global */
		return declareSymbol(variableName, false, () -> initialValue, VariableAccessMode.DYNAMIC, true, thePackage);
	}

	public static SubLSymbol defvar(String variableName, SubLObject initialValue) {
		/*
		 * SubL defvar : mutable dynamic global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, false);
	}

	public static SubLSymbol defvar(String variableName, Supplier<SubLObject> initialValue) {
		/*
		 * SubL defvar : mutable dynamic global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, false);
	}

	/**
	 * TODO Describe the purpose of this method.
	 *
	 * @param string
	 * @param nil
	 * @return
	 */
	public static SubLSymbol defvarCheckCL(String string, SubLObject v) {
		Symbol s = null;
		SubLPackage pack = SubLPackage.getCurrentPackage();
		s = pack.findAccessibleSymbol(string);
		if (s == null) {
			final Package packageCl = Lisp.PACKAGE_CL;
			s = packageCl.findAccessibleSymbol(string);
			if (s != null) {
				if (packageCl != pack) {
					pack.importSymbol(s);
				}
				if (!s.boundp()) {
					defvar(s, v);
				}
				return s;
			}
		}
		return defvar(string, v);
	}

	public static SubLSymbol defvar(Object variableName, SubLObject initialValue) {
		/*
		 * SubL defvar : mutable dynamic global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, false);
	}

	// @todo get rid of references to file -APB
	public static SubLSymbol defvar(SubLFile file, Object variableName, SubLObject initialValue) {
		/*
		 * SubL defvar : mutable dynamic global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, false);
	}

	public static SubLSymbol defProcessVar(SubLFile file, Object variableName, SubLObject initialValue) {
		/*
		 * SubL defvar : mutable dynamic global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, initialValue, VariableAccessMode.DYNAMIC, false).toLispObject().setProcessScope(true);
	}

	public static SubLSymbol defvar(SubLFile file, Object variableName, SubLObject initialValue, SubLPackage thePackage) {
		/*
		 * SubL defvar : mutable dynamic global, not re-initialized on re-evaluation
		 */
		return declareSymbol(variableName, false, () -> initialValue, VariableAccessMode.DYNAMIC, false, thePackage);
	}

	public static void handleThrowable() {
	}

	public static void initialize(String className) {
		Loader.initialize_subl_file_exact(className, !INEXACT);
		checkSymbolConflicts();
	}

	/**
	 * TODO Describe the purpose of this method.
	 */
	private static void checkSymbolConflicts() {
		if (true)
			return;
		for (Symbol s : (Set<Symbol>) SubLPackage.SUBLISP_PACKAGE.getLocalSymbols()) {
			String name = s.getName();
			Symbol s2 = SubLPackage.CYC_PACKAGE.findAccessibleSymbol(name);
			if (s2 != null && s2 != s) {
				s2 = SubLPackage.CYC_PACKAGE.findAccessibleSymbol(name);
				Debug.assertTrue(s == s2);
			}
		}
		for (Symbol s : (Set<Symbol>) SubLPackage.CYC_PACKAGE.getLocalSymbols()) {
			String name = s.getName();
			Symbol s2 = SubLPackage.SUBLISP_PACKAGE.findAccessibleSymbol(name);
			if (s2 != null && s2 != s) {
				s2 = SubLPackage.SUBLISP_PACKAGE.findAccessibleSymbol(name);
				Debug.assertTrue(s == s2);
			}
		}

	}

	/**
	 * TODO Describe the purpose of this method.
	 *
	 * @param clazz
	 */
	public static void initialize(Class clazz) {
		Loader.initialize_subl_file(clazz);

	}

	public static void initialize(SubLFile clazz) {
		Loader.initialize_subl_file(clazz);
	}

	/**
	 *
	 */
	public static void initializeFns() {
		for (Iterator iterator = functionMap.keySet().iterator(); iterator.hasNext(); ) {
			String string = (String) iterator.next();
			createFunction(string, functionMap.get(string));
		}
	}

	public static void completePass() {
		Loader.completePass();
	}

	/**
	 * @param string
	 * @param integer
	 */
	public static void createFunction(String string, Integer integer) {
		// com.cyc.cycjava.cycl.module0004.$f106$UnaryFunction.class.toString();
		String classname = string;
		switch (integer) {
			case 1:
				classname += "$UnaryFunction";
				break;
			case 0:
				classname += "$ZeroArityFunction";
				break;
			case 2:
				classname += "$BinaryFunction";
				break;
			default:
				// TODO Auto-generated method stub
				if (true)
					throw new AbstractMethodError("SubLFiles.createFunction");
				break;
		}
		try {
			Class c = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (true)
				throw new AbstractMethodError("SubLFiles.createFunction throw ClassNotFoundException");
		}
	}

	public static List<SubLSymbol> symbolInitializationOrder() {
		List<SubLSymbol> symbols = new ArrayList<SubLSymbol>();
		synchronized (symbolInitializationOrder) {
			symbols.addAll(symbolInitializationOrder);
		}
		return symbols;
	}

	private static LinkedHashSet<SubLSymbol> symbolInitializationOrder;
	public static HashMap<String, Integer> functionMap = new HashMap();

	static {
		symbolInitializationOrder = new LinkedHashSet<SubLSymbol>();
	}

}
