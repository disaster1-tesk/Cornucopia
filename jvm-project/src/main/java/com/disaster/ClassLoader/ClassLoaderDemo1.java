package com.disaster.ClassLoader;
/*
对于静态变量的赋值：
1.如果静态变量用final修饰并且是基本数据类型，那么显示赋值界面通常是在链接阶段的准备阶段进行。
2.对于String来说，如果使用字面量额方式赋值，使用static final修饰的话，则显示赋值通常是在链接阶段的准备缓解进行。

 */
public class ClassLoaderDemo1 {
    public static int a ;
    public static final int b = 100;
    public static final String str = "hello world";
    public static final String str1 = new String("hello world1");
    {
        a = 101;
    }
}
