package baobao.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baobao
 * @create 2019-09-15 21:52
 * @description 演示多个Condition精确控制线程执行顺序
 */

/*
* 需求：线程A打印10次，线程B打印20次，线程C打印30次。。。如此反复进行10轮
*
* 这里有3个线程，需要精确控制执行顺序(A->B->C)，此时老的wait/notifyAll方法做不到，因为一旦notifyAll，其余线程都会来争抢
* 执行权，无法精确控制下一个执行的线程是谁
* */
public class MultiConditionDemo {
    public static void main(String[] args) {
        Printer printer = new Printer();

        new Thread(() -> printer.print10(10),"A").start();
        new Thread(() -> printer.print20(10),"B").start();
        new Thread(() -> printer.print30(10),"C").start();
    }
}

class Printer{
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //A线程打印10次
    public void print10(int roundNum){
        for (int i = 1; i <= roundNum; i++) {
            lock.lock();
            try {
                //判断挂起条件：标志位不为1时挂起
                while (flag != 1){
                    c1.await();
                }
                //具体业务逻辑：打印10次，修改标记位
                for (int j = 1; j <= 10; j++) {
                    System.out.println(Thread.currentThread().getName() + ":" + j + "---" + "round:" + i);
                }
                flag = 2;
                //唤醒B线程
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    //B线程打印20次
    public void print20(int roundNum){
        for (int i = 1; i <= roundNum; i++) {
            lock.lock();
            try {
                //判断挂起条件：标志位不为2时挂起
                while (flag != 2){
                    c2.await();
                }
                //具体业务逻辑：打印20次，修改标记位
                for (int j = 1; j <= 20; j++) {
                    System.out.println(Thread.currentThread().getName() + ":" + j + "---" + "round:" + i);
                }
                flag = 3;
                //唤醒C线程
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    //C线程打印30次
    public void print30(int roundNum){
        for (int i = 1; i <= roundNum; i++) {
            lock.lock();
            try {
                //判断挂起条件：标志位不为3时挂起
                while (flag != 3){
                    c3.await();
                }
                //具体业务逻辑：打印30次，修改标记位
                for (int j = 1; j <= 30; j++) {
                    System.out.println(Thread.currentThread().getName() + ":" + j + "---" + "round:" + i);
                }
                flag = 1;
                //唤醒A线程
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }



}
