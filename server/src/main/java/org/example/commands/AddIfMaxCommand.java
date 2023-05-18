package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;
import org.example.collection.City;

public class AddIfMaxCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public AddIfMaxCommand(Receiver receiver) {
        super("add_if_max", "add a new element to the collection if its value is greater than the value of the largest element in this collection" , "{element}" );
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        City city = request.getCity();
        if (receiver.getMax() != null && city.compareTo(receiver.getMax()) <= 0) {
            return new Response("city doesn't added");
        }
        receiver.add(city);
        return new Response("Add max");
    }
}
