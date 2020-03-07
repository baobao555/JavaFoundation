package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-11-10 0:29
 * @description 演示SynchronousQueue
 *
 * SynchronousQueue是一个没有容量的阻塞队列，它的容量为0。任何插入操作在另一个线程调用取出操作
 * 之前都会失败。它对于一些集合功能的方法表现为一个空的集合，例如contains(e)方法总返回false
 *
 * 构造函数：
 *   SynchronousQueue()：创建一个非公平的阻塞队列
 *   SynchronousQueue​(boolean fair)：指定是否公平
 *
 *
 * 插入：
 *   boolean add​(E e)：有其他线程调用取出方法时返回true，否则抛出异常
 *   boolean offer​(E e)：有其他线程调用取出方法时返回true，否则返回false
 *   boolean offer​(E e, long timeout, TimeUnit unit) throws InterruptedException：有其他线程调用取出方法时返回true，否则超时后返回false
 *   void put​(E e) throws InterruptedException：有其他线程调用取出方法时插入成功立即返回，否则阻塞
 *
 * 取出：
 *   E remove()：队列非空时返回取出的元素；队列为空抛出异常
 *   E poll()：队列非空时返回取出的元素；队列为空返回null
 *   E poll​(long timeout, TimeUnit unit) throws InterruptedException：队列非空时返回取出的元素；超时后队列仍为空返回null
 *   E take() throws InterruptedException：队列非空时立即返回取出的元素；队列为空时阻塞
 *
 * 检测值：
 *   E element()：总是抛出异常，因为队列容量为0
 *   E peek()：总是返回null，因为队列容量为0
 *
 *
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        //创建SynchronousQueue
        BlockingQueue<String> queue = new SynchronousQueue<>();

        //创建生产线程，连续存入3个数据
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "putThread").start();


        //创建消费线程，每隔3秒取出一个数据
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take 1");
                queue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take 2");
                queue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take 3");
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "takeThread").start();
    }
}
