package baobao.jvm.classloader;

/**
 * @author baobao
 * @create 2020-04-02 19:53
 * @description
 */
public class ClassLoaderTest7 {
    public static void main(String[] args) {
        System.out.println(Child7.a);
        System.out.println("==================");
        Child7.doSomething();
    }
}

class Parent7{
    public static int a = 5;
    static {
        System.out.println("parent static block");
    }

    public static void doSomething(){
        System.out.println("parent do something");
    }
}

class Child7 extends Parent7{
    static {
        System.out.println("child static block");
    }
}
