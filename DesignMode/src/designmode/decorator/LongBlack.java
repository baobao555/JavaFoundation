package designmode.decorator;

/**
 * @author baobao
 * @create 2020-02-27 21:34
 * @description
 */
public class LongBlack extends Coffee {

    public LongBlack() {
        super("美式咖啡", 10);
    }

    @Override
    public int cost() {
        return getCost();
    }
}
