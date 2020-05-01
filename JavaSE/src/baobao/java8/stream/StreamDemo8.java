package baobao.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-14 22:16
 * @description flatMap应用
 */
public class StreamDemo8 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("hi", "hello", "你好");
        List<String> list2 = Arrays.asList("baobao", "curry", "thompson");
        //实现将list1中每个元素分别拼接list2中每个元素
        //hi baobao,hi curry,hi thompson,hello baobao,hello curry,hello thompson.....
        //因为执行s1 -> list2.stream().map(s2 -> s1 + " " + s2)映射后,返回的仍是一个
        //stream,所以用flatMap扁平化后返回Stream<String>。如果用map返回的将是Stream<Stream<String>>
        list1.stream().flatMap(s1 -> list2.stream().map(s2 -> s1 + " " + s2)).forEach(System.out::println);
    }
}
