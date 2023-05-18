package org.example.commands;

import org.example.Request;
import org.example.Response;

import java.util.Collection;

public class HelpCommand extends AbstractCommand implements Execute {
    Collection<AbstractCommand> collectionOfCommands;

    public HelpCommand(Collection<AbstractCommand> collectionOfCommands) {
        super("help", "display help on available commands"  , "" );
        this.collectionOfCommands = collectionOfCommands;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder execution = new StringBuilder();
        for (Execute command : collectionOfCommands) {
            execution.append(command.toString()).append("\n");
        }
        execution.delete(execution.toString().length() - 1, execution.toString().length());
        return new Response(execution.toString());
    }
}
