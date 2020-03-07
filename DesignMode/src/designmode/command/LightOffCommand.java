package designmode.command;

/**
 * @author baobao
 * @create 2020-03-01 21:09
 * @description 关灯命令
 */
public class LightOffCommand implements Command {
    private LightReceiver lightReceiver;

    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    //执行关灯命令
    @Override
    public void execute() {
        lightReceiver.off();
    }

    //撤销关灯命令
    @Override
    public void undo() {
        lightReceiver.on();
    }
}
