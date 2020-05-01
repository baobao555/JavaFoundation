package baobao.jvm.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author baobao
 * @create 2020-04-08 23:31
 * @description
 */
public class ServiceLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        System.out.println(clazz);
        //加载Driver
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        //获取迭代器
        Iterator<Driver> iterator = serviceLoader.iterator();
        //遍历所有定位到的Driver接口的实现类
        while (iterator.hasNext()){
            Driver driver = iterator.next();
            //打印实现类的类型和加载它的类加载器
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }

        System.out.println("当前线程上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("加载ServiceLoader的类加载器：" + ServiceLoader.class.getClassLoader());
    }
}
