package com.disaster.AliveUse;

/**
 * 当使用类、接口的静态字段时（final修饰特殊考虑），比如，使用getstatic或者putstatic指令。
 * 当使用java.lang.reflect包中的方法反射类的方法时
 */
public class AliveUseDemo2 {
    public static void main(String[] args) {
        System.out.println(Test.NUM_1);
        try {
            Class  clazz = Class.forName("com.disaster.AliveUse.Test");
            clazz.getClassLoader();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
class Test extends TestFather{
   static {
       NUM_1 = 2;
   }
}
class TestFather{
    public static int NUM_1 = 1;
    static {
        System.out.println("我被执行了");
    }
}

