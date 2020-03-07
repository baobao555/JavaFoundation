package designmode.command;

/**
 * @author baobao
 * @create 2020-03-01 21:07
 * @description 抽象命令接口
 */
public interface Command {
    //执行命令
    void execute();
    //撤销命令
    void undo();
}
