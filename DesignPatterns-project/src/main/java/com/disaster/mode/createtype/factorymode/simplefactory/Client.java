package com.disaster.mode.createtype.factorymode.simplefactory;

public class Client {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Car car = vehicleFactory.createCar();
        car.move();
    }
}
