package org.example;

import org.example.collection.City;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {

    private final String commandName;
    private final ArrayList<String> argName;
    private City city;

    public Request(String aCommand, ArrayList<String> aArgs) {
        commandName = aCommand;
        argName = aArgs;
        city = null;

    }

    public Request addCity(City aCity) {
        city = aCity;
        return this;
    }


    public String getCommand() {
        return commandName;
    }

    public ArrayList<String> getArg() {
        return argName;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return commandName + " "
                + (argName != null ? argName : "")
                + (city != null ? "\n"+city : "");
    }

}
