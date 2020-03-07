package designmode.factory.simplefactory;

/**
 * @author baobao
 * @create 2020-02-22 19:49
 * @description 奶酪披萨
 */


public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备奶酪披萨的原料");
    }

    @Override
    public void bake() {
        System.out.println("烘焙奶酪披萨");
    }

    @Override
    public void cut() {
        System.out.println("将奶酪披萨切块");
    }

    @Override
    public void box() {
        System.out.println("打包奶酪披萨");
    }
}
