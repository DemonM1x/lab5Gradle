package org.example.Exceptions;

public class CommandInterruptionException extends Exception{
    private final String enteredCommand;
    public String getEnteredCommand(){
        return enteredCommand;
    }
    public CommandInterruptionException(String enteredCommand){
        this.enteredCommand = enteredCommand;
    }
}
