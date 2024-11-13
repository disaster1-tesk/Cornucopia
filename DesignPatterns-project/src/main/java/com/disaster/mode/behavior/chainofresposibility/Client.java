package com.disaster.mode.behavior.chainofresposibility;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 17:10
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(16887,"图书",10000);
        DepartmentApprover departmentApprover = new DepartmentApprover("李系长");
        departmentApprover.processRequest(purchaseRequest);
    }
}
