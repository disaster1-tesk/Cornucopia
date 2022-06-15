package com.disaster.jvm.StringTable;
/*
String的不可变性：
    当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值
    当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
    当调用String的replace（）方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
通过字面量的方式（区别与new）给一个字符串赋值，此时的字符串值声明在字符串常量池中
 */
public class StringTableDemo1 {
    public static void main(String[] args) {
        String str = new String("good");
        char[] chars = {'t','e','s','t'};
        StringTableDemo1.replace(str,chars);
        System.out.println(str);
        System.out.println(chars);
    }
    public static void replace(String str,char[] chars){
        str = "hello";
        chars[0]='b';
    }

}
