package designmode.iterator;

import java.util.Iterator;

/**
 * @author baobao
 * @create 2020-03-05 20:03
 * @description 计算机学院迭代器
 */
public class ComputerCollegeIterator implements Iterator {
    //计算机学院是以数组形式保存专业数据
    private Major[] majors;
    private int index = 0;//遍历的索引

    //构造方法传入数组形式的数据，后续进行数组迭代
    public ComputerCollegeIterator(Major[] majors) {
        this.majors = majors;
    }

    @Override
    public boolean hasNext() {
        if (majors[index] == null || index >= majors.length){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        return majors[index++];
    }
}
