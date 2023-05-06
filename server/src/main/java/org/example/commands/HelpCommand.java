package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;

import java.util.Collection;

public class HelpCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    Collection<AbstractCommand> collectionOfCommands;

    public HelpCommand(Receiver receiver, Collection<AbstractCommand> collectionOfCommands) {
        super("help", "display help on available commands"  , "" );
        this.receiver = receiver;
        this.collectionOfCommands = collectionOfCommands;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder execution = new StringBuilder();
        for (Execute command : collectionOfCommands) {
            execution.append(command.toString() + "\n");
        }
        execution.delete(execution.toString().length() - 1, execution.toString().length());
        return new Response(execution.toString());
    }
}
