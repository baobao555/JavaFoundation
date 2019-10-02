package baobao.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author baobao
 * @create 2019-09-17 23:16
 * @description 演示线程池用法
 */

/*
* 创建线程的第四种方式：线程池
* 顶层接口Executor，常用接口ExecutorService
* Executor->ExecutorService 类似于Collection->List
* 常用的创建方式：使用Executors工具类：
* 1、public static ExecutorService	newFixedThreadPool​(int nThreads)：创建线程数量固定的线程池
* 2、public static ExecutorService	newSingleThreadExecutor()：创建只有1个线程的线程池
* 3、public static ExecutorService	newCachedThreadPool()：创建线程数量不定的线程池
* 4、public static ScheduledExecutorService newScheduledThreadPool​(int corePoolSize)：创建线程数量固定的可延时调度的线程池
* */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testFixedThreadPool();
        //testSingleThreadPool();
        //testCachedThreadPool();
        testScheduledThreadPool();
    }

    private static void testScheduledThreadPool() throws InterruptedException, ExecutionException {
        //创建线程数量固定为5的可延时调度的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        try {
            for (int i = 1; i <= 15; i++) {
                //向线程池提交延时执行的任务，延时多长时间再开始执行，并返回值
                ScheduledFuture<Integer> scheduledFuture = scheduledThreadPool.schedule(() -> {
                    System.out.print(Thread.currentThread().getName() + "---");
                    return new Random().nextInt(10);
                }, 1, TimeUnit.SECONDS);
                //获取返回值
                Integer result = scheduledFuture.get();
                System.out.println(result);
            }
        } finally {
            //关闭线程池
            scheduledThreadPool.shutdown();
        }
    }

    private static void testCachedThreadPool() throws InterruptedException, ExecutionException {
        //创建线程数量不定的线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i <= 15; i++) {
                //向线程池提交执行任务，并返回值
                Future<Integer> future = cachedThreadPool.submit(() -> {
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(Thread.currentThread().getName() + "---");
                    return new Random().nextInt(10);
                });
                //获取返回值
                Integer result = future.get();
                System.out.println(result);
            }
        } finally {
            //关闭线程池
            cachedThreadPool.shutdown();
        }
    }

    private static void testSingleThreadPool() throws InterruptedException, ExecutionException {
        //创建线程数量为1的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i <= 15; i++) {
                //向线程池提交执行任务，并返回值
                Future<Integer> future = singleThreadExecutor.submit(() -> {
                    System.out.print(Thread.currentThread().getName() + "---");
                    return new Random().nextInt(10);
                });
                //获取返回值
                Integer result = future.get();
                System.out.println(result);
            }
        }finally {
            //关闭线程池
            singleThreadExecutor.shutdown();
        }
    }

    private static void testFixedThreadPool() throws InterruptedException, ExecutionException {
        //创建线程数量固定为5的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        try {
            for (int i = 1; i <= 15; i++) {
                //向线程池提交执行任务，并返回值
                Future<Integer> future = fixedThreadPool.submit(() -> {
                    System.out.print(Thread.currentThread().getName() + "---");
                    return new Random().nextInt(10);
                });
                //获取返回值
                Integer result = future.get();
                System.out.println(result);
            }
        } finally {
            //关闭线程池
            fixedThreadPool.shutdown();
        }
    }
}
