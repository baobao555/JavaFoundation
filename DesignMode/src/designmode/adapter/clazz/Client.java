package designmode.adapter.clazz;

/**
 * @author baobao
 * @create 2020-02-27 20:18
 * @description 类适配器模式
 *
 * 类适配器模式要求：
 * 1、被适配src的是一个类
 * 2、适配结果dest是一个接口
 * 3、适配器adapter要继承src类并实现dest接口，在dest接口声明的适配方法中，调用src的方法获取原始结果，再将原始结果
 * 转换为希望的结果，完成适配
 *
 * 代码举例：
 * 1、src是原始电压输出220V
 * 2、dest中声明输出5V电压的方法
 * 3、adapter电源适配器继承src并实现dest，实现输出5V的方法中调用src获取220V电压，再转换为5V输出
 * 4、Phone当中声明一个充电方法，传入dest接口作为参数，实际传入一个adapter，将220V转换为5V进行充电
 *
 * 类适配器模式缺点：
 * 1、src是一个类，adapter要继承它，只能单继承，有一定局限性
 * 2、因为adapter继承了src，所以src的方法都暴露给了adapter，增加了使用成本
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}
