package designmode.adapter.object;

/**
 * @author baobao
 * @create 2020-02-27 20:36
 * @description 对象适配器模式
 *
 * 对象适配器模式和类适配器非常类似，只是adapter不再继承被适配的src，而是持有src对象的引用，将src聚合到adapter中
 *
 * 代码举例：
 *  1、src是原始电压输出220V
 *  2、dest中声明输出5V电压的方法
 *  3、adapter电源适配器实现dest，构造器传入src保存为属性，实现输出5V的方法中调用保存src引用的属性获取220V电压，再转换为5V输出
 *  4、Phone当中声明一个充电方法，传入dest接口作为参数，实际传入一个adapter，将220V转换为5V进行充电
 *
 *  对象适配器模式因为不是继承src而是将src聚合进来，所以更灵活，也不一定要求dest是接口
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }
}
