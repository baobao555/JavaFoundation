package baobao.concurrent.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baobao
 * @create 2019-09-15 21:40
 * @description 演示虚假唤醒
 */

/*
* 在多线程通信时，挂起条件的判断一定要用while，不能用if，否则会导致虚假唤醒
*
* 官方文档：
* The recommended approach to waiting is to check the condition being awaited in a while loop
* around the call to wait, as shown in the example below. Among other things, this approach avoids
* problems that can be caused by spurious wakeups.

     synchronized (obj) {
         while (<condition does not hold> and <timeout not exceeded>) {
             long timeoutMillis = ... ; // recompute timeout values
             int nanos = ... ;
             obj.wait(timeoutMillis, nanos);
         }
         ... // Perform action appropriate to condition or timeout
     }
* */
public class FakeWakeDemo {
    public static void main(String[] args) {
        //FakeWake();

        //解决虚假唤醒
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareResource.increase();
            }
        }, "producer1").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareResource.increase();
            }
        }, "producer2").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareResource.decrease();
            }
        }, "consumer1").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareResource.decrease();
            }
        }, "consumer2").start();

    }

    //演示虚假唤醒的错误代码
    private static void FakeWake() {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareData.increase();
            }
        }, "producer1").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareData.increase();
            }
        }, "producer2").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareData.decrease();
            }
        }, "consumer1").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareData.decrease();
            }
        }, "consumer2").start();
    }
}

class ShareResource{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase(){
        lock.lock();
        try {
            //判断挂起条件:当产品数大于0时不生产，挂起，等待消费者线程来消费
            while (number > 0){
                condition.await();
            }
            //具体业务逻辑:产品数为0，开始生产
            number++;
            System.out.println(Thread.currentThread().getName() + "生产了1个产品，当前产品数为：" + number);
            //唤醒其他线程:生产完成后唤醒消费者线程来消费
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease(){
        lock.lock();
        try {
            //判断挂起条件:当产品数为0时不消费，挂起，等待生产者线程来生产
            while (number == 0){
                condition.await();
            }
            //具体业务逻辑:产品数大于0，开始消费
            number--;
            System.out.println(Thread.currentThread().getName() + "消费了1个产品，当前产品数为：" + number);
            //唤醒其他线程:消费完成后唤醒生产者线程来消费
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
