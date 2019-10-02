package baobao.juc;

import java.net.Authenticator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author baobao
 * @create 2019-09-15 0:06
 * @description 演示ABA问题及解决方案
 */

/*
* 什么是ABA问题(狸猫换太子)：
* 假设有2个线程共同操作1个共享数据number(初始值A)，线程1先执行，它将number的值由A改为B，随后又由B改为A，
* 然后线程1结束，线程2开始执行。线程2将把number改为C，经过CAS对比发现，期望值与主内存中number的值相等，
* 故可以修改成功。但实际上这种情况并非没有问题(除非你不关心number中间经历了多少次变化)
*
*
* 解决方案：
* 引入版本机制，每修改一次数据，版本号增加1，通过对比版本号规避
*  假设初始版本号为1，线程1进行了ABA操作后，number的值和版本号变化如下：
*   A  1 ->  B  2 -> A  3
*
* 此时线程2想把number修改为C，但发现期望的版本号为初始值1不等于实际值3，故修改失败
*
* 利用AtomicStampedReference
*
* */
public class ABAProblemDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "thread1").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);//保证thread1先完成ABA操作
                boolean b = atomicReference.compareAndSet(100, 2019);
                System.out.println(b + "-" + Thread.currentThread().getName() + "-" + atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();


        Thread.sleep(2000);
        System.out.println("======以下是ABA问题的解决=====");


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();//获取当前版本号
            System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamp);
            try {
                Thread.sleep(1000); //保证thread4先获取到初始版本号1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第二次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第三次版本号：" + atomicStampedReference.getStamp());

        }, "thread3").start();

        new Thread(() -> {
            try {
                int stamp = atomicStampedReference.getStamp();//获取当前版本号
                Thread.sleep(2000);//保证thread3先完成ABA操作
                //比较期望版本号与当前实际版本号，因为期望版本号为1，实际版本号由于thread3的ABA操作已经变为3，不相等，
                // 说明共享数据的值被其他线程修改过，故本次修改失败，继续循环判断版本
                boolean b = atomicStampedReference.compareAndSet(100, 2019,
                        stamp,atomicStampedReference.getStamp());
                System.out.println(b + "-" + Thread.currentThread().getName() + "-" + atomicStampedReference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread4").start();
    }
}
