package designmode.observer;

/**
 * @author baobao
 * @create 2020-03-05 21:20
 * @description 抽象的观察者
 */
public interface Observer {
    //温度更新通知
    void update(int temperature);
}
