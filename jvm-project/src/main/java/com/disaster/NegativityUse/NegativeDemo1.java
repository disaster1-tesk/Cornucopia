package com.disaster.NegativityUse;
/*
1.当访问一个静态字段时，只有真正声明这个字段的类才会被初始化。
当通过子类引用父类的静态变量，不会导致子类初始化
2.通过数组定义类引用，不会触发此类的初始化。
 */
public class NegativeDemo1 {
    public static void main(String[] args) {
        System.out.println(Son.NUM_1);
//        Son[] sons = new Son[10];
    }
}
class Son extends Father{
    static {
        System.out.println("Son--->"+NUM_1);
    }
}
class Father{
    public static /*final*/ int NUM_1 = 10;
    static {
        System.out.println("Father"+NUM_1);
    }
}