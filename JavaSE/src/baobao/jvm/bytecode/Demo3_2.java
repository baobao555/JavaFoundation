package baobao.jvm.bytecode;

/**
 * @author baobao
 * @create 2020-05-13 20:57
 * @description
 */
public class Demo3_2 {
    public static void main(String[] args) {
        int a = 10;
        int b = a++ + ++a + a--;
        System.out.println(a); // 11
        System.out.println(b); // 34
    }
}
