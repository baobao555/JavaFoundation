package baobao.jvm.classloader;

/**
 * @author baobao
 * @create 2020-04-02 20:28
 * @description
 */
public class ClassLoaderTest9 {
    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        //线程上下文类加载器
        System.out.println("contextClassLoader:" + contextClassLoader);
        //系统类加载器
        System.out.println("systemClassLoader:" + systemClassLoader);
        //扩展类加载器
        System.out.println("systemClassLoader.parent:" + systemClassLoader.getParent());
        //启动类加载器
        System.out.println("systemClassLoader.grandParent:" + systemClassLoader.getParent().getParent());
    }
}
