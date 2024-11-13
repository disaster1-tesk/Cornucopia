package com.disaster.asm;

import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class HelloWorldDump implements Opcodes {
    public static byte[] dump() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "com/disaster/asm/demo/HelloWorld", null, "java/lang/Object", null);
        Label L0 = new Label();
        L0.info = "L0";
        Label L1 = new Label();
        L1.info = "L1";
        {
            MethodVisitor mv1 = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv1.visitCode();
            mv1.visitLineNumber(3, L0);
            mv1.visitVarInsn(ALOAD, 0);
            mv1.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv1.visitInsn(RETURN);
            mv1.visitLocalVariable("this", "Lcom/disaster/asm/demo/HelloWorld;", null, L0, L1, 0);
            mv1.visitMaxs(1, 1);
            mv1.visitEnd();
        }
        {
            MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
            mv2.visitCode();
            mv2.visitLineNumber(6, L0);
            mv2.visitLdcInsn("This is a HelloWorld object.");
            mv2.visitInsn(ARETURN);
            mv2.visitLocalVariable("this", "Lcom/disaster/asm/demo/HelloWorld;", null, L0, L1, 0);
            mv2.visitMaxs(1, 1);
            mv2.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
