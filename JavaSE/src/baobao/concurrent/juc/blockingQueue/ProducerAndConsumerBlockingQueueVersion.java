package baobao.concurrent.juc.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author baobao
 * @create 2019-12-22 17:56
 * @description 阻塞队列版本的生产者消费者
 */

class MyResource{
    private volatile boolean flag = true;//控制生产消费是否开启的标志位，默认开启
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<Integer> blockingQueue = null;

    public MyResource(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws InterruptedException {
        boolean result;
        while (flag){
            result = blockingQueue.offer(atomicInteger.incrementAndGet(), 2L, TimeUnit.SECONDS);
            if (result){
                System.out.println(Thread.currentThread().getName() + "生产成功" + atomicInteger.get());
            }else {
                System.out.println(Thread.currentThread().getName() + "生产失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("大老板叫停" + Thread.currentThread().getName() + "生产结束");
    }

    public void consume() throws InterruptedException {
        Integer result;
        while (flag){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result == 0){
                System.out.println(Thread.currentThread().getName() + "超过2秒没得到数据，消费失败");
                flag = false;
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费成功" + result);
        }
        System.out.println("大老板叫停" + Thread.currentThread().getName() + "消费结束");
    }

    public void stop(){
        this.flag = false;
    }
}
public class ProducerAndConsumerBlockingQueueVersion {
    public static void main(String[] args) throws InterruptedException {
        MyResource resource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            try {
                System.out.println("生产线程启动");
                resource.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "producer").start();

        new Thread(() -> {
            try {
                System.out.println("消费线程启动");
                resource.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        //主线程休眠5秒后叫停生产消费线程
        TimeUnit.SECONDS.sleep(5);
        resource.stop();
    }
}
