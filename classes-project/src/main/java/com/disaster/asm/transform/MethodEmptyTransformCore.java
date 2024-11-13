package com.disaster.asm.transform;

import com.disaster.asm.transform.visitor.MethodEmptyBodyVisitor;
import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import org.objectweb.asm.*;


public class MethodEmptyTransformCore {
    public static void main(String[] args) {
        String relative_path = "sample/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes1 = FileUtils.readBytes(filepath);

        //（1）构建 ClassReader
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建 ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //（3）串连 ClassVisitor
        int api = Opcodes.ASM9;
        ClassVisitor cv = new MethodEmptyBodyVisitor(api, cw, "test", "()V");

        //（4）结合 ClassReader 和 ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成 byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);
    }
}
