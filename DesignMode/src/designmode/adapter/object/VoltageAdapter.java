package designmode.adapter.object;

/**
 * @author baobao
 * @create 2020-02-27 20:32
 * @description
 */
public class VoltageAdapter implements Voltage5V{
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        if (this.voltage220V != null){
            int destVol = voltage220V.output220V() / 44;
            System.out.println("适配器转换电压为" + destVol + "V");
            return destVol;
        }
        throw new RuntimeException();
    }
}
