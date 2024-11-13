package com.disaster.asm.classreader;

import com.disaster.asm.classreader.visitor.ClassAddMethodVisitor;
import com.disaster.asm.demo.HelloWorld;
import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.*;

import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

public class ClassAddMethodCore {
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
        ClassVisitor cv = new ClassAddMethodVisitor(api, cw, Opcodes.ACC_PUBLIC, "mul", "(II)I", null, null) {
            @Override
            protected void generateMethodBody(MethodVisitor mv) {
                mv.visitCode();
                mv.visitVarInsn(ILOAD, 1);
                mv.visitVarInsn(ILOAD, 2);
                mv.visitInsn(IMUL);
                mv.visitInsn(IRETURN);
                mv.visitMaxs(2, 3);
                mv.visitEnd();
            }
        };

        //（4）结合 ClassReader 和 ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成 byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);
        Class<?> clazz = Class.forName("com.disaster.asm.demo.HelloWorld");
        System.out.println(clazz.getName());

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println("    " + m.getName());
        }


    }
}
