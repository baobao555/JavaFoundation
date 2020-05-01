package baobao.jvm.gc;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-04-25 23:37
 * @description
 */
public class GCTest5 {
    public static void main(String[] args) throws InterruptedException {
        int size = 1024 * 1024;
        byte[] myAlloc1 = new byte[4 * size];
        System.out.println("11111");
        byte[] myAlloc2 = new byte[4 * size];
        System.out.println("22222");
        byte[] myAlloc3 = new byte[4 * size];
        System.out.println("33333");
        byte[] myAlloc4 = new byte[2 * size];
        System.out.println("44444");

        TimeUnit.SECONDS.sleep(1);
    }
}
