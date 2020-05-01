package baobao.java8.lambda.functionalinterface;

/**
 * @author baobao
 * @create 2020-03-07 22:08
 * @description
 */
@FunctionalInterface//加了函数式接口注解，如果接口中并非只有1个待子类实现的抽象方法，编译器就会报错
public interface MyFunctionalInterface {
    void test(); //待实现的抽象方法

    //String myString(); //不能加第2个抽象方法，否则就不是函数式接口了

    String toString(); //可以加Object类的public方法，此时不会算作1个待实现的抽象方法。
                       //因为这个接口的实现类一定是Object的子类，所以它自然就会继承了Object的toString方法实现，
                       //这样它就可以无需实现这个方法，所以不算做1个待实现的抽象方法。
}
