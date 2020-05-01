package baobao.java8.stream;

import java.util.stream.Stream;

/**
 * @author baobao
 * @create 2020-03-14 22:03
 * @description 流的短路
 */
public class StreamDemo7 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello1", "world1", "hello world");
        stream.map(s -> {
            System.out.println(s);
            return s.length();
        }).filter(l -> l == 5).findFirst().ifPresent(System.out::println);
    }
}
