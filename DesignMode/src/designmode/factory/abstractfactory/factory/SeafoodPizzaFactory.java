package designmode.factory.abstractfactory.factory;

import designmode.factory.abstractfactory.pizza.*;
import designmode.factory.factorymethod.SeafoodPizza;

/**
 * @author baobao
 * @create 2020-02-22 21:17
 * @description 海鲜披萨工厂
 */
public class SeafoodPizzaFactory extends PizzaFactory {
    //生产大份海鲜披萨
    @Override
    public designmode.factory.abstractfactory.pizza.Pizza makeBigPizza() {
        BigSeafoodPizza cheesePizza = new BigSeafoodPizza();
        cheesePizza.prepare();
        cheesePizza.bake();
        cheesePizza.cut();
        cheesePizza.box();
        return cheesePizza;
    }

    //生产小份海鲜披萨
    @Override
    public Pizza makeSmallPizza() {
        SmallSeafoodPizza cheesePizza = new SmallSeafoodPizza();
        cheesePizza.prepare();
        cheesePizza.bake();
        cheesePizza.cut();
        cheesePizza.box();
        return cheesePizza;
    }
}
