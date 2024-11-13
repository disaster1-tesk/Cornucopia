package com.disaster.mode.behavior.templatemethod.v1;

public class Client {
    public static void main(String[] args) {
        SoybeanMilk blockBeanMilk = new BlockBeanMilk();
        SoybeanMilk redBeanMilk = new RedBeanMilk();
        blockBeanMilk.make();
        redBeanMilk.make();
    }
}
