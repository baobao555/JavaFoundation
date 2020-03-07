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
                    //由于new SingletonLazyDoubleCheckLock()不是一个原子操作，要分好几步：1、创建对象 2、初始化 3、赋值给变量
                    //所以instance如果不是volatile，那么当instance已经不是null的时候，由于指令重排，
                    //instance可能还没有完成初始化，这个时候返回instance是不正确的
                    instance = new SingletonLazyDoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
