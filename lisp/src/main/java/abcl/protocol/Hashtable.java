package abcl.protocol;

/** Mark object as implementing the Hashtable protocol. */
public interface Hashtable
  extends abcl.protocol.LispObject 
{
    public abcl.LispObject getEntries();
    
    @Deprecated
    public abcl.LispObject ENTRIES();
}