package com.disaster.mode.createtype.factorymode.simplefactory;

public class Aircraft implements MoveAble{
    @Override
    public void move() {
        System.out.println("飞机在飞...");
    }
}
