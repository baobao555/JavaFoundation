package designmode.factory.simplefactory;

/**
 * @author baobao
 * @create 2020-02-22 20:24
 * @description 披萨的简单工厂(静态方法工厂)
 *
 * 静态方法Pizza makePizza(String pizzaKind)：
 * 根据传入的参数判断需要生产那种类型的披萨
 */
public class PizzaFactory {
    public static Pizza makePizza(String pizzaKind){
        if ("cheese".equals(pizzaKind)){
            CheesePizza cheesePizza = new CheesePizza();
            cheesePizza.prepare();
            cheesePizza.bake();
            cheesePizza.cut();
            cheesePizza.box();
            return cheesePizza;
        }else if ("seafood".equals(pizzaKind)){
            SeafoodPizza seafoodPizza = new SeafoodPizza();
            seafoodPizza.prepare();
            seafoodPizza.bake();
            seafoodPizza.cut();
            seafoodPizza.box();
            return seafoodPizza;
        }else {
            return null;
        }
    }
}
