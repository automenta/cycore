/* For LarKC */
package subl.type.core;

import abcl.Cons;
import subl.CommonSymbols;
import subl.Errors;
import subl.SubLThread;
import subl.Threads;
import subl.type.number.SubLFixnum;
import subl.type.symbol.SubLNil;
import subl.type.symbol.SubLPackage;
import subl.type.symbol.SubLSymbol;
import subl.type.symbol.SubLSymbolFactory;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SubLReadWriteLock extends FromSubLisp implements SubLObject
{
	private class MyReentrantReadWriteLock extends ReentrantReadWriteLock
	{
		protected Thread myGetOwner()
		{
			return getOwner();
		}

		protected SubLThread mySubLGetOwner()
		{
			return SubLThread.intoSubLThread(myGetOwner());
		}

		protected Collection<Thread> myGetQueuedThreads()
		{
			return getQueuedThreads();
		}
	}

	SubLReadWriteLock(SubLString name)
	{
		nativeReadWriteLock = new MyReentrantReadWriteLock();
		this.name = name;
	}

	private SubLString name;
	private MyReentrantReadWriteLock nativeReadWriteLock;
	public static String READ_WRITE_LOCK_TYPE_NAME = "READ-WRITE-LOCK";
	public static SubLSymbol READ_WRITE_LOCK_TYPE_SYMBOL;
	static
	{
		READ_WRITE_LOCK_TYPE_SYMBOL = SubLSymbolFactory.makeSymbol("READ-WRITE-LOCK", SubLPackage.SUBLISP_PACKAGE);
	}

	@Override
	public boolean canFastHash()
	{
		return true;
	}

	public SubLString getName()
	{
		return name.toStr();
	}

	public SubLString getSubLName()
	{
		return name;
	}

	@Override
	public SubLSymbol getType()
	{
		return SubLReadWriteLock.READ_WRITE_LOCK_TYPE_SYMBOL;
	}

	@Override
	public SubLFixnum getTypeCode()
	{
		Errors.unimplementedMethod("SubLReadWriteLock.getTypeCode()");
		return CommonSymbols.ZERO_INTEGER;
	}

	public SubLObject getWaiters()
	{
		SubLObject result = SubLNil.NIL;
		try
		{
			Collection<Thread> threads = nativeReadWriteLock.myGetQueuedThreads();
			for (Thread thread : threads)
				result = new Cons(SubLThread.intoSubLThread(thread).getSubLProcess(), result);
		} catch (Exception ex)
		{
		}
		return result;
	}

	public SubLObject getWriter()
	{
		SubLObject result = SubLNil.NIL;
		try
		{
			Thread thread = nativeReadWriteLock.myGetOwner();
			result = SubLThread.intoSubLThread(thread).getSubLProcess();
		} catch (Exception ex)
		{
		}
		return result;
	}

	@Override
	public int hashCode(int currentDepth)
	{
		if (currentDepth < 8) return superHash();
		return 0;
	}

	@Override
	public boolean isAtom()
	{
		return true;
	}

	@Override
	public boolean isReadWriteLock()
	{
		return true;
	}

	public void releaseReadLock()
	{
		nativeReadWriteLock.readLock().unlock();
	}

	public void releaseWriteLock()
	{
		nativeReadWriteLock.writeLock().unlock();
	}

	public void seizeReadLock()
	{
		while (true)
		{
			try
			{
				nativeReadWriteLock.readLock().lockInterruptibly();
			} catch (InterruptedException ie)
			{
				Threads.possiblyHandleInterrupts(false);
				continue;
			}
			break;
		}
	}

	public void seizeWriteLock()
	{
		while (true)
		{
			try
			{
				nativeReadWriteLock.writeLock().lockInterruptibly();
			} catch (InterruptedException ie)
			{
				Threads.possiblyHandleInterrupts(false);
				continue;
			}
			break;
		}
	}

	@Override
	public SubLReadWriteLock toReadWriteLock()
	{
		return this;
	}

	@Override
	public String printObjectImpl()
	{
		checkUnreadableOk();
		return "#<" + toTypeName() + " " + getName() + " @ " + this.hashCode(0) + ">";
	}

	@Override
	public String toTypeName()
	{
		return "READ-WRITE-LOCK";
	}
}
