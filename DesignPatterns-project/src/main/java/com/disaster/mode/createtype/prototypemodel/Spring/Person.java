package com.disaster.mode.createtype.prototypemodel.Spring;

/**
 * @ClassName Person
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 19:53
 * @Version 1.0
 **/
public class Person {
    private String name = "com/disaster";
    private int age = 20;
    private int height = 180;

    public Person() {
        System.out.println("Person 创建....");
    }

    public Person(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
