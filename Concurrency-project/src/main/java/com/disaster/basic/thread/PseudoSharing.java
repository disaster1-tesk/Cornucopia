package com.disaster.basic.thread;

public class PseudoSharing {
    static final int LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        pseudoSharing0();
        pseudoSharing1();
    }

    private static void pseudoSharing0() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long s = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i * 2 + j;
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("cache time :" + (e - s));
    }

    private static void pseudoSharing1() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long s = System.currentTimeMillis();
        for (int i = 0; i < COLUM_NUM; i++) {
            for (int j = 0; j < LINE_NUM; j++) {
                array[j][i] = i * 2 + j;
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("cache time :" + (e - s));
    }
}
