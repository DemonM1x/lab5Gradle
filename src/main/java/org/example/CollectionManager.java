package org.example;

import org.example.сollection.City;
import org.example.exceptions.NoAccessToFileException;
import org.example.service.CustomCollectionService;
import org.example.service.WayOfOrder;
import org.example.interfaces.CollectionCustom;
import org.example.interfaces.Loadable;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static java.lang.System.getenv;

/**
 * this class gets a collection,
 * loads data from a file to a collection,
 * saves data from a collection to a file
 */
public class CollectionManager implements CollectionCustom<City> {
    private TreeSet<City> cities;
    private File xmlfile;
    private final Loadable fileManager;
    private ZonedDateTime initializationTime;
    private final MessageHandler messageHandler;

    /**
     * constructor for getting data from a file and saving it to a collection
     *
     * @param fileManager
     * @param messageHandler
     */
    public CollectionManager(Loadable fileManager, MessageHandler messageHandler) {
        this.fileManager = fileManager;
        this.messageHandler = messageHandler;
        for (; ; ) {
            try {

                var pathToFile = "";
                pathToFile = getenv("lab5");
                xmlfile = new File(pathToFile);
                fileManager.load(xmlfile);
                cities = fileManager.get();
                if (validateData())
                    cities = cities == null ? new TreeSet<>() : cities;
                else {
                    cities = new TreeSet<City>();
                    messageHandler.displayToUser("the cities in the specified file do not meet the validation criteria, loaded collection is cleared");
                }
                boolean up = true, down = true;
                var wayOfOrder = CustomCollectionService.determineWayOfOrder(cities.stream().toList());
                if (wayOfOrder == WayOfOrder.NON)
                    cities = cities.stream().sorted((p, p1) -> (int) (p1.getId()) - p.getId()).collect(Collectors.toCollection(TreeSet::new));
                initializationTime = ZonedDateTime.now();
                return;
            } catch (NoAccessToFileException exception) {
                messageHandler.displayToUser("You dont have access to the specified file. Try again.");
            } catch (Exception e) {
                messageHandler.log("You dont have access to the specified file. Try again.");
            }
        }
    }

    /**
     * the method for checks validation of date in file
     *
     * @return
     */
    @Override
    public boolean validateData() {
        if (cities.isEmpty())
            return true;
        var citiesId = new HashSet<Integer>();
        for (var city : cities) {

            if (city.getName() == null || city.getName().isEmpty() || city.getCoordinates() == null ||
                    city.getCoordinates().getX() == null ||
                    city.getCreationDate() == null || city.getCoordinates().getY() == null || city.getArea() <= 0 ||
                    city.getPopulation() == null || city.getPopulation() < 1 || city.getClimate() == null ||
                    city.getGovernor() == null) {
                return false;
            }
            citiesId.add(city.getId());
        }
        var ids = citiesId.toArray();
        var minId = Integer.MAX_VALUE;
        for (var id : ids) {
            minId = min((Integer) id, minId);
        }
        return minId >= 1 && ids.length >= cities.size();

    }

    /**
     * the method return collection
     *
     * @return
     */
    @Override
    public TreeSet<City> get() {
        return cities;
    }

    /**
     * the method return initializationTime of collection
     *
     * @return
     */
    @Override
    public ZonedDateTime getInitializationTime() {
        return initializationTime;
    }

    /**
     * the method return collection type
     *
     * @return
     */
    @Override
    public Class getElementType() {
        return City.class;
    }

    /**
     * the method checks validation of date and calls save from XmlFieHandler
     */
    @Override
    public void save() {
        try {
            if (!validateData()) {
                messageHandler.displayToUser("the product collection does not meet the validation criteria");
                return;
            }
            fileManager.save(cities, xmlfile);
            messageHandler.displayToUser("collections successfully saved");
        } catch (Exception exception) {
            messageHandler.log(exception.getMessage());
        }
    }

}
