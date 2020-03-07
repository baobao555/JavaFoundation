package designmode.strategy;

/**
 * @author baobao
 * @create 2020-03-06 21:10
 * @description 飞翔策略实现，不能飞翔
 */
public class NoFlyStrategy implements FlyStrategy {
    @Override
    public void fly() {
        System.out.println("不能飞翔");
    }
}
