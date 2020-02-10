package baobao.concurrent.juc;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author baobao
 * @create 2019-09-17 23:16
 * @description 演示线程池用法
 */

/*
* ---创建线程的第四种方式：线程池
* 顶层接口Executor，常用接口ExecutorService
* Executor->ExecutorService 类似于Collection->List
* 常用的创建方式：使用Executors工具类：
* 1、public static ExecutorService	newFixedThreadPool​(int nThreads)：创建线程数量固定的线程池
* 2、public static ExecutorService	newSingleThreadExecutor()：创建只有1个线程的线程池
* 3、public static ExecutorService	newCachedThreadPool()：创建线程数量不定的线程池
* 4、public static ScheduledExecutorService newScheduledThreadPool​(int corePoolSize)：创建线程数量固定的可延时调度的线程池
*
*
* ---创建线程池的通用方式，调用ThreadPoolExecutor构造方法：
* public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)

  7大参数：
* 1、corePoolSize
  核心池大小，除非设置了 allowCoreThreadTimeOut 否则哪怕线程超过空闲时间，池中也要最少要保留这个数目的线程。
  需要注意的是，corePoolSize所需的线程并不是立即创建的，需要在提交任务之后进行创建，
  所以如果有大量的缓存线程数可以先提交一个空任务让线程池将线程先创建出来，从而提升后续的执行效率。

2、maximumPoolSize
  允许的最大线程数。

3、keepAliveTime
  空闲线程空闲存活时间，核心线程需要 allowCoreThreadTimeOut 为true才会退出。

4、unit
  与keepAliveTime 配合，设置 keepAliveTime 的单位，如：毫秒、秒。

5、workQueue
  线程池中的任务队列。上面提到线程池的主要作用是复用线程来处理任务，所以我们需要一个队列来存放需要执行的任务，在使用池中的线程来处理这些任务，所以我们需要一个任务队列。

6、threadFactory
  当线程池判断需要新的线程时通过线程工程创建线程。

7、handler
  执行被阻止时的处理程序，线程池无法处理。这个与任务队列相关，比如队列中可以指定队列大小，
  * 如果超过了这个大小该怎么办呢？JDK已经为我们考虑到了，并提供了4个默认实现。

下列是JDK中默认携带的策略:
AbortPolicy (默认)
抛出 RejectedExecutionException 异常。

CallerRunsPolicy
调用当前线程池所在的线程去执行。

DiscardPolicy
直接丢弃当前任务。

DiscardOldestPolicy
将最旧的任务丢弃，将当前任务添加到队列。

*
* ---但是创建线程非常不推荐用Executors工具类，因为他底层也是调用ThreadPoolExecutor构造方法，但是采用的任务队列是无界队列，
* 如果大量线程被无限制放入队列，容易导致OOM。所以一般创建线程池推荐直接调用ThreadPoolExecutor的构造方法
*
*
* ---任务队列、核心线程数、最大线程数的逻辑关系：
* 当线程数小于核心线程数时，创建线程。
  当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列。
  当线程数大于等于核心线程数，且任务队列已满
     若线程数小于最大线程数，创建线程
     若线程数等于最大线程数，调用拒绝执行处理程序（默认效果为：抛出异常，拒绝任务）

  * 注意：如果只创建线程池对象，而不提交任务，那么线程池是不会创建核心线程的，程序过一会儿就会因为main线程
  * 结束并且没有其他活动线程而终止。只有当第一次提交了任务，线程池才会创建核心线程，之后就算核心线程空闲了
  * 也不会被销毁(如果调用了allowCoreThreadTimeOut(boolean value)例外，核心线程也会超时销毁)

* ---关闭线程池
* 1、public void shutdown():将正在执行的任务和待执行的任务(阻塞队列中的)执行完后关闭
* 2、public List<Runnable> shutdownNow()：立即终止所有正在执行的线程，并返回阻塞队列中待执行的任务
*
* 只要调用了shutdown或者shutdownNow后isShutdown就会返回true，但是isTerminated要等所有线程结束，
* 线程池真正终止后才会返回true
  *
  ---如何设置线程池最大线程数：
  * 1、CPU密集型任务：处理器最大核心数 + 1
  * 2、IO密集型：处理器最大核心数 * 2 或者 处理器最大核心数 / 0.1，看哪种效果好
* */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testFixedThreadPool();
        //testSingleThreadPool();
        //testCachedThreadPool();
        //testScheduledThreadPool();

        System.out.println("可用的CPU核心数：" + Runtime.getRuntime().availableProcessors());

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(1, 5, 1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                int temp = i;
                threadPoolExecutor.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(temp);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }finally {
            System.out.println("shutdown");
            List<Runnable> runnables = threadPoolExecutor.shutdownNow();
            System.out.println(runnables);
        }
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
