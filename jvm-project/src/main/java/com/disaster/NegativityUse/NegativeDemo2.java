package com.disaster.NegativityUse;
/*
3.引用常量不会触发此类或接口的初始化。因为常量在链接阶段就一定被显示赋值了。
4.调用ClassLoader类的loadClass（）方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 */
public class NegativeDemo2 {
    public static void main(String[] args) {
        try {
            ClassLoader.getSystemClassLoader().loadClass("com.disaster.NegativityUse.user");
//            new user();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
class user{
    public int NUM_2=10;
    static {
        System.out.println("user--->doing");
    }
}
