package org.example.сollection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeSet;

@XmlRootElement(name = "cities")
@XmlAccessorType(XmlAccessType.NONE)
public class Cities {
    @XmlElement(name = "city")
    private TreeSet<City> cities;

    public TreeSet<City> getCities(){
        return cities;
    }
    public void setCities(TreeSet<City> cities){
        this.cities = cities;
    }
    public Cities(){

    }

}
