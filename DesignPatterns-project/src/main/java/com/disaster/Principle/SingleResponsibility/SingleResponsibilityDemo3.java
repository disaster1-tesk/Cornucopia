package com.disaster.Principle.SingleResponsibility;
/*
这种方式虽然不是完全遵守单一职责原则，但是在某种情况下，方法遵循单一职责原则也是可以的
 */
public class SingleResponsibilityDemo3 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.roadRun("火车");
        vehicle2.waterRun("轮船");
        vehicle2.airRun("飞机");
    }
}

class Vehicle2{
    public void roadRun(String name){
        System.out.println(name+"在地上跑");
    }
    public void waterRun(String name){
        System.out.println(name+"在水里跑");
    }
    public void airRun(String name){
        System.out.println(name+"在天上跑");
    }
}