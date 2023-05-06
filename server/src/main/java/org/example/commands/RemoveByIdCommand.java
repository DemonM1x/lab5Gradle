package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;

import java.util.ArrayList;

public class RemoveByIdCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public RemoveByIdCommand(Receiver receiver) {
        super("remove_by_id", "remove element from collection by its id" , "id");
        this.receiver = receiver;

    }

    @Override
    public Response execute(Request request) {
        ArrayList<String> anArg = request.getArg();
        Integer anId = Integer.parseInt(anArg.get(0));


        if (receiver.getId(anId) == null) {
            return new Response("An object with this id does not exist!\n");
        }
        if (!receiver.remove(receiver.getId(anId))) {
            return new Response("Failed to remove the object!\n");
        }
        return new Response("Object has been removed!\n");
    }
}
