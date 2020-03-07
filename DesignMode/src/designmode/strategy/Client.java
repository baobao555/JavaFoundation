package designmode.strategy;

/**
 * @author baobao
 * @create 2020-03-06 21:16
 * @description 策略模式
 *
 * 策略模式可以让类通过聚合引入策略接口，根据接口的不同实现展现不同的行为，核心思想是多用聚合/组合，少用继承。
 * 比如实现鸭子类，如果在抽象的Duck类中事先实现了fly方法，而玩具鸭，北京鸭都继承Duck类，但是它们又不能飞，这时
 * 就必须重写fly方法表现不能飞的行为。但是如果用策略模式，把飞的行为定义成Duck类中的一个接口FlyStrategy，fly方法
 * 调用这个接口即可。而FlyStrategy可以有多种实现，在定义北京鸭，玩具鸭时，将对应的不能飞的策略实现NoFlyStrategy赋值
 * 给父类Duck的FlyStrategy接口，定义野鸭时将对应的高高飞翔策略实现HighFlyStrategy赋值给父类DuckFlyStrategy接口。
 * 这样子类调用fly方法就会展现不同行为，无需重写父类fly方法
 *
 */
public class Client {
    public static void main(String[] args) {
        WildDuck wildDuck = new WildDuck();
        ToyDuck toyDuck = new ToyDuck();
        wildDuck.fly();
        toyDuck.fly();
    }
}
