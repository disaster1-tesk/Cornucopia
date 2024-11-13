package com.disaster.mode.createtype.prototypemodel.Serializable;

import java.io.Serializable;

public class Son implements Cloneable,Serializable {
    private String name;
    private int age;

    public Son(String name, int age) {
        this.name = name;
        this.age = age;
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

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        Son son = null;
        son = (Son) super.clone();
        return son;
    }*/
}
