package baobao.concurrent.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author baobao
 * @create 2019-09-16 23:06
 * @description 演示CyclicBarrier
 */

/*
* CyclicBarrier：
* 创建对象时传入一个初始值和希望最终执行的线程，从0开始每完成一个线程任务加1，直到达到初始值才开始执行最终线程。
* 可以和CountDownLatch进行类比，CountDownLatch是做减法，CyclicBarrier是做加法
* CountDownLatch工作线程之间互不关心，CyclicBarrier工作线程之间需要互相协同
*
* 场景：
* 集齐7颗龙珠后才能召唤神龙
* */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //创建对象，7颗龙珠集齐后再执行召唤神龙的任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println(Thread.currentThread().getName() + "召唤出了神龙，实现了愿望"));
        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ":收集到了第" + temp + "颗龙珠");
                    //每收集到1颗龙珠后挂起，直到7颗龙珠集齐，所有线程恢复执行
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + ":第" + temp + "颗龙珠散落到了世界的某个地方");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
