package com.disaster.mode.createtype.prototypemodel.Clone.ShallowCopy;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person disaster = new Person("com/disaster","20",new Person("son","10"));
        Person disaster1 = (Person) disaster.clone();
        Person disaster2 = (Person) disaster.clone();
        Person disaster3 = (Person) disaster.clone();
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster.hashCode()+"====son===="+disaster.getSon().hashCode());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster1.hashCode()+"====son==="+disaster1.getSon().hashCode());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster2.hashCode()+"====son==="+disaster2.getSon().hashCode());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster3.hashCode()+"====son==="+disaster3.getSon().hashCode());
    }
}
