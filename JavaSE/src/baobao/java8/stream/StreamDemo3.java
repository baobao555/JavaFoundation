package baobao.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-12 21:33
 * @description
 */
public class StreamDemo3 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //对list中每个值乘以2后再求和
        int sum = list.stream().map(i -> i * 2).reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
