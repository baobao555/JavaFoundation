package baobao.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * @author baobao
 * @create 2019-12-27 17:17
 * @description 演示软引用
 *
 * 软引用：当一块堆内存只有软引用指向它的时候，内存充足的时候不回收，内存不够的时候回收
 */
public class SoftReferenceDemo {
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }


    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try{
            //需要将虚拟机运行时最大堆内存调小：-Xmx500m
            byte[] bytes = new byte[1000*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args){
        //softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }
}
