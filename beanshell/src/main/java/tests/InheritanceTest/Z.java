package bsh.tests. InheritanceTest;

class Z extends X implements C {
	@Override
	public void c() {
		System.out.println("Z.c()");
	}
	public void z() {
		System.out.println("Z.z()");
	}
	void z_() {
		System.out.println("Z.z_()");
	}
}
