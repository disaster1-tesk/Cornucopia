package com.disaster.mode.createtype.factorymethod.v1;

public class AircraftFactory extends Factory {
    @Override
    public MoveAble getVehicle() {
        return new Aircraft();
    }
}
