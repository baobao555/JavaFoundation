package baobao.jvm.classloader;

import java.util.UUID;

/**
 * @author baobao
 * @create 2020-03-31 22:07
 * @description
 */
public class ClassLoaderTest3 {
    public static void main(String[] args) {
        System.out.println(Parent3.str);
    }
}

class Parent3{
    public static final String str = UUID.randomUUID().toString();//编译期不能确定的值
    static {
        System.out.println("parent3 static block");
    }
}