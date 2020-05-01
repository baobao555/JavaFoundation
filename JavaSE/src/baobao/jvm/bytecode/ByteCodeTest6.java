package baobao.jvm.bytecode;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @author baobao
 * @create 2020-04-14 21:52
 * @description
 */
public class ByteCodeTest6 {
    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit orange = new Orange();
        apple.test();
        orange.test();

        apple = new Orange();
        apple.test();
    }
}

class Fruit{
    public void test(){
        System.out.println("fruit");
    }
}

class Apple extends Fruit{
    @Override
    public void test() {
        System.out.println("apple");
    }
}

class Orange extends Fruit{
    @Override
    public void test() {
        System.out.println("orange");
    }
}
