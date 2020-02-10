package baobao.concurrent.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author baobao
 * @create 2020-02-07 22:10
 * @description 演示StampedLock
 */

/*StampedLock介绍：
 jdk8引入，可以代替ReadWriteLock。在读线程大大多于写线程的情况下，性能优势非常明显
 如果我们深入分析ReadWriteLock，会发现它有个潜在的问题：如果有线程正在读，写线程需要等待
 读线程释放锁后才能获取写锁，即读的过程中不允许写，这是一种悲观的读锁。而我们日常情况大多都是
 读线程远远多于写线程，这样的话容易造成写线程饥饿，抢不到锁

StampedLock和ReadWriteLock相比，改进之处在于：读的过程中也允许获取写锁后写入！
这样一来，我们读的数据就可能不一致，所以，需要一点额外的代码来判断读的过程中是否有写入，
这种读锁是一种乐观锁。

乐观锁的意思就是乐观地估计读的过程中大概率不会有写入，因此被称为乐观锁。
反过来，悲观锁则是读的过程中拒绝有写入，也就是写入必须等待。显然乐观锁的并发效率更高，
但一旦有小概率的写入导致读取的数据不一致，需要能检测出来，再读一遍就行。
* */
public class StampedLockDemo {
    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        ShareData shareData = new ShareData();
        //开启20个读线程
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                //循环读取数据
                while (true){
                    // 获得一个乐观读锁，会立即返回当前stamp
                    long stamp = stampedLock.tryOptimisticRead();
                    //读取数据
                    int data = shareData.get();
                    //校验之前获得的stamp，如果不通过，说明期间有写线程抢到锁修改了数据，导致stamp变化
                    if (!stampedLock.validate(stamp)){
                        // 获取一个悲观读锁
                        stamp = stampedLock.readLock();
                        try {
                            //读取数据
                            data = shareData.get();
                        }finally {
                            //释放悲观读锁
                            stampedLock.unlockRead(stamp);
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "读取数据：" + data);
                    //读完1次休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            },"read" + i).start();
        }

        //开启2个写线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                //循环写入数据
                while (true){
                    //获取写锁
                    long stamp = stampedLock.writeLock();
                    try {
                        //写入数据
                        shareData.add();
                        System.out.println(Thread.currentThread().getName() + "写入数据：" + shareData.get());
                    }finally {
                        //释放写锁
                        stampedLock.unlockWrite(stamp);
                    }

                    //每写完1次数据休眠2秒
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            },"write" + i).start();
        }
    }

    //共享数据
    static class ShareData{
        private int data = 0;

        //写入数据
        public void add(){
            data++;
        }

        //读取数据
        public int get(){
            return data;
        }
    }
}


