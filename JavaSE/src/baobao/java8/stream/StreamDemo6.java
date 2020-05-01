package baobao.java8.stream;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author baobao
 * @create 2020-03-13 21:49
 * @description generate和iterate
 */
public class StreamDemo6 {
    public static void main(String[] args) {
        //构造一个保存了1个UUID的Stream
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        //从stream中找出第一个数据并打印。注意findFirst()为避免空指针返回的是Optional对象
        stream.findFirst().ifPresent(System.out::println);

        //迭代生成后一个元素比前一个元素大5的无限流，取前6个元素并打印
        Stream.iterate(1, i -> i + 5).limit(6).forEach(System.out::println);
    }
}
