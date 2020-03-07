package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-14 22:17
 * @description 演示LinkedBlockingDeque
 *
 * LinkedBlockingDeque类似于LinkedBlockingQueue，是一个可选有界或无界的阻塞队列，底层是链表实现
 * 它与LinkedBlockingQueue的区别是它底层的链表是双向的
 *
 * 构造方法：
 *   LinkedBlockingDeque()：创建一个容量为Integer.MAX_VALUE的队列，相当于无界队列
 *   LinkedBlockingDeque​(int capacity)：创建一个指定容量的有界队列
 *   LinkedBlockingDeque​(Collection<? extends E> c)：创建一个容量为Integer.MAX_VALUE的无界队列，指定初始元素
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
 * 特有方法：
 *   void addFirst(E e)：在队首插入元素，队列满时抛出异常
 *   void addLast(E e)：在队尾插入元素，队列满时抛出异常
 *   boolean offerFirst(E e)：在队首插入元素，队列满时返回false
 *   boolean offerLast(E e)：在队尾插入元素，队列满时返回false
 *   void putFirst(E e)：在队首插入元素，队列满时阻塞
 *   void putLast(E e)：在队尾插入元素，队列满时阻塞
 *
 *   E removeFirst()：移除队首元素并返回，队列为空时抛出异常
 *   E removeLast()：移除队尾元素并返回，队列为空时抛出异常
 *   E pollFirst()：移除队首元素并返回，队列为空时返回null
 *   E pollLast()：移除队尾元素并返回，队列为空时返回null
 *   E takeFirst()：移除队首元素并返回，队列为空时阻塞
 *   E takeLast()：移除队尾元素并返回，队列为空时阻塞
 *
 *   E getFirst()：返回队首元素，队列为空时抛出异常
 *   E getLast()：返回队尾元素，队列为空时抛出异常
 *   E peekFirst()：返回队首元素，队列为空时返回null
 *   E peekLast()：返回队尾元素，队列为空时返回null
 *
 *   void push(E e)：将元素插入到队首(实际上调用了addFirst(e))
 *   E pop()：将元素从队首移除并返回(实际上调用了removeFirst())
 */
public class LinkedBlockingDequeDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>(5);
        System.out.println("测试offerLast和pollLast");
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                System.out.println("offerLast：" + i + blockingDeque.offerLast(String.valueOf(i)));
            }
        }, "offer").start();

        TimeUnit.SECONDS.sleep(1);
        //逆序获取
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                System.out.println("pollLast：" + blockingDeque.pollLast());
            }
        }, "poll").start();
    }
}
