package baobao.jvm.bytecode;

import java.io.PipedReader;

/**
 * @author baobao
 * @create 2020-04-14 20:27
 * @description
 */
public class ByteCodeTest3 {
     private int i1;
     private int i2 = 1;
     private int i3 = 3;

     private static int s1;

     static {
         s2 = 3;
     }

    private static int s2 = 2;

    {
        i2 = 3;
    }

    private static int s3;

    public ByteCodeTest3(){

    }

    public ByteCodeTest3(int i){
        this.i3 = i;
    }
}
