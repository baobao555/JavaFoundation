package baobao.jvm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-04-20 19:28
 * @description 演示堆内存溢出
 */
public class HeapMemoryOut {
    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();
        while (true){
            TimeUnit.MILLISECONDS.sleep(1);
            list.add(new Object());
        }
    }
}
