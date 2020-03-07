package designmode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-05 21:24
 * @description 具体的一个主题，天气数据
 */
public class WeatherData implements Subject {
    //保存所有订阅了温度变化事件的观察者
    private List<Observer> observers = new ArrayList<>();
    //当前温度
    private int temperature;

    //更新温度
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        //通知所有观察者
        notifyObservers();
    }

    //注册一个观察者
    @Override
    public void registObserver(Observer observer) {
        observers.add(observer);
    }

    //移除一个观察者
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    //温度变化时通知所有观察者，并把更新后的温度传过去
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}
