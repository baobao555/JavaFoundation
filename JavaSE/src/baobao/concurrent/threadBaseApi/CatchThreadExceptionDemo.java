package baobao.concurrent.threadBaseApi;

/**
 * @author baobao
 * @create 2020-01-05 19:50
 * @description 演示捕获线程异常
 *
 * 一般线程的run方法无法抛出异常，只能在内部捕获。
 * 想要在外部得到线程执行异常，需要调用Thread对象的setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
 */
public class CatchThreadExceptionDemo {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("算数异常");
            int result = 10 / 0;
        });

        //为线程t设置异常捕获处理器
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "出异常了");
                System.out.println("异常信息：" + e.getMessage());
            }
        });

        t.start();
    }
}
