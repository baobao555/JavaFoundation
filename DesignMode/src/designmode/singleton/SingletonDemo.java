package designmode.singleton;

public class SingletonDemo {

	public static void main(String[] args) {
		//Singleton s = Singleton.getInstance();
	}

}


class Singleton1{  //懒汉式，等要用的时候再创建对象，有线程安全问题
	private Singleton1() {
		
	};
	
	private static Singleton1 instance = null;
	
	public static Singleton1 getInstance() {
		synchronized (Singleton1.class) {
			if(instance == null) {
				instance = new Singleton1();
			}
			return instance;
		}
		
	}
}