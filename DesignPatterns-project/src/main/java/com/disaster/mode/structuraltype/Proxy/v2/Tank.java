package com.disaster.mode.structuraltype.Proxy.v2;

import java.util.Random;

public class Tank implements Movable {

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("Cla...Cla...Cla");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

    public static void main(String[] args) {
        new Tank().move();
    }
}

interface Movable{
    void move();
}