package designmode.singleton;

/**
 * @author baobao
 * @date 2019/7/2 0002 15:13
 * @description 单例模式：饿汉式
 */
//饿汉式，加载类的时候马上创建对象，没有线程安全问题
public class SingletonHurry {
    static {
        System.out.println("SingletonHurry初始化");
    }
    private static SingletonHurry instance = new SingletonHurry();
    private SingletonHurry(){
        System.out.println("单例对象创建");
    }
    public static SingletonHurry getInstance(){
        return instance;
    }

    public static void otherStaticMethod(){}
}
