package com.disaster.mode.createtype.factorymethod.v1;

public class Client {
    public static void main(String[] args) {
        Factory aircraftFactory = new AircraftFactory();
        aircraftFactory.createVehicle();
    }
}
