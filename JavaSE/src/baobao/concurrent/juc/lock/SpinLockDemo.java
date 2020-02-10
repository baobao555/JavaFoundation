package baobao.concurrent.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author baobao
 * @create 2019-12-21 17:20
 * @description 演示自旋锁
 *
 *
 * 自旋锁：是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，
 * 这样的好处是减少线程上下文切换的消耗，缺点是：循环会消耗CPU资源。
 */
public class SpinLockDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建自旋锁
        MySpinLock spinLock = new MySpinLock();
        //启动a线程，获得锁3秒后释放
        new Thread(() -> {
            try {
                spinLock.lock();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                spinLock.unlock();
            }
        }, "a").start();

        //保证a线程先运行
        TimeUnit.SECONDS.sleep(1);

        //启动b线程，尝试获取锁
        new Thread(() -> {
            try {
                spinLock.lock();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                spinLock.unlock();
            }
        }, "b").start();

    }
}

class MySpinLock{
    //原子线程引用，用来保存当前获得锁的线程
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //CAS加锁
    public void lock(){
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + "come in");
        //如果原子引用为null，说明当前没有线程获得锁，把当前线程赋值给原子引用
        //如果原子引用非null，说明已经有线程获得锁，进入循环等待，直到相应线程释放锁
        while (!atomicReference.compareAndSet(null, currentThread)){

        }
    }

    //释放锁
    public void unlock(){
        Thread currentThread = Thread.currentThread();
        //如果原子引用的值为当前线程，说明当前线程获得了锁，为了释放锁，重新将原子引用赋值为null
        atomicReference.compareAndSet(currentThread, null);
        System.out.println(currentThread.getName() + "leave out");
    }
}
