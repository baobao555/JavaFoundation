package designmode.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i=0;i<100000000;i++){
//			SingletonHurry instance = SingletonHurry.getInstance();
//			SingletonLazy instance = SingletonLazy.getInstance();
//			SingletonLazyDoubleCheckLock instance = SingletonLazyDoubleCheckLock.getInstance();
//			SingletonStaticInnerClass instance = SingletonStaticInnerClass.getInstance();
			SingletonEnum instance = SingletonEnum.INSTANCE;
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

}


