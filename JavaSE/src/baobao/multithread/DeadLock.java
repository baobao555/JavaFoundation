package baobao.multithread;

/**
 * @author baobao
 * @date 2019/7/15 0015 15:16
 * @description 演示死锁
 */
public class DeadLock {
    public static void main(String[] args) {
        //创建2个锁对象
        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程1：lock1中嵌套lock2
                synchronized (lock1){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1----11111");

                    synchronized (lock2){
                        System.out.println("thread1----22222");
                    }
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程2：lock2中嵌套lock1
                synchronized (lock2){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread2----11111");

                    synchronized (lock1){
                        System.out.println("thread2----22222");
                    }
                }

            }
        }).start();

    }
}
