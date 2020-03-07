package designmode.command;

/**
 * @author baobao
 * @create 2020-03-01 21:08
 * @description 开灯命令
 */
public class LightOnCommand implements Command {
    private LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    //执行开灯命令
    @Override
    public void execute() {
        lightReceiver.on();
    }

    //撤销开灯命令
    @Override
    public void undo() {
        lightReceiver.off();
    }
}
