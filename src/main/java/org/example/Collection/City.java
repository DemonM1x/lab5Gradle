package org.example.Collection;

import org.example.XmlUtils.DateTimeAdapterZoned;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.NONE)
public class City implements Comparable<City>{
    public City(){

    }
    public City(int id, String name, Coordinates coordinates, float area, long population, double metersAboveSeaLevel,
                Climate climate, Government government, StandardOfLiving standardOfLiving, Human governor){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.area = area;
        this.creationDate = ZonedDateTime.now();
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.climate = climate;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;

    }
    @XmlElement
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement
    private Coordinates coordinates; //Поле не может быть null
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapterZoned.class)
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private float area; //Значение поля должно быть больше 0
    @XmlElement
    private long population; //Значение поля должно быть больше 0, Поле не может быть null
    @XmlElement
    private double metersAboveSeaLevel;
    @XmlElement
    private Climate climate; //Поле не может быть null
    @XmlElement
    private Government government; //Поле может быть null
    @XmlElement
    private StandardOfLiving standardOfLiving; //Поле может быть null
    @XmlElement
    private Human governor; //Поле не может быть null

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public ZonedDateTime getCreationDate(){
        return creationDate;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }
    @Override
    public int compareTo(City city){
        return (int)( getId() - city.getId());
    }


    public String toString(){
        var output = "id: " + id + "\n" +
                "name: " + name + "\n" +
                "coordinates:\n" +
                " ".repeat(2) + "X: " + coordinates.getX() + "\n" +
                " ".repeat(2) + "Y: " + coordinates.getY() + "\n" +
                "creation date: " + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z")) + "\n" +
                "area: " + area + "\n"+
                "population: " + population + "\n"+
                "metersAboveSeaLevel: " + metersAboveSeaLevel;
                if(climate != null){
                    output += "\n" + "climate: " + climate.toString();
                }
                if (government != null){
                    output += "\n" + "government: " + government.toString();
                }
                if (standardOfLiving != null){
                    output += "\n" + "standardOfLiving: " + standardOfLiving.toString();
                }
                if (governor != null){
                    output += "\n" + "governor: \n" +
                    " ".repeat(2) + "birthday: " + governor.getBirthday() + "\n";
                }
        return output;
    }
}
