package baobao.concurrent.juc.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author baobao
 * @create 2020-02-18 22:14
 * @description 测试各种线程安全Map的性能
 *
 * 测试对比HashTable/Collections.synchronizedMap/ConcurrentHashMap/ConcurrentSkipListMap
 *
 * Hashtable:8388
 * SynchronizedMap:7524
 * ConcurrentHashMap:1254
 * ConcurrentSkipListMap:4540
 */
public class ConcurrentMapPerformanceTest {
    public static void main(String[] args) throws InterruptedException {
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        Map<Object, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        ConcurrentSkipListMap<Object, Object> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        testPerformance(hashtable, 1000);
        testPerformance(synchronizedMap, 1000);
        testPerformance(concurrentHashMap, 1000);
        testPerformance(concurrentSkipListMap, 1000);
    }

    private static void testPerformance(Map map,int threadsNum) throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadsNum);
        int count = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadsNum; i++) {
            fixedThreadPool.submit(() -> {
                for (int j = 0; j < count; j++) {
                    map.put(j, j);
                    map.get(j);
                }
            });
        }
        fixedThreadPool.shutdown();
        //awaitTermination必须在shutdown之后调用才有效
        fixedThreadPool.awaitTermination(1, TimeUnit.HOURS);
        long cost = System.currentTimeMillis() - start;
        System.out.println(map.getClass().getSimpleName() + ":" + cost);

    }
}
