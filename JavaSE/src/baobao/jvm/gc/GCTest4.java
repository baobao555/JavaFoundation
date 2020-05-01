package baobao.jvm.gc;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author baobao
 * @create 2020-04-25 22:34
 * @description
 */
public class GCTest4 {
    public static void main(String[] args) throws InterruptedException {
        //创建2个512k的对象
        byte[] byte1 = new byte[512 * 1024];
        byte[] byte2 = new byte[512 * 1024];
        myGC();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("11111");

        myGC();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("22222");

        myGC();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("33333");

        myGC();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("44444");

        //创建3个1m的对象
        byte[] byte3 = new byte[1024 * 1024];
        byte[] byte4 = new byte[1024 * 1024];
        byte[] byte5 = new byte[1024 * 1024];

        myGC();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("55555");

        myGC();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("66666");

        System.out.println("end");



    }

    public static void myGC(){
        for (int i = 0; i < 40; i++) {
            //创建1m大小的对象
            byte[] bytes = new byte[1024 * 1024];
        }
    }
}
