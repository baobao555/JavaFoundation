package baobao.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author baobao
 * @create 2019-09-15 22:45
 * @description 演示ReadWriteLock
 */

/*
* ReadWriteLock：读写分离锁，可以实现读锁、写锁分离
*
* 需求：1个线程写入数据，100个线程读数据
* */
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteData readWriteData = new ReadWriteData();
        new Thread(() -> readWriteData.write(2019), "writeThread").start();

        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> readWriteData.read(), new StringBuilder().append("readThread").append(i).toString()).start();
        }
    }
}

class ReadWriteData{
    private int number;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();//获取写锁
    private Lock readLock = readWriteLock.readLock();//获取读锁

    public void write(int num){
        //写入数据时使用写锁
        writeLock.lock();
        try {
            number = num;
            System.out.println(Thread.currentThread().getName() + "写入了数据：" + number);
        }finally {
            writeLock.unlock();
        }
    }

    public void read(){
        //读取数据时使用读锁
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取了数据：" + number);
        }finally {
            readLock.unlock();
        }
    }
}
