package baobao.java8.stream;

import baobao.java8.methodreference.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baobao
 * @create 2020-03-16 22:43
 * @description
 */
public class StreamDemo10 {
    public static void main(String[] args) {
        Person person1 = new Person("curry", 18);
        Person person2 = new Person("baobao", 18);
        Person person3 = new Person("baobao", 31);

        Person person4 = new Person("thompson", 28);
        Person person5 = new Person("thompson", 28);

        List<Person> list = Arrays.asList(person1, person2, person3, person4, person5);

        //将stream转换为ArrayList
        List<Person> list1 = list.stream().collect(Collectors.toList());
        list1.forEach(System.out::println);

        //求出stream中元素的个数
        System.out.println(list.stream().count());
        System.out.println(list.stream().collect(Collectors.counting()));

        System.out.println("=============================");
        //求出stream中年龄最小的person
        list.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getAge)))
                .ifPresent(System.out::println);
        //求出stream中年龄最大的person
        list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)))
                .ifPresent(System.out::println);

        System.out.println("=============================");
        //求出stream中所有person年龄的平均值
        System.out.println(list.stream().collect(Collectors.averagingInt(Person::getAge)));
        //求出stream中所有person年龄的和
        System.out.println(list.stream().collect(Collectors.summingInt(Person::getAge)));
        //得到年龄的所有统计信息
        IntSummaryStatistics statistics = list.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println(statistics);

        System.out.println("=============================");
        //将stream中所有person的姓名字符串拼接
        System.out.println(list.stream().map(Person::getName).collect(Collectors.joining()));
        System.out.println(list.stream().map(Person::getName).collect(Collectors.joining(",")));
        System.out.println(list.stream().map(Person::getName).collect(Collectors.joining(",", "<prefix>", "<suffix>")));

        System.out.println("=============================");
        //将stream中所有person先按照name分组，再按照age分组
        Map<String, Map<Integer, List<Person>>> map = list.stream().collect(Collectors.groupingBy(Person::getName, Collectors.groupingBy(Person::getAge)));
        System.out.println(map);

        System.out.println("=============================");
        //将stream中所有person分成根据年龄是否大于20分成2组
        Map<Boolean, List<Person>> map1 = list.stream().collect(Collectors.partitioningBy(person -> person.getAge() > 20));
        System.out.println(map1);
        //将stream中所有person分成根据年龄是否大于20分成2组，再分别对这2组根据年龄是否小于30分组
        Map<Boolean, Map<Boolean, List<Person>>> map2 = list.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 20,
                Collectors.partitioningBy(p -> p.getAge() < 30)));
        System.out.println(map2);

        System.out.println("=============================");
        //将stream中所有person分成根据年龄是否大于20分成2组,再求出每组中的person个数
        Map<Boolean, Long> map3 = list.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 20,
                Collectors.counting()));
        System.out.println(map3);

        System.out.println("=============================");
        //将stream中所有person根据年龄分组，再找出每个组年龄最小的那个person
        Map<String, Person> map4 = list.stream().collect(Collectors.groupingBy(Person::getName,
                Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Person::getAge)), Optional::get)));
        System.out.println(map4);

        Map<String, Optional<Person>> map5 = list.stream().collect(Collectors.groupingBy(Person::getName,
                Collectors.minBy(Comparator.comparingInt(Person::getAge))));
        System.out.println(map5);

        list.stream().map(s -> s).forEach(System.out::println);
    }
}
