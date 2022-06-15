package com.disaster.High;

public class HighDemo3 {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        new Thread(() -> {
            synchronized (s1){
                s1.append("hello");
                s1.append("world");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (s2){
                    s2.append("hello");
                    s2.append("china");
                    System.out.println(s2.toString());
                    System.out.println(s1.toString());
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (s2){
                s2.append("no");
                s2.append("hello");
                synchronized (s1){
                    s1.append("no");
                    s1.append("world");
                    System.out.println(s2.toString());
                    System.out.println(s1.toString());
                }
            }
        }).start();
    }
}
