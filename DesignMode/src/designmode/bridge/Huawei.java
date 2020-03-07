package designmode.bridge;

/**
 * @author baobao
 * @create 2020-02-27 21:01
 * @description 华为手机
 */
public class Huawei implements Brand {
    @Override
    public void open() {
        System.out.println("华为手机开机");
    }

    @Override
    public void call() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void close() {
        System.out.println("华为手机关机");
    }
}
