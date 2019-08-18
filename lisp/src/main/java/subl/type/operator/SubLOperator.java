//
////
//
package subl.type.operator;

import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.symbol.SubLSymbol;

public interface SubLOperator extends SubLObject {
	SubLList getArglist();

	SubLSymbol getFunctionSymbol();

	boolean isSpecial();


	SubLSpecialOperator toSpecialOperator();
}
