package com.disaster.gc.GCRoots;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GCRootsDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        List<Object> lists = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            lists.add(new Object());
            Thread.sleep(10);
        }
        System.out.println("进行垃圾回收");
        new Scanner(System.in).next();
        Thread.sleep(10000);
        lists = null;
        date = null;
        System.out.println("垃圾回收完毕");
        Thread.sleep(100000);
    }
}
