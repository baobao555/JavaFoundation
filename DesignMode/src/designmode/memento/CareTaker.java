package designmode.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-05 21:57
 * @description 保存备忘录对象，统一管理
 */
public class CareTaker {
    //保存所有备忘录对象
    private List<Memento> mementos = new ArrayList<>();

    //添加备忘录对象
    public void addMemento(Memento memento){
        mementos.add(memento);
    }

    //获得备忘录对象，恢复数据
    public Memento getMemento(int index){
        return mementos.get(index);
    }
}
