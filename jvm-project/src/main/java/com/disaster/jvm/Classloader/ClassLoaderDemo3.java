package com.disaster.jvm.Classloader;


public class ClassLoaderDemo3 {
    public static void main(String[] args) {

        System.out.println("--------------------应用类加载器-----------------");
        String property = System.getProperty("java.ext.dirs");
        for (String path: property.split(";")) {
            System.out.println(path);
        }
    }
}
