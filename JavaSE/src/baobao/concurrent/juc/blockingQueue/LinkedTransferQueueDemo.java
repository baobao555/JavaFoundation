package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-15 21:58
 * @description 演示LinkedTransferQueue
 *
 * LinkedTransferQueue是一个无界的TransferQueue，底层是链表实现。它可以利用独有的transfer方法，在插入数据的同时
 * 等待，直到有消费者线程来取出数据后再返回。就像去送一个东西，不是送到了放下东西就自己走掉，而是等待
 * 接收人过来，亲手交到他手上之后再走
 *
 * 构造方法：
 *   LinkedTransferQueue()：创建一个空的LinkedTransferQueue
 *   LinkedTransferQueue​(Collection<? extends E> c)：指定初始元素
 *
 * 插入：
 *   boolean add​(E e)：总是返回true，因为队列无界
 *   boolean offer​(E e)：总是返回true，因为队列无界
 *   boolean offer​(E e, long timeout, TimeUnit unit) throws InterruptedException：总是返回true，因为队列无界，所以无需在等待时间内阻塞
 *   void put​(E e) throws InterruptedException：总是能成功插入，因为队列无界
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
 *   void transfer(E e) throws InterruptedException：
 *   由生产者线程调用，传递一个数据给消费者线程，如果已经有消费者线程调用了take()或带超时的poll()
 *   在等待接收数据，那么transfer方法会成功将数据传递给等待接收数据的消费者线程并立即返回；否则
 *   生产者线程将阻塞，直到有消费者数据来接收数据
 *
 *   boolean tryTransfer(E e)：
 *   由生产者线程调用，传递一个数据给消费者线程，如果已经有消费者线程调用了take()或带超时的poll()
 *   在等待接收数据，那么tryTransfer方法会成功将数据传递给等待接收数据的消费者线程并返回true；否则
 *   返回false，并且元素e不会插入到队列中
 *
 *   boolean tryTransfer(E e, long timeout, TimeUnit unit)：
 *   由生产者线程调用，传递一个数据给消费者线程，如果已经有消费者线程调用了take()或带超时的poll()
 *   在等待接收数据，那么tryTransfer方法会成功将数据传递给等待接收数据的消费者线程并立即；否则如果
 *   在指定的超时时间之后仍然没有消费者线程来接收数据，返回false，并且元素e不会插入到队列中
 *
 */
public class LinkedTransferQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<Integer> linkedTransferQueue = new LinkedTransferQueue<>();

        new Thread(() -> {
            //transfer 6个数据
            for (int i = 0; i < 6; i++) {
                try {
                    if (i == 5){
                        linkedTransferQueue.transfer(i);
                        System.out.println("transfer：" + i);
                    }else {
                        linkedTransferQueue.add(i);
                        System.out.println("add：" + i);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "transfer").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            //take 5个数据，由于最后1个transfer的数据一直没有被take，故transfer线程会阻塞
            for (int i = 0; i < 6; i++) {
                try {
                    System.out.println("take：" + linkedTransferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "take").start();
    }
}
