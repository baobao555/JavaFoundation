package baobao.concurrent.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-04 22:22
 * @description 演示Exchanger
 *
 * Exchanger可以实现在2个线程之间交换数据:
 * 1、调用构造方法Exchanger<Object> exchanger = new Exchanger<>();，泛型指定需要交换的数据类型
 * 2、在2个线程内部调用Object result = exchanger.exchange(data);其中data是需要发送的数据，result是接收另一个
 *    线程交换过来的数据
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        //创建Exchanger对象，泛型指定需要交换的数据类型
        Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            Object a2b = "data from A";
            System.out.println("线程A发送" + a2b);
            try {
                //A线程向B线程发送数据，阻塞等待B线程的数据
                //注意这里A线程接收到B发送过来的数据result，实际上内存地址和线程B创建的b2a对象相等
                //即A接收到的和B发送的在内存中是同一个对象
                Object result = exchanger.exchange(a2b);
                System.out.println("线程A接收" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            Object b2a = "data from B";
            System.out.println("线程B发送" + b2a);
            try {
                //B线程工作3秒后，向A线程发送数据，阻塞等待A线程的数据
                TimeUnit.SECONDS.sleep(3);
                Object result = exchanger.exchange(b2a);
                System.out.println("线程B接收" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
