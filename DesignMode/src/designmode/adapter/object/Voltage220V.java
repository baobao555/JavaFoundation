package designmode.adapter.object;

/**
 * @author baobao
 * @create 2020-02-27 20:31
 * @description
 */
public class Voltage220V {
    public int output220V(){
        int srcVol = 220;
        System.out.println("输出" + srcVol + "V电压");
        return srcVol;
    }
}
