package baobao.jvm.classloader;

/**
 * @author baobao
 * @create 2020-03-31 21:53
 * @description
 */
public class ClassLoaderTest2 {
    public static void main(String[] args) {
        System.out.println(Parent2.i);
    }
}

class Parent2{
    public static final int i = 5;
    static {
        System.out.println("parent static block");
    }
}
