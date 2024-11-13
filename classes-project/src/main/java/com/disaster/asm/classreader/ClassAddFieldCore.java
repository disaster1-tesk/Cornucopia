package com.disaster.asm.classreader;

import com.disaster.asm.classreader.visitor.ClassAddFieldVisitor;
import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Field;

public class ClassAddFieldCore {
    public static void main(String[] args) throws ClassNotFoundException {
        String relative_path = "com/disaster/asm/demo/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes1 = FileUtils.readBytes(filepath);

        //（1）构建 ClassReader
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建 ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //（3）串连 ClassVisitor
        int api = Opcodes.ASM9;
        ClassVisitor cv = new ClassAddFieldVisitor(api, cw, Opcodes.ACC_PUBLIC, "objValue", "Ljava/lang/Object;");

        //（4）结合 ClassReader 和 ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成 byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);

        Class<?> clazz = Class.forName("com.disaster.asm.demo.HelloWorld");
        System.out.println(clazz.getName());

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println("    " + f.getName());
        }
    }
}
