package designmode.flyweight;

/**
 * @author baobao
 * @create 2020-03-01 20:18
 * @description 网站用户
 */

//改变的部分，外部状态
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
