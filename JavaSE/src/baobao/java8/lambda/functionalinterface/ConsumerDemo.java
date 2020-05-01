package baobao.java8.lambda.functionalinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/**
 * @author baobao
 * @create 2020-03-10 21:10
 * @description
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Consumer<Integer> consumer1 = i -> System.out.println(i + 5);
        Consumer<Integer> consumer2 = i -> System.out.println(i);
        //将迭代出的每个数先输出加5后的值，再输出本身
        list.stream().forEach(consumer1.andThen(consumer2));
    }
}
