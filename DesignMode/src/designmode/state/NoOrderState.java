package designmode.state;

/**
 * @author baobao
 * @create 2020-03-05 22:36
 * @description 没有订单的状态，只能提交订单，不能付款或收货
 */
public class NoOrderState extends State {
    private OrderActivity orderActivity;

    //聚合需要定义状态的类
    public NoOrderState(OrderActivity orderActivity) {
        this.orderActivity = orderActivity;
    }

    //可以提交订单，将状态更新成可以付款状态
    @Override
    public void submitOrder() {
        System.out.println("提交了订单");
        orderActivity.setCurState(orderActivity.getCanPayState());
    }

    //无法付款
    @Override
    public void payMoney() {
        System.out.println("订单未提交，不能付款");
    }

    //无法收货
    @Override
    public void getGoods() {
        System.out.println("订单未提交，不能收货");
    }
}
