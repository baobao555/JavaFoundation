package designmode.decorator;

/**
 * @author baobao
 * @create 2020-02-27 21:43
 * @description 装饰设计模式
 *
 * 装饰设计模式可以很灵活对被装饰的类进行功能增强：
 * 1、定义装饰类，将被装饰类聚合进来
 * 2、在装饰类中定义和被装饰类某个想要增强的方法名称一样的方法，方法中调用被装饰类的对应方法，然后做额外操作，增强方法的功能
 *
 * 好处：
 * 1、避免继承类爆炸
 * 2、增强方法非常灵活
 */
public class Client {
    public static void main(String[] args) {
        //点一杯无因咖啡，加1份牛奶和1份巧克力
        Drink coffee = new Chocolate(new Milk(new Decaf()));

        System.out.println("总共花费：" + coffee.cost());
    }
}
