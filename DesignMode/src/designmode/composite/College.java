package designmode.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baobao
 * @create 2020-02-27 22:03
 * @description 学院节点
 *
 */
public class College extends OrganizationComponent {
    //创建List保存专业
    private List<OrganizationComponent> majorList = new ArrayList<>();

    public College(String name) {
        super(name);
    }

    //添加专业
    @Override
    protected void add(OrganizationComponent component) {
        majorList.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        majorList.remove(component);
    }

    //遍历所有专业并打印
    @Override
    protected void print() {
        System.out.println("----------------------" + this.getName() + "---------------------");
        for (OrganizationComponent component : majorList) {
            component.print();
        }
    }
}
