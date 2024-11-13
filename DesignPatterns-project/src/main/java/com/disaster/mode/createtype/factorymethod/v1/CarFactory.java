package com.disaster.mode.createtype.factorymethod.v1;

public  class CarFactory extends Factory{
    @Override
    public MoveAble getVehicle() {
        return new Car();
    }
}
