package org.example.сollection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name="coordinates")
public class Coordinates implements Serializable {
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

    public int compareTo(Coordinates o) {
        if (o.getX() - this.x != 0) return o.getX() - this.x;
        if (o.getY() - this.y != 0) return o.getY() - this.y;
        return 0;
    }
}
