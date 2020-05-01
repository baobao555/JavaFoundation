package baobao.jvm.memory;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-04-20 19:44
 * @description
 */
public class StackMemoryOut {
    private int count = 0;

    public void test() throws InterruptedException {
        count++;
        TimeUnit.SECONDS.sleep(1);
        test();
    }

    public static void main(String[] args) {
        StackMemoryOut stackMemoryOut = new StackMemoryOut();
        try {
            stackMemoryOut.test();
        } catch (Throwable t) {
            //打印栈内存溢出时递归调用方法的次数
            System.out.println(stackMemoryOut.count);
            t.printStackTrace();
        }
    }
}
