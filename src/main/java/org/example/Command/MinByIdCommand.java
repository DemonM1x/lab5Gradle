package org.example.Command;

import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

/**
 * this class represents the min_by_id command,
 * which display some object from element with minimal id
 */
public class MinByIdCommand extends CommandBase implements Command {
    public MinByIdCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    /**
     * the method display to user governor's birthday from element with minimal id
     * @param args
     * @return
     */
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
