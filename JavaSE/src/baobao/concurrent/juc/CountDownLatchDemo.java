package baobao.concurrent.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author baobao
 * @create 2019-09-16 21:21
 * @description 演示CountDownLatch
 */

/*
*CountDownLatch:
* 类似一个倒计时器，创建对象时传入一个初始值，每完成一个线程任务可以将初始值减1，直到减为0后，再执行最终线程。
* 应用场景：当某个特定线程需要在所有其他线程执行完之后才开始执行，可以用CountDownLatch来控制
*
* 场景：
* 5个同学和1个班长在教室自习，5位同学自习完离开教室的时间不定，班长必须在所有同学都自习完离开教室后再锁门并离开
*
* */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建对象，初始值5
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println("同学" + Thread.currentThread().getName() + "上完自习，离开了教室");
                //每有1位同学离开教室，countDownLatch减1
                countDownLatch.countDown();
            },i + "").start();
        }

        //在countDownLatch减为0之前一直挂起，直到减到0后再继续执行
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "所有同学都走了，班长锁门");
    }
}
