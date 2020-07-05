package baobao.concurrent.juc.atomic;

/**
 * @author baobao
 * @create 2019-09-14 10:12
 * @description 演示volatile的可见性
 */

/*volatile是Java虚拟机提供的轻量级的同步机制(乞丐版的synchronized)
    1.保证可见性：强制对CPU的缓存操作立刻刷新写入主内存，并且导致其他CPU的缓存失效，其他CPU对变量的操作必须重新从主内存中读取
    2.不保证原子性
    3.禁止指令重排：修饰的变量一个内存屏障，保障屏障前后的代码执行顺序不会被调整
 */

/*
由于JVM运行程序的实体是线程，而每个线程创建时JVM都会为其创建一个工作内存
（有些地方称为栈空间），工作内存是每个线程的私有数据区域，而Java内存模型中规定所有变量
都存储到主内存，主内存是共享内存区域，所有线程都可以访问，但线程对变量的操作
（读取、复制等）必须在工作内存中进行，首先要将变量从主内存拷贝到自己的工作内存空间，
然后对变量进行操作，操作完成后再将变量写回主内存，不能直接操作主内存中的变量，各个
线程中的工作内存中存储着主内存中的变量副本拷贝，因此不同的线程间无法访问对方的工作内存，
线程间的通信（传值）必须通过主内存来完成

可见性：
可能存在一个线程A修改了共享变量X的值但还未写回主内存时，另一个线程B又对准内存中同一个共享变量
X进行操作，但此时A线程工作内存中共享变量X对线程B来说并不是可见，这种工作内存与主内存同步
存在延迟现象就造成了可见性问题

volatile关键字修饰的变量可以在某线程的工作内存中修改了共享变量的值并写回主内存后立刻通知其他
线程从主内存中取共享变量的最新的值，从而保证了可见性
 */

public class VolatileSeeableDemo {
    //增加volatile关键字，保证共享数据的可见性
    //private volatile static boolean flag = false;
    private static boolean flag = false;
    //private volatile static Boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        //这里除了内存可见性的问题，java帮我们做了另一层优化才导致不加volatile不行：
        //java一看这段代码里只包含对共享变量flag的读操作，并没有写操作，所以认为不需要
        //从主内存去读取flag的最新值，每次都从cpu缓存中去值，所以导致了永远都感知不到其他线程对flag的修改
        new Thread(() -> {
            while (true){
                // 加上print，即使flag不用volatile，也能正常跳出循环
                System.out.print("");
                if (flag){
                    System.out.println(Thread.currentThread().getName() + " : flag is " + flag);
                    break;
                }
            }
        }, "thread1").start();

        Thread.sleep(1000);//保证thread1先启动

        new Thread(() -> {
            flag = true;
            System.out.println(Thread.currentThread().getName() + " : flag is " + flag);
        }, "thread2").start();
    }
}

