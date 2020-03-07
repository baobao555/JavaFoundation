package designmode.responsibility;

/**
 * @author baobao
 * @create 2020-03-06 22:30
 * @description 系长
 */
public class DepartmentApprover extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        //请求金额小于5000，由系长处理
        int money = request.getMoney();
        if (money <= 5000){
            System.out.println("系长处理了金额为" + money + "的请求");
        }else {
            //金额大于5000，找责任链的下一个处理者(院长)处理
            nextApprover.processRequest(request);
        }
    }
}
