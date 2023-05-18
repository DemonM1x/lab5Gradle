package org.example.xmlUtils;

import org.example.exception.NoAccessToFileException;
import org.example.collection.City;

import java.time.ZonedDateTime;
import java.util.TreeSet;

public class Loading {
    public interface Loadable {
        /** loads the collection from the file */
        void load() throws Exception, NoAccessToFileException;
        /** saves the collection LinkedList<Product> products to the file*/
        boolean save(TreeSet<City> city) throws Exception;
        /**
         * gets the initialization time
         */
        ZonedDateTime getInitializationTime();

        /**
         * returns loaded collection
         */
        TreeSet<City> get();
    }
}
