package abcl.protocol;

// TODO: transcribe CL:PATHNAME, hook abcl.Pathname up to use a proxied version of the ANSI contract.
/** Mark object as implementing the Hashtable protocol. */
public interface Pathname
  extends abcl.protocol.LispObject 
{
  public Pathname coerce();
}
