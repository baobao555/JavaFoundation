package baobao.concurrent.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baobao
 * @create 2019-09-15 20:59
 * @description  Lock&Condition版本的生产者消费者演示
 */

/*
* Lock -> synchronized代码块
* Condition -> 线程间通信:
* condition.await() -> object.wait()
* condition.signal() -> object.notify()
* condition.signalAll() -> object.notifyAll()
* */
public class ProducerAndConsumerLockVersion {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareData.increase();
            }
        }, "producer").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                shareData.decrease();
            }
        }, "consumer").start();
    }
}

//共享数据
class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase(){
        lock.lock();
        try {
            //判断挂起条件:当产品数大于0时不生产，挂起，等待消费者线程来消费;用while防止虚假唤醒
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
            //判断挂起条件:当产品数为0时不消费，挂起，等待生产者线程来生产；用while防止虚假唤醒
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
