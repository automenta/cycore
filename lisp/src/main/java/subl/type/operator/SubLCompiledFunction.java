//
////
//
package subl.type.operator;

import abcl.ControlTransfer;
import abcl.Function;
import abcl.Lisp;
import abcl.LispObject;
import subl.*;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.core.SubLObjectFactory;
import subl.type.exception.Unhandleable;
import subl.type.symbol.SubLPackage;
import subl.type.symbol.SubLSymbol;
import subl.util.PatchFileLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class SubLCompiledFunction extends Function implements SubLFunction {
    public class FuncallCounts {
	FuncallCounts() {
	    Arrays.fill(counts = new int[51], 0);
	    SubLCompiledFunction.funcallCountsArray.add(this);
	}

	public int[] counts;

	public String getMethodName() {
	    return method.getName();
	}

	synchronized void incCount(int arity) {
	    if (arity > 50) {
		return;
	    }
	    final int[] counts = this.counts;
	    ++counts[arity];
	}
    }

    @Override
    public boolean isSubLispBased() {
	return true;
    }

    SubLCompiledFunction(Method method, SubLSymbol functionSymbol, int requiredArgCount, int optionalArgCount, boolean allowsRest) {
	super(functionSymbol, requiredArgCount, optionalArgCount, allowsRest);
	//argList = null;
	funcallCounts = new FuncallCounts();
	if (method == null) {
	    Errors.error("Got null native method for: " + functionSymbol);
	}
	setMethod(method);
	functionSymbol.setFunction(this);
    }

    SubLCompiledFunction(String methodClassStr, String methodName, Class[] methodParameters, Class returnType, SubLSymbol functionSymbol, int requiredArgCount, int optionalArgCount, boolean allowsRest) {
	super(functionSymbol, requiredArgCount, optionalArgCount, allowsRest);
	//argList = null;
	funcallCounts = new FuncallCounts();
	this.methodClassStr = methodClassStr;
	this.methodName = methodName;
	this.methodParameters = methodParameters;
	this.returnType = returnType;
	functionSymbol.setFunction(this);
    }

	@Override
    protected void extraInfo(StringBuilder sb) {
	Method prev = method;
	Class methodClassPrev = methodClass;
	try {
	    String dotName = methodClassStr;
	    String mname = methodName;
	    if (methodClass == null || method == null) {
		method = getMethod();
		if (method != null)
		    methodClass = method.getDeclaringClass();
	    }
	    if (dotName == null || !dotName.contains("cycjava_")) {
		if (methodClass != null) {
		    dotName = Lisp.getDotName(methodClass);
		}
	    }
	    if (mname == null) {
		mname = method.getName();
	    }
	    sb.append(dotName);
	    sb.append("::" + mname);
	    final SubLList subLArgs = getArglist();
	    sb.append(subLArgs);
	} finally {
	    method = prev;
	    methodClass = methodClassPrev;
	}
    }

    private Method method;
    private Class methodClass;
    private Class returnType;
    private String methodClassStr;
    private String methodName;
    private Class[] methodParameters;
    //private final SubLList argList;
    public volatile FuncallCounts funcallCounts;
    //public static String FUNCTION_TYPE_NAME;
    public static boolean SHOULD_MAINTAIN_FUNCALL_COUNTS = true;
    public static int MAX_ARITY_TO_MAINTAIN_COUNTS_FOR = 50;
    public static int MIN_FUNCALL_COUNTS_TO_CARE_ABOUT = 100;
    public static ArrayList<FuncallCounts> funcallCountsArray;
    public static boolean USE_DIRECT_CALLING_MECHANISM = false;
    static {
	SubLCompiledFunction.FUNCTION_TYPE_NAME = "FUNCTION";
	funcallCountsArray = new ArrayList<FuncallCounts>();
    }

    private void setMethod(Method method) {
	if (method == null) {
	    Errors.error("Got null native method for: " + this);
	}
	methodClassStr = method.getDeclaringClass().getName();
	methodName = method.getName();
	methodParameters = method.getParameterTypes();
	returnType = method.getReturnType();
    }

    @Override
    public LispObject arrayify(LispObject... args) {
	return (LispObject) applyObject(args);
    }

    public SubLObject applyObject(SubLObject... args) {
	return funcall(args);
    }

    @Override
    public SubLObject apply(Object[] args) {
	int arity = 0;
	for (int i = 0, size = args.length; i < size; ++i) {
	    if (args[i] != UNPROVIDED) {
		if (args[i] instanceof SubLObject[]) {
		    arity += ((SubLObject[]) args[i]).length;
		} else {
		    ++arity;
		}
	    }
	}
	Method m = getMethod();
	funcallCounts.incCount(arity);
	SubLObject result = null;
	try {
	    result = (SubLObject) m.invoke(null, args);
	    return Values.setFirstMultipleValue(result);
	} catch (final InvocationTargetException ite) {
	    final Throwable e = ite.getCause();
	    if (e instanceof Unhandleable) {
		throw (Unhandleable) e;
	    }
	    if (e instanceof ControlTransfer) {
		throw (ControlTransfer) e;
	    }
	    if (e instanceof Error) {
		throw (Error) e;
	    }
	    e.printStackTrace();

	    try {
		result = (SubLObject) m.invoke(null, args);
	    } catch (Throwable e1) {
		// TODO Auto-generated catch block
	    }
	    Errors.error("Error calling " + methodName + ".", e);

	} catch (final Throwable e2) {
	    if (e2 instanceof Unhandleable) {
		throw (Unhandleable) e2;
	    }
	    if (e2 instanceof CatchableThrow) {
		throw (CatchableThrow) e2;
	    }
	    if (e2 instanceof Error) {
		throw (Error) e2;
	    }
	    Errors.error("Error calling " + methodName + ".", e2);
	}
	return NIL;
    }

	@Override
    public SubLList getArglist() {
	final SubLList existing = argList;
	if (existing != null) {
	    return existing;
	}
	final int required = getRequiredArgCount();
	final int optional = getOptionalArgCount();
	final boolean rest = allowsRest();
	final int size = required + (optional > 0 ? optional + 1 : 0) + (rest ? 2 : 0);
	SubLObject current;
	final SubLList arglist = (SubLList) (current = SubLObjectFactory.makeList(size, NIL));
	final SubLPackage sublispPackage = SubLPackage.findPackageNamed("SUBLISP");
	int i;
	for (i = 0; i < required; ++i, current = current.rest()) {
	    final SubLSymbol sym = SubLObjectFactory.makeSymbol("REQ-" + i, sublispPackage);
	    current.setFirst(sym);
	}
	if (optional > 0) {
	    current.setFirst(CommonSymbols.OPTIONAL_SYMBOL);
	    for (current = current.rest(); i < (required + optional); ++i, current = current.rest()) {
		final SubLSymbol sym = SubLObjectFactory.makeSymbol("OPT-" + i, sublispPackage);
		current.setFirst(sym);
	    }
	}
	if (rest) {
	    current.setFirst(CommonSymbols.REST_SYMBOL);
	    current = current.rest();
	    final SubLSymbol sym = SubLObjectFactory.makeSymbol("REST-LIST", sublispPackage);
	    current.setFirst(sym);
	}
	this.argList = arglist;
	return arglist;
    }

    @Override
    public boolean equal(SubLObject obj) {
	if (this == obj)
	    return true;
	if (obj instanceof SubLCompiledFunction) {
	    SubLCompiledFunction other = (SubLCompiledFunction) obj;
	    Method om = other.getMethod();
	    Method tm = this.getMethod();
	    if (!om.equals(tm))
		return false;
	    if (getRequiredArgCount() != other.getRequiredArgCount())
		return false;
	    if (getOptionalArgCount() != other.getOptionalArgCount())
		return false;
	    if (isSubLispBased() != other.isSubLispBased())
		return false;
	    //if (getOptionalArgCount() != other.getOptionalArgCount()) return false;
	    return true;
	}
	return super.equal(obj);
    }

    public Method getMethod() {
	if (method == null) {
	    try {
		final Class theClass = PatchFileLoader.PATCH_FILE_LOADER.loadClass(methodClassStr);
		method = theClass.getMethod(methodName, methodParameters);
	    } catch (final Exception e) {
		try {
		    final Class theClass2 = PatchFileLoader.PATCH_FILE_LOADER.loadClass(Loader.altClassString(methodClassStr));
		    method = theClass2.getMethod(methodName, methodParameters);
		} catch (final Exception e2) {
		    if (SubLMain.TINY_KB)
			return null;
		    e.printStackTrace();
		    Errors.error("Problem initializing function: " + methodClassStr + "." + methodName);
		}
	    }
	}
	return method;
    }

    @Override
    public int hashCode(int currentDepth) {
	if (currentDepth < 8) {
	    final Method method2 = getMethod();
	    if (method2 != null)
		return method2.hashCode();
	}
	return 0;
    }

	@Override
    public boolean isFunctionSpec() {
	return true;
    }

	@Override
    public boolean isInterpreted() {
	return false;
    }

	@Override
    public String toTypeName() {
	return SubLCompiledFunction.FUNCTION_TYPE_NAME;
    }
}
