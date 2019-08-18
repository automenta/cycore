//
////
//
package subl.type.operator;

import abcl.Function;
import abcl.LispObject;
import subl.*;
import subl.type.core.*;
import subl.type.exception.InvalidSubLExpressionException;
import subl.type.symbol.SubLSymbol;

import java.util.ArrayList;

public class SubLInterpretedFunction extends Function implements SubLFunction
{
  @Override
  public boolean isSubLispBased()
  {
    return true;
  }

  SubLInterpretedFunction( SubLCons form, SubLEnvironment env )
  {
    this( form, env, null );
  }

  SubLInterpretedFunction( SubLCons form, SubLEnvironment env, SubLSymbol functionSymbol )
  {
    super( functionSymbol );
    this.form = form;
    if( !isPossiblyLambdaExpression( form, functionSymbol == null ) )
    {
      throw new InvalidSubLExpressionException( "Got invalid interpreted function definition: " + form );
    }
    if( form.size() >= 2 )
    {
      argDesc = new FunctionArgListDescription( form.get( 1 ) );
    }
    else
    {
      argDesc = new FunctionArgListDescription( NIL );
    }
    requiredArgCount = argDesc.getRequiredArgCount();
    optionalArgCount = argDesc.getOptionalArgCount();
    allowsRest = argDesc.allowsRest();
    if( form.size() >= 3 )
    {
      body = form.cddr();
      if( !body.isCons() )
      {
        throw new InvalidSubLExpressionException( "Got invalid body for interpreted function: " + body );
      }
      body = SubLObjectFactory.makeCons( CommonSymbols.PROGN, body );
    }
    else
    {
      body = NIL;
    }
    this.env = env;
    if( functionSymbol != null )
    {
      functionSymbol.setFunction( this );
    }
  }

  public static boolean isLambdaSymbol(SubLObject obj)
  {
    return obj == CommonSymbols.LAMBDA_SYMBOL;
  }

  public static boolean isPossiblyLambdaExpression(SubLObject exp, boolean checkForLambda)
  {
    if( !exp.isCons() )
    {
      return false;
    }
    final SubLCons cons = exp.toCons();
    final int size = cons.size();
    if( size < 2 )
    {
      return false;
    }
    if( !checkForLambda )
    {
      return true;
    }
    final SubLObject possibleLamdaSymbol = cons.get( 0 );
    return isLambdaSymbol( possibleLamdaSymbol );
  }

  public static void main(String[] args)
  {}
  private final SubLList form;
  private FunctionArgListDescription argDesc;
  private SubLObject body;
  private final SubLEnvironment env;
  public static String LAMBDA_FUNCTION_TYPE_NAME = "INTERPRETED-FUNCTION";

  @Override
  public LispObject arrayify(LispObject... args)
  {
    if( args.length == 0 )
      return (LispObject) apply( args );
    return (LispObject) apply( args );
  }

  @Override
  public SubLObject apply(Object[] args)
  {
    final SubLEnvironment oldEnv = SubLEnvironment.currentEnvironment();
    final SubLEnvironment newEnv = env.extend();
    SubLObject result = NIL;
    ArrayList oldDynamicValues = null;
    final SubLThread thread = SubLProcess.currentSubLThread();
    thread.throwStack.push( CommonSymbols.RETURN_TAG );
    try
    {
      oldDynamicValues = argDesc.expandArgBindings( args, newEnv );
      body.eval( newEnv );
    }
    catch( final CatchableThrow ct )
    {
      result = Dynamic.handleCatchableThrow( ct, CommonSymbols.RETURN_TAG );
    }
    finally
    {
      thread.throwStack.pop();
      SubLSpecialOperatorDeclarations.possiblyRebindDynamics( oldDynamicValues );
      newEnv.unextend();
      SubLEnvironment.setCurrentEnvironment( oldEnv );
    }
    Values.setFirstMultipleValue( result );
    return result;
  }

	@Override
  public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException
  {
    return this;
  }

  @Override
  public SubLList getArglist()
  {
    Errors.unimplementedMethod( "SubLInterpretedFunction.getArglist()" );
    return CommonSymbols.NIL;
  }

  @Override
  public int hashCode(int currentDepth)
  {
    if( currentDepth < 8 )
    {
      return form.hashCode( currentDepth + 1 );
    }
    return 0;
  }

	@Override
  public boolean isFunctionSpec()
  {
    return true;
  }

	// @Override
  // public String toString() {
  // final SubLSymbol functionSymbol = getFunctionSymbol();
  // if (functionSymbol != null) {
  // return "#<Interpreted Function " + functionSymbol + ">";
  // }
  // return super.toString();
  // }
  @Override
  public String toTypeName()
  {
    return "INTERPRETED-FUNCTION";
  }
}
