package designmode.builder.correct;

/**
 * @author baobao
 * @create 2020-02-24 21:55
 * @description 抽象的建造者
 */
public abstract class AbstractHouseBuilder {
    protected House house = new House();

    public abstract void buildBase();

    public abstract void buildWall();

    public abstract void roofed();

    public House build(){
        return house;
    }
}
