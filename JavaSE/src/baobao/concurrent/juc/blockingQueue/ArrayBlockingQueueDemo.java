package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-14 20:11
 * @description 演示ArrayBlockingQueueDemo
 *
 * ArrayBlockingQueue是一个有界的阻塞队列，底层用数组实现，一旦创建后其容量就无法改变
 *
 * 构造函数：
 * ArrayBlockingQueue​(int capacity)：指定队列的容量
 * ArrayBlockingQueue​(int capacity, boolean fair)：指定队列容量，是否公平(默认非公平)；非公平有助于吞吐率，公平可以防止线程饿死
 * ArrayBlockingQueue​(int capacity, boolean fair, Collection<? extends E> c)：指定队列容量，是否公平，初始的队列数据
 *
 * 插入：
 * boolean add​(E e)：队列未满时插入成功并返回true；队列已满抛出异常
 * boolean offer​(E e)：队列未满时插入成功并返回true；队列已满返回false
 * boolean offer​(E e, long timeout, TimeUnit unit) throws InterruptedException：队列未满时插入成功并返回true；超时后还没成功返回false
 * void put​(E e) throws InterruptedException：队列未满时插入成功并立即返回；队列已满时阻塞
 *
 * 取出：
 * E remove()：队列非空时返回取出的元素；队列为空抛出异常
 * E poll()：队列非空时返回取出的元素；队列为空返回null
 * E poll​(long timeout, TimeUnit unit) throws InterruptedException：队列非空时返回取出的元素；超时后队列仍为空返回null
 * E take() throws InterruptedException：队列非空时立即返回取出的元素；队列为空时阻塞
 *
 * 检测值：
 * E element()：队列非空时返回队首元素(但队首元素不出队)；队列为空时抛出异常
 * E peek()：队列非空时返回队首元素(但队首元素不出队)；队列为空时返回null
 */


public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);
        System.out.println("测试offer和poll");
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                System.out.println("offer：" + blockingQueue.offer(String.valueOf(i)));
            }
        }, "offer").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                System.out.println("poll：" + blockingQueue.poll());
            }
        }, "poll").start();
    }
}
