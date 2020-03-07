package baobao.reflection;

import java.lang.reflect.Method;

public class ReflectionTest {

	public static void main(String[] args) throws Exception {
		//字节码对象是反射的核心，拿到字节码对象的3种方式
		//1、调用Class的静态方法forname，传入全类名
		Class clazz1 = Class.forName("baobao.reflection.Person");
		//2、创建对象，调用对象的getClass方法
		Class clazz2 = new Person().getClass();
		//3、对象名.class（这种方式不会使类的静态代码块执行）
		Class clazz3 = Person.class;
		
		//反射出类的方法
		//1、反射出类的所有方法
		Method[] methods = clazz1.getMethods();//得到所有public方法，包括父类的
		for(Method m : methods) {
			System.out.println(m.getName());
		}
		System.out.println("------------------");
		methods = clazz1.getDeclaredMethods();//得到本类中的所有方法（包括private），不包含父类方法
		for(Method m : methods) {
			System.out.println(m.getName());
		}
		
		//2、反射出类的某个方法
		Method method1 = clazz1.getMethod("setName", String.class);
		Method method2 = clazz1.getMethod("setAge", int.class);
		Method method3 = clazz1.getDeclaredMethod("test", null);//私有方法通过getDeclaredMethod获取
		
		//3、执行反射出的方法
		Object obj = clazz1.newInstance();//调用类的无参构造函数
		method1.invoke(obj, "baobao");
		method2.invoke(obj, 18);
		method3.setAccessible(true);//调用私有方法前要打开访问权限
		method3.invoke(obj, null);
		System.out.println(obj);
		
	}

}


class Person{
	static {
		System.out.println("Person静态代码块");
	}
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	private void test() {
		System.out.println("private factorymethod");
	}
	
	void test1() {
		
	}
	
	protected void test2() {
		
	}
}