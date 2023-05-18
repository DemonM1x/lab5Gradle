package org.example.collection;

import org.example.DataAdapters.DateTimeAdapterLocal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
@XmlRootElement(name = "governor")
@XmlAccessorType(XmlAccessType.NONE)
public class Human implements Serializable {
    public Human(){

    }
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapterLocal.class)
    private LocalDateTime birthday;

    public Human(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }
}
