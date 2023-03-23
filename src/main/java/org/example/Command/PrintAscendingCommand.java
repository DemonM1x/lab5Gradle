package org.example.Command;

import org.example.Collection.City;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

public class PrintAscendingCommand extends CommandBase implements Command {
    public PrintAscendingCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();
        var cities = commandManager.getCollectionManager().get();
        if(cities == null || cities.size() == 0){
            commandMessageHandler.displayToUser("there is no cities yet.. add a new one");
            return true;
        }
        var coordinatesX = new ArrayList<Integer>();
        var coordinatesY = new ArrayList<Integer>();
        var areas = new ArrayList<Float>();
        var creationDates = new ArrayList<ZonedDateTime>();
        var populations = new ArrayList<Long>();
        var metersAboveSeaLevels = new ArrayList<Double>();
        var governors = new ArrayList<LocalDateTime>();
        for (City city : cities) {
            coordinatesX.add(city.getCoordinates().getX());
            coordinatesY.add(city.getCoordinates().getY());
            areas.add(city.getArea());
            creationDates.add(city.getCreationDate());
            populations.add(city.getPopulation());
            metersAboveSeaLevels.add(city.getMetersAboveSeaLevel());
            governors.add(city.getGovernor().getBirthday());

        }
        String mess = "";
        coordinatesX = (ArrayList<Integer>) coordinatesX.stream().sorted().collect(Collectors.toList());
        coordinatesY = (ArrayList<Integer>) coordinatesY.stream().sorted().collect(Collectors.toList());
        areas = (ArrayList<Float>) areas.stream().sorted().collect(Collectors.toList());
        creationDates = (ArrayList<ZonedDateTime>)creationDates.stream().sorted().collect(Collectors.toList());
        populations = (ArrayList<Long>) populations.stream().sorted().collect(Collectors.toList());
        metersAboveSeaLevels = (ArrayList<Double>) metersAboveSeaLevels.stream().sorted().collect(Collectors.toList());
        governors = (ArrayList<LocalDateTime>) governors.stream().sorted().collect(Collectors.toList());
        mess +="coordinatesX: ";
        for (var coordinateX : coordinatesX){
            mess +=coordinateX.toString()+" ";
        }
            commandMessageHandler.displayToUser(mess+"\n");
        mess = "coordinatesY: ";
        for (var coordinateY : coordinatesY){
            mess += coordinateY.toString()+" ";
        }
        commandMessageHandler.displayToUser(mess+"\n");
        mess = "areas: ";
        for (var area : areas){
            mess += area.toString()+" ";
        }
        commandMessageHandler.displayToUser(mess+"\n");
        mess = "creationDates: ";
        for (var creationDate : creationDates){
            mess += creationDate.toString()+" ";
        }
        commandMessageHandler.displayToUser(mess+"\n");
        mess = "populations: ";
        for (var population : populations){
            mess += population.toString()+" ";
        }
        commandMessageHandler.displayToUser(mess+"\n");
        mess = "metersAboveSeaLevels: ";
        for (var metersAboveSeaLevel : metersAboveSeaLevels){
            mess += metersAboveSeaLevel.toString()+" ";
        }
        commandMessageHandler.displayToUser(mess+"\n");
        mess = "governors: ";
        for (var governor : governors){
            mess += governor.toString()+" ";
        }
        commandMessageHandler.displayToUser(mess+"\n");
        return true;
    }

    @Override
    public String getInfo() {
        return "print sorted elements of the collection";
    }
}
