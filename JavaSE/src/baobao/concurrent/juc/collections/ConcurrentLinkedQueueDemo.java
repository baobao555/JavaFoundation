package baobao.concurrent.juc.collections;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author baobao
 * @create 2020-02-18 21:59
 * @description 演示ConcurrentLinkedQueue&ConcurrentLinkedDeque
 *
 * ConcurrentLinkedQueue是LinkedList的线程安全版本
 *
 * 注意：size()方法会遍历链表计算，效率低。在判断队列是否为空时，应该用isEmpty()而不是size()==0
 *
 * ConcurrentLinkedDeque是双向链表版本
 */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Object> linkedQueue = new ConcurrentLinkedQueue<>();
    }
}
