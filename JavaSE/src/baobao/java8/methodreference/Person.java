package baobao.java8.methodreference;

/**
 * @author baobao
 * @create 2020-03-12 19:58
 * @description
 */
public class Person {
    private String name;
    private int age;

    //实例方法，年龄正序比较
    public int compareToByAge(Person p){
        return this.getAge() - p.getAge();
    }

    //实例方法，名字正序比较
    public int compareToByName(Person p){
        return this.getName().compareToIgnoreCase(p.getName());
    }

    //静态方法，年龄正序比较
    public static int compareByAge(Person p1,Person p2){
        return p1.getAge() - p2.getAge();
    }

    //静态方法，名字正序比较
    public static int compareByName(Person p1,Person p2){
        return p1.getName().compareToIgnoreCase(p2.getName());
    }



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
