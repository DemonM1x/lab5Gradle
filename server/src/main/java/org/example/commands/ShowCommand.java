package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;

public class ShowCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public ShowCommand(Receiver receiver) {
        super("show", "print to standard output all elements of the collection in string representation" , "" );
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder stringBuilder = new StringBuilder();
        if (receiver.getMainCollection().size() == 0) {
            return new Response("There is no elements in main collection.");
        }
        stringBuilder.append(receiver.show());
        return new Response(stringBuilder.delete(stringBuilder.toString().length() - 1, stringBuilder.toString().length()).toString());
    }
}
