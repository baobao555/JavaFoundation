package designmode.factory.factorymethod;

/**
 * @author baobao
 * @create 2020-02-22 21:17
 * @description 奶酪披萨工厂
 */
public class CheesePizzaFactory extends PizzaFactory {
    @Override
    public Pizza makePizza() {
        CheesePizza cheesePizza = new CheesePizza();
        cheesePizza.prepare();
        cheesePizza.bake();
        cheesePizza.cut();
        cheesePizza.box();
        return cheesePizza;
    }
}
