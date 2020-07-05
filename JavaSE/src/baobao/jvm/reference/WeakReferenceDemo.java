package baobao.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * @author baobao
 * @create 2019-12-27 19:37
 * @description 演示弱引用
 *
 * 弱引用：当一块堆内存只有弱引用指向它的时候，只要触发GC，不管内存是否充足都会被回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o = new Object();
        //创建o的弱引用
        WeakReference<Object> weakReference = new WeakReference<>(o);
        //去掉o的强引用
        o = null;
        //主动触发gc，一定会将只有软引用指向的o回收掉
        System.gc();
        System.out.println(o);//null
        System.out.println(weakReference.get());//null
    }
}
