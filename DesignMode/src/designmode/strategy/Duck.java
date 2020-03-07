package designmode.strategy;

/**
 * @author baobao
 * @create 2020-03-06 21:07
 * @description 鸭子的抽象类
 */
public abstract class Duck {
    //名字
    private String name;
    //飞行策略
    private FlyStrategy flyStrategy;

    public Duck(String name, FlyStrategy flyStrategy) {
        this.name = name;
        this.flyStrategy = flyStrategy;
    }

    public void fly(){
        System.out.println(name);
        if (flyStrategy != null){
            //根据不同的飞行策略，展现不同的飞行方式
            flyStrategy.fly();
        }
    }
}
