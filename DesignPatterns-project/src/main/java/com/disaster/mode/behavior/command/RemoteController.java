package com.disaster.mode.behavior.command;

public class RemoteController {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command noCommand;

    public RemoteController() {
        this.onCommands = new Command[5];
        this.offCommands = new Command[5];
        for (int i = 0; i < onCommands.length; i++) {
            onCommands[i] = new Nocommand();
            offCommands[i] = new Nocommand();
        }
    }

    public void setCommands(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    public void onButtonWasPushed(int no) {
        onCommands[no].execute();
        noCommand = onCommands[no];
    }

    public void offButtonWasPushed(int no) {
        offCommands[no].execute();
        noCommand = offCommands[no];
    }

    public void undo() {
        noCommand.undo();
    }
}
