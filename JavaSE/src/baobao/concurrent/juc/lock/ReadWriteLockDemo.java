package baobao.concurrent.juc.lock;

import java.util.HashMap;
import java.util.Map;
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
* 需求：5个线程写入数据，5个线程读数据
* */
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteData readWriteData = new ReadWriteData();
        //开启5个线程写入数据
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> readWriteData.write(String.valueOf(temp),temp), "writeThread" + i).start();
        }

        //确保写入线程先执行完毕
        TimeUnit.SECONDS.sleep(1);

        //开启5个线程读取数据
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> readWriteData.read(String.valueOf(temp)), "readThread" + i).start();
        }
    }
}

class ReadWriteData{
    private Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();//获取写锁
    private Lock readLock = readWriteLock.readLock();//获取读锁

    public void write(String key,Object value){
        //写入数据时使用写锁
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入了数据：" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完毕");
        }finally {
            writeLock.unlock();
        }
    }

    public void read(String key){
        //读取数据时使用读锁
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取了数据：" + map.get(key));
            System.out.println(Thread.currentThread().getName() + "读取完毕");
        } finally {
            readLock.unlock();
        }
    }
}
