package com.disaster.asm.type;

import com.disaster.asm.ASMPrint;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.Printer;

public class TypeCore {
    public static void main(String[] args) {
        Type t = Type.getType("Ljava/lang/String;");

        int sort = t.getSort();                    // ASM
        String className = t.getClassName();       // Java File
        String internalName = t.getInternalName(); // Class File
        String descriptor = t.getDescriptor();     // Class File

        System.out.println(sort);         // 10，它对应于 Type.OBJECT 字段
        System.out.println(className);    // java.lang.String   注意，分隔符是“.”
        System.out.println(internalName); // java/lang/String   注意，分隔符是“/”
        System.out.println(descriptor);   // Ljava/lang/String; 注意，分隔符是“/”，前有“L”，后有“;”

        //通过class获取type
        Type type = Type.getType(ASMPrint.class);
        System.out.println(type.getSort());
        System.out.println(type.getClassName());
        System.out.println(type.getInternalName());
        System.out.println(type.getDescriptor());

        //通过全限定字符获取类型
        Type t1 = Type.getType("Ljava/lang/String;");
        System.out.println(t1);

        // 这里是方法的描述符
        Type t2 = Type.getMethodType("(II)I");
        System.out.println(t2);

        // 通过internal name 获取 Type 对象
        Type t3 = Type.getObjectType("java/lang/String");
        System.out.println(t3);

        //通过静态字段获取type对象
        Type t4 = Type.INT_TYPE;
        System.out.println(t4);


        //数组类型处理
        Type t5 = Type.getType("[[[[[Ljava/lang/String;");
        int dimensions = t5.getDimensions();
        Type elementType = t5.getElementType();
        System.out.println(dimensions);    // 5
        System.out.println(elementType);   // Ljava/lang/String;

        //方法类型处理
        Type methodType = Type.getMethodType("(Ljava/lang/String;I)V");
        String descriptor1 = methodType.getDescriptor();
        Type[] argumentTypes = methodType.getArgumentTypes();
        Type returnType = methodType.getReturnType();
        System.out.println("Descriptor: " + descriptor1);
        System.out.println("Argument Types:");
        for (Type type1 : argumentTypes) {
            System.out.println("    " + type1);
        }
        System.out.println("Return Type: " + returnType);


        Type t6 = Type.FLOAT_TYPE;

        int[] opcodes = new int[]{
                Opcodes.IALOAD,
                Opcodes.IASTORE,
                Opcodes.ILOAD,
                Opcodes.ISTORE,
                Opcodes.IADD,
                Opcodes.ISUB,
                Opcodes.IRETURN,
        };

        for (int oldOpcode : opcodes) {
            int newOpcode = t6.getOpcode(oldOpcode);

            String oldName = Printer.OPCODES[oldOpcode];
            String newName = Printer.OPCODES[newOpcode];

            System.out.printf("%-7s --- %-7s%n", oldName, newName);
        }

    }
}
