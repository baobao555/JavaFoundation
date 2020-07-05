package baobao.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author baobao
 * @create 2019-12-27 19:51
 * @description 演示虚引用
 *
 *
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        //创建引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        //创建o1的虚引用，传入o1和引用队列
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

        System.out.println(o1);
        //打印虚引用指向的对象
        System.out.println("回收前，通过虚引用获取其指向的对象：" + phantomReference.get());
        //打印虚引用本身
        System.out.println(phantomReference);
        //看引用队列中是否有被回收的虚引用
        System.out.println(referenceQueue.poll());

        System.out.println("=====================");
        //去掉o1的强引用
        o1 = null;
        //主动触发gc，虚引用会被回收掉
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println("回收后，通过虚引用获取其指向的对象：" + phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
