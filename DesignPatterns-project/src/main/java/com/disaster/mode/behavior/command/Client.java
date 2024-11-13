package com.disaster.mode.behavior.command;

public class Client {
    public static void main(String[] args) {
        RemoteController remoteController = new RemoteController();
        LightReceiver lightReceiver = new LightReceiver();
        Command lightOffCommand = new LightOffCommand(lightReceiver);
        Command lightOnCommand = new LightOnCommand(lightReceiver);
        remoteController.setCommands(0, lightOnCommand, lightOffCommand);
        remoteController.onButtonWasPushed(0);
        remoteController.offButtonWasPushed(0);
        remoteController.undo();
    }
}
