package baobao.designmode.singleton;

/**
 * @author baobao
 * @date 2019/7/2 0002 15:39
 * @description 单例模式：懒汉式优化，双重校验锁
 */
public class SingletonLazyDoubleCheckLock {
    private static SingletonLazyDoubleCheckLock instance;

    private SingletonLazyDoubleCheckLock(){}

    public static SingletonLazyDoubleCheckLock getInstance(){
        //一上来先判断对象是否创建，如果已创建就跳过判断锁的步骤，提升效率
        if (instance == null){
            synchronized (SingletonLazyDoubleCheckLock.class){
                if (instance == null){
                    instance = new SingletonLazyDoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
