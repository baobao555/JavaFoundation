package designmode.proxy;

/**
 * @author baobao
 * @date 2019/7/18 0018 9:38
 * @description 静态代理
 */
public class StaticProxy {
    public static void main(String[] args) {
        Agent agent = new Agent(new Singer("Jay"));
        agent.sing();
    }
}

//定义接口，被代理类和代理类都需要实现
interface SingSong{
    void sing();
}

//被代理类
class Singer implements SingSong{

    private String name;

    public Singer(String name) {
        this.name = name;
    }

    //被代理类实际来实现接口的功能
    @Override
    public void sing() {
        System.out.println(name + "唱歌");
    }
}

//代理类
class Agent implements SingSong{
    //代理类需持有被代理类的引用
    private Singer singer;

    public Agent(Singer singer) {
        this.singer = singer;
    }

    @Override
    public void sing() {
        //代理类实现接口功能时，可以做一些额外操作，然后再实际调用被代理类的方法实现功能
        System.out.println("安排日程");
        System.out.println("收费");
        singer.sing();
    }
}