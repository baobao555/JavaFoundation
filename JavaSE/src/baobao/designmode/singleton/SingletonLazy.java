package baobao.designmode.singleton;

/**
 * @author baobao
 * @date 2019/7/2 0002 15:21
 * @description 单例模式：懒汉式
 */
public class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy(){}

    //懒汉式，等要用的时候再创建对象，为了线程安全需要判断锁，故效率较低
    public static SingletonLazy getInstance(){
        synchronized (SingletonLazy.class)
        {
            if (instance == null){
                instance = new SingletonLazy();
            }
        }
        return instance;
    }
}
