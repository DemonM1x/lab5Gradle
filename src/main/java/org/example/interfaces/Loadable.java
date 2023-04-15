package org.example.interfaces;

import org.example.сollection.City;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.TreeSet;


public interface Loadable {
    /** loads the collection from the file */
    void load(File file) throws Exception;
    /**
     * saves the collection to the file
     */
    boolean save(TreeSet<City> city, File file)throws Exception;
    /** gets the initialization time */
    ZonedDateTime getInitializationTime();
    /** returns loaded collection */
    TreeSet<City> get();
}
