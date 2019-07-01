package baobao.interfacedemo;

public interface Demo {
	public abstract void show1();
	void show2();
	public static final int F1 = 1;
	int F2 = 2;
}

interface Demo3 extends Demo1, Demo2 {
	
}

interface Demo1 {
	
}

interface Demo2 {
	
}