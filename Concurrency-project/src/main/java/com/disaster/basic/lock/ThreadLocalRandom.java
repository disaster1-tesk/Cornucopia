package com.disaster.basic.lock;

import java.util.Random;

public class ThreadLocalRandom {
    public static void main(String[] args) {
        random();
        threadLocalRandom();
    }

    private static void random() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }

    private static void threadLocalRandom() {
        java.util.concurrent.ThreadLocalRandom current = java.util.concurrent.ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(current.nextInt(5));
        }
    }
}
