package com.disaster.jvm.Classloader;

public class CLassloaderDemo1 {
    private static int number = 10;

    static {
        num = 1;
        number = 20;
    }

    private static int num = 10;

    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(number);
    }
}
