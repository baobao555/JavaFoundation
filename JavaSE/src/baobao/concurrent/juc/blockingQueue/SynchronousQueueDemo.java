package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-11-10 0:29
 * @description 演示SynchronousQueue
 *
 *
 * SynchronousQueue：最多只有1个元素的阻塞队列，当队列中已有1个元素时，往队列中添加元素的操作会被阻塞，
 * 直到这个元素被取走
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        //创建SynchronousQueue
        BlockingQueue<String> queue = new SynchronousQueue<>();

        //创建生产线程，连续存入3个数据
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "putThread").start();


        //创建消费线程，每隔3秒取出一个数据
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take 1");
                queue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take 2");
                queue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take 3");
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "takeThread").start();
    }
}
