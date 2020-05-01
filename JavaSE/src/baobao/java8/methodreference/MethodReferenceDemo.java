package baobao.java8.methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author baobao
 * @create 2020-03-12 19:56
 * @description 演示方法引用1
 *
 * 方法引用共有4种形式
 * 1、类名::静态方法名
 * 2、实例名::实例方法名
 * 3、类名::实例方法名
 * 4、类名::new
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        Person person1 = new Person("baobao", 30);
        Person person2 = new Person("curry", 18);
        Person person3 = new Person("thompson", 28);

        List<Person> personList = Arrays.asList(person1, person2, person3);

        //传统写法
        //年龄升序排序
        personList.sort((p1,p2) -> p1.getAge() - p2.getAge());
        System.out.println(personList);
        //姓名升序排序
        personList.sort((p1,p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        System.out.println(personList);

        //方法引用方式一
        personList.sort(Person::compareByAge);
        System.out.println(personList);
        personList.sort(Person::compareByName);
        System.out.println(personList);

        //方法引用方式二
        PersonComparator personComparator = new PersonComparator();
        personList.sort(personComparator::compareByAge);
        System.out.println(personList);
        personList.sort(personComparator::compareByName);
        System.out.println(personList);

        //方法引用方式三
        personList.sort(Person::compareToByAge);
        System.out.println(personList);
        personList.sort(Person::compareToByName);
        System.out.println(personList);

        List<String> list = Arrays.asList("curry", "thompson", "baobao");
        //list.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        list.sort(String::compareToIgnoreCase);
        System.out.println(list);

        //方法引用方式四
        Function<String,String> function = String::new;
        System.out.println(function.apply("aaa"));
    }
}
