package org.example.Command;

import org.example.interfaces.CommandManagerCustom;

public class CommandBase {
    protected CommandManagerCustom commandManager;

    public CommandBase(CommandManagerCustom commandManager){
        this.commandManager = commandManager;
    }
}
