package designmode.bridge;

/**
 * @author baobao
 * @create 2020-02-27 21:06
 * @description 桥接模式
 *
 * 桥接模式是将实现和抽象放在2个不同的类层次中，使2个层次可以独立改变
 *
 * 代码举例：
 * 手机有品牌(小米，华为...)和样式(折叠式，直屏...)，如果采用传统继承方式，会产生类爆炸，新增1种手机成本很高。
 *
 * 桥接模式实现：
 * 1、将手机品牌抽取成一个接口Brand，其中有开机、打电话、关机方法，实现类有小米、华为
 * 2、定义手机顶层类Phone，聚合Brand，用Brand的方法实现开机、打电话、关机的功能
 * 3、子类折叠手机、直屏手机继承Phone，重写相应的开机、关机、打电话方法，调用父类并增加自己个性化的操作
 *
 * 桥接模式相当于把手机品牌和样式独立放到了2个不同的线路中，互相不影响，通过聚合来实现合并，避免类爆炸，可扩展性强。
 * 但是与此同时，需要我们自己分析出2条独立的线路，增加设计难度
 */
public class Client {
    public static void main(String[] args) {
        Phone phone1 = new FoldPhone(new Xiaomi());
        phone1.open();
        phone1.call();
        phone1.close();

        System.out.println("===============================");
        Phone phone2 = new UpRightPhone(new Huawei());
        phone2.open();
        phone2.call();
        phone2.close();
    }
}
