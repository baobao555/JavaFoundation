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
        WeakReference<Object> weakReference = new WeakReference<>(o);
        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(weakReference.get());
    }
}
