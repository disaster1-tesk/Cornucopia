package com.disaster.High;

public class HighDemo4 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }
}
class MyRunnable implements Runnable {
    private int NUM = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                if (NUM<100){
                    System.out.println("当前的数字为："+NUM);
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
}