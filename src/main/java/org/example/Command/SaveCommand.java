package org.example.Command;

import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

public class SaveCommand extends CommandBase implements Command {
    public SaveCommand(CommandManagerCustom commandManager){
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        commandManager.getCollectionManager().save();
        commandManager.getMessageHandler().displayToUser("collection was successfully saved");
        return true;
    }

    @Override
    public String getInfo() {
        return "save the collection to file";
    }
}
