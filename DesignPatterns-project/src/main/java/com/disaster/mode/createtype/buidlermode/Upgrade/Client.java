package com.disaster.mode.createtype.buidlermode.Upgrade;

public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("i7900","三星")
                .setUsbCount(1)
                .build();
    }
}
