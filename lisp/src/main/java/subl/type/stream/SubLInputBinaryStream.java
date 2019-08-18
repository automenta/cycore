/* For LarKC */
package subl.type.stream;

import subl.type.core.SubLString;

public interface SubLInputBinaryStream extends SubLInputStream {
	long numBytesAvailable();

	int read();

	int read(byte[] p0);

	int read(byte[] p0, int p1, int p2);

	long readByteSequenceToPositiveInteger(int p0, boolean p1);

	int readByteSequenceToString(SubLString p0);

	long skip(long p0);

	void unread(int p0);
}
