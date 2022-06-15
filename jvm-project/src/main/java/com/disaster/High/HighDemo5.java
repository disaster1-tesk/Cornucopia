package com.disaster.High;

public class HighDemo5 {
    public static /*volatile*/ Object o = new Object();
    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }
}

class MyThread extends Thread {
    private int NUM = 0;
    @Override
    public void run() {
        while (true) {
            synchronized (HighDemo5.o) {
                System.out.println("当前的数字为：" + NUM);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                NUM++;
            }
        }
    }
}
