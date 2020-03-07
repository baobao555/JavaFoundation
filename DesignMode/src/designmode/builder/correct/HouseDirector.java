package designmode.builder.correct;

/**
 * @author baobao
 * @create 2020-02-24 22:01
 * @description 房子建造指挥者
 */
public class HouseDirector {
    //指挥者持有建造者
    private AbstractHouseBuilder houseBuilder;

    public HouseDirector(AbstractHouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //如何建造房子交给指挥者
    public House buildHouse(){
        houseBuilder.buildBase();
        houseBuilder.buildWall();
        houseBuilder.roofed();
        return houseBuilder.build();
    }
}
