package designmode.state;

/**
 * @author baobao
 * @create 2020-03-05 22:37
 * @description 可以付款状态，不能提交订单或收货
 */
public class CanPayState extends State {
    //聚合需要定义状态的类
    private OrderActivity orderActivity;

    public CanPayState(OrderActivity orderActivity) {
        this.orderActivity = orderActivity;
    }

    //不能提交订单
    @Override
    public void submitOrder() {
        System.out.println("还未付款，不能重复提交订单");
    }

    //可以付款，将状态更新成可以收货状态
    @Override
    public void payMoney() {
        System.out.println("付款");
        orderActivity.setCurState(orderActivity.getCanTakeState());
    }

    //不能收货
    @Override
    public void getGoods() {
        System.out.println("还未付款，不能收货");
    }
}
