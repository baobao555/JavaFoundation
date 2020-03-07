package designmode.state;

/**
 * @author baobao
 * @create 2020-03-05 22:33
 * @description 抽象状态，定义了所有状态会进行的行为的集合
 */
public abstract class State {
    //提交订单
    public abstract void submitOrder();
    //付款
    public abstract void payMoney();
    //收取货物
    public abstract void getGoods();
}
