package baobao.jvm.classloader.myclassloader;

import sun.misc.Launcher;

/**
 * @author baobao
 * @create 2020-04-06 21:28
 * @description
 */
public class MyClassLoaderTest5 {
    public static void main(String[] args) {
        //打印应用类加载器本身是由哪个类加载器加载的
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getClassLoader());
        //打印扩展类加载器本身是由哪个类加载器加载的
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getClass().getClassLoader());

        //打印应用类加载器
        System.out.println("应用类加载器：" + ClassLoader.getSystemClassLoader());
    }
}
