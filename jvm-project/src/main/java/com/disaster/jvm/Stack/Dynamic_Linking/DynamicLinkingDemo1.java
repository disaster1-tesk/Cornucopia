package com.disaster.jvm.Stack.Dynamic_Linking;


/*
每一个栈帧内部都包含一个指向运行时常量池中改栈帧所属方法的引用。
包含这个引用的目的就是为了支持当前方法的代码能够实现“动态链接”。比如：invokedynamic指令

在java源文件被编译到字节码文件中时，所有的变量和方法引用都作为符号引用保存在class
文件的常量池里。比如：描述一个方法调用了另外的其他方法时，就是通过常量池中
指向方法的符号引用来表示的，那么动态链接的作用就是为了将这些符号引用转换为
调用方法的直接引用。
 */
public class DynamicLinkingDemo1 {
    public static void main(String[] args) {



    }
}
