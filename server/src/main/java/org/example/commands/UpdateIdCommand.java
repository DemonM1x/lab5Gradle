package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;
import org.example.collection.City;

import java.util.ArrayList;

public class UpdateIdCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public UpdateIdCommand(Receiver receiver) {
        super("update", "update the value of the collection element whose" +
                " id is equal to the given one", "id {element}" );
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        ArrayList<String> anArg = request.getArg();
        City upgradedCity = request.getCity();

        City city = receiver.getId(Integer.parseInt(anArg.get(0)));

        if (city != null) receiver.remove(city);
        else {
            return new Response("An object with this id does not exist!");
        }

        upgradedCity.setId(Integer.parseInt(anArg.get(0)));
        receiver.add(upgradedCity);

        return new Response("Object has been updated!");
    }
}

