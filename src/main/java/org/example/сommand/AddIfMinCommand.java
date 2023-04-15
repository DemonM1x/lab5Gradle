package org.example.сommand;

import org.example.сollection.City;
import org.example.exceptions.CommandInterruptionException;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 *  this class represents the add_if_min command,
 *  which add new element to the collection if element is minimal
 */
public class AddIfMinCommand extends CommandBase implements Command {
    public AddIfMinCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    /**
     * the method adds an element to the collection if element is minimal
     * @param args
     * @return
     */
    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();
        try {
            var inputService = commandManager.getInputService();
            commandMessageHandler.displayToUser("adding city..");
            var maxId = Integer.MIN_VALUE;
            var cities = commandManager.getCollectionManager().get();
            for (var city : cities) {
                maxId = Integer.max(maxId, city.getId());
            }
            var id = cities.size() == 0 ? 1 : maxId + 1;

            var name = inputService.inputName();
            var coord = inputService.inputCoordinates();
            var area = inputService.inputArea();
            var population = inputService.inputPopulation();
            var metersAboveSeaLevel = inputService.getDouble();
            var climate = inputService.inputClimate();
            var governor = inputService.getPerson();
            int yesOrNoGov = 0;
            int yesOrNoStandard = 0;
            var minCoordX = Integer.MAX_VALUE;
            var minCoordY = Integer.MAX_VALUE;
            var minArea = Float.MAX_VALUE;
            var minPopulation = Long.MAX_VALUE;
            var minMetersAboveSeaLevel = Double.MAX_VALUE;




            for( ; ; ) {
                try {
                    commandMessageHandler.displayToUser("enter a number: ");
                    commandMessageHandler.displayToUser("should we add government? enter the number: 1 - Yes or 2 - No");
                    yesOrNoGov = commandManager.getInputService().getInt();
                    if(yesOrNoGov != 1 && yesOrNoGov != 2)
                        continue;
                    if(yesOrNoGov == 2)
                        commandMessageHandler.displayToUser("government will not be defined");
                    break;
                } catch (InputMismatchException e) {
                    commandMessageHandler.displayToUser("enter a number: ");
                }
            }
            for( ; ; ) {
                try {
                    commandMessageHandler.displayToUser("enter a number: ");
                    commandMessageHandler.displayToUser("should we add StandardOfLiving? enter the number: 1 - Yes or 2 - No");
                    yesOrNoStandard = commandManager.getInputService().getInt();
                    if(yesOrNoStandard != 1 && yesOrNoStandard != 2)
                        continue;
                    if(yesOrNoStandard == 2)
                        commandMessageHandler.displayToUser("StandardOfLiving will not be defined");
                    break;
                } catch (InputMismatchException e) {
                    commandMessageHandler.displayToUser("enter a number: ");
                }
            }
            for(var city : cities){
                minCoordX = Integer.min(minCoordX, city.getCoordinates().getX());
                minCoordY = Integer.min(minCoordY, city.getCoordinates().getY());
                minArea = Float.min(minArea, city.getArea());
                minPopulation = Long.min(minPopulation, city.getPopulation());
                minMetersAboveSeaLevel = Double.min(minMetersAboveSeaLevel, city.getMetersAboveSeaLevel());
            }
            if (coord.getX() < minCoordX && coord.getY() < minCoordY && area < minArea &&
                    population < minPopulation && metersAboveSeaLevel < minMetersAboveSeaLevel){
                var city = new City(id, name, coord, area, population,
                        metersAboveSeaLevel, climate, yesOrNoGov == 1 ? inputService.inputGovernment(): null,
                        yesOrNoStandard == 1 ? inputService.inputStandardOfLiving() : null, governor);


                cities.add(city);

            }
            else{
                commandMessageHandler.displayToUser("adding city was canceled. The element is not minimal");
            }
        } catch (NoSuchElementException exception){
            commandMessageHandler.displayToUser("adding city was canceled");
        }
        catch (CommandInterruptionException e){
            commandMessageHandler.displayToUser("adding city was canceled by entered command");
            commandManager.executeCommand(e.getEnteredCommand());
        }
        return true;
    }

    @Override
    public String getInfo() {
        return "add new element to the collection if element is minimal";
    }
}
