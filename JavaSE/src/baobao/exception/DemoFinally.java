package baobao.exception;

public class DemoFinally {

	public static void main(String[] args) {
		// TODO Auto-generated factorymethod stub
		System.out.println(method());//30

	}

	public static int method() {
		int x = 10;
		try {
			x = 20;
			System.out.println(10/0);
			return x;
		}catch(Exception e) {
			x = 30;
			return x;
		}
		finally {
			x = 40;
			System.out.println(x);//40
			//return x;   千万不要在finally里面写return，这样前面的return都会被覆盖掉
		}
	}
}
