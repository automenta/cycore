//
//For LarKC
//
package subl.type.stream;

import abcl.Stream;
import abcl.Symbol;
import subl.Errors;
import subl.type.symbol.SubLSymbol;

import static abcl.Keyword.BINARY_KEYWORD;

public abstract class AbstractSubLBinaryStream extends Stream
{
  public AbstractSubLBinaryStream( Symbol twoWayStream )
  {
    super( twoWayStream );
  }

  public AbstractSubLBinaryStream( Symbol twoWayStream, SubLSymbol direction )
  {
    super( twoWayStream, direction );
  }

  AbstractSubLBinaryStream( SubLSymbol elementType, SubLSymbol direction, SubLSymbol ifExists, SubLSymbol ifNotExists )
  {
    super( elementType, direction, ifExists, ifNotExists );
    if( elementType != BINARY_KEYWORD )
    {
      Errors.error( "Got bad stream element type: " + elementType );
    }
  }

  AbstractSubLBinaryStream( String fileName, SubLSymbol elementType, SubLSymbol direction, SubLSymbol ifExists, SubLSymbol ifNotExists )
  {
    super( fileName, elementType, direction, ifExists, ifNotExists );
    if( elementType != BINARY_KEYWORD )
    {
      Errors.error( "Got bad stream element type: " + elementType );
    }
  }
  // public AbstractSubLBinaryStream(SubLSymbol binaryKeyword, SubLSymbol
  // inputKeyword, SubLSymbol direction,
  // SubLSymbol errorKeyword, SubLSymbol errorKeyword2) {
  // super( binaryKeyword, inputKeyword, direction, errorKeyword,
  // errorKeyword2);
  //
  // }
}
// ~ Formatted by Jindent --- http://www.jindent.com
