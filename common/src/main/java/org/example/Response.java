package org.example;

import org.example.сollection.City;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

public class Response implements Serializable {
    private String information;
    private City city;
    private Set<City> collection;



    public Response(City aCity) {
        city = aCity;
    }

    public Response(String anInformation){
         information = anInformation;
    }

    public Response(Set<City> aCollection) {
        collection = aCollection;
    }

    public City getCity() {
        return city;
    }

    public Set<City> getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (information != null)
            sb.append(information);

        if (city != null)
            sb.append(city).append("\n");

        if (collection != null)
            collection.stream().sorted(Comparator.comparing(City::getName)).
                    forEach(sg -> sb.append(sg).append("\n"));
        return sb.toString();
    }
    public int getSize() throws IOException {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        return byteOutputStream.toByteArray().length;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        return byteOutputStream.toByteArray();
    }
}
