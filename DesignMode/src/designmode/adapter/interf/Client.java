package designmode.adapter.interf;

/**
 * @author baobao
 * @create 2020-02-27 20:50
 * @description 接口适配器模式
 *
 * 接口适配器模式使用场景：
 * 一个接口有很多方法，我们想使用接口的部分方法，但是不关心剩余其他方法时，可以先定义一个Adapter适配器，
 * 把接口的所有方法做空实现，这样我们使用接口时只要创建Adapter，实现我们关心的方法，而无需实现所有方法。
 */
public class Client {
    public static void main(String[] args) {
        Inter inter = new AbstarctAdapter();
    }
}
