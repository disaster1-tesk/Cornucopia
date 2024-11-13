package com.disaster.mode.structuraltype.Proxy.v4;

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
            new TimeProxy(new Tank()).move();
    }
}

class TimeProxy implements Movable {
    Tank tank;

    public TimeProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        tank.move();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}

interface Movable{
    void move();
}