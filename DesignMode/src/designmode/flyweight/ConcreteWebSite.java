package designmode.flyweight;

/**
 * @author baobao
 * @create 2020-03-01 20:19
 * @description 具体的网站
 */
public class ConcreteWebSite extends WebSite {
    //网站类型，共享的部分，内部状态
    private String type;

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站类型:" + type + "-----" + user.getName() + "在使用");
    }
}
