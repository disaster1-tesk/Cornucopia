package com.disaster.asm.util;

import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;

import static org.objectweb.asm.Opcodes.*;

public class ClassCheckCore {
    public static void main(String[] args) throws Exception {
        String relative_path = "sample/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);

        // (1) 生成 byte[] 内容
        byte[] bytes = dump();

        // (2) 保存 byte[] 到文件
        FileUtils.writeBytes(filepath, bytes);
    }

    public static byte[] dump() throws Exception {
        // (1) 创建 ClassWriter 对象
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new CheckClassAdapter(cw);

        // (2) 调用 visitXxx() 方法
        cv.visit(V1_8, ACC_PUBLIC + ACC_SUPER, "sample/HelloWorld",
                null, "java/lang/Object", null);

        {
            FieldVisitor fv = cv.visitField(ACC_PRIVATE, "intValue", "I", null, null);
            fv.visitEnd();
        }

        {
            MethodVisitor mv1 = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitMaxs(1, 1);
            mv1.visitEnd();
        }

        {
            MethodVisitor mv2 = cv.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            mv2.visitCode();
            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv2.visitLdcInsn("Hello World");
            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            //错误visitor
//            mv2.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            mv2.visitLdcInsn("Hello World");
//            mv2.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv2.visitInsn(RETURN);
//            mv2.visitMaxs(2, 1);
            mv2.visitEnd();
        }
        cv.visitEnd();

        // (3) 调用 toByteArray() 方法
        return cw.toByteArray();

    }
}
