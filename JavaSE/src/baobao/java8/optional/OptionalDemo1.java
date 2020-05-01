package baobao.java8.optional;

import java.util.Optional;

/**
 * @author baobao
 * @create 2020-03-11 19:39
 * @description Optional的基本使用
 */
public class OptionalDemo1 {
    public static void main(String[] args) {
        String value = "hello";

        //创建一个value为空的Optional
        Optional<String> optional1 = Optional.empty();
        //根据传入的value创建Optional，如果传入的value为空抛出异常
        Optional<String> optional2 = Optional.of(value);
        //根据传入的value创建Optional，如果传入的value为空则调用Optional.empty()创建一个空的Optional
        Optional<String> optional3 = Optional.ofNullable(value);

        //不要这样写，这样写与传统的判断空指针方式没有区别
        if (optional1.isPresent()){ //判断Optional中的value是否为空
            String s = optional1.get();
            System.out.println(s);
        }

        String s = optional1.orElse("other");
        String s1 = optional1.orElseGet(() -> "other");

        //这样写一行代码即可完成空指针判断
        optional1.ifPresent(v -> System.out.println(v));
    }
}
