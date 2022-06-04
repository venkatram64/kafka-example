package com.venkat.kafka.deser;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class ZonedDateTimeDeserializer  extends JsonDeserializer<ZonedDateTime> {

    public static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT);
        LocalDateTime localDate = LocalDateTime.parse(jsonParser.getText(),dateTimeFormatter);

        //return localDate.atStartOfDay(ZoneOffset.UTC);
        ZonedDateTime zdt = localDate.atZone(ZoneId.of("US/Eastern"));
        return zdt;
        //DateTimeFormatter.ISO_LOCAL_DATE_TIME
        //System.out.println("*******************" + jsonParser.getValueAsString());
        //return ZonedDateTime.parse(jsonParser.getValueAsString(),DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT) );
    }


    //public static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'hh:mm:ss-00:00";//
    /*@Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT);
        LocalDate localDate = LocalDate.parse(jsonParser.getText(),dateTimeFormatter);

        return localDate.atStartOfDay(ZoneOffset.UTC);
    }*/
}
