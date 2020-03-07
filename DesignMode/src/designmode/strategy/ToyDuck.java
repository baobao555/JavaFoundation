package designmode.strategy;

/**
 * @author baobao
 * @create 2020-03-06 21:13
 * @description 玩具鸭，不能飞翔
 */
public class ToyDuck extends Duck {
    public ToyDuck() {
        super("玩具鸭", new NoFlyStrategy());
    }
}
