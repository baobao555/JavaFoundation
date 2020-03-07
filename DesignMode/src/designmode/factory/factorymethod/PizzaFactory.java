package designmode.factory.factorymethod;

/**
 * @author baobao
 * @create 2020-02-22 20:24
 * @description 披萨的工厂抽象类
 *
 * 抽象方法Pizza makePizza();
 * 交给具体的工厂去生产相应类型的披萨，将披萨的创建延迟到子类工厂中
 */
public abstract class PizzaFactory {
    public abstract Pizza makePizza();
}
