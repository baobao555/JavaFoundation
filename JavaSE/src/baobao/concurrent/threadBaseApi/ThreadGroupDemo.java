package baobao.concurrent.threadBaseApi;

/**
 * @author baobao
 * @create 2019-12-30 17:51
 * @description 演示线程组
 *
 * Thread完整构造方法：
 * public Thread(ThreadGroup group, Runnable target, String name,long stackSize)
 *
 * 其中group代表线程组，如果不传，则会将该线程加入父线程（即调用该线程start方法的线程）所在的线程组
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("子线程名称：" + Thread.currentThread().getName());
            System.out.println("子线程所在线程组名称：" + Thread.currentThread().getThreadGroup().getName());
        },"myThread").start();

        System.out.println("父线程名称：" + Thread.currentThread().getName());
        System.out.println("父线程所在线程组名称：" + Thread.currentThread().getThreadGroup().getName());

        //遍历线程组中所有活动线程
        Thread[] threadList = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadList);
        for (Thread thread : threadList) {
            System.out.println(thread);
        }
    }
}
