package baobao.jvm.reference;

/**
 * @author baobao
 * @create 2019-12-27 17:14
 * @description 演示强引用
 *
 * 强引用：只要引用变量还在，就不会回收对应的堆内存，哪怕出现堆内存溢出
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义默认的就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;
        System.gc();
        System.out.println(obj2);//java.lang.Object@1e643faf
    }
}
