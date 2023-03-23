package org.example.Command;

import org.example.Collection.City;
import org.example.Exceptions.CommandInterruptionException;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.util.NoSuchElementException;

public class StartsNameCommand extends CommandBase implements Command {
    public StartsNameCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();

        try {

            int count = 0;
            var cities = commandManager.getCollectionManager().get();
            var inputService = commandManager.getInputService();
            var name = inputService.inputName();
            if (cities == null || cities.size() == 0) {
                commandMessageHandler.displayToUser("there is no cities yet.. add a new one");
                return true;
            }
            for (City city : cities) {
                if (city.getName().startsWith(name)) {
                    count+=1;
                    String mess = city.toString();
                    commandMessageHandler.displayToUser(mess); //TODO:
                }
            }
            if (count == 0){
                commandMessageHandler.displayToUser("there is no city starting with the specified substring");
            }

        } catch (NoSuchElementException exception){
            commandMessageHandler.displayToUser("printing city was canceled");
        }
        catch (CommandInterruptionException e){
            commandMessageHandler.displayToUser("printing city was canceled by entered command");
            commandManager.executeCommand(e.getEnteredCommand());
        }
        return true;
    }

    @Override
    public String getInfo() {
        return "outputs elements whose name field value starts with the specified substring";
    }
}
