/* For LarKC */
package subl.type.core;

import abcl.Layout;
import subl.SubLStructDecl;
import subl.type.symbol.SubLSymbol;

public interface SubLStruct extends SubLObject {
	void clear();

	boolean isInitialized();
	
	boolean isDontTrack();
	
	@Override
	SubLObject getField(int p0);

	SubLObject getField(SubLSymbol p0);

	int getFieldCount();

	SubLSymbol getName();

	SubLStructDecl getStructDecl();

	int id();

	@Override
	void setField(int p0, SubLObject p1);

	void setField(SubLSymbol p0, SubLObject p1);

	void setName(SubLSymbol p0);

	void setLayout(Layout structdecl);
}
