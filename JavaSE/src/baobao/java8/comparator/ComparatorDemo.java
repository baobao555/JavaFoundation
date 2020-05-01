package baobao.java8.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-17 21:14
 * @description
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "hello world");

        //根据字符串长度排序
        list.sort((s1,s2) -> s1.length() - s2.length());

        //根据字符串长度逆序排序
        list.sort(Comparator.comparingInt(String::length).reversed());
        list.sort(Comparator.comparingInt((String s) -> s.length()).reversed());

        //根据字符串长度排序，如果长度相等根据首字母asc排序
        list.sort(Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        list.sort(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.comparing(String::toLowerCase)));

        //根据字符串长度排序，如果长度相等根据首字母asc逆序排序
        list.sort(Comparator.comparingInt(String::length)
        .thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));
        list.sort(Comparator.comparingInt(String::length)
        .thenComparing(String::toLowerCase,Comparator.reverseOrder()));

        //根据字符串长度逆序排序，如果长度相等根据首字母asc逆序排序
        list.sort(Comparator.comparingInt(String::length).reversed()
        .thenComparing(String::toLowerCase,Comparator.reverseOrder()));
        System.out.println(list);
    }
}
