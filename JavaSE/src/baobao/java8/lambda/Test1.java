package baobao.java8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-08 20:51
 * @description
 */
public class Test1 {
    public static void main(String[] args) {
        //编译器解析lambda表达式需要靠上下文进行推断
        //下面右边的lambda表达式完全一样，编译器是靠左边的接口类型来推断lambda表达式参数返回值是否合法
        MyInterface1 interface1 = () -> {};
        MyInterface2 interface2 = () -> {};

        List<String> list = Arrays.asList("a","b","c");
        list.forEach(s -> System.out.println(s.toUpperCase()));

        list.stream().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
    }
}

interface MyInterface1{
    void method();
}

interface MyInterface2{
    void method();
}