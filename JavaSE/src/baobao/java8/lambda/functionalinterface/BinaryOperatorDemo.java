package baobao.java8.lambda.functionalinterface;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * @author baobao
 * @create 2020-03-10 21:24
 * @description
 */
public class BinaryOperatorDemo {
    public static void main(String[] args) {
        System.out.println(compute(5, 3, (x,y) -> x + y));
        System.out.println(compute(5, 3, (x,y) -> x - y));
        System.out.println(compute(5, 3, (x,y) -> x * y));
        System.out.println(compute(5, 3, (x,y) -> x / y));

        System.out.println("====================");
        //返回长度大的字符串
        System.out.println(max("helloworld", "baobao", (s1,s2) -> s1.length() - s2.length()));//helloworld
        //返回首字符asc码小的字符串
        System.out.println(min("helloworld", "baobao", (s1,s2) -> s1.compareTo(s2)));//baobao
    }

    private static int compute(int x, int y, BinaryOperator<Integer> binaryOperator){
        return binaryOperator.apply(x, y);
    }

    //根据比较条件comparator返回s1和s2的最大值
    private static String max(String s1, String s2, Comparator<String> comparator){
        return BinaryOperator.maxBy(comparator).apply(s1,s2);
    }

    //根据比较条件comparator返回s1和s2的最小值
    private static String min(String s1, String s2, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(s1, s2);
    }

}
