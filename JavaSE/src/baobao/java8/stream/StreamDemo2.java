package baobao.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author baobao
 * @create 2020-03-12 21:29
 * @description 演示IntStream
 *
 * 如果Stream中的元素是原生类型的int、long或者double，那么我们就应该使用
 * Stream提供的对应原生类型的IntStream、LongStream或DoubleStream，这样
 * 的好处是可以节省内存，并且无需装箱拆箱操作，提高了效率
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        //生成1个IntStream
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        intStream.forEach(System.out::println);

        //产生1~4的整数流
        IntStream.range(1, 5).forEach(System.out::println);
        //产生1~5的整数流
        IntStream.rangeClosed(1, 5).forEach(System.out::println);

        //将Integer类型的Stream转换为IntStream
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();
        IntStream intStream1 = stream.mapToInt(i -> i);

        //将IntStream转换为Integer类型的Stream
        Stream<Integer> integerStream = intStream1.boxed();
    }
}
