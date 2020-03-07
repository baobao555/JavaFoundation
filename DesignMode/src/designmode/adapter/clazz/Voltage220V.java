package designmode.adapter.clazz;

/**
 * @author baobao
 * @create 2020-02-27 20:05
 * @description 被适配的类，输出220V电压
 */
public class Voltage220V {
    public int output220V(){
        int srcVoltage = 220;
        System.out.println("输出"+ srcVoltage + "V电压");
        return srcVoltage;
    }
}
