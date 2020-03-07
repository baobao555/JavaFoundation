package designmode.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-05 20:10
 * @description 信息学院迭代器
 */
public class InfoCollegeIterator implements Iterator {
    //信息学院以List形式保存专业数据
    private List<Major> majorList;
    private int index = 0;

    //构造方法传入List，后续根据List进行迭代
    public InfoCollegeIterator(List<Major> majorList) {
        this.majorList = majorList;
    }

    @Override
    public boolean hasNext() {
        return index < majorList.size();
    }

    @Override
    public Object next() {
        return majorList.get(index++);
    }
}
