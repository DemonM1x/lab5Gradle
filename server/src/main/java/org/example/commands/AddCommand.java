package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;
import org.example.сollection.City;

public class AddCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public AddCommand(Receiver receiver) {
        super("add", "add a new element to the collection", "{element}");
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        City studyGroup = request.getCity();
        receiver.add(studyGroup);
        return new Response(("City has been added!"));
    }

}
