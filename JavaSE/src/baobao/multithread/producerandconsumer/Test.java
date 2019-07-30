package baobao.multithread.producerandconsumer;

/**
 * @author baobao
 * @date 2019/7/22 0022 18:59
 * @description 生产者消费者测试
 */
public class Test {
    public static void main(String[] args) {
        Product product = new Product();
        Producer producer = new Producer(product);
        Consumer consumer = new Consumer(product);

        new Thread(producer, "producer").start();
        new Thread(consumer, "consumer").start();
    }
}
