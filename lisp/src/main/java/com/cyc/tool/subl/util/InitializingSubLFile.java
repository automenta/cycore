/* For LarKC */
package com.cyc.tool.subl.util;

//import org.logicmoo.system.BeanShellCntrl;

public abstract class InitializingSubLFile implements SubLFile {
    public InitializingSubLFile() {
	initSpot = 0;
	lastPercentile = 0;
	sysdclSize = 0;
	//BeanShellCntrl.addSubLFile(this);
    }

    protected int initSpot;
    protected int lastPercentile;
    protected int sysdclSize;

    protected int computePercentile() {
	if (sysdclSize <= 0 || initSpot <= 0)
	    return 0;
	if (initSpot >= sysdclSize)
	    return 100;
	return Math.min(99, initSpot * 100 / sysdclSize);
    }

    protected void finishPercentProgress() {
	System.out.println();
	System.out.println(" Done.");
	System.out.flush();
    }

    protected void initializeClass(String className) {
	SubLFiles.initialize(className);
	incrementCompleteness();
    }

    protected void initializeClass(Class clazz) {
	SubLFiles.initialize(clazz);
	incrementCompleteness();
    }

    /**
     * TODO Describe the purpose of this method.
     */
    protected void incrementCompleteness() {
	++initSpot;
	int percentile = computePercentile();
	if (percentile != lastPercentile) {
	    if (percentile % 10 == 0) {
		System.out.printf(" %d%% ", percentile);
		if (percentile == 50) {
		    System.out.println();
		    System.out.print(" ");
		}
	    } else
		System.out.print('.');
	    System.out.flush();
	    lastPercentile = percentile;
	}
    }

    protected void preparePercentProgress(int total) {
	sysdclSize = total;
	initSpot = 0;
	System.out.println(" Loading implementation classes ... ");
	System.out.print(" ");
	System.out.flush();
    }
}
