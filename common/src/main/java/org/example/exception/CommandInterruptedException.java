package org.example.exception;

public class CommandInterruptedException {
    private final String enteredCommand;
    public String getEnteredCommand(){
        return enteredCommand;
    }
    public CommandInterruptedException(String enteredCommand){
        this.enteredCommand = enteredCommand;
    }
}
