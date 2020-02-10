package baobao.concurrent.threadBaseApi;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-01-28 21:10
 * @description 设计一个FutureTask
 *
 * FutureTask执行任务时不会长时间阻塞主线程，主线程可以做其他事，FutureTask执行完后可以将结果回调给主线程
 *
 * Future接口：包含任务执行结果
 * FutureTask接口：代表一个任务
 * FutureService：桥接Future和FutureTask，用于启动一个FutureTask异步任务并返回包含结果的Future对象
 *
 * 比喻：你去蛋糕店窗口（FutureService），表明定制蛋糕的需求（FutureTask），店员告诉你需要等3个小时并且
 * 给了你一张蛋糕领取券（Future）。。你去其他地方逛了一会，2个小时后拿着领取券（Future）去取，店员说3个小时
 * 还没到，于是你又在店里等了1个小时（任务没执行完成就获取结果会一直阻塞），最后拿到蛋糕
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        //启动异步任务，并返回一个future对象，异步任务结束时会将执行结果通知并封装到future中
        Future<String> future = futureService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Success";
        });

        //做其他事
        System.out.println("====================");
        //获取任务执行结果，如果任务还没执行完，会一直阻塞，直到执行完成获得结果
        System.out.println(future.get());
    }
}


interface Future<T>{
    T get() throws InterruptedException;
}

class AsyncFuture<T> implements Future<T>{
    //任务是否结束标志位
    private volatile boolean isDone = false;
    //保存执行结果
    private T result;

    //任务结束时将结果通知给Future
    public void done(T result){
        synchronized (this){
            isDone = true;
            this.result = result;
            //唤醒正在等待结果的线程
            this.notifyAll();
        }
    }

    //获得执行结果
    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            //如果任务未执行完成，阻塞
            while (!isDone){
                this.wait();
            }
        }
        //返回结果
        return result;
    }
}

interface FutureTask<T> {
    //需要执行的任务
    T call();
}


class FutureService{
    public<T> Future<T> submit(FutureTask<T> task){
        //创建future对象，保存执行结果
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            //执行任务返回结果
            T result = task.call();
            //将结果通知给future对象，以便其他线程可以通过future对象获得结果
            asyncFuture.done(result);
        }).start();
        //返回future对象
        return asyncFuture;
    }
}
