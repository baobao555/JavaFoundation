package baobao.juc;

/**
 * @author baobao
 * @create 2019-09-14 21:06
 * @description 演示volatile不能保证原子性
 */

//原子性：即一致性，数据的操作不能被中断
//volatile不能保证原子性，在多线程访问中会出现安全问题
public class VolatileNotAtomicDemo {
    public static void main(String[] args) {
        Data data = new Data();
        /*
        这里开启了10个线程，每个线程对number进行1000次++操作，理想结果应为10000
        而实际上结果总会小于10000，等于说丢失了很多次++操作
        原因：number的初始值为0，假设线程1将number的值从主内存拷贝至工作内存后对其进行了++操作，
        并且在将number值写回主内存之前失去了执行权(此时主内存中的number仍为0)，然后线程2开始执行，也将
        number的值从主内存(0)拷贝至工作内存后对其进行了++操作(1)，并写回主内存(此时主内存的number值=1)，
        而后线程1重新拥有了执行权，它将继续之前的操作，将工作内存中的number值(1)写回主内存的number(1)，
        这样2个线程分别执行1次++操作后，主内存number值仍为1，并不是理想的2，相当于因操作中断造成了数据不一致，故不能保证原子性
        */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.plusOne();
                }
            },String.valueOf(i)).start();
        }

        //等待所有子线程执行结束，因为默认后台有2个线程：主线程和GC线程，存活线程数大于2说明我们开的线程没执行完
        while (Thread.activeCount() > 2){

        }

        //输出结果(大概率不是理想值10000)
        System.out.println("number =" + data.number);


    }
}

class Data{
    volatile int number = 0;

    public void plusOne(){
        number++;
    }
}
