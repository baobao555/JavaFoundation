package baobao.concurrent.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-09-16 23:42
 * @description 演示Semaphore
 */

/*
* Semaphore：
* 实现多个线程争抢多个共享数据的场景
*
* 场景：
* 6辆车争抢3个车位
*
* acquire()：获取1个信号量，如果当前无剩余信号量，则被阻塞，直到其他线程release了信号量；可以被interrupt
* acquireUninterruptibly()：获取1个信号量，如果当前无剩余信号量，则被阻塞，直到其他线程release了信号量；不能被interrupt
* tryAcquire()：尝试获取1个信号量，如果当前无剩余信号量，立即返回，不会阻塞
* */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //模拟3个车位
        Semaphore semaphore = new Semaphore(3);

        //模拟6辆车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //尝试争抢1个车位，再抢到前会一直阻塞
                    semaphore.acquire();
                    //抢到了车位
                    System.out.println(Thread.currentThread().getName() + ":抢到了车位");
                    //抢到车位后停留随机的一段时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    //停留一段时间后离开，车位腾出可以给其他车争抢
                    System.out.println(Thread.currentThread().getName() + ":离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //离开后释放车位资源，归还给Semaphore
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
