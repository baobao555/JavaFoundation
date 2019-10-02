package designmode.singleton;

/**
 * @author baobao
 * @date 2019/7/2 0002 15:39
 * @description 单例模式：懒汉式优化，双重校验锁
 */

//DCL（双端检锁）机制不一定线程安全，原因是有指令重排序的存在，加入volatile可以禁止指令重排。
//原因在于某一个线程执行到第一个检测，读取到的instance不为null时，instance的引用对象
//可能没有完成初始化
public class SingletonLazyDoubleCheckLock {
    //为了规避指令重排在多线程执行中的不确定性，需要加volatile关键字禁用指令重排
    private volatile static SingletonLazyDoubleCheckLock instance;

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
