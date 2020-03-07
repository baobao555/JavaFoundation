package designmode.bridge;

/**
 * @author baobao
 * @create 2020-02-27 21:05
 * @description 直屏手机实现
 */
public class UpRightPhone extends Phone {
    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        System.out.println("直屏手机");
        super.open();
    }

    @Override
    public void call() {
        System.out.println("直屏手机");
        super.call();
    }

    @Override
    public void close() {
        System.out.println("直屏手机");
        super.close();
    }
}
