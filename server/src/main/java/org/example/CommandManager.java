package org.example;

import org.example.commands.*;

import java.util.HashMap;

/**
 * this class adds commands to the commandsСontainer and checks for the existence of a command in the container
 */
public class CommandManager{
    private final Receiver receiver;
    private final HashMap<String, AbstractCommand> commandsMap;
    /**
     * the constructor accepts commands as input and adds them to the commandsContainer
     * @param receiver
     */
    public CommandManager(Receiver receiver){
        this.receiver = receiver;
        commandsMap = new HashMap<>();
        var helpCommand = new HelpCommand(receiver, commandsMap.values());
        var infoCommand = new InfoCommand(receiver);
        var clearCommand = new ClearCommand(receiver);
        var showCommand = new ShowCommand(receiver);
        var addCommand = new AddCommand(receiver);
        var updateCommand = new UpdateIdCommand(receiver);
        var executeCommand = new ExecuteScriptCommand();
        var addMaxCommand = new AddIfMaxCommand(receiver);
        var addMinCommand = new AddIfMinCommand(receiver);
        var removeGreaterCommand = new RemoveGreaterCommand(receiver);
        var minById = new MinByIdCommand(receiver);
        var filterStartsWithName = new StartsNameCommand(receiver);
        var removeLowerCommand = new RemoveLowerCommand(receiver);
        var removeById = new RemoveByIdCommand(receiver);
        commandsMap.put("add",addCommand);
        commandsMap.put("clear",clearCommand);
        commandsMap.put("remove_greater", removeGreaterCommand);
        commandsMap.put("add_if_min", addMinCommand);
        commandsMap.put("remove_by_id", removeById);
        commandsMap.put("remove_lower", removeLowerCommand);
        commandsMap.put("update_id", updateCommand);
        commandsMap.put("show", showCommand);
        commandsMap.put("min_by_id", minById);
        commandsMap.put("help", helpCommand);
        commandsMap.put("info", infoCommand);
        commandsMap.put("filter_starts_with_name", filterStartsWithName);
        commandsMap.put("execute_script", executeCommand);
    }
    public  Response execute(Request request) {
        String command = request.getCommand();
        return commandsMap.get(command).execute(request);
    }

}
