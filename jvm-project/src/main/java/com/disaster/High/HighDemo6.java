package com.disaster.High;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class HighDemo6 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class aClass = Class.forName("java.util.HashMap");
        StringBuffer strs = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(aClass.getModifiers())+" class "+aClass.getSimpleName()+"{\n");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f:declaredFields){
            String s = Modifier.toString(f.getModifiers());
            String simpleName = f.getType().getSimpleName();
            String name = f.getName();
            stringBuffer.append("\t");
            stringBuffer.append(s);
            stringBuffer.append(" ");
            stringBuffer.append(simpleName);
            stringBuffer.append(" ");
            stringBuffer.append(name);
            stringBuffer.append(";");
            stringBuffer.append("\n");
        }
        stringBuffer.append("}");
        System.out.println(stringBuffer);
        System.out.println("----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        strs.append(Modifier.toString(aClass.getModifiers()) + " class " + aClass.getSimpleName() + " {\n");
        Method[] Methods = aClass.getDeclaredMethods();
        for (Method method : Methods) {
            strs.append("\t");
            strs.append(Modifier.toString(method.getModifiers()));
            strs.append(" ");
            strs.append(method.getReturnType().getSimpleName());
            strs.append(" ");
            strs.append("(");
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length > 0) {
                for (Class parameter : parameterTypes) {
                    strs.append(parameter.getSimpleName());
                    strs.append(",");
                }
                strs.deleteCharAt(strs.length() - 1);
                strs.append("){}\n");
            } else {
                strs.append("){}\n");
            }

        }
        strs.append("}");
        System.out.println(strs);
    }
}