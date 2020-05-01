package baobao.jvm.classloader;

import java.util.Random;

/**
 * @author baobao
 * @create 2020-03-31 22:27
 * @description
 */
public class ClassLoaderTest5 {
    public static void main(String[] args) {
        System.out.println(Parent5.a);
    }
}

interface Parent5{
    public static final int a = new Random().nextInt(3);
    public static final Thread t = new Thread(){
        {
            System.out.println("Thread created");
        }
    };
}

interface Child5 extends Parent5{
    public static final int b = new Random().nextInt(3);
}
