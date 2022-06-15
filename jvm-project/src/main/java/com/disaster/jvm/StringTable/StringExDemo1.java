package com.disaster.jvm.StringTable;

public class StringExDemo1 {
    public static void main(String[] args) {
        String str = new String("ab")+new String("c");
        str.intern();
        String s = "abc";
//        String s1= new String("abc");
        System.out.println(str == s);//在jdk1.6是false，在jdk1.8之后是true
    }
}
