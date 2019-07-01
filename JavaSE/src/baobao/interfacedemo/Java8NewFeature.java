package baobao.interfacedemo;

public class Java8NewFeature {

	public static void main(String[] args) {
		SubClass subClass = new SubClass();
		//知识点1：接口中定义的静态方法，只能通过接口来调用。
		//subClass.method1(); 报错
		A.method1();
		
		//知识点2：通过实现类的对象，可以调用接口中的默认方法。
		//如果实现类重写了接口中的默认方法，调用时，仍然调用的是重写以后的方法
		subClass.method2();
		subClass.method3();
		
		//知识点3：如果子类(或实现类)继承的父类和实现的接口中声明了同名同参数的默认方法，
		//那么子类在没有重写此方法的情况下，默认调用的是父类中的同名同参数的方法。-->类优先原则
		subClass.method4();
		
		//知识点4：如果实现类实现了多个接口，而这多个接口中定义了同名同参数的默认方法，
		//那么在实现类没有重写此方法的情况下，报错。-->接口冲突。
		//这就需要我们必须在实现类中重写此方法
		subClass.method5();
	}

}

/*
 * JDK8接口的新特性：除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法
 */
interface A{
	public static void method1() {
		System.out.println("接口A中的静态方法:method1");
	}
	
	public default void method2() {
		System.out.println("接口A中的默认方法:method2");
	}
	
	default void method3() {
		System.out.println("接口A中的默认方法:method3");
	}
	
	default void method4() {
		System.out.println("接口A中的默认方法:method4");
	}
	
	default void method5() {
		System.out.println("接口A中的默认方法:method5");
	}
}

interface B{
	default void method5() {
		System.out.println("接口B中的默认方法:method5");
	}
}

class SubClass extends SuperClass implements A, B {

	@Override
	public void method3() {
		System.out.println("subclass:method3");
	}

	@Override
	public void method5() {
		System.out.println("-----------------------------");
		System.out.println("subclass:method5");
		//知识点5：如何在子类(或实现类)的方法中调用父类、接口中被重写的方法
		super.method5();
		A.super.method5();
		B.super.method5();
	}
}

class SuperClass{
	public void method4() {
		System.out.println("SuperClass:method4");
	}
	
	public void method5() {
		System.out.println("SuperClass:method5");
	}
}