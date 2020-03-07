package designmode.command;

/**
 * @author baobao
 * @create 2020-03-01 21:13
 * @description 开关灯的命令接收者，实际完成开灯/关灯功能
 */
public class LightReceiver {
    public void on(){
        System.out.println("开灯");
    }

    public void off(){
        System.out.println("关灯");
    }
}
