package baobao.java8.lambda.functionalinterface;

import java.util.function.Function;

/**
 * @author baobao
 * @create 2020-03-09 20:18
 * @description 演示Function接口
 *
 * 需求：实现针对一个输入参数x，求x + 3,x * 2,x平方的结果
 */
public class FunctionDemo {
    public static void main(String[] args) {
        //传统方式需要事先编写好3种运算对应的3个方法
        /*System.out.println(compute1(5));
        System.out.println(compute2(5));
        System.out.println(compute3(5));*/

        //使用lambda表达式可以在使用时再传入运算的逻辑，更加灵活。传递的是行为，不是数据
        System.out.println(compute1(5, x -> x * 2,x -> x * x)); //50
        System.out.println(compute2(5, x -> x * 2,x -> x * x)); //100

    }


    private static int compute1(int x,Function<Integer,Integer> function1,Function<Integer,Integer> function2){
        //先执行function2，再执行function1
        return function1.compose(function2).apply(x);
    }

    private static int compute2(int x,Function<Integer,Integer> function1,Function<Integer,Integer> function2){
        //先执行function1，再执行function2
        return function1.andThen(function2).apply(x);
    }



    //传统方式实现
    /*private static int compute1(int x){
        return x + 3;
    }

    private static int compute2(int x){
        return x * 2;
    }

    private static int compute3(int x){
        return x * x;
    }*/
}
