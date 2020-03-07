package designmode.bridge;

/**
 * @author baobao
 * @create 2020-02-27 21:03
 * @description 折叠手机实现
 */
public class FoldPhone extends Phone {
    public FoldPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        System.out.println("折叠手机");
        super.open();
    }

    @Override
    public void call() {
        System.out.println("折叠手机");
        super.call();
    }

    @Override
    public void close() {
        System.out.println("折叠手机");
        super.close();
    }
}
