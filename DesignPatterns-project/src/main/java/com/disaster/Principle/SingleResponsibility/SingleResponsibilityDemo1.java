package com.disaster.Principle.SingleResponsibility;

/*
    引发出单一职责原则
 */
public class SingleResponsibilityDemo1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("飞机");
        vehicle.run("火车");
        vehicle.run("轮船");

    }
}

class Vehicle {
    public void run(String name) {
        System.out.println(name+"在跑");
    }
}
