package designmode.responsibility;

/**
 * @author baobao
 * @create 2020-03-06 22:28
 * @description 审批请求
 */
public class PurchaseRequest {
    //请求的金额
    private int money;

    public PurchaseRequest(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
