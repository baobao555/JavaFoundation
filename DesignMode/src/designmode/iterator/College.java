package designmode.iterator;

import java.util.Iterator;

/**
 * @author baobao
 * @create 2020-03-05 19:55
 * @description 抽象学院
 */
public interface College {
    String getName();

    //增加一个专业
    void addMajor(Major major);

    //获取学院的迭代器
    Iterator createIterator();
}
