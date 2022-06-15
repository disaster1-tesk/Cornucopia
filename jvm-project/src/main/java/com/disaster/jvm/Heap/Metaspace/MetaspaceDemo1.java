package com.disaster.jvm.Heap.Metaspace;/*
package com.disaster.jvm.Heap.Metaspace;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

public class MetaspaceDemo1 extends ClassLoader{
    public static void main(String[] args) {
        int j = 0;
        try {
            MetaspaceDemo1 test = new MetaspaceDemo1();
            for (int i = 0; i < 100000; i++) {
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_6,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                byte[] bytes = classWriter.toByteArray();
                test.defineClass("Class"+i,bytes,0,bytes.length);
                j++;
            }
        }finally {
            System.out.println(j);
        }
    }
}
*/
