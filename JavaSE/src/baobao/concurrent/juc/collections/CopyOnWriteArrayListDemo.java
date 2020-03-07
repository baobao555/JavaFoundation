package baobao.concurrent.juc.collections;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author baobao
 * @create 2020-02-18 22:06
 * @description 演示CopyOnWriteArrayList&CopyOnWriteArraySet
 *
 * CopyOnWriteArrayList是ArrayList的线程安全版本
 * CopyOnWriteArraySet是HashSet的线程安全版本
 *
 * CopyOnWriteArrayList底层为数组实现，实现线程安全的原理为写时复制，即多个线程读取数据的时候
 * 不加锁，多个线程写入数据的时候创建一个新的数组，把原数组的值拷贝过去，然后在新数组的最后加上
 * 要写入的数据，最后把原数组的指针指向新数组。缺点是比较消耗内存。适合读线程多写线程少的场景。
 *
 * CopyOnWriteArraySet底层就是用CopyOnWriteArrayList：
 * public CopyOnWriteArraySet() {
 *         al = new CopyOnWriteArrayList<E>();
 *     }
 * 插入数据之前，通过判断是否与已有的数据重复，如果重复就不做任何操作，不重复才进行插入，以此
 * 来保证数据的唯一性
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Object> arrayList = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<Object> arraySet = new CopyOnWriteArraySet<>();
    }
}
