package designmode.bridge;

/**
 * @author baobao
 * @create 2020-02-27 21:00
 * @description 小米手机
 */
public class Xiaomi implements Brand {
    @Override
    public void open() {
        System.out.println("小米手机开机");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void close() {
        System.out.println("小米手机关机");
    }
}
