package com.disaster.mode.structuraltype.Proxy.v6;

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
        new LogProxy(
                new TimeProxy(
                        new Tank()
                )
        ).move();
    }
}

class TimeProxy implements Movable {
    Movable m;

    public TimeProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        m.move();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

class LogProxy implements Movable {
    Movable m;

    public LogProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("Tank startEngine....");
        m.move();
        System.out.println("Tank shutDownEngine....");
    }
}

interface Movable {
    void move();
}