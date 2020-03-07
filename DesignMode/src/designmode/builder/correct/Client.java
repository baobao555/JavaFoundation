package designmode.builder.correct;

/**
 * @author baobao
 * @create 2020-02-24 22:04
 * @description 演示建造者模式
 *
 * 建造者模式好处是可以让客户端定制一些产品的属性，一定程度上干预建造对象的流程，而不像传统方式一下就完成对象创建并返回，
 * 但是其中细节完全不暴露。对于一些复杂对象的创建可以使用建造者模式
 *
 * 建造者模式的4个角色：
 * 1、Product产品
 * 2、AbstractBuilder抽象建造者
 * 3、ConcreteBuilder具体的建造者
 * 4、Director指挥者，指挥建造者来如何创建产品对象
 *
 * 实际开发中很多情况下都会省略指挥者这个角色，直接在客户端调用具体建造者设置属性并创建对象，连缀调用builder.setxxx.setxxx.build()
 */
public class Client {
    public static void main(String[] args) {
        HouseDirector houseDirector = new HouseDirector(new CommonHouseBuilder());
        House house = houseDirector.buildHouse();
    }
}
