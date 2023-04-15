package org.example.сollection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="coordinates")
public class Coordinates {
    @XmlElement
    private Integer x;
    @XmlElement
    private Integer y; //Поле не может быть null

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates() {
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
    @Override
    public String toString(){
        return "coordinates{" +
                "x = " + x +
                "y = " + y +
                "}";
    }
}
