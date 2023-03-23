package org.example.Command;

import org.example.CommandManager;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.time.format.DateTimeFormatter;

public class InfoCommand extends CommandBase implements Command {
    public InfoCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        var cities = commandManager.getCollectionManager().get();
        var collection = commandManager.getCollectionManager();
        var time =collection.getInitializationTime();
        var toPrint = String.format("type of collection %s\ninitialization date: %s\nnumber of element: %s",
                collection.getElementType(),time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), cities.size());
        System.out.println(toPrint);
        return true;
    }

    @Override
    public String getInfo() {
        return "prints information about the collection to the standard output stream"
        +"(type, initialization date, number of elements, etc.)";
    }
}
