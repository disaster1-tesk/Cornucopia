package com.disaster.basic.thread;


public class Contended {
    @sun.misc.Contended//此注解能解决伪共享问题
    private volatile long value = 0l;

    static class NoContended {
        private volatile long value = 0l;
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Contended contended = new Contended();
        }
        long e = System.currentTimeMillis();
        System.out.println("consumption time :" + (e - s));

        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            NoContended contended = new NoContended();
        }
        long e1 = System.currentTimeMillis();
        System.out.println("consumption time :" + (e1 - s1));
    }
}
