package org.example.сollection;

import org.example.annotation.Complex;
import org.example.DataAdapters.DateTimeAdapterZoned;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.NONE)
public class City implements Comparable<City>, Serializable {
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
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement
    @Complex
    private Coordinates coordinates; //Поле не может быть null
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapterZoned.class)
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private float area; //Значение поля должно быть больше 0
    @XmlElement
    private long population; //Значение поля должно быть больше 0, Поле не может быть null
    @XmlElement
    private double metersAboveSeaLevel;
    @XmlElement
    @Complex
    private Climate climate; //Поле не может быть null
    @XmlElement
    @Complex
    private Government government; //Поле может быть null
    @XmlElement
    @Complex
    private StandardOfLiving standardOfLiving; //Поле может быть null
    @XmlElement
    @Complex
    private Human governor; //Поле не может быть null

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }



    public Coordinates getCoordinates() {
        return coordinates;
    }
    public ZonedDateTime getCreationDate(){
        return creationDate;
    }



    public Float getArea() {
        return area;
    }



    public Long getPopulation() {
        return population;
    }



    public Double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }



    public Climate getClimate() {
        return climate;
    }



    public Government getGovernment() {
        return government;
    }



    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }



    public Human getGovernor() {
        return governor;
    }

    @Override
    public int compareTo(City o) {
        if (o.getName().compareTo(this.name) != 0) return o.getName().compareTo(this.name);
        if (o.getArea() - this.area != 0) return (int) (o.getArea() - this.area);
        if (o.getPopulation().compareTo(this.population) != 0) return o.getPopulation().compareTo(this.population);
        if (o.getCoordinates().compareTo(this.coordinates) != 0) return o.getCoordinates().compareTo(this.coordinates);
        if (o.getMetersAboveSeaLevel() - this.metersAboveSeaLevel != 0) return (int) (o.getMetersAboveSeaLevel()  - this.metersAboveSeaLevel);
        return 0;
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
    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor);
    }
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;

        if (otherObject == null) return false;

        if (!(otherObject instanceof City)) return false;

        City other = (City) otherObject;

        return (this.getName().equals(other.getName())
                && this.getCoordinates().equals(other.getCoordinates())
                && this.getArea().equals(other.getArea())
                && this.getPopulation().equals(other.getPopulation())
                && this.getMetersAboveSeaLevel().equals(other.getMetersAboveSeaLevel())
                && this.getClimate().equals(other.getClimate())
                && this.getGovernment().equals(other.getGovernment())
                && this.getStandardOfLiving().equals(other.getStandardOfLiving())
                && this.getGovernor().equals(other.getGovernor()));
    }

}
