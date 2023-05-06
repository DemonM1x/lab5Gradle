package org.example.commands;

import org.example.Receiver;
import org.example.Request;
import org.example.Response;
import org.example.сollection.City;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StartsNameCommand extends AbstractCommand implements Execute {

    /**
     * this class represents the filter_starts_with_name command,
     * which, display to user the collection elements whose name begins with the specified substring
     * @param receiver
     */
    private final Receiver receiver;
    public StartsNameCommand(Receiver receiver) {
        super("filter_starts_with_name", "output elements whose name field value starts"+
                "with the specified substring", "name");
        this.receiver = receiver;
    }

    /**
     * the method checks the collection for emptiness and outputs all elements whose name begins with the specified substring
     * @param request
     * @return
     */
    @Override
    public Response execute(Request request) {
        String substring = request.getArg().get(0);
        StringBuilder execution = new StringBuilder();
        TreeSet<City> collection = receiver.getMainCollection();
        if (collection.size() == 0)
            return new Response("Collection is empty");
        Set<City> cities = collection.stream().
                filter(city -> city.getName().startsWith(substring)).
                collect(Collectors.toSet());
        if (cities.isEmpty())
            return new Response("No objects found");

        return new Response(cities.toString());

    }
}
