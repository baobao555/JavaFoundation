package baobao.jvm.classloader;

/**
 * @author baobao
 * @create 2020-03-31 21:16
 * @description
 */
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        System.out.println(Child.str);
    }
}

class Parent{
    public static String str = "parent";
    //类的初始化会导致静态代码块执行
    static {
        System.out.println("parent static block");
    }
}

class Child extends Parent{
    public static String str = "child";
    static {
        System.out.println("child static block");
    }
}
