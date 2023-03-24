package org.example.Container;

import java.util.List;

/**
 * this class implements command storage
 */
public class CommandsContainer {
    private static List<String> commandNames;
    public static void setCommands(List<String> commands){
        commandNames = commands;
    }

    /**
     * the method return true if the specified command exists
     * @param supposedlyCommand - transmitted command
     * @return
     */
    public static boolean contains(String supposedlyCommand){
        return commandNames.contains(supposedlyCommand.trim().toLowerCase());
    }
}
