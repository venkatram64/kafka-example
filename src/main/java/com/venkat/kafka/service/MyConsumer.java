package com.venkat.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyConsumer {

  /*  @KafkaListener(topics={"my-topic"},groupId = "mygroup")
    public void consume(String message){
        System.out.println("Consuming message :" + message);
    }*/

    /*@KafkaListener(topics={"my-topic"},groupId = "mygroup")
    public void consume(ConsumerRecord<Integer,String> message){
        message.headers().headers("USER")
                .forEach(header -> System.out.println(new String(header.value())));
        String payload = message.value();
        System.out.println("Consuming message :" + payload);
    }*/

    /*@KafkaListener(topics={"my-topic"},groupId = "mygroup")
    public void consume(@Payload String payload){
        System.out.println("Consuming message :" + payload);
    }*/

    @KafkaListener(topics={"my-topic"},groupId = "mygroup")
    public void consume(ConsumerRecord<Integer, com.venkat.kafka.ser.MessageQuote> payload){
        com.venkat.kafka.ser.MessageQuote msg = payload.value();
        System.out.println("*********Consuming message************ :" + msg);
    }


    /*public Map<String, Object> consumerProperties(){
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.GROUP_ID_CONFIG, "spring-ccloud",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false,
                ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class
        );
    }

    public void consumeWordCount(){
        KafkaConsumer<String, Long> kafkaConsumer = new KafkaConsumer<String, Long>(consumerProperties());
        kafkaConsumer.subscribe(Arrays.asList("stream-wordcount-output"));

        ConsumerRecords<String, Long> records = kafkaConsumer.poll(Duration.ofSeconds(1));
        records.forEach(record -> {
            System.out.println("Message Key is " + record.key());
            System.out.println("Message value is " + record.value());
        });

        kafkaConsumer.close();

    }*/

    /*@KafkaListener(topics={"my-topic"},groupId = "mygroup")
    public void consume(@Payload String payload, @Header("USER") String header){
        System.out.println(header);
        System.out.println("Consuming message :" + payload);
    }*/


}
