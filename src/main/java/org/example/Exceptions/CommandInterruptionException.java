package org.example.Exceptions;

/**
 * it is thrown when the command is interrupted
 */
public class CommandInterruptionException extends Exception{
    private final String enteredCommand;
    public String getEnteredCommand(){
        return enteredCommand;
    }
    public CommandInterruptionException(String enteredCommand){
        this.enteredCommand = enteredCommand;
    }
}
