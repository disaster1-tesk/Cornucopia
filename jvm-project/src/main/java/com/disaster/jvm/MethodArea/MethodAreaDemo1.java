package com.disaster.jvm.MethodArea;

/**
 * 静态变量的创建是在堆空间中的，但是静态变量的引用符是在方法区中
 */
public class MethodAreaDemo1 {
    public static void main(String[] args) {
        user user = new user();
        Object o = new Object();
    }
    static class user{
        public static byte[] bytes = new byte[1024*1024*100];
    }
}
