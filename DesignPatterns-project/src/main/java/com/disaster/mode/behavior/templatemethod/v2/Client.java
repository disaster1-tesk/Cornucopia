package com.disaster.mode.behavior.templatemethod.v2;

public class Client {
    public static void main(String[] args) {
        SoybeanMilk blockBeanMilk = new BlockBeanMilk();
        SoybeanMilk redBeanMilk = new RedBeanMilk();
        SoybeanMilk pureBeanMilk = new PureBeanMilk();
        blockBeanMilk.make();
        redBeanMilk.make();
        pureBeanMilk.make();
    }
}
