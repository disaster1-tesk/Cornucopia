package com.disaster.jvm.Classloader;
/*
获取ClassLoader类的几种方式：
    1.Class.forName("java.lang.String").getClassLoader()
    2.ClassLoader.getSystemClassLoader().getParent();
    3.Thread.currentThread().getContextClassLoader();
 */
public class ClassLoaderDemo4 {
    public static void main(String[] args) {
        try {
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);

            ClassLoader parent = ClassLoader.getSystemClassLoader().getParent();
            System.out.println(parent);

            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println(contextClassLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
