package designmode.decorator;

/**
 * @author baobao
 * @create 2020-02-27 21:38
 * @description
 */
public class Decaf extends Coffee {

    public Decaf() {
        super("无因咖啡", 5);
    }

    @Override
    public int cost() {
        return getCost();
    }
}
