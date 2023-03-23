package org.example.XmlUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAdapterZoned extends XmlAdapter<String, ZonedDateTime> {
    DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    @Override
    public String marshal(ZonedDateTime v){
        return v.format(ISO_FORMATTER);
    }
    public ZonedDateTime unmarshal(String v)throws ParseException{
        return ZonedDateTime.parse(v);
    }
}
