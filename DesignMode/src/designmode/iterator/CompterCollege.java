package designmode.iterator;

import java.util.Iterator;

/**
 * @author baobao
 * @create 2020-03-05 19:58
 * @description 计算机学院，以数组形式保存专业数据
 */
public class CompterCollege implements College {
    private Major[] majors = new Major[5];
    private int majorNum = 0;

    @Override
    public String getName() {
        return "计算机学院";
    }

    public CompterCollege() {
        addMajor(new Major("java"));
        addMajor(new Major("c"));
        addMajor(new Major("大数据"));
    }

    //增加专业
    @Override
    public void addMajor(Major major) {
        majors[majorNum++] = major;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(majors);
    }
}
