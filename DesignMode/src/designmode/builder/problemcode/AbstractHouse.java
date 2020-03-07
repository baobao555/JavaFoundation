package designmode.builder.problemcode;

/**
 * @author baobao
 * @create 2020-02-24 21:43
 * @description 抽象的房子类，假设建造房子要3个过程：打地基，砌墙，封顶
 */
public abstract class AbstractHouse {
    //打地基
    protected abstract void buildBase();
    //砌墙
    protected abstract void buildWall();
    //封顶
    protected abstract void roofed();

    //按照特定的步骤造房子
    public void build(){
        buildBase();
        buildWall();
        roofed();
    }
}
