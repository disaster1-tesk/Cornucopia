package com.disaster.asm;

import com.disaster.asm.classwriter.HelloWorldGenerateCore;

public class MyClassLoader extends ClassLoader{
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("com.disaster.asm.classwriter.HelloWorld".equals(name)){
            try {
                byte[] dump = HelloWorldGenerateCore.dump();
                Class<?> clazz = defineClass(name, dump, 0, dump.length);
                return clazz;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("com.disaster.asm.demo.HelloWorld".equals(name)) {
            byte[] bytes = HelloWorldDump.dump();
            Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
            return clazz;
        }
        if ("com.disaster.asm.classwriter.HelloWorld".equals(name)){
            try {
                byte[] dump = HelloWorldGenerateCore.dump();
                Class<?> clazz = defineClass(name, dump, 0, dump.length);
                return clazz;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        throw new ClassNotFoundException("Class Not Found: " + name);
    }
}
