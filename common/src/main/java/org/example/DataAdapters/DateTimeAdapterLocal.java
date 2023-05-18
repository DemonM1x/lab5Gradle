package org.example.DataAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * this class formats localDateTime for parsing
 */
public class DateTimeAdapterLocal extends XmlAdapter<String, LocalDateTime> {
    DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    @Override
    public String marshal(LocalDateTime v){
        return v.format(ISO_FORMATTER);
    }
    public LocalDateTime unmarshal(String v) {
        return LocalDateTime.parse(v);
    }
}
