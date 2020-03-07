package designmode.builder.correct;

/**
 * @author baobao
 * @create 2020-02-24 21:59
 * @description 具体建造者，普通房子
 */
public class CommonHouseBuilder extends AbstractHouseBuilder {
    @Override
    public void buildBase() {
        System.out.println("普通房子打地基");
    }

    @Override
    public void buildWall() {
        System.out.println("普通房子砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("普通房子封顶");
    }
}
