package com.disaster.mode.behavior.chainofresposibility;

/**
 * @ClassName ViceSchoolMasterApprover
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 16:43
 * @Version 1.0
 **/
public class ViceSchoolMasterApprover extends Approver{
    public ViceSchoolMasterApprover(String name) {
        super(name);
        approver = new SchoolMasterApprover("张校长");
        setApprover(approver);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice()<30000){
            System.out.println(this.name+"正在处理价格为"+purchaseRequest.getPrice()+"的项目");
        }else{
            approver.processRequest(purchaseRequest);
        }
    }
}
