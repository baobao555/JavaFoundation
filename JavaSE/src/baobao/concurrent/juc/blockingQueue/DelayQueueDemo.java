package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-15 20:43
 * @description 演示DelayQueue
 *
 * DelayQueue是一个无界阻塞队列，底层是PriorityQueue。其插入的元素必须都实现Delayed接口，接口中只有1个方法getDelay​(TimeUnit unit)，
 * 用于判断元素是否超时。当插入元素后，若想要取出元素，会调用getDelay方法，如果返回的值小于等于0，则判断已经超时，
 * 取出的操作成功，否则在未超时的情况下无法取出元素。除了取出的方法会对元素做超时判断，其他一些方法对待
 * 所有元素就像普通队列的元素一样，比如size()会返回队列的总大小，包括了已超时未被取出的元素和未超时的元素。
 * 因为插入队列的元素按照到期时间的先后排序的，不是按照插入顺序，所以实现Delayed接口的getDelay()方法的同时要
 * 实现Comparable接口的compareTo方法(Delayed接口实际上是Comparable的子接口)，实现对到期时间的排序
 *
 * 构造方法：
 *   DelayQueue()：创建空的队列
 *   DelayQueue​(Collection<? extends E> c)：指定初始的元素
 *
 * 插入：
 *    boolean add​(E e)：总是返回true，因为队列无界(实际上就是调用了offer方法)
 *    boolean offer​(E e)：总是返回true，因为队列无界
 *    boolean offer​(E e, long timeout, TimeUnit unit)：总是返回true，因为队列无界，所以无需在等待时间内阻塞(实际上就是调用了offer方法)
 *    void put​(E e)：总是能成功插入，因为队列无界(实际上就是调用了offer方法)
 *
 *  取出：
 *    E remove()：队首元素已超时返回取出的元素；队列为空或者队首元素未超时抛出异常
 *    E poll()：队首元素已超时返回取出的元素；队列为空或者队首元素未超时返回null
 *    E poll​(long timeout, TimeUnit unit) throws InterruptedException：队首元素已超时返回取出的元素；超时后队列为空或者队首元素未超时返回null
 *    E take() throws InterruptedException：队首元素已超时立即返回取出的元素；队列为空或者队首元素未超时阻塞
 *
 *  检测值(无需考虑是否超时)：
 *    E element()：队列非空时返回队首元素(但队首元素不出队)；队列为空时抛出异常
 *    E peek()：队列非空时返回队首元素(但队首元素不出队)；队列为空时返回null
 *
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<MyElement> delayQueue = new DelayQueue<>();
        System.out.println("测试put和take");
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                //插入5个数据，延时分别为0,2,4,6,8秒
                delayQueue.put(new MyElement(i, i * 2));
                System.out.println("put：" + i);
            }
        }, "put").start();
        TimeUnit.MILLISECONDS.sleep(30);
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    //取出数据，每隔2秒会获得一个数据
                    System.out.println("take：" + delayQueue.take().getValue() + " Time：" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "take").start();
    }
}

//自定义元素类，实现Delayed接口
class MyElement implements Delayed{
    //数据
    private int value;
    //需要延时的时长(秒)
    private int delaySecond;
    //延时结束那个时刻的时间戳
    private long finishTime;

    //获取数据
    public int getValue() {
        return value;
    }


    public MyElement(int value, int delaySecond) {
        this.value = value;
        this.delaySecond = delaySecond;
        //延时结束那个时刻的时间戳 = 当前时间戳 + 延时时长
        this.finishTime = System.currentTimeMillis() + delaySecond * 1000;
    }

    //判断延时是否结束，是否可以取出数据
    @Override
    public long getDelay(TimeUnit unit) {
        //延时是否结束：延时结束的时间戳 - 当前时间戳
        return this.finishTime - System.currentTimeMillis();
    }

    //实现元素按照延时长短排序插入队列
    @Override
    public int compareTo(Delayed o) {
        //按照延时时长排序，延时时间长的排队列后面，短的排前面
        return this.delaySecond - ((MyElement)o).delaySecond;
    }
}
