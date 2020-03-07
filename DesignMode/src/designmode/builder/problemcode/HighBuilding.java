package designmode.builder.problemcode;

/**
 * @author baobao
 * @create 2020-02-24 21:47
 * @description 高楼
 */
public class HighBuilding extends AbstractHouse{
    @Override
    protected void buildBase() {
        System.out.println("高楼打地基");
    }

    @Override
    protected void buildWall() {
        System.out.println("高楼砌墙");
    }

    @Override
    protected void roofed() {
        System.out.println("高楼封顶");
    }
}
