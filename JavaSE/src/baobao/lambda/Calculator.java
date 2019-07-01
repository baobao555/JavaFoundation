package baobao.lambda;

//函数式接口：只有1个抽象方法的接口

@FunctionalInterface //检测是不是函数式接口
public interface Calculator {
	public abstract int sum(int a, int b);
}
