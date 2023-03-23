package org.example.Command;

import org.example.CommandManager;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

public class UndoCommand extends CommandBase implements Command {
    public UndoCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
   //     var numberOfCommands = Integer.parseInt(args[0]);
     //   commandManager.getUndoManager().undoCommands(numberOfCommands);
       // commandManager.getMessageHandler().displayToUser(numberOfCommands + " commands were successfully canceled");
        return true;
    }

    @Override
    public String getInfo() {
        return "return to the collection state that it wast in N commands ago";
    }
}
