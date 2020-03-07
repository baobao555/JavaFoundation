package designmode.factory.abstractfactory;

import designmode.factory.abstractfactory.factory.CheesePizzaFactory;
import designmode.factory.abstractfactory.factory.SeafoodPizzaFactory;

/**
 * @author baobao
 * @create 2020-02-22 22:12
 * @description 抽象工厂模式
 *
 * 抽象工厂模式与工厂方法模式最大的区别：抽象工厂中每个工厂可以创建多种类的产品；而工厂方法每个工厂只能创建一类
 *
 * 抽象工厂模式使用步骤：
 * 步骤1： 创建抽象工厂类(PizzaFactory)，定义具体工厂的公共接口；
 * 步骤2： 创建抽象产品族类(Pizza)，定义抽象产品的公共接口；
 * 步骤3： 创建继承抽象产品族类的抽象产品类 (CheesePizza/SeafoodPizza)，定义具体产品的公共接口；
 * 步骤4： 创建继承抽象产品类的具体产品类(BigCheesePizza/SmallCheesePizza/BigSeafoodPizza/SmallSeafoodPizza) & 定义生产的具体产品；
 * 步骤5：创建继承抽象工厂类的具体工厂类(CheesePizzaFactory/SeafoodPizzaFactory)，定义创建对应具体产品实例的方法(makeBigPizza/makeSmallPizza)；
 * 步骤6：客户端通过实例化具体的工厂类，并调用其创建不同目标产品的方法(makeBigPizza/makeSmallPizza)创建不同具体产品类的实例
 *
 *
 * 抽象工厂模式优点：
 * 1、降低耦合
 * 2、更符合开-闭原则：新增一种产品类时，只需要增加相应的具体产品类和相应的工厂子类即可
 *
 * 抽象工厂模式缺点：
 * 抽象工厂模式很难支持新种类产品的变化：
 * 这是因为抽象工厂接口中已经确定了可以被创建的产品集合，如果需要添加新产品，此时就必须去修改抽象工厂的接口，
 * 这样就涉及到抽象工厂类的以及所有子类的改变，这样也就违背了开闭原则。
 *
 */
public class OrderPizzaClient {
    public static void main(String[] args) {
        CheesePizzaFactory cheesePizzaFactory = new CheesePizzaFactory();
        SeafoodPizzaFactory seafoodPizzaFactory = new SeafoodPizzaFactory();
        cheesePizzaFactory.makeBigPizza();
        cheesePizzaFactory.makeSmallPizza();
        seafoodPizzaFactory.makeBigPizza();
        seafoodPizzaFactory.makeSmallPizza();
    }
}
