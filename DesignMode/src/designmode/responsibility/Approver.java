package designmode.responsibility;

/**
 * @author baobao
 * @create 2020-03-06 22:29
 * @description 责任链上的请求处理者抽象类
 */
public abstract class Approver {
    //保存下一个处理者
    protected Approver nextApprover;

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    //处理请求
    public abstract void processRequest(PurchaseRequest request);
}
