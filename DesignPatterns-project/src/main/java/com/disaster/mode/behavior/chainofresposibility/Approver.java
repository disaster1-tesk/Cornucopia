package com.disaster.mode.behavior.chainofresposibility;

/**
 * @ClassName Approver
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 16:40
 * @Version 1.0
 **/
public abstract class Approver {
    protected Approver approver;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
