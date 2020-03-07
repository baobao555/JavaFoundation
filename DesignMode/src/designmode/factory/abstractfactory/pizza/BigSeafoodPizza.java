package designmode.factory.abstractfactory.pizza;

/**
 * @author baobao
 * @create 2020-02-22 22:04
 * @description 大份海鲜披萨
 */
public class BigSeafoodPizza extends SeafoodPizza {
    @Override
    public void prepare() {
        System.out.println("准备大份海鲜披萨的原料");
    }

    @Override
    public void bake() {
        System.out.println("烘焙大份海鲜披萨");
    }

    @Override
    public void cut() {
        System.out.println("将大份海鲜披萨切块");
    }

    @Override
    public void box() {
        System.out.println("打包大份海鲜披萨");
    }
}
