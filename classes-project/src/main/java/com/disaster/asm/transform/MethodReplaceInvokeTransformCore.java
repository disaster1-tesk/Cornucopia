package com.disaster.asm.transform;

import com.disaster.asm.transform.visitor.MethodReplaceInvokeVisitor;
import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.*;



public class MethodReplaceInvokeTransformCore {
    public static void main(String[] args) {
        String relative_path = "com/disaster/asm/demo/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes1 = FileUtils.readBytes(filepath);

        //（1）构建 ClassReader
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建 ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //（3）串连 ClassVisitor
        int api = Opcodes.ASM9;
        ClassVisitor cv = new MethodReplaceInvokeVisitor(api, cw,
                "java/lang/Math", "max", "(II)I",
                Opcodes.INVOKESTATIC, "java/lang/Math", "min", "(II)I");

        ClassVisitor cv1 = new MethodReplaceInvokeVisitor(api, cv,
                "java/io/PrintStream", "println", "(I)V",
                Opcodes.INVOKESTATIC, "com/disaster/asm/demo/ParameterUtils", "output", "(Ljava/io/PrintStream;I)V");

        //（4）结合 ClassReader 和 ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv1,parsingOptions);

        //（5）生成 byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);
    }
}
