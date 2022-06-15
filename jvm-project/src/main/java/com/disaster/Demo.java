package com.disaster;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author Disaster
 * @Date 2022/4/28 22:48
 * @Version 1.0
 **/
public class Demo {
    private String name;
    private Integer age;

    public Demo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Demo() {
        super();
    }

    public Demo(String name) {
        this(name,null);
        this.name = name;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"disaster","disaster2","disaster","disaster3"};
        System.out.println(getIndexForStr(strings, "disaster"));
    }
    public void sayHello(){

    }
    public static Integer getIndexForStr(String[] srcStr,String targetStr){
        List<String> strings = Arrays.asList(srcStr);
        boolean contains = strings.contains(targetStr);
        if (contains){
            return strings.indexOf(targetStr);
        }
        return -1;
    }
}
