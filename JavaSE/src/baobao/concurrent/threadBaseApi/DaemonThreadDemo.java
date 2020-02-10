package baobao.concurrent.threadBaseApi;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-12-31 12:26
 * @description 演示守护线程
 *
 * 守护线程：与它的父线程相关联，在父线程结束后，不管任务是否执行完毕，都将随着父线程一起结束
 * 应用：比如心跳包线程可以设置为网络连接线程的守护线程，网络连接线程结束后，心跳包线程也就没有存在的意义了
 */
public class DaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "：心跳包线程启动");
            while (true){
                try {
                    //每隔1秒发送心跳包
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "：发送心跳包");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "heart beat thread");

        //设置线程t为main线程的守护线程，必须在启动前设置
        t.setDaemon(true);
        t.start();

        //休眠5秒后main线程结束
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "：main线程结束");
    }
}
