package baobao.java8.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author baobao
 * @create 2020-03-12 21:38
 * @description 实现Stream转换为数组、List、Set
 */
public class StreamDemo4 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("1", "hello", "world");
        //使用Stream中的元素创建出一个数组
        //String[] strings = stream.toArray(length -> new String[length]);
        String[] strings = stream.toArray(String[]::new);

        //使用Stream中的元素创建出1个List
        Stream<String> stream1 = Stream.of("1", "hello", "world");
        Stream<String> stream2 = Stream.of("1", "hello", "world");
        Stream<String> stream3 = Stream.of("1", "hello", "world");

        List<String> list = stream3.collect(Collectors.toList());

        //利用Stream的原始collect方法生成ArrayList
        ArrayList<String> collectArrayList = stream1.collect(() -> new ArrayList<String>(),
                (theList, item) -> theList.add(item),
                (finalList, theList) -> finalList.addAll(theList));

        //利用Stream的原始collect方法生成LinkedList
        LinkedList<Object> collectLinkedList = stream2.collect(LinkedList::new,
                LinkedList::add,
                LinkedList::addAll);
        System.out.println(collectArrayList);
        System.out.println(collectLinkedList);

        //利用Collectors的快捷方法生成LinkedList
        Stream<String> stream4 = Stream.of("1", "hello", "world");
        LinkedList<String> linkedList = stream4.collect(Collectors.toCollection(LinkedList::new));
        System.out.println(linkedList);

        //利用Collectors的快捷方法生成HashSet
        Stream<String> stream5 = Stream.of("1", "hello", "world");
        HashSet<String> hashSet = stream5.collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashSet);

        //利用Collectors的joining方法拼接字符串
        Stream<String> stream6 = Stream.of("1", "hello", "world");
        String s = stream6.collect(Collectors.joining());
        System.out.println(s);

    }
}
