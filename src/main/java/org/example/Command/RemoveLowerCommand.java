package org.example.Command;

import org.example.CommandManager;
import org.example.Exceptions.CommandInterruptionException;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.util.NoSuchElementException;

public class RemoveLowerCommand extends CommandBase implements Command {
    public RemoveLowerCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();
        try {


            var inputService = commandManager.getInputService();
            var cities = commandManager.getCollectionManager().get();
            var coord = inputService.inputCoordinates();
            var area = inputService.inputArea();
            var population = inputService.inputPopulation();
            var metersAboveSeaLevel = inputService.getDouble();
            for (var city : cities){
                if (coord.getX() > city.getCoordinates().getX() && coord.getY() > city.getCoordinates().getY() &&
                        area > city.getArea() && population > city.getPopulation() && metersAboveSeaLevel > city.getPopulation()){
                    cities.remove(city);
                }
            }
            commandMessageHandler.displayToUser("all items that are less than the specified one have been successfully deleted");
        } catch (NoSuchElementException exception){
            commandMessageHandler.displayToUser("remove city was canceled.");
        }
        catch (CommandInterruptionException e){
            commandMessageHandler.displayToUser("remove city was canceled by entered command.");
        }
        return true;
    }

    @Override
    public String getInfo() {
        return "removes all items smaller than the specified one from the collection";
    }
}
