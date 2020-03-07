package designmode.strategy;

/**
 * @author baobao
 * @create 2020-03-06 21:09
 * @description 飞翔策略实现，高高飞翔
 */
public class HighFlyStrategy implements FlyStrategy {
    @Override
    public void fly() {
        System.out.println("高高地飞翔");
    }
}
