package designmode.builder.correct;

/**
 * @author baobao
 * @create 2020-02-24 22:00
 * @description 具体建造者，高楼
 */
public class HighBuildingBuilder extends AbstractHouseBuilder {
    @Override
    public void buildBase() {
        System.out.println("高楼打地基");
    }

    @Override
    public void buildWall() {
        System.out.println("高楼砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("高楼封顶");
    }
}
