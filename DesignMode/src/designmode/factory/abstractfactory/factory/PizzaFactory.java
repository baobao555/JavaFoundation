package designmode.factory.abstractfactory.factory;


import designmode.factory.abstractfactory.pizza.Pizza;

/**
 * @author baobao
 * @create 2020-02-22 20:24
 * @description 披萨的工厂抽象类
 *
 * 抽象方法Pizza makeBigPizza();Pizza makeSmallPizza();
 * 交给具体的工厂去生产相应类型的披萨，将披萨的创建延迟到子类工厂中
 */
public abstract class PizzaFactory {
    //生产大份披萨
    public abstract Pizza makeBigPizza();
    //生产小份披萨
    public abstract Pizza makeSmallPizza();
}
