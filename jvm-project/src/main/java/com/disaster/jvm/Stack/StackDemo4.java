package com.disaster.jvm.Stack;
/*
Slot变量槽：
    1.参数之的存放总是在局部变量数组的index0开始，到数组长度-1的索引结束。
    2.局部变量表，最基本的存储单元是Slot（变量槽）
    3.局部变量表中存放编译器可知的各种数据类型数据类型（8种），引用类型（reference ），returnAddress类型的变量。
    4.在局部变量表中，32位以内的类型只占用一个slot（包括returnAddress类型），64位的类型（long和double）占用两个slot。
        byte、short、char在存储钱被转换为int，double也被转换为int，0表示false，非0表示true。
        long和double则占据两个slot
Slot变量槽的复用：当一个变量的作用域不能覆盖整个方法时，那么这个变量的变量槽会被重复使用。
*/
public class StackDemo4 {
    public static void main(String[] args) {
        StackDemo4 test = new StackDemo4();
        test.test1();
    }
    public void test1(){
        int a = 0;
        int f = 2;
        String s = "asd";
        char c = 'a';
        {
            int b = 1;
            b = b+a+f;
            b=f-a;
        }
        double d = 1.0;
        test2();
    }
    public void test2(){
        System.out.println("11");
    }

    public int test3(){
        return 10;
    }

    public void gettest3(){
        int a = test3();
    }

    public void add(){
        //第一类问题
        int i1=0;
        i1++;
        ++i1;

        //第二类问题
        int i2 = 10;
        int i3 = i2++;
        int i4 = ++i2;

        //第三类问题
        int i5 =10;
        i5 = i5++;
        i5 = ++i5;

        //第四类问题
        int i6 = 10;
        int i7 = i6++ + ++i6;




    }
}

