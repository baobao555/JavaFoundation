package designmode.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-05 20:18
 * @description 信息学院，以List形式保存专业数据
 */
public class InfoCollege implements College {
    private List<Major> majorList = new ArrayList<>();

    public InfoCollege() {
        addMajor(new Major("通信工程"));
        addMajor(new Major("自动化"));
        addMajor(new Major("电子科学与技术"));
    }

    @Override
    public String getName() {
        return "信息学院";
    }

    @Override
    public void addMajor(Major major) {
        majorList.add(major);
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(majorList);
    }
}
