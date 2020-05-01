package baobao.jvm.classloader.myclassloader;

/**
 * @author baobao
 * @create 2020-04-04 22:03
 * @description
 */
public class MyClassLoaderTest2 {
    public static void main(String[] args) throws Exception {
        //创建loader1，父加载器为应用类加载器
        MyClassLoader loader1 = new MyClassLoader("loader1", "C:\\Users\\admin\\Desktop\\");
        //加载Animal
        Class<?> clazz = loader1.loadClass("baobao.jvm.classloader.myclassloader.Animal");
        //打印clazz的hashcode
        System.out.println("clazz hashcode:" + clazz.hashCode());
        //创建Animal实例，Animal构造方法会创建Cat实例，主动使用Cat，导致Cat也要被加载并初始化
        Object instance = clazz.newInstance();
    }
}
