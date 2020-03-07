package designmode.factory.factorymethod;

/**
 * @author baobao
 * @create 2020-02-22 21:17
 * @description 海鲜披萨工厂
 */
public class SeafoodPizzaFactory extends PizzaFactory {
    @Override
    public Pizza makePizza() {
        SeafoodPizza seafoodPizza = new SeafoodPizza();
        seafoodPizza.prepare();
        seafoodPizza.bake();
        seafoodPizza.cut();
        seafoodPizza.box();
        return seafoodPizza;
    }
}
