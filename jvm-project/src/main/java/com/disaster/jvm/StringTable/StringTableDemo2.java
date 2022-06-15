package com.disaster.jvm.StringTable;

import org.junit.Test;

/*
   字符串的拼接金典题型
 */
public class StringTableDemo2 {
    public static void main(String[] args) {
        String str = "adc";
    }

    /*
    对于两个变量的字符串的拼接，会创建一个StringBuilder来进行拼接，最后再调用toString（）来实现
     */
    @Test
    public void test1(){
        String s1 = "1";
        String s2 = "2";
        String s3="12";
        String s4 = s1+s2;
        System.out.println(s3==s4);
    }
    /*
    对于只有一个变量或者全是字符串的拼接，需要创建StringBuilder来进行拼接
     */
    @Test
    public void test2(){
        String s1 = "1";
        String s2 = "2";
        String s3="12";
        String s4 = s1+"2";
        System.out.println(s3==s4);
    }
    /*
    如果两个字符串都是常量不是类引用，那么在编译期间就会完成字符串的拼接
     */
    @Test
    public void test3(){
        String s1 = "1";
        String s2 = "2";
        String s3="12";
        String s4 = "1"+"2";
        System.out.println(s3==s4);
    }
    /*
    如果字符串引用变量是用final修饰的，那么在编译期间就会完成字符串的拼接
     */
    @Test
    public void test4(){
        final String s1 = "1";
        final String s2 = "2";
        String s3="12";
        String s4 = s1+s2;
        System.out.println(s3==s4);
    }
}
