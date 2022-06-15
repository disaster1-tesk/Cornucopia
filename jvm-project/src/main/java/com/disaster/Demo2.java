package com.disaster;

/**
 * @ClassName Demo2
 * @Description TODO
 * @Author Disaster
 * @Date 2022/5/1 10:32
 * @Version 1.0
 **/
public class Demo2 {
    public static void main(String[] args) {
        System.out.println("hello world");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
