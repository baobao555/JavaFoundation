package baobao.object;

public class MutiState {
	//1.成员变量没有多态，运行看父类
	//2.成员方法有多态，运行子类的
	public static void main(String[] args) {
		Person p = new Man();
		System.out.println(p.name + p.age); //person18
		p.eat();  //男人吃饭
	}

}

class Person{
	public String name ="person";
	public int age = 18;
	public void eat() {
		System.out.println("人吃饭");
	}
}

class Man extends Person {
	public String name = "man";
	public int age = 20;
	public void eat() {
		System.out.println("男人吃饭");
	}
}