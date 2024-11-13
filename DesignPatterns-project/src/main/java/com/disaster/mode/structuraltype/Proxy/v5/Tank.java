package com.disaster.mode.structuraltype.Proxy.v5;

import java.util.Random;

/**
 * @author Lenovo
 */

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
        new LogProxy(new Tank()).move();
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
        System.out.println(endTime - startTime);
    }
}

class LogProxy implements Movable {
    Tank tank;

    public LogProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        System.out.println("Tank startEngine....");
        tank.move();
        System.out.println("Tank shutDownEngine....");
    }
}

interface Movable {
    void move();
}