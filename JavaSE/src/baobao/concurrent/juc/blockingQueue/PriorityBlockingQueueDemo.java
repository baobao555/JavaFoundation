package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-14 21:02
 * @description 演示PriorityBlockingQueue
 *
 * PriorityBlockingQueue是一个无界的优先级阻塞队列，底层是数组实现。插入的元素必须实现Comparable接口
 * 或在构造函数的时候自定义Comparator实现类来指定排序规则(否则会抛出ClassCastException)，插入队列的数据会根据规则排序。因为队列无界，所以
 * 可以一直插入数据(底层数组满了以后会扩容)，直到内存溢出
 *
 * 构造方法：
 * PriorityBlockingQueue()：创建一个数组初始大小为11的队列
 * PriorityBlockingQueue​(int initialCapacity)：指定数组的初始大小
 * PriorityBlockingQueue​(int initialCapacity, Comparator<? super E> comparator)：指定数组的初始大小，自定义的元素比较器
 * PriorityBlockingQueue​(Collection<? extends E> c)：指定队列的初始数据
 *
 * 插入：
 *  boolean add​(E e)：总是返回true，因为队列无界(实际上就是调用了offer方法)
 *  boolean offer​(E e)：总是返回true，因为队列无界
 *  boolean offer​(E e, long timeout, TimeUnit unit)：总是返回true，因为队列无界，所以无需在等待时间内阻塞(实际上就是调用了offer方法)
 *  void put​(E e)：总是能成功插入，因为队列无界(实际上就是调用了offer方法)
 *
 *  取出：
 *  E remove()：队列非空时返回取出的元素；队列为空抛出异常
 *  E poll()：队列非空时返回取出的元素；队列为空返回null
 *  E poll​(long timeout, TimeUnit unit) throws InterruptedException：队列非空时返回取出的元素；超时后队列仍为空返回null
 *  E take() throws InterruptedException：队列非空时立即返回取出的元素；队列为空时阻塞
 *
 *  检测值：
 *  E element()：队列非空时返回队首元素(但队首元素不出队)；队列为空时抛出异常
 *  E peek()：队列非空时返回队首元素(但队首元素不出队)；队列为空时返回null
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();
        new Thread(() -> {
            //逆序添加
            for (int i = 6; i > 0; i--) {
                System.out.println("add：" + blockingQueue.add(i));
            }
        }, "offer").start();

        TimeUnit.SECONDS.sleep(1);
        //顺序获取
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                System.out.println("peek：" + blockingQueue.peek());
                System.out.println("poll：" + blockingQueue.poll());
            }
        }, "poll").start();
    }
}
