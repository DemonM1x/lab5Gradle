package org.example.сommand;

import org.example.interfaces.CommandManagerCustom;

/**
 * Base command class, host commandManager
 */
public class CommandBase {
    protected CommandManagerCustom commandManager;

    /**
     * Base constructor
     * @param commandManager
     */
    public CommandBase(CommandManagerCustom commandManager){
        this.commandManager = commandManager;
    }
}
