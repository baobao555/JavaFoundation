package baobao.jvm.bytecode;

/**
 * @author baobao
 * @create 2020-05-13 23:06
 * @description
 */
public class TestFinallyReturn {
    public static void main(String[] args) {
        int result = test();
        System.out.println(result);
    }
    public static int test() {
        try {
            return 10;
        } finally {
            return 20;
        }
    }
}
