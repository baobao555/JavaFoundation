package baobao.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author baobao
 * @create 2020-03-12 21:12
 * @description 创建Stream的方法
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        //方式一：Stream.of
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);

        //方式二：Arrays.stream
        IntStream stream2 = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        //方式三：通过Collection接口的默认方法
        //default Stream<E> stream() : 返回一个顺序流
        //default Stream<E> parallelStream() : 返回一个并行流
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream3 = list.stream();
    }
}
