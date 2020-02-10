package baobao.concurrent.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author baobao
 * @create 2019-09-14 22:48
 * @description 解决volatile不能保证原子性的问题
 */

/*
    volatile不能保证原子性的解决方案：
    1、给操作共享数据的方法加上synchronized(不推荐，太重量级，杀鸡用牛刀)
    2、使用保证原子性的数据类型如AtomicInteger
*/

/*
    AtomicInteger保证原子性的底层原理：
    1、采用了CAS(Compare-And-Swap)，它是一条CPU并发原语，它的功能是判断内存某个位置的值是否为预期值，如果是则更改为新的值，这个过程是原子的。

    2、Unsafe：
    是CAS的核心类，由于Java方法无法直接访问底层系统，需要通过本地（native）方法来访问，
    Unsafe相当于一个后门，基于该类可以直接操作特定内存的数据。Unsafe类存在于sun.misc包中
    ，其内部方法操作可以像C的指针一样直接操作内存，因为Java中CAS操作的执行依赖于Unsafe类的方法。
    注意Unsafe类中的大部分方法都是native修饰的，也就是说Unsafe类中的方法都直接调用操作系统底层资源执行相应任务。
    CAS并发原语体现在JAVA语言中就是sun.misc.Unsafe类中的各个方法。调用UnSafe类中CAS方法，JVM会帮我们实现
    CAS汇编指令。这是一种完全依赖于硬件的功能，通过它实现原子性。再次强调，由于CAS是一种系统原语，
    原语属于操作系统用语范畴，是由若干指令组成的，用语完成某个功能的一个过程，并且原语的执行必须是连续的，
    在执行过程中不允许被中断，也就是说CAS是一条CPU的原子指令，不会造成所谓的数据不一致性问题

    3、AtomicInteger保证原子性的源码解析：
    atomicInteger.getAndIncrement():
    public final int getAndIncrement() {
        return U.getAndAddInt(this, VALUE, 1);//其中U就是Unsafe类型
    }

    @HotSpotIntrinsicCandidate
    Object o ：当前主内存中的AtomicInteger对象
    long offset：主内存中AtomicInteger对象的地址
    int delta：AtomicInteger对象的值需要增加的量
    public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            //在主内存相应地址找到AtomicInteger对象，并保存到变量v(期望值)中
            v = getIntVolatile(o, offset);
            //进行修改操作前，再通过地址获取主内存中AtomicInteger对象的值，并与之前保存的值v进行比较,
            //如果相等，则说明没有其他线程对AtomicInteger对象的值做过修改，此时修改值，weakCompareAndSetInt
            //方法返回true，取反后为false，while循环退出；如果再次获取的主内存中AtomicInteger对象的值不等于v，
            //则什么都不做，且weakCompareAndSetInt返回false，取反后为true，while循环继续执行，直到v等于再次获取
            //的主内存中AtomicInteger对象的值并且修改数据成功为止
        } while (!weakCompareAndSetInt(o, offset, v, v + delta));
        return v;
    }
*/

/*
    CAS的缺点：
        1、如果CAS失败，会一直进行尝试。如果CAS长时间一直不成功，可能会给CPU带来很大的开销。
        2、会带来ABA问题
*/
public class AtomicDemo {
    public static void main(String[] args) {
        MyData data = new MyData();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.plusOne();
                }
            },String.valueOf(i)).start();
        }

        //等待所有子线程执行结束，因为默认后台有2个线程：主线程和GC线程，存活线程数大于2说明我们开的线程没执行完
        while (Thread.activeCount() > 2){

        }

        //输出结果(保证是10000)
        System.out.println("number =" + data.atomicInteger);


    }
}

class MyData{
    //利用AtomicInteger保证原子性
    volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public void plusOne(){
        //相当于number++操作
        atomicInteger.getAndIncrement();
    }
}