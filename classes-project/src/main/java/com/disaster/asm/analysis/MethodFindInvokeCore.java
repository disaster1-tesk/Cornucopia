package com.disaster.asm.analysis;

import com.disaster.asm.analysis.visit.MethodFindInvokeVisitor;
import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class MethodFindInvokeCore {
    public static void main(String[] args) {
        String relative_path = "com/disaster/asm/analysis/demo/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = FileUtils.readBytes(filepath);

        //（1）构建 ClassReader
        ClassReader cr = new ClassReader(bytes);

        //（2）分析 ClassVisitor
        int api = Opcodes.ASM9;
        ClassVisitor cv = new MethodFindInvokeVisitor(api, null, "test", "(II)V");

        //（3）结合 ClassReader 和 ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);
    }
}
