package bsh.tests.mypackage;

/*
	See if bsh can access the inner class
*/
public class ReflectTest {
	public Runnable getRunnable() {
		return new Runnable() {
			@Override
			public void run() { System.out.println("run!"); }
		};
	}
}

