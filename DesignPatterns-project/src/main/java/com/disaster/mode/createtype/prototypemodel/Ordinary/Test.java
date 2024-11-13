package com.disaster.mode.createtype.prototypemodel.Ordinary;

public class Test {
    public static void main(String[] args) {
        Person disaster = new Person("com/disaster", "20",new Person("disaster-son","10"));
        Person disaster1 = new Person(disaster.getName(),disaster.getAge());
        Person disaster2 = new Person(disaster.getName(),disaster.getAge());
        Person disaster3 = new Person(disaster.getName(),disaster.getAge());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster.hashCode()+"===="+disaster.getSon());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster1.hashCode()+"===="+disaster1.getSon());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster2.hashCode()+"===="+disaster2.getSon());
        System.out.println("disaster:"+disaster.toString()+"===="+"hashcode:"+disaster3.hashCode()+"===="+disaster3.getSon());
    }
}
