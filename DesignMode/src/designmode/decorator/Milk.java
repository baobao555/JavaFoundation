package designmode.decorator;

/**
 * @author baobao
 * @create 2020-02-27 21:40
 * @description
 */
public class Milk extends Decorator {
    public Milk(Drink coffee) {
        super("牛奶", 2, coffee);
    }
}
