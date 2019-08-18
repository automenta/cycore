/* For LarKC */
package subl.type.stream;

public interface SubLOutputStream extends SubLStream {
	void flush();

	String getStringOutput();

	boolean isStringOutputStream();

	void writePositiveIntegerAsByteSequence(long p0, int p1, boolean p2);
}
