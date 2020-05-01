package baobao.java8.lambda.functionalinterface;



import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author baobao
 * @create 2020-03-10 20:31
 * @description
 */
public class PredicateDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //过滤奇数
        filterNum(numbers, i -> i % 2 != 0); //1 3 5 7 9
        System.out.println();
        //过滤偶数
        filterNum(numbers, i -> i % 2 == 0);//2 4 6 8 10
        System.out.println();
        //过滤大于5的数
        filterNum(numbers, i -> i > 5);//6 7 8 9 10
        System.out.println();
        //过滤小于5的数
        filterNum(numbers, i -> i < 5);//1 2 3 4
        System.out.println();

        System.out.println("=============================");
        //过滤大于5并且是偶数
        filterNumByAnd(numbers, i -> i % 2 == 0, i -> i > 5);//6 8 10
        System.out.println();
        //过滤大于5或者是偶数
        filterNumByOr(numbers, i -> i % 2 == 0, i -> i > 5);//2 4 6 7 8 9 10
    }

    //根据2个条件过滤List中的数，2个条件均要满足
    private static void filterNumByAnd(List<Integer> numbers,Predicate<Integer> predicate1,Predicate<Integer> predicate2){
        for (Integer number : numbers) {
            if (predicate1.and(predicate2).test(number)){
                System.out.print(number + " ");
            }
        }
    }

    //根据2个条件过滤List中的数，2个条件满足1个即可
    private static void filterNumByOr(List<Integer> numbers,Predicate<Integer> predicate1,Predicate<Integer> predicate2){
        for (Integer number : numbers) {
            if (predicate1.or(predicate2).test(number)){
                System.out.print(number + " ");
            }
        }
    }


    //过滤List中的数，过滤条件调用时传入predicate
    private static void filterNum(List<Integer> numbers,Predicate<Integer> predicate){
        for (Integer number : numbers) {
            if (predicate.test(number)){
                System.out.print(number + " ");
            }
        }
    }
}
