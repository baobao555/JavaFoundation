package baobao.jvm.memory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author baobao
 * @create 2020-05-08 21:56
 * @description
 */
public class DirectMemoryFree {
    static int _1GB = 1024 * 1024 * 1024;
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1GB);
        System.out.println("分配了1G直接内存");
        System.in.read();
        System.out.println("开始释放直接内存");
        byteBuffer = null;
        System.gc();
    }
}
