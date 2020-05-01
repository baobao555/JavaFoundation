package baobao.jvm.bytecode;

/**
 * @author baobao
 * @create 2020-04-14 22:20
 * @description
 */
public class ByteCodeTest7 {
    public int calculate() {
        int a = 1;
        int b = 2;
        int c = 1;
        int d = 9;

        int result = (a + b - c) * d;
        return result;
    }
}
