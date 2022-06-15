package com.disaster.jvm.Stack.Operation_Stack;

import java.io.*;

/*
什么是操作数栈？
每一个独立的栈帧中除了包含局部变量表意外，还包含一个后进先出的队列
这个队列就叫做操作数栈。
操作数栈，在方法的执行过程中，根据字节码指令，往栈中写入数据或提取数据，
即入栈（push）/出栈（pop）。


如果被调用的方法有返回值的话，其返回值会被压入当前栈帧中的
操作数栈，并跟新pc寄存器中下一条需要执行的字节码指令

我们所说的jvm中的解释器就是基于栈的执行引擎，其中栈就是指的操作数栈

栈顶缓存技术：
    随着技术的发展，栈中数据不断被取和存的次数的增加，程序的效率就会降低，为了解决这个问题
    我们提出了栈顶缓存技术，其目的就是将操作数栈顶中的数据放入到物理CPU中存储，这样能有效
    提高效率。



虚方法和非虚方法：
        如果方法在编译器就确定了具体的调用版本，这个版本在运行时是不可变的，这样的方法称为非虚方法
 */
public class OperationStackDemo1 {
    public static void main(String[] args) {
        OperationStackDemo1 test = new OperationStackDemo1();
        test.test1();
    }

    public void test1() {
        test2();
    }

    public void test2() {
        test3();
    }

    public String test3() {
        File file = new File("");
        byte[] bytes = null;
        BufferedInputStream bis = null;
        int read = 0;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            bytes = new byte[bis.available()];
            read = bis.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (read > 0) {
            return new String(bytes);
        }
        return "0";
    }
}