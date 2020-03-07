package designmode.builder.problemcode;

/**
 * @author baobao
 * @create 2020-02-24 21:46
 * @description 普通房子
 */
public class CommonHouse extends AbstractHouse {
    @Override
    protected void buildBase() {
        System.out.println("普通房子打地基");
    }

    @Override
    protected void buildWall() {
        System.out.println("普通房子砌墙");
    }

    @Override
    protected void roofed() {
        System.out.println("普通房子封顶");
    }
}
