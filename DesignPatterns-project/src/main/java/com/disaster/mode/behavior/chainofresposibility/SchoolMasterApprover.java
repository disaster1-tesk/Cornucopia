package com.disaster.mode.behavior.chainofresposibility;

/**
 * @ClassName SchoolMasterApprover
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 16:43
 * @Version 1.0
 **/
public class SchoolMasterApprover extends Approver {
    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice()<50000){
            System.out.println(this.name+"正在处理价格为"+purchaseRequest.getPrice()+"的项目");
        }else{
            System.out.println("已经无法进行处理");
        }
    }
}
