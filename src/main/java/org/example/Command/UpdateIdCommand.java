package org.example.Command;

import org.example.Collection.City;
import org.example.Exceptions.CommandInterruptionException;
import org.example.Service.InputService;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.util.InputMismatchException;

public class UpdateIdCommand extends CommandBase implements Command {
    private final InputService inputService;
    {
        inputService = commandManager.getInputService();
    }
    public UpdateIdCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();
        try {
            int id = Integer.parseInt(args[0]);
            var cities = commandManager.getCollectionManager().get();
            if (id <= 0){
                commandMessageHandler.displayToUser("ID must be an number greater than 0. Try typing this command again");
                return false;
            }
            commandMessageHandler.displayToUser("updating product with id: " + id);
            for (City city : cities){
                int intId = city.getId();
                if (intId == id){
                    var name = inputService.inputName();
                    var coord = inputService.inputCoordinates();
                    var area = inputService.inputArea();
                    var population = inputService.inputPopulation();
                    var metersAboveSeaLevel = inputService.getDouble();
                    var climate = inputService.inputClimate();
                    var governor = inputService.getPerson();
                    int yesOrNoGov = 0;
                    int yesOrNoStandard = 0;
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
                    var cit = new City(id, name, coord, area, population,
                            metersAboveSeaLevel, climate, yesOrNoGov == 1 ? inputService.inputGovernment(): null,
                            yesOrNoStandard == 1 ? inputService.inputStandardOfLiving() : null, governor);
                    cities.remove(city);
                    cities.add(cit);
                    commandMessageHandler.displayToUser("Element was updated");
                    return true;
                }
                }

        } catch (NumberFormatException exception){
            System.out.println("ID must be a number. Try again.");
        } catch (CommandInterruptionException e){
            commandMessageHandler.displayToUser("updating product was canceled by entered command");
            commandManager.executeCommand(e.getEnteredCommand());
        }
        System.out.println("Element with this ID is not defined. Try again.");
        return true;
    }

    @Override
    public String getInfo() {
        return "update the element`s value, whose ID is equal to the given." +
                " You should enter ID after entering a command.";
    }
}
