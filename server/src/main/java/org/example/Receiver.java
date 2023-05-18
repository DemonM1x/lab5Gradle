package org.example;

import org.example.collection.City;

import java.util.Date;
import java.util.Stack;
import java.util.TreeSet;

public class Receiver {
    private final TreeSet<City> cities;
    private final Stack<Integer> usedId;
    private int highUsedId;

    private final LocalDateBase localDateBase;

    public Receiver(LocalDateBase localDateBase) {
        usedId = new Stack<>();
        highUsedId = 0;

        this.localDateBase = localDateBase;
        this.cities = localDateBase.getMainCollection();
    }
    public Date getDateOfInitialization() {
        return localDateBase.getDateOfInitialization();
    }

    public TreeSet<City> getMainCollection() {
        return localDateBase.getMainCollection();
    }

    public Date getDateOfLastChange() {
        return localDateBase.getDateOfLastChange();
    }

    public void setDateOfLastChange() {
        localDateBase.setDateOfLastChange(new Date());
    }

    public LocalDateBase getLocalDateBase() {
        return localDateBase;
    }


    public void add(City city) {
        Integer newCityId = city.getId();
        if (!usedId.add(newCityId)) cities.remove(this.getId(newCityId));

        if (cities.add(city)) {
            usedId.add(city.getId());
            if (highUsedId < city.getId()) highUsedId = city.getId();
        } else usedId.remove(city.getId());
    }
    public City getId(Integer key) {
        return cities.stream().filter(sg -> sg.getId().equals(key)).findAny().orElse(null);
    }
    public City getMax(){
        return cities.stream().max(City::compareTo).orElse(null);
    }
    public City getMin(){
        return cities.stream().min(City::compareTo).orElse(null);
    }
    public String clearCollection() {
        if (getMainCollection().size() != 0) {
            getMainCollection().clear();
            setDateOfLastChange();
            return "Main collection cleared";
        } else {
            return "There is no elements in main collection";
        }
    }
    public String removeGreater(City city){
        int countRemoved = 0;
        for (City cities : this.getMainCollection()){
            if (cities.compareTo(city) > 0){
                remove(cities);
                countRemoved+=1;
            }

        }
        if (countRemoved > 0){
            setDateOfLastChange();
        }
        return "Successfully removed " + countRemoved +" Cities";
    }

    public String removeLower(City city){
        int countRemoved = 0;
        for (City cities : this.getMainCollection()){
            if (cities.compareTo(city) < 0){
                remove(cities);
                countRemoved+=1;
            }

        }
        if (countRemoved > 0){
            setDateOfLastChange();
        }
        return "Successfully removed " + countRemoved +" Cities";
    }

    public String show(){
        StringBuilder stringBuilder = new StringBuilder();

        for(City city : getMainCollection()) {
            stringBuilder.append(city.toString() + "\n");
        }
        return stringBuilder.toString();
    }
    public boolean remove(City city){
        return cities.remove(city);
    }
    public String removeID(String idValue) {
        try {
            int id = Integer.parseInt(idValue);
            TreeSet<City> mainCollection = this.getMainCollection();
            for (City city : mainCollection) {
                if (city.getId() == id) {
                    this.remove(city);
                    setDateOfLastChange();
                    return "Successfully";
                }
            }
        } catch (NumberFormatException e) {
            return "Failed. You typed wrong id.";
        }
        return "There is no element with that id.";
    }
}
