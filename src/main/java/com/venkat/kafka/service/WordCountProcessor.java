package com.venkat.kafka.service;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;

@Service
public class WordCountProcessor {

    public static final String topic = "my-topic";

    @Autowired
    public void process(StreamsBuilder builder){

        final Serde<Integer> integerSerde = Serdes.Integer();
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();

        KStream<Integer, String> textLines = builder.stream(topic, Consumed.with(integerSerde,stringSerde));
        KTable<String, Long> wordCounts = textLines
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
                //.groupBy((key, value) -> value)
                .count();

        wordCounts.toStream().to("stream-wordcount-output", Produced.with(stringSerde,longSerde));
    }
}
