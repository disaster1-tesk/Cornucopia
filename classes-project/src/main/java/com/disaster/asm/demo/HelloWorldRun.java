package com.disaster.asm.demo;

import com.disaster.asm.MyClassLoader;

import java.lang.reflect.Method;

public class HelloWorldRun {
    public static void main(String[] args) throws Exception{
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.loadClass("com.disaster.asm.demo.HelloWorld");
        Object instance = clazz.newInstance();
        System.out.println(instance);
//        Class<?> clazz = Class.forName("com.disaster.asm.classwriter.HelloWorld");
//        System.out.println(clazz);
        Class<?> clazz1 = classLoader.loadClass("com.disaster.asm.classwriter.HelloWorld");
        Object instance1 = clazz1.newInstance();
        Method sayHello = instance1.getClass().getMethod("sayHello",String.class);
        System.out.println(sayHello.invoke(instance1, "hh"));
    }
}
