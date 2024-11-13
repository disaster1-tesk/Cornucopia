package com.disaster.mode.createtype.factorymethod.v1;

public class ShipFactory extends Factory {
    @Override
    public MoveAble getVehicle() {
        return new Ship();
    }
}
