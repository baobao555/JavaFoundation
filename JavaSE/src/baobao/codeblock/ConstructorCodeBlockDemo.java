package baobao.codeblock;

public class ConstructorCodeBlockDemo {
	public static void main(String[] args) {
		System.out.println("main方法执行");
		Student s1 =new Student("baobao1",18);
		Student s2 =new Student();
		
	}
	
	static {
		System.out.println("main方法的静态代码块");
	}
}

class Student {
	private String name;
	private int age;

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("带参数的构造方法");
	}

	public Student() {
		System.out.println("无参数的构造方法");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	{
		System.out.println("构造代码块");//构造代码块，每次创建对象的时候都会执行，优先于构造方法执行
	}

	static {
		System.out.println("静态代码块");//静态代码块，只在类加载的时候执行一次
	}

}