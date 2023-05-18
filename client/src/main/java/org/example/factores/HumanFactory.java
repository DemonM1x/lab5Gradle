package org.example.factores;

import org.example.collection.Human;

import java.time.LocalDateTime;

public class HumanFactory {
    public Human createHuman(String args){
    String[] argsForTime = args.split("-");
    LocalDateTime birthday = LocalDateTime.of(Integer.parseInt(argsForTime[0]),
            Integer.parseInt(argsForTime[1]), Integer.parseInt(argsForTime[2]), 0 , 0 , 0 , 0);
    Human newHuman = new Human(birthday);
    return newHuman;
    }
}
