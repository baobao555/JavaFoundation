package designmode.factory.factorymethod;

/**
 * @author baobao
 * @create 2020-02-22 19:44
 * @description 披萨的抽象基类
 *
 * 制作披萨包含4个过程：
 * 1、准备材料(不同类型披萨准备的材料不一样，故声明为抽象方法)
 * 2、烘焙
 * 3、切块
 * 4、打包
 */

public abstract class Pizza {
    public abstract void prepare();

    public void bake(){
        System.out.println("烘焙披萨");
    }

    public void cut(){
        System.out.println("将披萨切块");
    }

    public void box(){
        System.out.println("打包披萨");
    }
}
