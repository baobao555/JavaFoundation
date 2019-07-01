package baobao.generic;

public class Generic<T> {
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	
	public<Q> void getDiff(Q t) {
		
	}
	
	/*public static void getDiffStatic(T t) {		//静态方法中的泛型不能跟类泛型一样，要独立定义方法泛型
		
	}*/
	
	public static<S> void getDiffStatic(S t) {		//静态方法中的泛型不能跟类泛型一样，要独立定义方法泛型
		
	}
}

// ? extends E 可以存放E及其子类
