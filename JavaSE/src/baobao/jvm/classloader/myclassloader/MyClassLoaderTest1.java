package baobao.jvm.classloader.myclassloader;

/**
 * @author baobao
 * @create 2020-04-04 22:03
 * @description
 */
public class MyClassLoaderTest1 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader1 = new MyClassLoader("loader1", "C:\\Users\\admin\\Desktop\\");
        Class<?> clazz1 = loader1.loadClass("baobao.jvm.classloader.ClassLoaderTest1");
        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz1.hashCode());

        //将加载的类和自定义类加载器都置为空
        clazz1 = null;
        loader1 = null;
        //调用垃圾回收器
        System.gc();

        /*System.out.println();

        //创建loader2，不指定父加载器，默认父加载器为应用类加载器
        MyClassLoader loader2 = new MyClassLoader("loader2", "C:\\Users\\admin\\Desktop\\");
        Class<?> clazz2 = loader2.loadClass("baobao.jvm.classloader.ClassLoaderTest1");
        System.out.println(clazz2.getClassLoader());
        System.out.println(clazz2.hashCode());*/
    }
}
