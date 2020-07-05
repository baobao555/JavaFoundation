package designmode.singleton;

/**
 * @author baobao
 * @date 2019/7/2 0002 15:54
 * @description 单例模式：静态内部类实现
 */
public class SingletonStaticInnerClass {
    private SingletonStaticInnerClass(){
        System.out.println("单例对象创建");
    }

    //定义静态内部类，包含一个静态的单例对象
    private static class StaticInnerClass{
        static {
            System.out.println("StaticInnerClass初始化");
        }
        private static SingletonStaticInnerClass instance = new SingletonStaticInnerClass();
    }

    public static SingletonStaticInnerClass getInstance(){
        //调用此方法时加载静态内部类(加载时能保证线程安全)，初始化单例对象，实现延迟加载
        return StaticInnerClass.instance;
    }

    public static void otherStaticMethod(){}
}
