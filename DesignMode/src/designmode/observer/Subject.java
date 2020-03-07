package designmode.observer;

/**
 * @author baobao
 * @create 2020-03-05 21:23
 * @description 主题，需要被订阅
 */
public interface Subject {
    //注册观察者
    void registObserver(Observer observer);

    //移除观察者
    void removeObserver(Observer observer);

    //主题触发事件时通知所有观察者
    void notifyObservers();
}
