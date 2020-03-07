package designmode.memento;

/**
 * @author baobao
 * @create 2020-03-05 22:01
 * @description 游戏角色，需要恢复数据
 */
public class GamePlayer {
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    private int hp;

    public GamePlayer(int hp) {
        this.hp = hp;
    }

    //将hp保存到备忘录
    public Memento saveHP2Memento(){
        return new Memento(this.hp);
    }

    //从备忘录中恢复到之前的HP状态
    public void recoverHPFromMemento(Memento memento){
        this.hp = memento.getHp();
    }
}
