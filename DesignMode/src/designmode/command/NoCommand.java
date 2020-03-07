package designmode.command;

/**
 * @author baobao
 * @create 2020-03-01 21:13
 * @description 空命令，对Command接口做空实现。初始化RemoteController时使用，省去判空操作
 */
public class NoCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
