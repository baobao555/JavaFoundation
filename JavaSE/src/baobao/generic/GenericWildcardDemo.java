package baobao.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-18 21:47
 * @description 演示泛型的通配符
 *
 * ? ：代表所有类型
 * ? extends Class：代表Class及其所有子类，也叫上限通配符(上限是Class)
 * ? super Class：代表Class及其所有父类，也叫下限通配符(下限是Class)
 *
 * 注意通配符?和泛型<T>的区别：
 * 它们都代表任何类型，但是<T>是在定义泛型时使用，?是在给泛型传入具体类型时使用
 */
public class GenericWildcardDemo {
    public static void main(String[] args) {
        ArrayList<Audi> list1 = new ArrayList<>();
        list1.add(new Audi());

        ArrayList<BMW> list2 = new ArrayList<>();
        list2.add(new BMW());

        ArrayList<Dog> list3 = new ArrayList<>();
        list3.add(new Dog());

        run(list1);
        run(list2);
        //run(list3);
    }

    private static void run(List<? extends Car> list){
    }
}

class Car{
}

class Audi extends Car{
}

class BMW extends Car{
}

class Dog{
}
