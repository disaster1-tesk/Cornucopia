package com.disaster.mode.createtype.prototypemodel.Ordinary;

public class Person {
    private String name;
    private String age;
    private Person son;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, String age, Person son) {
        this.name = name;
        this.age = age;
        this.son = son;
    }

    public Person getSon() {
        return son;
    }

    public void setSon(Person son) {
        this.son = son;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", son=" + son +
                '}';
    }

}
