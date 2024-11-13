package com.disaster.mode.createtype.factorymode.simplefactory;

public class Car implements MoveAble {
    @Override
    public void move() {
        System.out.println("车在跑...");
    }
}
