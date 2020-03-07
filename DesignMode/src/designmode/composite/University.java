package designmode.composite;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baobao
 * @create 2020-02-27 22:04
 * @description 学校节点
 */
public class University extends OrganizationComponent {
    //创建List保存学院
    private List<OrganizationComponent> collegeList = new ArrayList<>();

    public University(String name) {
        super(name);
    }


    //添加学院
    @Override
    protected void add(OrganizationComponent component) {
        collegeList.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        collegeList.remove(component);
    }

    //遍历打印所有学院
    @Override
    protected void print() {
        System.out.println("==================" + this.getName() + "========================");
        for (OrganizationComponent component : collegeList) {
            component.print();
        }
    }
}
