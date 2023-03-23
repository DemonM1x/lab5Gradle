package org.example.Command;

import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

public class MinByIdCommand extends CommandBase implements Command {
    public MinByIdCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        var commanMessageHendler = commandManager.getMessageHandler();
        var cities = commandManager.getCollectionManager().get();
        var city = cities.first();
        String mess = "governor: \n birthday: " + city.getGovernor().getBirthday().toString();
        commanMessageHendler.displayToUser(mess +"\n");
        return true;
    }

    @Override
    public String getInfo() {
        return "outputs the birthday of the governor from the collection, the value of the id field of which is the minimum";
    }
}
