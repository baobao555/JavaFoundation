package baobao.concurrent.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baobao
 * @create 2020-02-03 20:19
 * @description 演示如何获取Unsafe对象
 *
 * Unsafe是各种原子类型利用CAS算法实现原子性操作的一个底层核心类
 *
 * 它本身有一个静态方法getUnsafe可以获取其对象，但是利用静态方法获取会出异常，
 * 故只能使用反射来获取，其保存Unsafe对象的属性名称是theUnsafe
 *
 * public final class Unsafe {
 *     private static final Unsafe theUnsafe;
 */
public class UnsafeDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        //使用静态方法获取会报异常
        //Exception in thread "main" java.lang.SecurityException: Unsafe
        //System.out.println(Unsafe.getUnsafe());
        //System.out.println(getUnsafe());//sun.misc.Unsafe@677327b6

        //测试各种锁的效率
        //1、NolockCouter  result:94765031  time:3352
        //2、SynCounter    result:100000000 time:4859
        //3、LockCounter   result:100000000 time:3586
        //4、AtomicCounter result:100000000 time:4941
        //5、MyCASCounter  result:100000000 time:12501

        //创建10000个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        //定义自增器
        Counter counter = new MyCASCounter();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            //提交10000个线程，每个线程利用自增器自增10000次
            executorService.submit(new CounterTask(counter,10000));
        }
        executorService.shutdown();
        //等待线程池中所有任务执行完毕
        executorService.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        //打印自增器结果
        System.out.println("result:" + counter.getValue());
        //打印执行时间
        System.out.println("time:" + (end - start));
    }

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        //获得Unsafe的字节码对象
        Class<Unsafe> unsafeClass = Unsafe.class;
        //反射得到theUnsafe属性
        Field field = unsafeClass.getDeclaredField("theUnsafe");
        //暴力反射打开访问权限
        field.setAccessible(true);
        //获取并返回theUnsafe属性
        return (Unsafe) field.get(null);
    }
}

//利用反射得到的Unsafe对象的CAS算法实现原子自增
class MyCASCounter implements Counter{
    private volatile int value = 0;
    private Unsafe unsafe;
    private long offset;//value属性在内存中的偏移量

    public MyCASCounter() throws NoSuchFieldException, IllegalAccessException {
        //获取Unsafe对象
        unsafe = UnsafeDemo.getUnsafe();
        //利用Unsafe获取MyCASCounter对象value属性在内存中的偏移量
        offset = unsafe.objectFieldOffset(MyCASCounter.class.getDeclaredField("value"));
    }

    @Override
    public void increment() {
        //临时变量保存当前value的值
        int current = value;
        //对比current与当前内存中value属性的值：
        //如果一致，直接将value+1
        //如果不一致，说明已经有其他线程修改了value，则将current更新为最新的value值，继续比较，直到自增成功为止
        while (!unsafe.compareAndSwapInt(this, offset, current, current + 1)){
            current = value;
        }
    }

    @Override
    public int getValue() {
        return value;
    }
}

//利用AtomicInteger进行原子的自增
class AtomicCounter implements Counter{
    private AtomicInteger value = new AtomicInteger();

    @Override
    public void increment() {
        value.getAndIncrement();
    }

    @Override
    public int getValue() {
        return value.get();
    }
}

//利用Lock进行加锁的自增
class LockCounter implements Counter{
    private int value = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public void increment() {
        try {
            lock.lock();
            value++;
        }finally {
           lock.unlock();
        }
    }

    @Override
    public int getValue() {
        return value;
    }
}

//利用synchronized进行加锁的自增
class SynCounter implements Counter{
    private int value = 0;
    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public int getValue() {
        return value;
    }
}


//不加任何锁的自增
class NolockCouter implements Counter{
    private int value = 0;
    @Override
    public void increment() {
        value++;
    }

    @Override
    public int getValue() {
        return value;
    }
}

//自增器
interface Counter{
    //自增方法
    void increment();
    //获得值
    int getValue();
}

//自增任务
class CounterTask implements Runnable{
    //自增器
    private Counter counter;
    //自增次数
    private int num;

    public CounterTask(Counter counter, int num) {
        this.counter = counter;
        this.num = num;
    }

    @Override
    public void run() {
        //利用自增器自增num次
        for (int i = 0; i < num; i++) {
            counter.increment();
        }
    }
}
