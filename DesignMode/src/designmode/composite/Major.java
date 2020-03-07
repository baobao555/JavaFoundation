package designmode.composite;

/**
 * @author baobao
 * @create 2020-02-27 22:09
 * @description 专业节点
 */
//因为是叶子节点，无需实现add和remove
public class Major extends OrganizationComponent {

    public Major(String name) {
        super(name);
    }

    //叶子节点打印自身即可，无需再遍历
    @Override
    protected void print() {
        System.out.println(this.getName());
    }
}
