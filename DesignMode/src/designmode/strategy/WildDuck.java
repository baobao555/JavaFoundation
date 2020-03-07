package designmode.strategy;

/**
 * @author baobao
 * @create 2020-03-06 21:12
 * @description 野鸭，可以高高飞翔
 */
public class WildDuck extends Duck {
    public WildDuck() {
        super("野鸭", new HighFlyStrategy());
    }
}
