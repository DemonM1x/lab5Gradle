package org.example.сollection;

import org.example.xmlUtils.DateTimeAdapterLocal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
@XmlRootElement(name = "governor")
@XmlAccessorType(XmlAccessType.NONE)
public class Human {
    public Human(){

    }
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapterLocal.class)
    private java.time.LocalDateTime birthday;

    public Human(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }
}
