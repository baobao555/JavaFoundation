package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-12-22 17:29
 * @description 演示阻塞队列
 *
 * 阻塞队列是一种特殊的队列：
 *当阻塞队列为空时，从队列中获取元素的操作将会被阻塞。
 *当阻塞队列为满时，从队列里添加元素的操作将会被阻塞。
 *
 * 为什么用？ 有什么好处？
 * 在多线程领域：所谓阻塞，在某些情况下会挂起线程（阻塞），一旦条件满足，被挂起的线程又会自动被唤醒。
 *
 * 为什么需要阻塞队列
 * 好处是我们不需要关心什么时候需要阻塞线程，什么时候唤醒线程，因为这一切BlockingQueue都给你一手包办了。
 *
 *
操作类型	  Throws Exception	 Special Value	  Blocked	       Timed out
插入	      add(o)	        offer(o)	   put(o)	       offer(o, timeout, unit)
取出(删除)	 remove(o)	        poll()	       take()	       poll(timeout, unit)

 常见的7种阻塞队列：
ArrayBlockingQueue：由数组结构组成的有界阻塞队列，
LinkedBlockingQueue：由链表结构组成的有界（但是默认值大小为Integer,MAX_VALUE）阻塞队列。
PriorityBlockingQueue：支持优先级排序的无界阻塞队列。
DelayQueue：使用优先级队列支持延迟的无界阻塞队列。
SynchronousQueue：不存储元素的队列，也即单个元素的队列。没有容量，每一个put操作必须等待一个take操作，否则不能添加元素，反之亦然。
LinkedTransferQueue：由链表结构组成的无界阻塞队列。
LinkedBlockingDeque：由链表结构组成的双向阻塞队列。

 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    blockingQueue.put(i);
                    System.out.println(Thread.currentThread().getName() + "put" + i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"put").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + "get" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "take").start();
    }
}
