package baobao.concurrent.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baobao
 * @create 2019-09-15 20:38
 * @description 多个线程卖票演示Lock
 */

/*
* 多线程代码模板：
* 1、定义共享资源类，封装对资源类的操作，实现高内聚
* 2、开启多线程操作资源类
* */

/*
* Lock的作用类比于synchronized代码块，使用方式：
* Lock l = ...;
 l.lock();
 try {
   // access the resource protected by this lock
 } finally {
   l.unlock();
 }
* */
public class LockDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "线程A").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "线程B").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "线程C").start();
    }
}

class Ticket{
    private int ticketNum = 50;//总票数
    private Lock lock = new ReentrantLock();//定义Lock

    //定义卖票的方法
    public void sale(){
        lock.lock();//加锁
        try {
            if (ticketNum > 0){
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (51 - ticketNum--) + "张票---" + "余票" + ticketNum);
            }
        }finally {
            lock.unlock();//释放锁
        }
    }
}
