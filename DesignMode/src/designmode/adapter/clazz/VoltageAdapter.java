package designmode.adapter.clazz;

/**
 * @author baobao
 * @create 2020-02-27 20:12
 * @description 适配器类，继承原来的220V电压类，实现输出5V电压接口，将得到的220V电压转换成5V
 */
public class VoltageAdapter extends Voltage220V implements Voltage5V{

    @Override
    public int output5V() {
        int destVoltage = output220V() / 44;
        System.out.println("适配器转换电压为" + destVoltage + "V");
        return destVoltage;
    }
}
