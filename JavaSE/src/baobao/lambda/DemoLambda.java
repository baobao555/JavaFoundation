package baobao.lambda;

public class DemoLambda {
	public static void main(String[] args) {
		method((x,y) -> x+y);
		
		method((int x,int y) -> {return x+y;});
		
		Calculator c = (int x, int y) -> {return x+y;};
		method(c);
		
		method(Math :: max);
	}
	
	public static void method(Calculator calc) {
		System.out.println(calc.sum(100, 200));
	}
}
