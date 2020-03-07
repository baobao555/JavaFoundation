package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-14 21:50
 * @description 演示LinkedBlockingQueue
 *
 * LinkedBlockingQueue是一个可选有界或无界的阻塞队列，底层是链表实现，插入快查找慢
 *
 * 构造方法：
 * LinkedBlockingQueue()：创建一个容量为Integer.MAX_VALUE的队列，相当于无界队列
 * LinkedBlockingQueue​(int capacity)：创建一个指定容量的有界队列
 * LinkedBlockingQueue​(Collection<? extends E> c)：创建一个容量为Integer.MAX_VALUE的无界队列，指定初始元素
 *
 * 插入：
 *   boolean add​(E e)：队列未满时插入成功并返回true；队列已满抛出异常
 *   boolean offer​(E e)：队列未满时插入成功并返回true；队列已满返回false
 *   boolean offer​(E e, long timeout, TimeUnit unit) throws InterruptedException：队列未满时插入成功并返回true；超时后还没成功返回false
 *   void put​(E e) throws InterruptedException：队列未满时插入成功并立即返回；队列已满时阻塞
 *
 * 取出：
 *   E remove()：队列非空时返回取出的元素；队列为空抛出异常
 *   E poll()：队列非空时返回取出的元素；队列为空返回null
 *   E poll​(long timeout, TimeUnit unit) throws InterruptedException：队列非空时返回取出的元素；超时后队列仍为空返回null
 *   E take() throws InterruptedException：队列非空时立即返回取出的元素；队列为空时阻塞
 *
 * 检测值：
 *   E element()：队列非空时返回队首元素(但队首元素不出队)；队列为空时抛出异常
 *   E peek()：队列非空时返回队首元素(但队首元素不出队)；队列为空时返回null
 *
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(5);
        System.out.println("测试put和take");
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    blockingQueue.put(String.valueOf(i));
                    System.out.println("put：" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "offer").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    System.out.println("take：" + blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "poll").start();
    }
}
