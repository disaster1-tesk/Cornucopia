package com.disaster.mode.behavior.chainofresposibility;

/**
 * @ClassName CollegeApprover
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 16:43
 * @Version 1.0
 **/
public class CollegeApprover extends Approver {
    public CollegeApprover(String name) {
        super(name);
        approver = new ViceSchoolMasterApprover("李副校长");
        setApprover(approver);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
            if (purchaseRequest.getPrice()<10000){
                System.out.println(this.name+"正在处理价格为"+purchaseRequest.getPrice()+"的项目");
            }else{
                approver.processRequest(purchaseRequest);
            }
    }
}
