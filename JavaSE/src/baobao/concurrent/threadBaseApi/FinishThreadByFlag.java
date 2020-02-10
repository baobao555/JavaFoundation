package baobao.concurrent.threadBaseApi;

import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2019-12-31 17:50
 * @description 演示采用标志位结束线程
 */
public class FinishThreadByFlag {
        public static void main(String[] args) throws InterruptedException {
            //开启worker线程
            Worker worker = new Worker();
            new Thread(worker, "worker").start();
            //3秒后结束work线程
            TimeUnit.SECONDS.sleep(3);
            worker.stop();
        }

        private static class Worker implements Runnable{
            private boolean flag = true;
            @Override
            public void run() {
                while (flag){
                    try {
                        System.out.println(Thread.currentThread().getName() + ":正在执行");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":执行完毕");
            }

            //标志位置false，结束run方法
            public void stop(){
                flag = false;
            }
        }
}
