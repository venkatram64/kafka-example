package com.venkat.kafka.deser;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageQuote {

    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime zonedDateTime;
    private String quote;
}
