package designmode.responsibility;

/**
 * @author baobao
 * @create 2020-03-06 22:31
 * @description 校长
 */
public class SchoolApprover extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        int money = request.getMoney();
        //请求金额大于10000，由校长处理
        if (money > 10000){
            System.out.println("校长处理了金额为" + money + "的请求");
        }else {
            //金额小于10000，找责任链的下一个处理者(系长)处理
            nextApprover.processRequest(request);
        }
    }
}
