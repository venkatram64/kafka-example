package com.venkat.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
//@EnableKafkaStreams
public class MyKafkaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyKafkaSpringApplication.class, args);
	}

	@Bean
	NewTopic countsTopic(){
		return TopicBuilder.name("stream-wordcount-output").partitions(6).build();
	}

	@Bean
	NewTopic streamTopic(){
		return TopicBuilder.name("streamingTopic1").partitions(6).build();
	}

	@Bean
	NewTopic streamTopic1(){
		return TopicBuilder.name("streamingTopic2").partitions(6).build();
	}
}
