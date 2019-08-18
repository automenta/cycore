/* For LarKC */
package com.cyc.tool.subl.jrtl.nativeCode.type.core;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.*;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLFixnum;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;

import java.util.concurrent.locks.ReentrantLock;

public class SubLLock extends FromSubLisp implements SubLObject
{
	public static class MyReentrantLock extends ReentrantLock
	{
		public Thread getLockOwner()
		{
			return getOwner();
		}
	}

	SubLLock(SubLString name)
	{
		lock = new MyReentrantLock();
		this.name = name;
	}

	private SubLString name;
	private MyReentrantLock lock;
	public static String LOCK_TYPE_NAME = "LOCK";
	private static boolean DO_LOCKING_CHECKS = false;

	@Override
	public boolean canFastHash()
	{
		return true;
	}

	public SubLProcess getLocker()
	{
		Object thread = lock.getLockOwner();
		if (thread == null) return null;
		if (!(SubLThread.isSubLThread(thread))) Errors.error("SubLLock " + toString() + " held by non-SubLThread " + thread);
		return ((SubLThread) thread).getSubLProcess();
	}

	public SubLString getName()
	{
		return name;
	}

	@Override
	public SubLSymbol getType()
	{
		return Types.$dtp_lock$;
	}

	@Override
	public SubLFixnum getTypeCode()
	{
		return CommonSymbols.FIFTEEN_INTEGER;
	}

	@Override
	public int hashCode(int currentDepth)
	{
		if (currentDepth < 8) return lock.hashCode();
		return 0;
	}

	@Override
	public boolean isAtom()
	{
		return true;
	}

	public boolean isIdle()
	{
		return !lock.isLocked();
	}

	@Override
	public boolean isLock()
	{
		return true;
	}

	public void releaseLock()
	{
		lock.unlock();
	}

	public void seizeLock()
	{
		while (true)
		{
			try
			{
				lock.lockInterruptibly();
			} catch (InterruptedException ie)
			{
				Threads.possiblyHandleInterrupts(false);
				continue;
			}
			break;
		}
	}

	@Override
	public SubLLock toLock()
	{
		return this;
	}

	@Override
	public String printObjectImpl()
	{
		checkUnreadableOk();
		return "#<LOCK " + getName() + " @ " + this.hashCode(0) + ">";
	}

	@Override
	public String toTypeName()
	{
		return "LOCK";
	}
}
