package designmode.memento;

/**
 * @author baobao
 * @create 2020-03-05 21:56
 * @description 备忘录，保存需要恢复的数据
 */
public class Memento {
    private int hp;

    public int getHp() {
        return hp;
    }

    public Memento(int hp) {
        this.hp = hp;
    }
}
