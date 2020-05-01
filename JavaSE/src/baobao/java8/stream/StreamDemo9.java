package baobao.java8.stream;

import baobao.java8.methodreference.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author baobao
 * @create 2020-03-14 22:27
 * @description 分组与分区
 *
 * 分组：类似于sql的概念，将某个属性相同的对象放到同一个组中
 * 分区：分组的一种特殊形式，根据给定的条件分成2个组，满足条件和不满足的各1个组
 */
public class StreamDemo9 {
    public static void main(String[] args) {
        Person person1 = new Person("baobao", 18);
        Person person2 = new Person("baobao", 31);
        Person person3 = new Person("curry", 18);
        Person person4 = new Person("thompson", 28);

        List<Person> personList = Arrays.asList(person1, person2, person3,person4);
        //根据年龄分组，返回的Map：key是年龄的值，value是年龄=key的List<Person>
        Map<Integer, List<Person>> map1 =
                personList.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(map1);

        //根据年龄分组并统计每个分组的元素个数，返回的Map：key是年龄的值，value是年龄=key的Person的个数
        Map<Integer, Long> map2 = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(map2);

        //根据姓名分组并统计每个分组的年龄的平均值，返回的Map：key是姓名，value是姓名=key的所有Person的年龄的平均值
        Map<String, Double> map3 = personList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println(map3);

        //以Person的年龄是否>=18为条件来进行分区，返回的Map：key为true和false，true保存了所有满足条件的Person，false保存不满足条件的Person
        Map<Boolean, List<Person>> map4 = personList.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
        System.out.println(map4);

    }
}
