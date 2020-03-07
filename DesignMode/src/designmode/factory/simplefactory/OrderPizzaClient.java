package designmode.factory.simplefactory;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @author baobao
 * @create 2020-02-22 20:27
 * @description 订购披萨的客户端
 *
 * 客户端不需要依赖披萨的实际类型CheesePizza或SeafoodPizza，只需要通过PizzaFactory
 * 传入不同参数来获取不同类型的披萨。如果后期需要新增披萨类型，直接修改PizzaFactory即可，
 * 客户端不需要做任何改动。
 *
 * 简单工厂使用步骤：
 * 1、创建抽象产品类(Pizza)
 * 2、创建继承抽象产品类的具体产品类(CheesePizza/SeafoodPizza)
 * 3、创建工厂类(PizzaFactory)，声明一个静态方法，根据传入不同参数从而创建不同具体产品类的实例
 * 4、客户端(OrderPizzaClient)通过调用工厂类的静态方法，传入不同参数从而创建不同具体产品类的实例
 *

 *
 * 简单工厂优点：
 * 1、将创建实例的工作与使用实例的工作分开，使用者不必关心类对象如何创建，实现了解耦；
 * 2、把初始化实例时的工作放到工厂里进行，使代码更容易维护。 更符合面向对象的原则 &
 *    面向接口编程，而不是面向实现编程。
 *
 * 简单工厂缺点：
 * 1、工厂类集中了所有实例（产品）的创建逻辑，一旦这个工厂不能正常工作，整个系统都会受到影响；
 * 2、违背开闭原则，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂。
 * 3、简单工厂模式由于使用了静态工厂方法，静态方法不能被继承和重写，会造成工厂角色无法形成基于继承的等级结构。
 *
 *
 * 简单工厂使用场景：
 * 1、客户如果只知道传入工厂类的参数，对于如何创建对象的逻辑不关心时；
 * 2、当工厂类负责创建的对象（具体产品）比较少时。
 *
 */
public class OrderPizzaClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入你要订购的披萨类型：");
            String pizzaKind = scanner.next();
            Pizza pizza = PizzaFactory.makePizza(pizzaKind);
            if (pizza == null){
                break;
            }
        }
    }
}
