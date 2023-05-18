package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;
import org.example.collection.City;

public class AddIfMinCommand extends AbstractCommand implements Execute{
    private final Receiver receiver;
    public AddIfMinCommand(Receiver receiver){
        super("add_if_min","add new element to the collection if element is minimal","{element}");
        this.receiver = receiver;
    }
    public Response execute(Request request){
        City city = request.getCity();
        if (receiver.getMin() != null && city.compareTo(receiver.getMin()) >= 0) {
            return new Response("city doesn't added");
        }
        receiver.add(city);
        return new Response("Add min");
    }
}
