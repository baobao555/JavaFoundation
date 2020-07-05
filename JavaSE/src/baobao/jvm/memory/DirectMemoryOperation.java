package baobao.jvm.memory;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author baobao
 * @create 2020-05-08 22:15
 * @description
 */
public class DirectMemoryOperation {
    private static int _1GB = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        Unsafe unsafe = getUnsafe();
        //使用unsafe分配内存，得到内存地址
        long base = unsafe.allocateMemory(_1GB);
        //给内存设置初始值
        unsafe.setMemory(base, _1GB, (byte) 0);
        System.out.println("分配了1GB直接内存");
        System.in.read();
        System.out.println("释放内存");
        //根据地址释放内存
        unsafe.freeMemory(base);
    }

    //反射获取Unsafe对象
    private static Unsafe getUnsafe(){
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
