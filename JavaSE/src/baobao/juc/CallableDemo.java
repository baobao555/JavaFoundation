package baobao.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author baobao
 * @create 2019-09-16 21:06
 * @description 演示Callable
 */

/*
* 创建线程的第三种方式：Callable接口
* 使用方式：实现Callable接口的Call方法
*
* 与Runnable的区别：
* 1、带泛型
* 2、可以有返回值，返回值的类型就是泛型的类型
* 3、可以抛出异常
* */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Thread类并没有接收Callable类型参数的构造方法，故需要通过实现Runnable接口的类FutureTask来转接

        //创建FutureTask，构造方法接收一个Callable接口的实现
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        //因为FutureTask实现了Runnable接口，故可以作为参数传入Thread的构造方法，从而实现了Callable到Runnable的转接
        new Thread(futureTask, "MyCallable").start();

        //获取返回值。此方法会一直阻塞直到线程执行完成并返回结果，故建议等其他业务逻辑都处理完成后再最后调用该方法获取结果
        Integer result = futureTask.get();
        System.out.println("result=" + result);
    }
}

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 2019;
    }
}
