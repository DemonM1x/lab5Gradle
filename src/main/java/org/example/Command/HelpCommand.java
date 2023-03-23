package org.example.Command;

import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

public class HelpCommand extends CommandBase implements Command {
    public HelpCommand(CommandManagerCustom commandManager){
        super(commandManager);
    }
    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();

        for (var commInfo : commandManager.getCommandsInfo()
        ) {
            commandMessageHandler.displayToUser(commInfo);
        }
        return true;
    }

    @Override
    public String getInfo() {
        return "print all elements in string representation to standard output";
    }
}
