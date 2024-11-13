package com.disaster.asm.transform;

import com.disaster.asm.demo.HelloWorld;
import com.disaster.asm.transform.visitor.MethodParameterVisitor2;
import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class MethodParameterTransformCore2 {
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
        ClassVisitor cv = new MethodParameterVisitor2(api, cw);

        //（4）结合 ClassReader 和 ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成 byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);

        HelloWorld helloWorld = new HelloWorld();
        int disaster = helloWorld.test("disaster", 20, 1234567890L, helloWorld);
        System.out.println("disaster = " + disaster);
    }
}
