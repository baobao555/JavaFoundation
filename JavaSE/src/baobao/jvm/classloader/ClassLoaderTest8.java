package baobao.jvm.classloader;

/**
 * @author baobao
 * @create 2020-04-02 20:00
 * @description
 */
public class ClassLoaderTest8 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = systemClassLoader.loadClass("baobao.jvm.classloader.CL");
        System.out.println(clazz);
        System.out.println("========================");
        Class<?> clazz1 = Class.forName("baobao.jvm.classloader.CL");
        System.out.println(clazz1);
    }
}

class CL{
    static {
        System.out.println("CL init");
    }
}