package designmode.factory.factorymethod;

/**
 * @author baobao
 * @create 2020-02-22 19:52
 * @description 海鲜披萨
 */
public class SeafoodPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备海鲜披萨的原料");
    }

    @Override
    public void bake() {
        System.out.println("烘焙海鲜披萨");
    }

    @Override
    public void cut() {
        System.out.println("将海鲜披萨切块");
    }

    @Override
    public void box() {
        System.out.println("打包海鲜披萨");
    }
}
