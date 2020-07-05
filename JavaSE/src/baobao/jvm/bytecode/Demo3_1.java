package baobao.jvm.bytecode;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.locks.LockSupport;

/**
 * @author baobao
 * @create 2020-05-13 20:21
 * @description
 */
public class Demo3_1 {

    static Object obj = new Object();
    public static void method1() {
        synchronized( obj ) {
// 同步块
        }
    }

}
