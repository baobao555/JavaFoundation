package baobao.multithread;

/**
 * @author baobao
 * @date 2019/7/15 0015 17:04
 * @description 使用两个线程打印 1~ 100 。线程 1, 线程 2 交替打印
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter();
        Thread thread1 = new Thread(numberPrinter,"thread1");
        Thread thread2 = new Thread(numberPrinter,"thread2");
        thread1.start();
        thread2.start();
    }
}

class NumberPrinter implements Runnable{
    //初始化线程共享数据：要打印的数，从1开始
    int number = 1;
    @Override
    public void run() {
        //同步代码块，以this作为锁
        synchronized (this){
            //判断是否已经打印到100
            while (number <= 100){
                //唤醒其他线程
                notifyAll();
                //打印数字
                System.out.println(Thread.currentThread().getName() + "-" + number++);
                try {
                    //进入阻塞状态并释放锁，等待被唤醒后才继续执行
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
