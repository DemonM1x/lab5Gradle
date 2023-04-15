package org.example.сommand;

import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

public class SaveCommand extends CommandBase implements Command {
    /**
     *  this class represents the save command,
     *  which save the collection to file
     * @param commandManager
     */
    public SaveCommand(CommandManagerCustom commandManager){
        super(commandManager);
    }

    /**
     * the method calls the collectionManager method save, which save collection to file
     * @param args
     * @return
     */
    @Override
    public boolean execute(String[] args) {
        commandManager.getCollectionManager().save();
        return true;
    }

    @Override
    public String getInfo() {
        return "save the collection to file";
    }
}
