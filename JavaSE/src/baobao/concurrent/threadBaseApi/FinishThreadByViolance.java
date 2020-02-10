package baobao.concurrent.threadBaseApi;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-01-01 11:02
 * @description 演示暴力结束线程
 *
 * 之前两种结束线程的方式，利用标志位和interrupt方法，本质都是在run方法中利用while循环判断标志位，
 * 但是这样对有些场景是失效的，比如while循环中实际执行的任务需要的时间很长（比如网络、io操作等），
 * 直接阻塞在实际执行的代码上，这样就算将标志位改变，也无法马上重新判断标志位。
 *
 * 暴力结束线程思路：
 * 1、在工作线程t中再开一个实际执行任务的线程t0，将t0设置为t的守护线程
 * 2、工作线程t中调用t0的join方法，等待t0执行完毕t才结束
 * 3、想要结束t0时，只要调用t的interrupt方法，由于之前t中调用了t0.join()，所以t在等待t0结束的过程中被打断，
 *    会抛出异常，可以在捕获异常后结束t，t一旦结束了，作为守护线程的t0自然也就结束了
 */
public class FinishThreadByViolance {
    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();
        long start = System.currentTimeMillis();
        //提交一个需要执行10秒的异步任务
        threadService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //如果5秒没执行完毕，强制终止任务
        threadService.shutdown(5000);
        long end = System.currentTimeMillis();
        System.out.println("任务共执行了" + (end - start) + "毫秒");
    }
}

class ThreadService{
    //执行任务线程t
    private Thread t;
    //正常执行结束的标志位，必须设置volatile保证多线程间的可见性
    private volatile boolean isFinished = false;

    /**
     * 执行一个异步任务
     * @param task 需要执行的异步任务
     */
    public void execute(Runnable task){
        //创建工作线程t
        t = new Thread(() -> {
            //在t的执行代码中创建实际执行task的线程t0
            Thread t0 = new Thread(task, "t0");
            //将t0设置为t的守护线程
            t0.setDaemon(true);
            //启动t0
            t0.start();

            try {
                //t阻塞，等待t0执行完成
                t0.join();
                //t0正常执行完成，标志位置为true
                isFinished = true;
                System.out.println("任务正常结束");
            } catch (InterruptedException e) {
                //t0执行异常，t被外界调用其interrupt方法强制中断，守护线程t0也随之结束
                System.out.println("任务被强制中断");
            }
        }, "t");

        //启动工作线程t
        t.start();
    }


    /**
     * 结束执行异步任务的线程
     * @param millis 异步任务执行超过多少毫秒时终止
     */
    public void shutdown(long millis){
        long start = System.currentTimeMillis();
        //判断任务是否已经正常执行结束(因为该方法是主线程调用，所以主线程访问判断isFinished标志位，而该标志位
        // 会在线程t中被修改，所以要加volatile修饰保证多线程间的可见性)
        while (!isFinished){
            //任务没有正常结束，判断是否已超时
            long now = System.currentTimeMillis();
            if ((now - start) > millis){
                //任务超时，强制中断任务，跳出循环
                System.out.println("任务超时，需要强制中断");
                t.interrupt();
                break;
            }
        }
        //重置标志位
        isFinished = false;
    }
}
