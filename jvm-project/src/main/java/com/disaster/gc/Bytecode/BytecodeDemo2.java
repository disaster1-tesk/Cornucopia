package com.disaster.gc.Bytecode;

public class BytecodeDemo2 {
    public static void main(String[] args) {
        String str = new String("hello")+new String("World");
        String str1 = "helloWorld";
        System.out.println(str == str1);
    }
}
