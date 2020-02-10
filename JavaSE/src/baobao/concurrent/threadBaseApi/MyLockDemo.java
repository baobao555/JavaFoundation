package baobao.concurrent.threadBaseApi;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-01-05 12:24
 * @description 演示自定义锁
 */
public class MyLockDemo {
    public static void main(String[] args) {
        //创建锁
        SimpleLock simpleLock = new SimpleLock();
        //创建5个线程，每个线程均加锁后休眠5秒，然后释放锁
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //加锁，如果超过3秒没有获取到锁，抛出异常
                    simpleLock.lock(2000);
                    //模拟工作中，休眠
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MyLock.TimeOutException e) {
                    e.printStackTrace();
                } finally {
                    //输出正在等待锁的线程数
                    System.out.println("有" + simpleLock.getBlockedSize() + "个线程在等待锁");
                    //释放锁
                    simpleLock.unlock();
                }
            }).start();
        }
    }
}

class SimpleLock implements MyLock{
    //标识是否有线程正在占用锁
    private boolean lockIsUsing = false;
    //保存因正在等待锁而被阻塞的线程
    private Set<Thread> blockedThreads = new HashSet<>();
    //当前正在占用锁的线程
    private Thread currentThread;

    //获取锁
    @Override
    public synchronized void lock() throws InterruptedException {
        //如果有线程正在占用锁(用while循环判断，避免虚假唤醒)
        while (lockIsUsing){
            System.out.println(Thread.currentThread().getName() + ":锁被别人占用，我进入阻塞状态");
            //将当前线程加入到阻塞线程集合
            blockedThreads.add(Thread.currentThread());
            //当前线程等待
            this.wait();
        }
        //当前没有线程占用锁
        System.out.println(Thread.currentThread().getName() + ":我获得了锁");
        //设置标志位
        lockIsUsing = true;
        //保存当前线程为占有锁的线程
        currentThread = Thread.currentThread();
        //将当前线程从阻塞线程集合中移除
        blockedThreads.remove(currentThread);
    }

    /**
     * 在指定时间内获取锁，超时抛出异常
     * @param millis 超时的时间
     * @throws InterruptedException
     * @throws TimeOutException
     */
    @Override
    public synchronized void lock(long millis) throws InterruptedException, TimeOutException {
        //计算超时的时刻
        long endTime = System.currentTimeMillis() + millis;
        //如果有线程正在占用锁(用while循环判断，避免虚假唤醒)
        while (lockIsUsing){
            //如果已经超时
            if ((endTime - System.currentTimeMillis()) <= 0){
                System.out.println(Thread.currentThread().getName() + ":等待锁超时了");
                //抛出超时异常
                throw new TimeOutException(Thread.currentThread().getName() + "等待超时");
            }

            System.out.println(Thread.currentThread().getName() + ":锁被别人占用，我进入阻塞状态，等待" + millis + "毫秒");
            //将当前线程加入到阻塞线程集合
            blockedThreads.add(Thread.currentThread());
            //当前线程等待一定的时长
            this.wait(millis);
        }
        //当前没有线程占用锁
        System.out.println(Thread.currentThread().getName() + ":我获得了锁");
        //设置标志位
        lockIsUsing = true;
        //保存当前线程为占有锁的线程
        currentThread = Thread.currentThread();
        //将当前线程从阻塞线程集合中移除
        blockedThreads.remove(currentThread);
    }

    //释放锁
    @Override
    public synchronized void unlock() {
        //如果当前需要释放锁的线程等于之前获得了锁的线程
        if (currentThread == Thread.currentThread()){
            //设置标志位
            lockIsUsing = false;
            System.out.println(Thread.currentThread().getName() + ":我释放了锁");
            //唤醒其他因等待锁而阻塞的线程
            this.notifyAll();
        }
    }

    //得到当前正在阻塞的线程集合
    @Override
    public Set<Thread> getBlockedThreads() {
        return Collections.unmodifiableSet(blockedThreads);
    }

    //得到当前阻塞的线程数量
    @Override
    public int getBlockedSize() {
        return blockedThreads.size();
    }
}

//定义锁接口
interface MyLock{
    //自定义超时异常，获取锁超时时抛出
    class TimeOutException extends Exception{
        public TimeOutException(String msg){
            super(msg);
        }
    }

    //获取锁
    void lock() throws InterruptedException;

    //在指定时间内获取锁，超时抛出异常
    void lock(long millis) throws InterruptedException,TimeOutException;

    //释放锁
    void unlock();

    //得到当前阻塞的线程
    Set<Thread> getBlockedThreads();

    //得到当前阻塞的线程数
    int getBlockedSize();
}
