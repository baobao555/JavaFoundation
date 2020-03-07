package designmode.factory.abstractfactory.factory;

import designmode.factory.abstractfactory.pizza.BigCheesePizza;
import designmode.factory.abstractfactory.pizza.Pizza;
import designmode.factory.abstractfactory.pizza.SmallCheesePizza;


/**
 * @author baobao
 * @create 2020-02-22 21:17
 * @description 奶酪披萨工厂
 */
public class CheesePizzaFactory extends PizzaFactory {

    //生产大份奶酪披萨
    @Override
    public Pizza makeBigPizza() {
        BigCheesePizza cheesePizza = new BigCheesePizza();
        cheesePizza.prepare();
        cheesePizza.bake();
        cheesePizza.cut();
        cheesePizza.box();
        return cheesePizza;
    }

    //生产小份奶酪披萨
    @Override
    public Pizza makeSmallPizza() {
        SmallCheesePizza cheesePizza = new SmallCheesePizza();
        cheesePizza.prepare();
        cheesePizza.bake();
        cheesePizza.cut();
        cheesePizza.box();
        return cheesePizza;
    }
}
