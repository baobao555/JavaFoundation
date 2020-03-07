package designmode.command;

/**
 * @author baobao
 * @create 2020-03-01 21:26
 * @description 命令模式
 * 命令模式将客户端想要的操作封装到命令中，只需要发出命令，并不关心谁执行，最后获得结果。
 *
 * 命令模式的几个角色：
 * 1、Command：命令的抽象接口或者类
 * 2、ConcreteCommand：具体的命令，会聚合Receiver来完成命令的执行
 * 3、Receiver：命令接收者，它具体完成命令所要执行的功能，被聚合到ConcreteCommand中
 * 4、Invoker：命令调用者
 *
 * 场景举例：
 * ConcreteCommand：开灯和关灯命令LightOnCommand/LightOffCommand
 * Receiver：开灯关灯命令的接收者LightReceiver
 * Invoker：远程遥控RemoteController
 *
 * Client想要开灯了，调用RemoteController执行开灯命令，RemoteController调用LightOnCommand执行开灯命令，
 * LightOnCommand调用聚合进去的LightReceiver最终完成开灯操作
 */
public class Client {
    public static void main(String[] args) {
        LightReceiver lightReceiver = new LightReceiver();
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        RemoteController remoteController = new RemoteController();
        remoteController.setCommand(0, lightOnCommand, lightOffCommand);

        remoteController.pressOnButton(0);
        remoteController.undo();
    }
}
