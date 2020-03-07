package designmode.state;

/**
 * @author baobao
 * @create 2020-03-05 22:50
 * @description 状态模式
 *
 * 状态模式用于对象需要维护多种状态，在不同的状态有不同的行为，状态改变时对应的行为也要改变。
 * 可以避免传统模式利用if-else判断状态造成分支过多不易维护
 *
 * 要素：
 * 1、context：需要维护状态变化的对象，内部保存好所有State的对象，并对外暴露方法可以更新当前状态。
 *             需要某个具体操作时，调用当前状态来执行，这样对于当前状态不支持的方法，ConcreteState对象
 *             已经做好了提示，所以可以无需if判断
 * 2、State：抽象状态接口，里面要定义context各种状态下所有可能的行为方法
 * 3、ConcreteState：具体某种的状态，实现所有状态可能的方法，对于支持的方法做正常实现，对于不支持的方法
 *    可以给与不支持的提示。同时把context聚合进来，调用支持的方法后，通过context实例的方法更新其状态
 *
 * 举例：一个订单从提交、付款到收货的流程
 * 1、context：OrderActivity，订单类。保存了订单状态、可以付款状态、可以收货状态的实例，以及一个当前状态变量，
 *             暴露一个方法更新当前状态。提交订单、付款、收货操作时调用当前状态的方法。这样假如当前状态是可以收款，
 *             那么通过可以收款状态调用提交订单必然会失败，从而保证状态转换安全，并且无需if-else判断
 * 2、State：抽象状态接口，定义了提交订单、付款、收货3个方法
 * 3、ConcreteState：包含没有订单状态、可以付款状态、可以收货状态，都实现了提交订单、付款、收货3个方法，并聚合
 *    OrderActivity，完成对应操作后可以通过OrderActivity更新其状态
 *    NoOrderState没有订单的状态，提交订单可以成功并将OrderActivity更新为可以付款状态，付款、收货方法会提示失败
 *    CanPayState可以付款的状态，付款方法可以成功并将OrderActivity更新为可以收货状态，提交订单、收货方法会提示失败
 *    CanTakeState可以收货的状态，收货方法可以成功并将OrderActivity更新为可以没有订单状态，付款、提交订单方法会提示失败
 */
public class Client {
    public static void main(String[] args) {
        OrderActivity orderActivity = new OrderActivity();
        orderActivity.submitOrder();
        orderActivity.submitOrder();
        orderActivity.payMoney();
        orderActivity.getGoods();
        orderActivity.submitOrder();
    }
}
