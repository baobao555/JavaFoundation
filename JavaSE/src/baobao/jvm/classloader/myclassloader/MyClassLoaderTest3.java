package baobao.jvm.classloader.myclassloader;

import java.lang.reflect.Method;

/**
 * @author baobao
 * @create 2020-04-04 22:03
 * @description
 */
public class MyClassLoaderTest3 {
    public static void main(String[] args) throws Exception {
        //创建loader1和loader2，父加载器为应用类加载器
        MyClassLoader loader1 = new MyClassLoader("loader1", "C:\\Users\\admin\\Desktop\\");
        MyClassLoader loader2 = new MyClassLoader("loader2", "C:\\Users\\admin\\Desktop\\");
        //loader1和loader2分别加载Person
        Class<?> clazz1 = loader1.loadClass("baobao.jvm.classloader.myclassloader.Person");
        Class<?> clazz2 = loader2.loadClass("baobao.jvm.classloader.myclassloader.Person");
        System.out.println(clazz1 == clazz2);

        //反射创建clazz1和clazz2的实例对象
        Object person1 = clazz1.newInstance();
        Object person2 = clazz2.newInstance();

        //反射得到clazz1的setPerson方法
        Method method = clazz1.getMethod("setPerson", Object.class);
        //调用person1对象的setPerson方法，传入person2
        method.invoke(person1, person2);
    }
}
