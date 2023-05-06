package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;

public class MinByIdCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public MinByIdCommand(Receiver receiver){
        super("min_by_id", "outputs the birthday of the governor from the collection, the value of the id field of which is the minimum", "");
        this.receiver = receiver;

    }

    public Response execute(Request request){
        return new Response("governor:\nbirthday: "+ receiver.getMainCollection().first().getGovernor().getBirthday().toString());
    }

}
