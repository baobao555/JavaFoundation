package baobao.jvm.memory;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-04-20 20:34
 * @description
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("baobao");
        }
    }
}
