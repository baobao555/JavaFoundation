package baobao.concurrent.threadBaseApi;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-12-31 20:54
 * @description 演示使用interrupt方法结束线程
 *
 * interrupt方法作用：
 * 1、调用线程t的interrupt实际上是将线程的中断标志位设置为true，即调用t.isInterrupted()返回true
 *
 * 2、如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、1.5中的condition.await、
 * 以及可中断的通道上的 I/O 操作方法后可进入阻塞状态），则在线程在检查中断标示时如果发现中断标示为true，
 * 则会在这些阻塞方法（sleep、join、wait、1.5中的condition.await及可中断的通道上的 I/O 操作方法）
 * 调用处抛出InterruptedException异常，并且在抛出异常后立即将线程的中断标示位清除，即重新设置为false。
 * 抛出异常是为了线程从阻塞状态醒过来，并在结束线程前让程序员有足够的时间来处理中断请求。
 */
public class FinishThreadByInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            //循环判断中断标志位，如果true则退出循环，结束线程
            while (!Thread.currentThread().isInterrupted()){
                try {
                    //正常执行线程工作
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + ":正在执行");
                } catch (InterruptedException e) {
                    //线程在wait或sleep期间被中断了
                    //因为上面正常执行代码中有sleep，在sleep阻塞期间调用interrupt会抛出异常，进入catch代码块
                    //因为抛出异常后，中断标志位会被重置为false，所以为了让线程退出，必须手动将标志位置true
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":执行完毕");
        }, "worker");

        worker.start();

        //休眠3秒后中断work线程
        TimeUnit.SECONDS.sleep(3);
        worker.interrupt();
    }
}
