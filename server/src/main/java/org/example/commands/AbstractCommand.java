package org.example.commands;

import org.example.Request;
import org.example.Response;

public abstract class AbstractCommand implements Execute {
    private String name;
    private String description;
    private String fullname;


    public AbstractCommand(String name, String description  , String fullname ) {
        this.name = name;
        this.description = description;

        this.fullname = fullname;
    }



    @Override
    public String toString() {
        if(fullname == ""){
            return name + " - " + description;
        }
        return name + " " + fullname + " - " + description;
    }
    @Override
    public abstract Response execute(Request aRequest);
}
