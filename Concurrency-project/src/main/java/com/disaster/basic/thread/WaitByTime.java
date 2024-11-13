package com.disaster.basic.thread;

public class WaitByTime  {
    public static void main(String[] args) {
        Object o = new Object();
        Thread thread = new Thread(() -> {
            synchronized (o){
                try {
                    System.out.println("============begin wait===========");
                    o.wait(2000);
                    o.wait(2000,1);
                    System.out.println("============end wait===========");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
