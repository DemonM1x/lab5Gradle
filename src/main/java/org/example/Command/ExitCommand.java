package org.example.Command;

import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.util.concurrent.TimeUnit;

/**
 * this class represents the exit command,
 * which exit from program without save
 */
public class ExitCommand extends CommandBase implements Command {
    public ExitCommand(CommandManagerCustom commandManager) {
        super(commandManager);
    }

    /**
     * the method exit from program
     * @param args
     * @return
     */
    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();

        try {
            commandMessageHandler.displayToUser("Program will be finished now. ");
            TimeUnit.SECONDS.sleep(3);
            System.exit(0);
        } catch (InterruptedException interruptedException) {
            commandMessageHandler.displayToUser("Program will be finished now.");
            System.exit(0);
        }
        return true;
    }

    @Override
    public String getInfo() {
        return "end the program (without saving to file)";
    }
}
