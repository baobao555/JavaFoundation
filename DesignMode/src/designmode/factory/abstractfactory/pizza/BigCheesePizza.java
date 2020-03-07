package designmode.factory.abstractfactory.pizza;

/**
 * @author baobao
 * @create 2020-02-22 22:02
 * @description 大份奶酪披萨
 */
public class BigCheesePizza extends CheesePizza {
    @Override
    public void prepare() {
        System.out.println("准备大份奶酪披萨的原料");
    }

    @Override
    public void bake() {
        System.out.println("烘焙大份奶酪披萨");
    }

    @Override
    public void cut() {
        System.out.println("将大份奶酪披萨切块");
    }

    @Override
    public void box() {
        System.out.println("打包大份奶酪披萨");
    }
}
