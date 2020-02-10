package baobao.concurrent.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baobao
 * @create 2019-12-21 10:51
 * @description 介绍公平锁和非公平锁
 *
 *
 * （1）公平锁和非公平锁是什么？
 *
 * 公平锁：是指多个线程按照申请锁的顺序来获取锁，类似排队打饭，先来后到。
 *
 * 非公平锁：是指多个线程获取锁的顺序并不是按照申请锁的循序，有可能后申请的线程比先申请的线程优先获取锁。
 * 但是，在高并发的情况下，有可能会造成优先级反转或者饥饿现象。
 *
 * （2）公平锁和非公平锁的区别是什么？
 *
 * 　　公平锁：线程按照他们申请锁的顺序获取锁，公平锁就是很公平，在并发环境下，
 *            每个线程在获取锁时会先查看此锁维护的等待队列，如果队列为空，获取当前线程时等待队列的第一个，就占有锁。
 *            否则，就会加入到等待队列中，以后会按照FIFO的规则从队列中获取锁。
 *
 * 　　非公平锁：非公平所比较粗鲁，上来就尝试占有锁，如果尝试失败，就采用类似公平锁的方式获取锁。
 *
 * 补充：ReentrantLock即使通过构造函数指定该锁是否是公平锁，默认是非公平锁。
 * 非公平锁的优点就是吞吐量比公平锁大。同样的synchronized也是一种非公平锁。
 */
public class UnFairLockDemo {
    public static void main(String[] args) throws InterruptedException {
        FairLock fairLock = new FairLock();
        UnfairLock unfairLock = new UnfairLock();
        /*new Thread(fairLock, "fairLock1").start();
        new Thread(fairLock, "fairLock2").start();*/
        //TimeUnit.SECONDS.sleep(2);
        System.out.println("==================================");
        new Thread(unfairLock, "unfairLock1").start();
        new Thread(unfairLock, "unfairLock2").start();

    }
}

class FairLock implements Runnable{
    //构造方法传入true创建公平锁
    private Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得了锁");
            }finally {
                lock.unlock();
            }
        }

    }
}

class UnfairLock implements Runnable{
    //默认构造方法为非公平锁
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得了锁");
            }finally {
                lock.unlock();
            }
        }
    }
}


