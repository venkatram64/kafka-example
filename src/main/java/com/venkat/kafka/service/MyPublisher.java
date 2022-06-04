package com.venkat.kafka.service;

import com.github.javafaker.Faker;
import com.venkat.kafka.ser.MessageQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class MyPublisher {

    public static final String topic = "my-topic";

    @Autowired
    private KafkaTemplate<Integer, MessageQuote> kafkaTemplate;
    private Faker faker;

    //when application starts this will be invoked by spring boot
    @EventListener(ApplicationStartedEvent.class)
    public void publishToTopic2(){
        faker = Faker.instance();
        MessageQuote messageQuote = null;
        while(true){
            String msg = faker.hobbit().quote();
            messageQuote = new MessageQuote();
            messageQuote.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));
            messageQuote.setQuote(msg);
            int id = faker.random().nextInt(57);
            this.kafkaTemplate.send(topic, id, messageQuote);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*//when application starts this will be invoked by spring boot
    @EventListener(ApplicationStartedEvent.class)
    public void publishToTopic(){
        String loggedInUser = "Venkatram";
        faker = Faker.instance();
        while(true){
            String msg = faker.hobbit().quote();
            int id = faker.random().nextInt(57);
            ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>(topic,id,msg);
            producerRecord.headers().add("USER", loggedInUser.getBytes(StandardCharsets.UTF_8));
            this.kafkaTemplate.send(producerRecord);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
