package com.disaster.basic.thread;

public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        interrupt();
    }

    private static void interrupt() throws InterruptedException {
        Object o = new Object();
        Thread thread = new Thread(() -> {
            synchronized (o){
                try {
                    System.out.println("============begin wait===========");
                    o.wait();
                    System.out.println("============end wait===========");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        System.out.println("===========begin interrupt===========");
        thread.interrupt();
        System.out.println("===========end interrupt===========");
    }
}
