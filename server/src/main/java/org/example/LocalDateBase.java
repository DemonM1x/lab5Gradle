package org.example;

import org.example.collection.City;

import java.util.Date;
import java.util.TreeSet;

public class LocalDateBase {
    private TreeSet<City> mainCollection;
    private Date dateOfLastChange;
    private final Date dateOfInitialization;

    public LocalDateBase(TreeSet<City> mainCollection) {
        this.mainCollection = mainCollection;
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public TreeSet<City> getMainCollection() {
        return mainCollection;
    }

    public void setMainCollection(TreeSet<City> mainCollection) {
        this.mainCollection = mainCollection;
    }

    public Date getDateOfLastChange() {
        return dateOfLastChange;
    }

    public Date getDateOfInitialization() {
        return dateOfInitialization;
    }

    public void setDateOfLastChange(Date date){
        this.dateOfLastChange = new Date();
    }

}
