package designmode.memento;

/**
 * @author baobao
 * @create 2020-03-05 22:04
 * @description 备忘录模式
 *
 * 备忘录模式可以让对象的数据恢复到一个历史状态，并且不需要关心对象内部的结构细节
 *
 * 元素：
 * 1、originator：需要保存、恢复状态的对象
 * 2、Memento：备忘录对象，保存originator的内部数据，它内部变量应该与originator需要保存的数据结构一致
 * 3、CareTaker：守护者对象，保存多个备忘录对象，集中管理
 *
 * 举例：
 * 1、originator：游戏角色，拥有HP属性，需要保存状态时调用方法生成一个Memento，将HP传给它保存。需要恢复到之前的HP时
 *                从CareTaker中取出对应的Memento，再从Memento中取出HP数据覆盖掉自己当前的HP
 * 2、Memento：备忘录对象，有一个int类型属性，保存游戏角色的HP
 * 3、CareTaker：守护者对象，保存多个Memento。提供获取Memento的方法
 */
public class Client {
    public static void main(String[] args) {
        GamePlayer gamePlayer = new GamePlayer(100);
        System.out.println("打boss前HP：" + gamePlayer.getHp());
        CareTaker careTaker = new CareTaker();
        //保存状态
        careTaker.addMemento(gamePlayer.saveHP2Memento());
        gamePlayer.setHp(70);
        System.out.println("打boss后HP：" + gamePlayer.getHp());
        //恢复状态
        gamePlayer.recoverHPFromMemento(careTaker.getMemento(0));
        System.out.println("升级后补充了HP：" + gamePlayer.getHp());
    }
}
