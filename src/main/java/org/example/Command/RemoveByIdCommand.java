package org.example.Command;

import org.example.Collection.City;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

public class RemoveByIdCommand extends CommandBase implements Command {

    public RemoveByIdCommand(CommandManagerCustom commandManager){
        super(commandManager);
    }
    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();
        try {


            var id = Integer.parseInt(args[0]);
            var cities = commandManager.getCollectionManager().get();
            if (cities.stream().map(City::getId).toList().contains(id)) {
                var prodWithId = cities.stream().filter(p -> p.getId() == id).findFirst();
                if (prodWithId.isEmpty())
                    throw new NumberFormatException();
                cities.remove(prodWithId.get());
                return true;
            }
            commandMessageHandler.displayToUser("Element with this id doesnt exist");
            return false;
        }
         catch(NumberFormatException exception){
                commandMessageHandler.displayToUser("ID must be an number. Try typing this command again");
                return false;
            }
    }
    @Override
    public String getInfo() {
        return "remove an element from the collection by its ID.";
    }
}
