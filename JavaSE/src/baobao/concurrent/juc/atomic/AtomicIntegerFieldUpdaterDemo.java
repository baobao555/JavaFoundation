package baobao.concurrent.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author baobao
 * @create 2020-02-03 19:39
 * @description 演示AtomicIntegerFieldUpdater
 *
 * AtomicIntegerFieldUpdater可以将一个类下面的volatile int类型的属性变成原子性的。使用步骤：
 * 1、自定义一个类，并声明一个volatile int类型的属性，访问权限不能是private或protected
 * 2、调用AtomicIntegerFieldUpdater.newUpdater方法，传入对应类的Class对象以及需要变成原子的属性名称，
 *    创建出AtomicIntegerFieldUpdater对象
 * 3、创建自定义类的对象
 * 4、调用AtomicIntegerFieldUpdater对象的方法，传入自定义类的对象，即可实现对自定义类volatile int
 *    属性的各种原子操作
 *
 * 为什么要使用AtomicIntegerFieldUpdater而不直接将自定义类的属性定义成AtomicInteger类型？
 * 这样做主要好处是可以节省内存。定义成volatile int比AtomicInteger更加节省内存，适合需要创建
 * 很多这样的自定义类对象的场景
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建AtomicIntegerFieldUpdater
        AtomicIntegerFieldUpdater<MyValue> updater = AtomicIntegerFieldUpdater.newUpdater(MyValue.class, "value");
        //创建自定义类
        MyValue myValue = new MyValue();
        //创建10个线程对自定义类的属性进行操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    //每个线程对MyValue的value属性自增1000次
                    updater.getAndIncrement(myValue);
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(myValue.getValue());
        System.out.println(updater.get(myValue));
    }
}

//自定义类
class MyValue{
    //需要进行原子操作的属性
    volatile int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
