package designmode.observer;

/**
 * @author baobao
 * @create 2020-03-05 21:31
 * @description 观察者模式
 *
 * 观察者模式适合一对多的情况，多的一方需要感知一的一方的变化，观察者模式很容易将这种变化通知到多的一方
 *
 * 要素：
 * 1、Subject：抽象主题，即变化的一方，提供注册观察者、移除观察者、通知观察者等方法。需要观察者来订阅变化事件
 * 2、ConcreteSubject：具体的主题实现
 * 3、Observer：抽象的观察者，提供主题变化时通知更新的回调方法
 * 4、ConcreteObserver：具体的观察者实现
 *
 * 举例：
 * 1、ConcreteSubject：WeatherData，天气温度变化的主题，用一个List保存注册进来的Observer，气温变化时调用
 *    Observer的回调方法通知它们
 * 2、ConcreteObserver：BaiduWebsite、TencentWebsite，百度、腾讯网站，被注册到WeatherData，订阅气温变化
 *    一旦气温改变，其回调方法被调用
 */
public class Client {
    public static void main(String[] args) {
        //创建主题
        WeatherData weatherData = new WeatherData();
        //创建观察者
        Observer baiduWebsite = new BaiduWebsite();
        Observer tencentWebsite = new TencentWebsite();
        //注册观察者
        weatherData.registObserver(baiduWebsite);
        weatherData.registObserver(tencentWebsite);

        //改变温度，触发事件，通知到所有观察者
        weatherData.setTemperature(25);

        weatherData.removeObserver(baiduWebsite);

        weatherData.setTemperature(28);
    }
}
