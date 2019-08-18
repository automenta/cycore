/* For LarKC */
package subl.type.core;

import subl.CommonSymbols;
import subl.Errors;
import subl.Threads;
import subl.type.number.SubLFixnum;
import subl.type.symbol.SubLPackage;
import subl.type.symbol.SubLSymbol;
import subl.type.symbol.SubLSymbolFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SubLSemaphore extends FromSubLisp implements SubLObject {
	SubLSemaphore(SubLString name) {
		this(name, 1);
	}

	SubLSemaphore(SubLString name, int count) {
		sem = new Semaphore(count);
		this.name = name;
	}

	private Semaphore sem;
	private SubLString name;
	public static String SEMAPHORE_TYPE_NAME = "SEMAPHORE";
	public static SubLSymbol SEMAPHORE_TYPE_SYMBOL;
	public static SubLSemaphore sleepSem;
	static {
		SEMAPHORE_TYPE_SYMBOL = SubLSymbolFactory.makeSymbol("SEMAPHORE", SubLPackage.SUBLISP_PACKAGE.toPackage());
		sleepSem = SubLObjectFactory.makeSemaphore(SubLObjectFactory.makeString("sleep semaphore"));
	}

	public void acquire() {
		this.acquire(1);
	}

	public void acquire(int count) {
		while (true) {
			try {
				sem.acquire(count);
			} catch (InterruptedException e) {
				Threads.possiblyHandleInterrupts(false);
				continue;
			}
			break;
		}
	}

	public boolean acquireNonBlocking() {
		return this.acquireNonBlocking(1);
	}

	public boolean acquireNonBlocking(int count) {
		return sem.tryAcquire(count);
	}

	public boolean acquireWithTimeoutNanoSecs(int count, long nanosecs) {
		while (true) {
			long startNanos = System.nanoTime();
			try {
				return sem.tryAcquire(count, nanosecs, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				Threads.possiblyHandleInterrupts(false);
				long nanoSecsWaited = System.nanoTime() - startNanos;
				if (nanoSecsWaited < nanosecs)
					nanosecs -= nanoSecsWaited;
				else
					nanosecs = 0L;
			}
		}
	}

	public boolean acquireWithTimeoutNanoSecs(long nanosecs) {
		return this.acquireWithTimeoutNanoSecs(1, nanosecs);
	}

	@Override
	public boolean canFastHash() {
		return true;
	}

	public int drain() {
		return sem.drainPermits();
	}

	public int getCount() {
		return sem.availablePermits();
	}

	public String getName() {
		return name.getStringValue();
	}

	public SubLString getSubLName() {
		return name;
	}

	@Override
	public SubLSymbol getType() {
		return SubLSemaphore.SEMAPHORE_TYPE_SYMBOL;
	}

	@Override
	public SubLFixnum getTypeCode() {
		Errors.unimplementedMethod("SubLSemaphore.getTypeCode()");
		return CommonSymbols.ZERO_INTEGER;
	}

	@Override
	public int hashCode(int depth) {
		return sem.hashCode();
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	@Override
	public boolean isSemaphore() {
		return true;
	}

	public void release() {
		this.release(1);
	}

	public void release(int count) {
		sem.release(count);
	}

	@Override
	public SubLSemaphore toSemaphore() {
		return this;
	}

	@Override
	public String printObjectImpl() {
		checkUnreadableOk();
		return "#<" + toTypeName() + " \"" + getName() + "\" Permits(" + sem.availablePermits() + ") @ "
				+ this.hashCode(0) + ">";
	}

	@Override
	public String toTypeName() {
		return "SEMAPHORE";
	}
}
