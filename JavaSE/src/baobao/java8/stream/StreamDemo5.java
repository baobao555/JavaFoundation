package baobao.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author baobao
 * @create 2020-03-13 21:28
 * @description map和flatMap
 */
public class StreamDemo5 {
    public static void main(String[] args) {
        //将Stream中所有字符串转换大写后输出
        Stream<String> stream1 = Stream.of("baobao", "curry", "thompson");
        stream1.map(String::toUpperCase).forEach(System.out::println);

        //将Stream中所有数字平方后输出
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
        stream2.map(i -> i * i).forEach(System.out::println);


        //将Stream<List<Integer>>中的所有List偏平化为Stream<Integer>，再将所有元素平方后输出
        Stream<List<Integer>> stream3 = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        stream3.flatMap(list -> list.stream()).map(i -> i * i).forEach(System.out::println);
    }
}
