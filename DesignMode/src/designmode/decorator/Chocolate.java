package designmode.decorator;

/**
 * @author baobao
 * @create 2020-02-27 21:43
 * @description
 */
public class Chocolate extends Decorator{
    public Chocolate(Drink coffee) {
        super("巧克力", 3, coffee);
    }
}
