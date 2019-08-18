/* For LarKC */
package subl.util;

public interface SubLPatcher {
	void doPatch();

	String[] getPatchedClasses();

	long getPatchOrdering();
}
