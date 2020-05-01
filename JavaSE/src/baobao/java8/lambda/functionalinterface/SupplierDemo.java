package baobao.java8.lambda.functionalinterface;

import java.util.function.Supplier;

/**
 * @author baobao
 * @create 2020-03-10 21:02
 * @description
 */
public class SupplierDemo {
    public static void main(String[] args) {
        //写法1：直接new Person
        Supplier<Person> personFactory1 = () -> new Person();

        //写法2：构造方法引用
        Supplier<Person> personFactory2 = Person::new;

        Person person1 = personFactory1.get();
        Person person2 = personFactory2.get();
    }
}
