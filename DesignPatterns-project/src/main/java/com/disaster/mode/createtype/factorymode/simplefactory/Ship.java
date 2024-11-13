package com.disaster.mode.createtype.factorymode.simplefactory;

public class Ship implements MoveAble {
    @Override
    public void move() {
        System.out.println("船在水上跑...");
    }
}
