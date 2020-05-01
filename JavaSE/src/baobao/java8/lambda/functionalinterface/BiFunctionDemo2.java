package baobao.java8.lambda.functionalinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author baobao
 * @create 2020-03-09 21:39
 * @description
 */
public class BiFunctionDemo2 {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("curry",18),
                new Person("baobao",28),new Person("thompson",30));
        //过滤出姓名为curry的Person
        List<Person> list1 = getByName(persons, "curry");
        list1.forEach(person -> System.out.println(person));

        System.out.println("================================");
        //过滤出age大于20的Person
        List<Person> list2 = getByAge(persons, 20);
        list2.forEach(System.out::println);

        System.out.println("================================");
        //过滤出age小于30的Person，使用时传入BiFunction动态确定了过滤规则
        List<Person> list3 = getByAge2(persons, 30, (personList, age) ->
                personList.stream().filter(p -> p.getAge() < age).collect(Collectors.toList()));
        list3.forEach(System.out::println);

    }


    //直接使用stream过滤出指定name的Person并返回List
    private static List<Person> getByName(List<Person> persons,String name){
        return persons.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
    }

    //构造BiFunction，调用其方法过滤出年龄大于age的Person并返回List
    private static List<Person> getByAge(List<Person> persons,int age){
        //以getByAge方法为模板构造一个BiFunction，实现其逻辑
        BiFunction<List<Person>,Integer,List<Person>> biFunction = (personList,personAge) ->
            personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList());
        //调用构造好的BiFunction传入参数返回结果
        return biFunction.apply(persons, age);
    }

    //将BiFunction作为方法参数，方法中不直接确定如何对age进行过滤，在使用时传入BiFunction确定过滤规则
    private static List<Person> getByAge2(List<Person> persons,int age,BiFunction<List<Person>,Integer,List<Person>> biFunction){
        return biFunction.apply(persons, age);
    }
}
