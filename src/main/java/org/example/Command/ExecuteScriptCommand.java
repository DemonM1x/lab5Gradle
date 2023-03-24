package org.example.Command;

import org.example.Collection.City;
import org.example.Container.CommandsContainer;
import org.example.Exceptions.CommandInterruptionException;
import org.example.MessageHandler;
import org.example.XmlFileHandler;
import org.example.interfaces.CollectionCustom;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import java.io.*;
import java.util.*;

/**
 * this class represents the execute_script command,
 * which reads and executes a script from the specified file
 */
public class ExecuteScriptCommand extends CommandBase implements Command {


    private final LinkedList<String> scriptFilesBeingExecuted;
    private int recDepth = -1;
    public ExecuteScriptCommand(CommandManagerCustom commandManager) {
        super(commandManager);
        scriptFilesBeingExecuted = new LinkedList<>();
    }

    /**
     * the method that is called when catching some error
     * @return
     * @throws CommandInterruptionException
     */
    private int specifyRecDepth() throws CommandInterruptionException {
        var commandMessageHandler = commandManager.getMessageHandler();
        commandMessageHandler.displayToUser("WARNING. Recursion is detected.");
        commandMessageHandler.displayToUser("please specify the recursion depth");
        var sc = new Scanner(System.in);
        for (; ; ) {
            try {
                var str = sc.nextLine();
                if (str.equals("")) {
                    commandManager.getMessageHandler().displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if (CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                return Integer.parseInt(str);
            } catch (InputMismatchException inputMismatchException) {
                commandManager.getMessageHandler().displayToUser("This value must be a number. Try again. ");
                sc.next();
            }
        }
    }

    /**
     * the method reads commands from the script and executes them
     * @param args
     * @return
     */
    @Override
    public boolean execute(String[] args) {
        var commandMessageHandler = commandManager.getMessageHandler();
        try {
            if (scriptFilesBeingExecuted.contains(args[0])) {
                if (recDepth == -1) {
                    recDepth = specifyRecDepth();
                }
                var currentRecursionDepth = scriptFilesBeingExecuted.stream().filter(s -> s.equals(args[0])).count();
                if (currentRecursionDepth >= recDepth) {
                    recDepth = -1;
                    return true;
                }
            }
            scriptFilesBeingExecuted.add(args[0]);

            Scanner reader = new Scanner(new FileReader(args[0]));
            commandManager.getInputService().setScanner(reader);
            String command;
            while (reader.hasNext() && (command = reader.nextLine()) != null) {
                commandManager.executeCommand(command);
                commandManager.getInputService().setScanner(reader);
            }
            commandMessageHandler.displayToUser("Commands ended.");
            reader.close();

            scriptFilesBeingExecuted.remove(args[0]);

        } catch (FileNotFoundException fileNotFoundException) {
            commandMessageHandler.log("File not found. Try again.");
            return false;
        } catch (CommandInterruptionException e) {
            commandMessageHandler.displayToUser("script execution was canceled by entered command");
            commandManager.executeCommand(e.getEnteredCommand());
        }
        return true;
    }

    @Override
    public String getInfo() {
        return "read and execute a script from specified file. You should enter path to file after entering a command.";
    }
}
