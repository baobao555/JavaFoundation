package baobao.jvm.memory;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @author baobao
 * @create 2020-05-08 21:46
 * @description
 */
public class DirectMemoryOut {
    private static int _100M = 100 * 1024 * 1024;
    public static void main(String[] args) {
        //记录分配100M直接内存的次数
        int i = 0;
        ArrayList<ByteBuffer> list = new ArrayList<>();
        try {
            while (true){
                //分配100M直接内存
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_100M);
                list.add(byteBuffer);
                //分配次数+1
                i++;
            }
        } catch (Throwable e) {
            System.out.println(i);
            e.printStackTrace();
        }
    }
}
