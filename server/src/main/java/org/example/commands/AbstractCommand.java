package org.example.commands;

import org.example.Request;
import org.example.Response;

import java.util.Objects;

public abstract class AbstractCommand implements Execute {
    private final String name;
    private final String description;
    private final String fullname;


    public AbstractCommand(String name, String description  , String fullname ) {
        this.name = name;
        this.description = description;

        this.fullname = fullname;
    }



    @Override
    public String toString() {
        if(Objects.equals(fullname, "")){
            return name + " - " + description;
        }
        return name + " " + fullname + " - " + description;
    }
    @Override
    public abstract Response execute(Request aRequest);
}
