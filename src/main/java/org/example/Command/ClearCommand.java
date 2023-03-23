package org.example.Command;

import org.example.CommandManager;
import org.example.interfaces.Command;

public class ClearCommand extends CommandBase implements Command {
    public ClearCommand(CommandManager commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        var cities =commandManager.getCollectionManager().get();
        var numbOfLoops = cities.size();
        for (int i = 0; i < numbOfLoops; i++){
            commandManager.executeCommand("remove_by_id" + cities.last().getId());
        }
        return true;
    }

    @Override
    public String getInfo() {
        return "clear the collection";
    }
}
