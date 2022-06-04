package com.venkat.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MyKafkaSpringApplicationTests {

	@Test
	void contextLoads() {

		double dv = 1654253449.302644000;
		Date d = new Date(1654253449);
		System.out.println(d.toString());
	}

}
