package com.disaster.mode.structuraltype.Proxy.v3;

import java.util.Random;

public class Tank implements Movable {

    @Override
    public void move() {
        System.out.println("Cla...Cla...Cla");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TankProxy().move();
    }
}

class TankProxy extends Tank{

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        super.move();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}

interface Movable{
    void move();
}