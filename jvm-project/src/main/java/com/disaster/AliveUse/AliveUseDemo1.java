package com.disaster.AliveUse;


import org.junit.Test;

import java.io.*;
/*
1.当对象被创建时会调用clinit方法（new、克隆、序列化、反射）
2.当对象中的静态方法被调用的时候
 */
public class AliveUseDemo1 {
    public static void main(String[] args) {
        writeUser();
        readUser();
    }

    @Test
    public void test(){
        User.test();
    }

    public static void writeUser(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("User.txt"));
            oos.writeObject(new User());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void readUser(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("User.txt"));
            User user = (User) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
class User implements Serializable {
    static {
        System.out.println("User类的初始化过程...");
    }
    public static void test(){
        System.out.println("静态方法");
    }
}
