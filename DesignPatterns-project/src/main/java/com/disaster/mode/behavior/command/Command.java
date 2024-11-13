package com.disaster.mode.behavior.command;

public interface Command {
    //执行操作
    void execute();
    //撤销操作
    void undo();
}
