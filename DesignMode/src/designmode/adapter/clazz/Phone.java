package designmode.adapter.clazz;

/**
 * @author baobao
 * @create 2020-02-27 20:16
 * @description 手机类，充电方法接收适配器接口实现适配
 */
public class Phone {
    public void charging(Voltage5V adapter){
        int output = adapter.output5V();
        if (output <= 5){
            System.out.println("电压在5V以内，可以充电");
        }else {
            System.out.println("电压高于5V，无法充电");
        }
    }
}
