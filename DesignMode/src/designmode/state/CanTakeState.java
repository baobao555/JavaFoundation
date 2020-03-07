package designmode.state;

/**
 * @author baobao
 * @create 2020-03-05 22:38
 * @description 可以收货状态，不能提交订单或付款
 */
public class CanTakeState extends State {
    //聚合需要定义状态的类
    private OrderActivity orderActivity;

    public CanTakeState(OrderActivity orderActivity) {
        this.orderActivity = orderActivity;
    }

    //不能提交订单
    @Override
    public void submitOrder() {
        System.out.println("还未收货，不能重复提交订单");
    }

    //不能付款
    @Override
    public void payMoney() {
        System.out.println("还未收货，不能重复付款");
    }

    //可以收货，并将状态更新成没有订单状态
    @Override
    public void getGoods() {
        System.out.println("收货");
        orderActivity.setCurState(orderActivity.getNoOrderState());
    }
}
