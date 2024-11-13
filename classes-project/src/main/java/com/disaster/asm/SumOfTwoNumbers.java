package com.disaster.asm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class SumOfTwoNumbers {
    public int sum(int i, int m) {
        return i + m;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
    }

    private static byte[] generate() {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        {
            MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/disaster/asm/AsmSumOfTwoNumbers", null, "java/lang/Object", null);
            // 添加方法;修饰符、方法名、描述符、签名、异常
            MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sum", "(II)I", null, null);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
            methodVisitor.visitInsn(Opcodes.IADD);
            // 返回
            methodVisitor.visitInsn(Opcodes.IRETURN);
            // 设置操作数栈的深度和局部变量的大小
            methodVisitor.visitMaxs(2, 3);
            methodVisitor.visitEnd();
        }
        // 类完成
        classWriter.visitEnd();
        // 生成字节数组
        return classWriter.toByteArray();
    }
}