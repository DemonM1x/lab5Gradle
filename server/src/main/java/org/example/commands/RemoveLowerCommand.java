package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;
import org.example.сollection.City;

public class RemoveLowerCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public RemoveLowerCommand(Receiver receiver) {
        super("remove_lower", "remove from the collection all elements lower than the given", "{element}");
        this.receiver = receiver;

    }
    @Override
    public Response execute(Request request){
        City city = request.getCity();
        return new Response(receiver.removeLower(city));
    }
}
