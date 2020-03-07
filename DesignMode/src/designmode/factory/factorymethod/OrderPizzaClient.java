package designmode.factory.factorymethod;

import designmode.factory.simplefactory.Pizza;
import designmode.factory.simplefactory.PizzaFactory;

import java.util.Scanner;

/**
 * @author baobao
 * @create 2020-02-22 20:27
 * @description 订购披萨的客户端
 *
 * 工厂方法模式将具体产品的创建延迟到工厂类的子类（具体工厂）中完成，
 * 即由子类来决定应该实例化（创建）哪一个类。
 * 工厂方法模式新增产品时只需要增加抽象工厂的子类即可，解决了简单工厂增加产品需要修改工厂类内部代码(违反开闭原则)的问题
 *
 * 工厂方法使用步骤：
 * 步骤1： 创建抽象工厂类(PizzaFactory)，定义具体工厂的公共接口；
 * 步骤2： 创建抽象产品类(Pizza)，定义具体产品的公共接口；
 * 步骤3： 创建具体产品类（继承抽象产品类）,定义生产的具体产品(CheesePizza/SeafoodPizza)；
 * 步骤4：创建继承抽象工厂类的具体工厂类（CheesePizzaFactory/SeafoodPizzaFactory），定义创建对应具体产品实例的方法；
 * 步骤5：客户端通过调用具体工厂类的方法，从而创建不同具体产品类的实例
 *
 *
 * 工厂方法优点：
 * 1、更符合开-闭原则，新增一种产品时，只需要增加相应的具体产品类和相应的工厂子类即可
 * 2、符合单一职责原则，每个具体工厂类只负责创建对应的产品
 * 3、不使用静态工厂方法，可以形成基于继承的等级结构
 *
 * 工厂方法缺点：
 * 1、添加新产品时，除了增加新产品类外，还要提供与之对应的具体工厂类，系统类的个数将成对增加
 * 2、由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义
 * 3、虽然保证了工厂方法内部的对修改关闭，但对于客户端来说，如果要更换另外一种产品，仍然需要修改代码增加具体的工厂类
 * 4、一个具体工厂只能创建一种具体产品
 *
 *
 * 工厂方法使用场景：
 * 1、当一个类不知道它所需要的对象的类时：
 * 在工厂方法模式中，客户端不需要知道具体产品类的类名，只需要知道所对应的工厂即可
 * 2、将创建对象的任务委托给多个工厂子类中的某一个，客户端在使用时可以无须关心是哪一个工厂子类创建产品子类，需要时再动态指定，
 * 可将具体工厂类的类名存储在配置文件或数据库中
 *
 */
public class OrderPizzaClient {
    public static void main(String[] args) {
        //调用对应的工厂生产对象
        CheesePizzaFactory cheesePizzaFactory = new CheesePizzaFactory();
        SeafoodPizzaFactory seafoodPizzaFactory = new SeafoodPizzaFactory();
        cheesePizzaFactory.makePizza();
        seafoodPizzaFactory.makePizza();
    }
}
