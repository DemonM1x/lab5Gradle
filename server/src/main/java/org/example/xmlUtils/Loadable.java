package org.example.xmlUtils;

import org.example.collection.City;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.TreeSet;

public interface Loadable {
    /** loads the collection from the file */
    void load() throws Exception;
    /** saves the collection LinkedList<Product> products to the file*/
    boolean save(List<City> cities) throws Exception;
    /** gets the initialization time */
    ZonedDateTime getInitializationTime();
    /**
     * returns loaded collection
     */
    TreeSet<City> get();
}