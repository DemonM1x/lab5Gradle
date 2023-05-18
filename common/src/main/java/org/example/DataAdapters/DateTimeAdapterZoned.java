package org.example.DataAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * this class formats ZonedDateTime for parsing
 */
public class DateTimeAdapterZoned extends XmlAdapter<String, ZonedDateTime> {
    DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    @Override
    public String marshal(ZonedDateTime v){
        return v.format(ISO_FORMATTER);
    }
    public ZonedDateTime unmarshal(String v) {
        return ZonedDateTime.parse(v);
    }
}
