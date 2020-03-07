package designmode.flyweight;

/**
 * @author baobao
 * @create 2020-03-01 20:28
 * @description 享元模式
 *
 * 享元模式即为共享对象，像数据库连接池、线程池都用到了享元模式。
 * 共享对象可以大大减少内存消耗，减少复杂对象的创建过程，提高效率
 *
 * 享元模式的对象：
 * 1、抽象的享元类
 * 2、具体的享元类
 * 3、享元工厂，维护一个池，根据需求从池中返回对象给调用者
 */
public class Client {
    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite webSite = webSiteFactory.getWebSite("新闻");
        webSite.use(new User("baobao"));
    }
}
