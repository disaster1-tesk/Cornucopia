package com.disaster.mode.behavior.chainofresposibility;

/**
 * @ClassName DepartmentApprover
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 16:42
 * @Version 1.0
 **/
public class DepartmentApprover extends Approver{
    public DepartmentApprover( String name) {
        super(name);
        approver = new CollegeApprover("王院长");
        setApprover(approver);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (1000<purchaseRequest.getPrice()&&purchaseRequest.getPrice()<3000){
            System.out.println(this.name+"正在处理价格为"+purchaseRequest.getPrice()+"的项目");
        }else {
            approver.processRequest(purchaseRequest);
        }
    }


}
