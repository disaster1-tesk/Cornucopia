package com.disaster.asm.transform;

import com.disaster.asm.demo.HelloWorld;
import com.disaster.asm.transform.visitor.MethodTimerVisitor;
import com.disaster.asm.transform.visitor.MethodTimerVisitor2;
import com.disaster.asm.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Field;
import java.util.Random;

public class MethodTimerTransformCore2 {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        // 编写代码实现 MethodTimerTransformCore 类
        // 1. 读取 HelloWorld.class 字节码
        // 2. 创建 MethodTimerVisitor 并对 HelloWorld 类进行 visit 处理
        // 3. 编写 ClassWriter 并将 HelloWorld 类中的字节码写入到 HelloWorld_timer.class
        String relative_path = "com/disaster/asm/demo/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes1 = FileUtils.readBytes(filepath);

        //（1）构建 ClassReader
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建 ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //（3）串连 ClassVisitor
        int api = Opcodes.ASM9;
        ClassVisitor cv = new MethodTimerVisitor2(api, cw);

        //（4）结合 ClassReader 和 ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成 byte[]
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);

        HelloWorld instance = new HelloWorld();
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            boolean flag = rand.nextBoolean();
            int a = rand.nextInt(50);
            int b = rand.nextInt(50);
            if (flag) {
                int c = instance.add(a, b);
                String line = String.format("%d + %d = %d", a, b, c);
                System.out.println(line);
                // 第二部分，来查看方法运行的时间
                printTimer();
            } else {
                int c = instance.sub(a, b);
                String line = String.format("%d - %d = %d", a, b, c);
                System.out.println(line);
                // 第二部分，来查看方法运行的时间
                printTimer();
            }
        }

        // 第二部分，来查看方法运行的时间
        printTimer();

    }

    private static void printTimer() throws IllegalAccessException {
        Class<?> clazz = HelloWorld.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            String fieldName = f.getName();
            f.setAccessible(true);
            if (fieldName.startsWith("timer")) {
                Object FieldValue = f.get(null);
                System.out.println(fieldName + " = " + FieldValue);
            }
        }
    }
}
