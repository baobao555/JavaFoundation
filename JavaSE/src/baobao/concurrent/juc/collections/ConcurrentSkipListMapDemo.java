package baobao.concurrent.juc.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-18 21:07
 * @description 演示ConcurrentSkipListMap
 *
 * ConcurrentSkipListMap是TreeMap的线程安全版本，是线程安全的有序的哈希表
 *
 * ConcurrentSkipListMap和TreeMap，它们虽然都是有序的哈希表。但是，第一，它们的线程安全机制不同，TreeMap是非线程安全的，
 * 而ConcurrentSkipListMap是线程安全的。第二，ConcurrentSkipListMap是通过跳表实现的，而TreeMap是通过红黑树实现的。
 *
 * 在4线程1.6万数据的条件下，ConcurrentHashMap 存取速度是ConcurrentSkipListMap 的4倍左右。
 * 但ConcurrentSkipListMap有几个ConcurrentHashMap 不能比拟的优点：
 * 1、ConcurrentSkipListMap的key是有序的。
 * 2、ConcurrentSkipListMap支持更高的并发。ConcurrentSkipListMap 的存取时间是log（N），和线程数几乎无关。
 * 也就是说在数据量一定的情况下，并发的线程越多，ConcurrentSkipListMap越能体现出他的优势。在非多线程的情况下，
 * 应当尽量使用TreeMap。此外对于并发性相对较低的并行程序可以使用Collections.synchronizedSortedMap将TreeMap进行包装，
 * 也可以提供较好的效率。对于高并发程序，应当使用ConcurrentSkipListMap，能够提供更高的并发度。
 *
 * 注意：调用ConcurrentSkipListMap的size时，由于多个线程可以同时对映射表进行操作，
 * 所以映射表需要遍历整个链表才能返回元素个数，这个操作是个O(log(n))的操作。所以判断是否为空时不要
 * 调用size()==0，而是应该调用isEmpty()方法
 *
 * ConcurrentSkipListMap底层数据结构是跳表：
 * 跳表分为许多层(level)，每一层都可以看作是数据的索引，这些索引的意义就是加快跳表查找数据速度。每一层的数据都是有序的，
 * 上一层数据是下一层数据的子集，并且第一层(level 1)包含了全部的数据；层次越高，跳跃性越大，包含的数据越少。
 * 跳表包含一个表头，它查找数据时，是从上往下，从左往右进行查找。
 */
public class ConcurrentSkipListMapDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentSkipListMap<Integer, String> skipListMap = new ConcurrentSkipListMap<>();
        for (int i = 6; i > 0; i--) {
            int key = i;
            new Thread(() -> {
                System.out.println("put" + key);
                skipListMap.put(key,"v" + key);
            }, "put" + key).start();
        }

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            //获取第一个key-value对
            Map.Entry<Integer, String> firstEntry = skipListMap.firstEntry();
            //获取最后一个key-value对
            Map.Entry<Integer, String> lastEntry = skipListMap.lastEntry();
            System.out.println("first:" + firstEntry);
            System.out.println("last:" + lastEntry);
        }, "first-last").start();

        new Thread(() -> {
            //获取key值大于等于0但是与0最接近的key-value对
            Map.Entry<Integer, String> ceilingEntry = skipListMap.ceilingEntry(0);
            //获取key值小于等于9但是与9最接近的key-value对
            Map.Entry<Integer, String> floorEntry = skipListMap.floorEntry(9);
            System.out.println("ceiling:" + ceilingEntry);
            System.out.println("floor:" + floorEntry);
        }, "ceiling-floor").start();
    }
}
