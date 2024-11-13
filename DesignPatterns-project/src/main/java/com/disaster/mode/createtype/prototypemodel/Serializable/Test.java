package com.disaster.mode.createtype.prototypemodel.Serializable;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person disaster = new Person("com/disaster","20",new Son("son",10));
        Person disaster1 = (Person) disaster.deepCopy();
        Person disaster2 = (Person) disaster.deepCopy();
        Person disaster3 = (Person) disaster.deepCopy();
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster.hashCode()+"====son===="+disaster.getSon().hashCode());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster1.hashCode()+"====son==="+disaster1.getSon().hashCode());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster2.hashCode()+"====son==="+disaster2.getSon().hashCode());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster3.hashCode()+"====son==="+disaster3.getSon().hashCode());
    }
}
