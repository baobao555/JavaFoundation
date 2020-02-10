package baobao.concurrent.juc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author baobao
 * @create 2019-09-18 0:12
 * @description 演示集合的多线程不安全性
 */

/*
* 普通集合在多线程中并发访问会发生并发修改异常ConcurrentModifyException
* 解决方法：
* 1、使用Collections工具类将集合转换为线程安全的
* 2、使用juc包下的CopyOnWriteArrayList、CopyOnWriteArraySet、ConcurrentHashMap
* */
public class ConcurrentModifyExceptionDemo {
    public static void main(String[] args) {
        //testUnSafeList();
        //testUnSafeSet();
        testUnSafeMap();
    }

    private static void testUnSafeMap() {
        //Map<Integer,Integer> map = new HashMap<>();//java.util.ConcurrentModificationException
        Map<Integer,Integer> map = new ConcurrentHashMap<>();//不会发生异常
        for (int i = 1; i <= 20; i++) {
            int temp = i;
            new Thread(() -> {
                map.put(temp,temp);
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void testUnSafeSet() {
        //Set<Integer> set = new HashSet<>();//java.util.ConcurrentModificationException
        Set<Integer> set = new CopyOnWriteArraySet<>();//不会发生异常
        for (int i = 1; i <= 20; i++) {
            int temp = i;
            new Thread(() -> {
                set.add(temp);
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void testUnSafeList() {
        //List<Integer> list = new ArrayList<>();//java.util.ConcurrentModificationException
        List<Integer> list = new CopyOnWriteArrayList<>();//不会发生异常
        for (int i = 1; i <= 20; i++) {
            int temp = i;
            new Thread(() -> {
                list.add(temp);
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
