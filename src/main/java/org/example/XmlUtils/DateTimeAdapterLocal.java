package org.example.XmlUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAdapterLocal extends XmlAdapter<String, LocalDateTime> {
    DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    @Override
    public String marshal(LocalDateTime v){
        return v.format(ISO_FORMATTER);
    }
    public LocalDateTime unmarshal(String v)throws ParseException {
        return LocalDateTime.parse(v);
    }
}
