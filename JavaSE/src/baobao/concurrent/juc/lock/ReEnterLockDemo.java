package baobao.concurrent.juc.lock;

/**
 * @author baobao
 * @create 2019-11-16 0:07
 * @description 演示可重入锁
 *
 * 可重入锁（也就是递归锁）：指的是同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。也就是说，线程可以进入任何一个
 * 它已经拥有的锁所有同步着的代码块。
 *
 * synchronized和ReentrantLock都是可重入锁
 */
public class ReEnterLockDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {shareData.outerMethod();}, "t1").start();
        new Thread(() -> {shareData.outerMethod();}, "t2").start();
    }


    private static class ShareData{
        public synchronized void outerMethod(){
            System.out.println(Thread.currentThread().getName() + "outerMethod invoke");
            innerMethod();
        }

        public synchronized void innerMethod(){
            System.out.println(Thread.currentThread().getName() + "innerMethod invoke");
        }
    }
}
