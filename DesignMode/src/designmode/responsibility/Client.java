package designmode.responsibility;

/**
 * @author baobao
 * @create 2020-03-06 22:39
 * @description 责任链模式
 *
 * 责任链模式为请求创建一个接收者对象的链，实现发送者和接收者解耦。责任链上的每个接收者都保存了下一个接收者的引用，
 * 如果一个接收者无法处理请求，那么它会调用下一个接收者处理
 *
 * 要素：
 * 1、Approver：抽象的请求处理者，有一个保存责任链上下一个请求处理者的成员变量
 * 2、ConcreteApprover：具体的请求处理者，实现请求处理方法，如果判断自己无法处理，则调用下一个请求处理者处理
 * 3、Request：请求对象，包含很多属性，请求处理者根据属性判断自己是否能处理某个请求
 *
 * 举例：审批请求，金额小于5000由系长处理，大于5000小于10000由院长处理，大于10000由校长处理
 * 1、ConcreteApprover：系长DepartmentApprover、院长CollegeApprover、校长SchoolApprover
 * 2、Request：PurchaseRequest，包含请求的金额
 */
public class Client {
    public static void main(String[] args) {
        //创建审批请求，金额15000
        PurchaseRequest purchaseRequest = new PurchaseRequest(15000);
        //创建责任链上的各个请求处理者
        DepartmentApprover departmentApprover = new DepartmentApprover();
        CollegeApprover collegeApprover = new CollegeApprover();
        SchoolApprover schoolApprover = new SchoolApprover();

        //设置每个请求处理者在责任链上的下一个处理者，形成一个闭环
        departmentApprover.setNextApprover(collegeApprover);
        collegeApprover.setNextApprover(schoolApprover);
        schoolApprover.setNextApprover(departmentApprover);
        //处理请求
        departmentApprover.processRequest(purchaseRequest);
    }
}
