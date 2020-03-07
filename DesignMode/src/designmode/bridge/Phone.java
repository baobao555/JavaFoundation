package designmode.bridge;

/**
 * @author baobao
 * @create 2020-02-27 21:01
 * @description 手机类，持有Brand接口，即某种品牌的手机
 */
public class Phone {
    private Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    public void open(){
        brand.open();
    }

    public void call(){
        brand.call();
    }

    public void close(){
        brand.close();
    }
}
