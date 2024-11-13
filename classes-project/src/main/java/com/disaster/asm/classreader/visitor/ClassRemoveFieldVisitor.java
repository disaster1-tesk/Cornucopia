package com.disaster.asm.classreader.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public class ClassRemoveFieldVisitor extends ClassVisitor {
    private final String fieldName;
    private final String fieldDesc;

    public ClassRemoveFieldVisitor(int api, ClassVisitor cv, String fieldName, String fieldDesc) {
        super(api, cv);
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (name.equals(fieldName) && descriptor.equals(fieldDesc)) {
            // 对于想删除的字段，返回一个 null 值
            return null;
        }
        else {
            // 对于不想删除的字段，正常处理
            return super.visitField(access, name, descriptor, signature, value);
        }
    }
}
