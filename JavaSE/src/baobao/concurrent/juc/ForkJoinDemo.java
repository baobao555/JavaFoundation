package baobao.concurrent.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author baobao
 * @create 2020-03-07 19:12
 * @description 演示ForkJoin
 *
 * ForkJoin核心思想是将一个大的任务不断拆分成小任务，多核并发执行小任务提升效率
 * 主要涉及3个类：
 * 1、ForkJoinPool：执行ForkJoin任务的线程池，可以使用invoke方法提交任务
 * 2、RecursiveTask：继承自ForkJoinTask，代表有返回值的ForkJoin任务
 * 3、RecursiveAction：继承自ForkJoinTask，代表无返回值的ForkJoin任务
 *
 * 自定义ForkJoinTask步骤：
 * 1、定义一个任务类，继承自RecursiveTask或RecursiveAction
 * 2、重写compute方法，判断任务是否可拆分，如果可拆分，拆分到新的任务实例对象中；如果不可拆分，
 *    直接执行计算逻辑。最后将拆分的结果进行合并返回
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        //testForkJoinPool();
        //testTradition();
        testStream();
    }

    //执行时间：9864
    //932356074711512064
    private static void testStream(){
        long startTime = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, 100000000000L).parallel().sum();
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime));
        System.out.println(sum);
    }

    //执行时间：25545
    //932356074711512064
    private static void testTradition(){
        long start = 1;
        long end = 100000000000L;
        long sum = 0;
        long startTime = System.currentTimeMillis();
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime));
        System.out.println(sum);
    }

    //执行时间：13833
    //932356074711512064
    private static void testForkJoinPool(){
        //创建递归任务，从1累加到100000000000
        SumTask sumTask = new SumTask(1, 100000000000L);
        //创建与cpu逻辑核心数相同的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        //ForkJoinPool同步执行递归任务并返回结果
        Long result = forkJoinPool.invoke(sumTask);
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
        System.out.println(result);
    }
    //递归任务
    static class SumTask extends RecursiveTask<Long>{
        private long start;
        private long end;

        public SumTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            //当任务已经足够小时，直接执行
            if (end - start < 10000){
                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }else {
                //将任务拆分成2部分
                long mid = (start + end) / 2;
                SumTask leftTask = new SumTask(start, mid);
                SumTask rightTask = new SumTask(mid + 1, end);
                //自己和别的线程各执行1部分
                invokeAll(leftTask,rightTask);

                /*错误写法：
                * leftTask.fork();
                * rightTask.fork();
                * return leftTask.join() + rightTask.join();
                *
                * 打个比方，假设一个酒店有400个房间，一共有4名清洁工，每个工人每天可以打扫100个房间，这样，
                * 4个工人满负荷工作时，400个房间全部打扫完正好需要1天。Fork/Join的工作模式就像这样：首先，
                * 工人甲被分配了400个房间的任务，他一看任务太多了自己一个人不行，所以先把400个房间拆成两个
                * 200，然后叫来乙，把其中一个200分给乙。紧接着，甲和乙再发现200也是个大任务，于是甲继续把
                * 200分成两个100，并把其中一个100分给丙，类似的，乙会把其中一个100分给丁，这样，最终4个人
                * 每人分到100个房间，并发执行正好是1天。
                *
                * 而这种错误写法相当于：
                * 甲把一个200分给乙，把另一个200分给丙，然后，甲成了监工，不干活，等乙和丙干完了他直接汇报
                * 工作。乙和丙在把200分拆成两个100的过程中，他俩又成了监工，这样，本来只需要4个工人的活，
                * 现在需要7个工人才能1天内完成，其中有3个是不干活的
                *
                * */

                //将拆分的任务的执行结果进行合并
                return leftTask.join() + rightTask.join();
            }
        }
    }
}
