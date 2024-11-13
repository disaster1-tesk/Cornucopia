package com.disaster.mode.createtype.factorymode.simplefactory;

public class VehicleFactory {
    public Car createCar(){
        return new Car();
    }
    public Aircraft createAircraft(){
        return new Aircraft();
    }
    public Ship createShip(){
        return new Ship();
    }
}
