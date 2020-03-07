package designmode.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-05 20:23
 * @description 迭代器模式
 *
 * 迭代器模式用于遍历对象内部的数据的统一方式，而不暴露内部数据的保存形式
 *
 * 迭代器模式要素：
 * 1、Iterator：迭代器顶层接口，提供hasNext、next等迭代方法
 * 2、ConcreteIterator：具体的迭代器，保存了需要迭代数据的引用，根据数据的保存类型进行迭代的实现
 * 3、Aggregate：统一的聚合接口，提供创建迭代器的方法
 * 4、ConcreteAggregate：具体的Aggregate实现，保存需要迭代的数据，创建迭代器时将底层数据引用传给迭代器，
 *    迭代器进而根据其数据的形式实现具体迭代操作
 * 5、Client：客户端，通过ConcreteAggregate的方法得到Iterator，进行迭代操作
 *
 * 案例：
 * 迭代学院下面的所有专业进行输出
 * 1、Aggregate：College接口
 * 2、ConcreteAggregate：具体的学院，InfoCollege(以List形式保存专业数据)，ComputerCollege(以数组形式保存专业数据)
 * 3、ConcreteIterator：InfoCollegeIterator(迭代List)，ComputerCollegeIterator(迭代数组)
 */
public class Client {
    public static void main(String[] args) {
        ArrayList<College> colleges = new ArrayList<>();
        colleges.add(new InfoCollege());
        colleges.add(new CompterCollege());

        print(colleges);
    }

    //给定学院列表，通过遍历每个学院，利用其迭代器迭代输出学院的每个专业
    private static void print(List<College> collegeList){
        for (College college : collegeList) {
            System.out.println("----------------" + college.getName() + "----------------");
            Iterator iterator = college.createIterator();
            while (iterator.hasNext()){
                Major major = (Major)iterator.next();
                System.out.println(major.getName());
            }
        }
    }
}
