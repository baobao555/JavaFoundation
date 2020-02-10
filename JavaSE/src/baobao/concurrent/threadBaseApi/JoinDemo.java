package baobao.concurrent.threadBaseApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-12-31 17:28
 * @description 演示Thread的join方法
 *
 * Thread的join方法作用：
 * 假设2个线程t1和t2，t1中调用t2.join()，那么t1会等待t2执行结束再执行自己的逻辑
 *
 * join有3个重载方法：
 * void join()
 * Waits for this thread to die.
 *
 * void join(long millis)
 * Waits at most millis milliseconds for this thread to die.
 *
 * void join(long millis, int nanos)
 * Waits at most millis milliseconds plus nanos nanoseconds for this thread to die.
 *
 * 应用场景：5个同学(子线程)和1个班长(主线程)在教室自习，5位同学自习完离开教室的时间不定，班长必须在所有同学都自习完离开教室后再锁门并离开
 * 可以实现类似CountdownLatch的效果
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> students = new ArrayList<>(10);

        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(800);
                    System.out.println(Thread.currentThread().getName() + ":离开了教室");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "同学" + i);

            t.start();
            students.add(t);
        }

        //主线程等待所有线程执行完毕,必须在start之后调用join
        for (Thread s : students){
            s.join();
        }

        System.out.println(Thread.currentThread().getName() + ":所有同学都走了，班长锁门");
    }
}
