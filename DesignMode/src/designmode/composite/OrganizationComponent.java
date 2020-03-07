package designmode.composite;

/**
 * @author baobao
 * @create 2020-02-27 22:00
 * @description 顶层抽象组件
 */
public abstract class OrganizationComponent {
    private String name;

    //添加子节点方法，叶子节点无需实现，所以不声明成抽象的，而是做了一个默认实现
    protected void add(OrganizationComponent component){
        throw new UnsupportedOperationException();
    }

    //移除子节点方法，叶子节点无需实现，所以不声明成抽象的，而是做了一个默认实现
    protected void remove(OrganizationComponent component){
        throw new UnsupportedOperationException();
    }

    //打印方法，子类需实现
    protected abstract void print();

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
