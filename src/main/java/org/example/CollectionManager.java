package org.example;

import com.sun.source.tree.Tree;
import org.example.Collection.Cities;
import org.example.Collection.City;
import org.example.Collection.Human;
import org.example.Exceptions.NoAccessToFileException;
import org.example.Service.CustomCollectionService;
import org.example.Service.WayOfOrder;
import org.example.interfaces.CollectionCustom;
import org.example.interfaces.Loadable;

import javax.swing.undo.UndoManager;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static java.lang.Math.*;
import static java.lang.System.getenv;

public class CollectionManager implements CollectionCustom<City> {
    private TreeSet<City> cities;
    private File xmlfile;
    private final Loadable fileManager;
    private ZonedDateTime initializationTime;
    private final MessageHandler messageHandler;
    private UndoManager undoManager = null;
    public CollectionManager(Loadable fileManager, MessageHandler messageHandler) {
        this.fileManager = fileManager;
        this.messageHandler = messageHandler;
        for (; ; ) {
            try {
                Scanner scanner = new Scanner(System.in);
                var pathToFile = "";
                pathToFile = getenv("lab5");
//                Map<String, String> env = System.getenv();
//                if (env != null && env.get("pathToXMLCollection") != null)
//                    pathToFile = env.get("pathToXMLCollection");
//                else {
//                    messageHandler.displayToUser("Enter a full path to XML file with collection or of the file where collection elements are " +
//                            "going to be stored to while being saved: ");
//                    pathToFile = scanner.nextLine();
//                }
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
                    cities = cities.stream().sorted((p,p1) -> (int)(p1.getId()) - p.getId()).collect(Collectors.toCollection(TreeSet::new));
                initializationTime = ZonedDateTime.now();
                return;
            } catch (NoAccessToFileException exception) {
                messageHandler.displayToUser("You dont have access to the specified file. Try again.");
            } catch (Exception e) {
                messageHandler.log("You dont have access to the specified file. Try again.");
            }
        }
    }


    @Override
    public boolean validateData() {
        if (cities.isEmpty())
            return true;
        var citiesId = new HashSet<Integer>();
        for (var city: cities){

            if (city.getName() == null || city.getName().isEmpty() || city.getCoordinates() == null ||
                    city.getCoordinates().getX() == null ||
            city.getCreationDate() == null || city.getCoordinates().getY() == null || city.getArea() <= 0 ||
                    city.getPopulation() == null || city.getPopulation() < 1 || city.getClimate() == null ||
                    city.getGovernor() == null){
                return false;
            }
            citiesId.add(city.getId());
        }
        var ids = citiesId.toArray();
        var minId = Integer.MAX_VALUE;
        for(var id : ids){
            minId = min((Integer) id, minId);
        }
        if (minId < 1 || ids.length < cities.size()){
            return false;
        }
        return true;

    }

    @Override
    public TreeSet<City> get() {
        return cities;
    }

    @Override
    public ZonedDateTime getInitializationTime() {
        return initializationTime;
    }

    @Override
    public Class getElementType() {
        return City.class;
    }

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
