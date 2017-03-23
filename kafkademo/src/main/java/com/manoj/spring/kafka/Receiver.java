package com.manoj.spring.kafka;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

 
    @KafkaListener(id = "foo", topics = "topic1")
	public void listen(ConsumerRecord<?, ?> record) {
		// This allows the POJO to signal that a message is received.
		System.out.println(record);
		latch.countDown();
	}
    
    public CountDownLatch getLatch() {
        return latch;
    }
}