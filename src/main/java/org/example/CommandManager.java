package org.example;

import org.example.Collection.City;
import org.example.Command.*;
import org.example.Container.CommandsContainer;
import org.example.Service.InputService;
import org.example.interfaces.CollectionCustom;
import org.example.interfaces.Command;
import org.example.interfaces.CommandManagerCustom;

import javax.swing.undo.UndoManager;
import java.util.*;

public class CommandManager implements CommandManagerCustom {
    private CollectionCustom<City> collectionManager = null;
    private final HashMap<String, Command> commandsMap;
    private LinkedList<String> commandHistory;
    private final InputService inputService;
    private final MessageHandler messageHandler;
    public CommandManager(CollectionCustom<City> manager, MessageHandler messageHandler, InputService inputService){
        this.messageHandler = messageHandler;
        this.collectionManager = manager;
        this.inputService = inputService;
        commandHistory = new LinkedList<>();
        commandsMap = new HashMap<>();
        commandsMap.put("add", new AddCommand(this));
        commandsMap.put("clear", new ClearCommand(this));
        commandsMap.put("remove_greater", new RemoveGreaterCommand(this));
        commandsMap.put("add_if_min", new AddIfMinCommand(this));
        commandsMap.put("remove_by_id", new RemoveByIdCommand(this));
        commandsMap.put("remove_lower", new RemoveLowerCommand(this));
        commandsMap.put("update_id", new UpdateIdCommand(this));
        commandsMap.put("show", new ShowCommand(this));
        commandsMap.put("min_by_id", new MinByIdCommand(this));
        commandsMap.put("help", new HelpCommand(this));
        commandsMap.put("info", new InfoCommand(this));
        commandsMap.put("filter_starts_with_name", new StartsNameCommand(this));
        commandsMap.put("execute_script", new ExecuteScriptCommand(this));
        commandsMap.put("print_ascending", new PrintAscendingCommand(this));
        commandsMap.put("save", new SaveCommand(this));
        commandsMap.put("exit", new ExitCommand(this));
        commandsMap.put("undo_commands", new UndoCommand(this));
        CommandsContainer.setCommands(commandsMap.keySet().stream().toList());
    }

    @Override
    public boolean executeCommand(String userInput) {
        var commandUnits = userInput.trim().toLowerCase().split(" ", 2);
        if(!commandsMap.containsKey(commandUnits[0])){
            messageHandler.displayToUser("Unknown command. Write help for help.");
            return false;
        }
        var enteredCommand = commandUnits[0].trim().toLowerCase();
        var command = commandsMap.get(enteredCommand);
        commandHistory.add(enteredCommand);
        command.execute(Arrays.copyOfRange(commandUnits, 1, commandUnits.length));
        return true;
    }

    @Override
    public List<String> getCommandHistory() {
        return commandHistory;
    }

    @Override
    public List<String> getCommandsInfo() {
        var commandInfos = new ArrayList<String>(commandsMap.size());
        commandsMap.forEach((key, value) -> commandInfos.add(key + " - " + value.getInfo()));
        return commandInfos;
    }

    @Override
    public InputService getInputService() {
        return inputService;
    }

    @Override
    public CollectionCustom<City> getCollectionManager() {
        return collectionManager;
    }

    @Override
    public MessageHandler getMessageHandler() {
        return messageHandler;
    }


}
