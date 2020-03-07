package designmode.observer;

/**
 * @author baobao
 * @create 2020-03-05 21:22
 * @description 具体的观察者，百度网站
 */
public class BaiduWebsite implements Observer {
    //接收到温度更新后打印
    @Override
    public void update(int temperature) {
        System.out.println("百度网站气温更新，当前气温：" + temperature);
    }
}
