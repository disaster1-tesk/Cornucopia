package com.disaster.Volatile;

public class VolatileTest {
    public static volatile int race = 0;
    private static final int THREADS_COUNTS = 20;

    public static void increase() {
        race++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNTS];
        for (int i = 0; i < THREADS_COUNTS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        Thread.sleep(20000);
        System.out.println("race = "+race);
        System.out.println("ThreadActiveCount = "+Thread.activeCount());
    }
}
