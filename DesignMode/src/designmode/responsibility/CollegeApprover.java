package designmode.responsibility;

/**
 * @author baobao
 * @create 2020-03-06 22:30
 * @description 院长
 */
public class CollegeApprover extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        int money = request.getMoney();
        //请求金额大于5000小于10000，由院长处理
        if (money > 5000 && money <= 10000){
            System.out.println("院长处理了金额为" + money + "的请求");
        }else {
            //金额大于10000，找责任链的下一个处理者(校长)处理
            nextApprover.processRequest(request);
        }
    }
}
