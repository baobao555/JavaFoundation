package designmode.state;

/**
 * @author baobao
 * @create 2020-03-05 22:37
 * @description 需要维护状态变化的类
 */
public class OrderActivity {
    //事先创建好所有状态对象
    private State noOrderState = new NoOrderState(this);
    private State canPayState = new CanPayState(this);
    private State canTakeState = new CanTakeState(this);
    //当前状态，初始化为没有订单的状态
    private State curState = this.noOrderState;

    //后续提交订单、付款和收货操作都通过当前状态来调用，如果是当前状态不支持的操作，就会有相应提示
    public void submitOrder() {
        curState.submitOrder();
    }


    public void payMoney() {
        curState.payMoney();
    }


    public void getGoods() {
        curState.getGoods();
    }

    //暴露给状态对象完成某个操作后更新当前状态
    public void setCurState(State curState) {
        this.curState = curState;
    }

    public State getNoOrderState() {
        return noOrderState;
    }

    public State getCanPayState() {
        return canPayState;
    }

    public State getCanTakeState() {
        return canTakeState;
    }
}
