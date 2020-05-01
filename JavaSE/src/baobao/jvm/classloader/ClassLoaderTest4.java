package baobao.jvm.classloader;

import java.util.UUID;

/**
 * @author baobao
 * @create 2020-03-31 22:13
 * @description
 */
public class ClassLoaderTest4 {
    public static void main(String[] args) {
        Parent4[] parent4s = new Parent4[1];
        Parent4[][] parent4s1 = new Parent4[1][1];
        System.out.println(parent4s.getClass());
        System.out.println(parent4s1.getClass());
        System.out.println(parent4s.getClass().getSuperclass());
        System.out.println(parent4s1.getClass().getSuperclass());

        System.out.println("-----------------------------");
        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
    }
}

class Parent4{
    static {
        System.out.println("parent4 static block");
    }
}