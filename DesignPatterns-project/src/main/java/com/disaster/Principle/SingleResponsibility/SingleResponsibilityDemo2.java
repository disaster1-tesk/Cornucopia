package com.disaster.Principle.SingleResponsibility;

public class SingleResponsibilityDemo2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        WaterVehicle waterVehicle = new WaterVehicle();
        AirVehicle airVehicle = new AirVehicle();
        roadVehicle.run("火车");
        waterVehicle.run("轮船");
        airVehicle.run("飞机");
    }
}

class RoadVehicle {
    public void run(String name) {
        System.out.println(name + "在陆地上跑");
    }
}

class WaterVehicle {
    public void run(String name) {
        System.out.println(name + "在水里跑");
    }
}

class AirVehicle {
    public void run(String name) {
        System.out.println(name + "在天上跑");
    }
}

