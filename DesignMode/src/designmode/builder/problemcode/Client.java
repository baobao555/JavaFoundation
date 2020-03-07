package designmode.builder.problemcode;

/**
 * @author baobao
 * @create 2020-02-24 21:48
 * @description 这种传统方式制造对象过程简单，但是把建造过程都打包在build一个方法中，其中的一些过程没有对外暴露。
 *              这样的话耦合性太强，不灵活，我们像自己定制一些build过程中的属性不方便。
 */
public class Client {
    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        commonHouse.build();
        HighBuilding highBuilding = new HighBuilding();
        highBuilding.build();
    }
}
