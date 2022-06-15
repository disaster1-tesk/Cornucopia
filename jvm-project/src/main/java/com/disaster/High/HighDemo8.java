package com.disaster.High;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.Executors;

public class HighDemo8 {
    public static void main(String[] args) {
        User user = new User();
        String str = new String();
        Integer integer = new Integer(10);
        User[] users = new User[10];
        System.out.println(ClassLayout.parseInstance(integer).toPrintable());
        System.out.println("===================================================");
        System.out.println(ClassLayout.parseInstance(str).toPrintable());
        System.out.println("====================================================");
        System.out.println(ClassLayout.parseInstance(users).toPrintable());
        System.out.println("====================================================");
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }
}
class User{
    private int a =1;
    private long l = 10l;
    private String str = "abc";
}
