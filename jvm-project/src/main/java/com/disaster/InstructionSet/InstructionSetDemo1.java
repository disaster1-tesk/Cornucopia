package com.disaster.InstructionSet;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class InstructionSetDemo1 {
    private long l = 10;

    public static void main(String[] args) {
        Proxy.getInvocationHandler(new Object());
    }

    public void load(int a, long b) {
        int sum = a + 10;
        byte bytes = 1;
        String str = "你好";
        b = 10;
    }

    public void store(boolean flag, int sum) {
        {
            float s = 10;
        }
        flag = true;
        sum = 10 + 2;
        float f = sum + 1;
    }

    //对于byte、short、char数据类型进行宽泛类型转换的时候将他们都看做int
    public void method(char c) {
        int i = c;
        float f = c;
        long l = c;
        double d = c;
    }

    //宽化类型转换 int-->long-->float-->double
    // 窄化类型转换 byte、short、char在与long float double类型转换时，没有直接转换的字节码指令，只能通过先转换成int再转换成小容量的数据类型
    public void method2(int a) {
        long l = a;
        float f = a;
        double d = a;
        /*byte b = (byte) a;
        char c = (char) a;
        short s = (short) a;*/
        byte b = (byte) l;
        char c = (char) f;
        short s = (short) d;
        String str = "hello world";
        if (str instanceof Object) {
            Object o = str;
        }
    }

    //对象的创建
    public void method3() {
        Object o = new Object();
        File file = new File("D://IDEA");
    }

    //对前++和后++的理解
    @Test
    public void method4() {
        int a = 10;
        double d = 100;
        long l = 100l;
        a = a++;
        System.out.println(a);
        a = ++a;
        System.out.println(a);
    }

    //字段和方法的字节码指令
    @Test
    public void method5() {
        User user = new User();
        System.out.println(User.a);
        System.out.println(user.str);
    }

    //数组的相关的字节码操作指令
    public void method6() {
        int[] array = new int[10];
        array[1] = 100;
        array[2] = 200;
        int a = array[1];
        String[] strarray = new String[10];
        strarray[1] = "hello";
        strarray[2] = "world";
        String str = strarray[1];
        int[][] arrays = new int[10][];
        int[][] arrays1 = new int[10][10];
    }

    //类型检查指令
    public void method7(String str) {
        if (str instanceof String) {
            System.out.println(str);
        } else {
            return;
        }

    }

    //方法调用指令
    public void method8() {
        User user = new User();
        User.method2();
        User.a = 10;
        user.method();
        user.method3();
        user.str = "1";
        AA aa = new User();
        aa.method1();
        AA.method2();
    }

    //操作数栈管理指令
    public long method9() {
        User user = new User();
        user.method3();
        user.method4();
        return l++;
    }

    //条件跳转指令（如果进行比较的是float/long/double这三种类型，使用比较指令结合条件跳转指令。
    // 如果进行比较的是byte/short/int/char和引用数据类型，则使用比较条件跳转指令
    public void method10(double d, long l, float f) {
        if (d > l) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (f < d) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (l == f) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    public void method11(int i, char c, short s, Object o1, Object o2) {
        if (i > c) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (s < c) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (s == c) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (o1 == o2) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    //多条件跳转
    public void method12(int num) {
        switch (num) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    //当switch判断的是字符串的时候，通过hash值与equals方法来判断两个字符串是否一致
    public void method13(String str) {
        switch (str) {
            case "SPRING":
                break;
            case "SUMMER":
                break;
            case "AUTUMN":
                break;
            case "WINTER":
                break;
        }
    }

    //无条件跳转(一般出现在循环中)
    public void method14(int a) {
        for (int i = 0; i < 10; i++) {
            a++;
        }
    }

    public void method15(int a) {
        do {
            a++;
        } while (a > 0);
    }

    public void method16(int a) {
        while (a > 0) {
            a++;
        }
    }

    //异常表指令
    public void method17() {
        File file = new File("D://IDEA");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void method18(){
        String str = "Hello World";
        try{
            return;
        }finally {
            str = "111";
        }
    }
    //线程同步
    public void method19(){
        Object o = new Object();
        synchronized (o){
            System.out.println(o);
        }
    }
    static class User implements AA {
        public String str = "Hello World";
        public static int a = 100;
        public static long l = 10;

        public void method() {

        }

        public static void method2() {

        }

        private int method3() {
            return 0;
        }

        public long method4() {
            return 1;
        }
    }

    interface AA {
        public default void method1() {

        }

        public static void method2() {

        }
    }

}

