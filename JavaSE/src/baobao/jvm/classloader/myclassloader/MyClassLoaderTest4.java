package baobao.jvm.classloader.myclassloader;

import javax.sound.midi.Soundbank;

/**
 * @author baobao
 * @create 2020-04-06 21:28
 * @description
 */
public class MyClassLoaderTest4 {
    public static void main(String[] args) throws ClassNotFoundException {
        //启动类加载器加载的类的路径
        System.out.println(System.getProperty("sun.boot.class.path"));
        //扩展类加载器加载的类的路径
        System.out.println(System.getProperty("java.ext.dirs"));
        //应用类加载器加载的类的路径
        System.out.println(System.getProperty("java.class.path"));

        Class<?> clazz = Class.forName("baobao.jvm.classloader.ClassLoaderTest1");
        System.out.println(clazz.getClassLoader());
    }
}
