package baobao.concurrent.juc.collections;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author baobao
 * @create 2020-02-18 22:14
 * @description 测试各种线程安全List的性能
 *
 * 测试对比Collections.synchronizedList/ConcurrentLinkedQueue/CopyOnWriteArrayList
 *
 * SynchronizedRandomAccessList:1962
 * ConcurrentLinkedQueue:3619
 * CopyOnWriteArrayList:+无穷
 */
public class ConcurrentListPerformanceTest {
    public static void main(String[] args) throws InterruptedException {
        List<Object> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        ConcurrentLinkedQueue<Object> linkedQueue = new ConcurrentLinkedQueue<>();
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        testPerformance(synchronizedList, 100);
        testPerformance(linkedQueue, 100);
        testPerformance(copyOnWriteArrayList, 100);
    }

    private static void testPerformance(Collection list, int threadsNum) throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadsNum);
        int count = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadsNum; i++) {
            fixedThreadPool.submit(() -> {
                for (int j = 0; j < count; j++) {
                    list.add(j);
                }
            });
        }
        fixedThreadPool.shutdown();
        //awaitTermination必须在shutdown之后调用才有效
        fixedThreadPool.awaitTermination(1, TimeUnit.HOURS);
        long cost = System.currentTimeMillis() - start;
        System.out.println(list.getClass().getSimpleName() + ":" + cost);

    }
}
