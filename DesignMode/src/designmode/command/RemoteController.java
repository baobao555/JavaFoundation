package designmode.command;

/**
 * @author baobao
 * @create 2020-03-01 21:17
 * @description 远程遥控器，负责发送命令
 */
public class RemoteController {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    //初始化
    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    //给按钮设置命令
    public void setCommand(int no,Command onCommand,Command offCommand){
        this.onCommands[no] = onCommand;
        this.offCommands[no] = offCommand;
    }

    //按下开
    public void pressOnButton(int no){
        this.onCommands[no].execute();
        //记录下这次操作，方便撤销
        undoCommand = onCommands[no];
    }

    //按下关
    public void pressOffButton(int no){
        this.offCommands[no].execute();
        //记录下这次操作，方便撤销
        undoCommand = offCommands[no];
    }

    //撤销命令
    public void undo(){
        if (undoCommand != null){
            undoCommand.undo();
        }
    }
}
