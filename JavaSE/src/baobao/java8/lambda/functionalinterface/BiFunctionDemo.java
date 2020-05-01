package baobao.java8.lambda.functionalinterface;


import java.util.function.BiFunction;

/**
 * @author baobao
 * @create 2020-03-09 21:17
 * @description 利用BiFunction实现加减乘除
 */
public class BiFunctionDemo {
    public static void main(String[] args) {
        System.out.println(compute(5, 3, (x,y) -> x + y));
        System.out.println(compute(5, 3, (x,y) -> x - y));
        System.out.println(compute(5, 3, (x,y) -> x * y));
        System.out.println(compute(5, 3, (x,y) -> x / y));
    }

    private static int compute(int x,int y,BiFunction<Integer,Integer,Integer> biFunction){
        return biFunction.apply(x, y);
    }
}
