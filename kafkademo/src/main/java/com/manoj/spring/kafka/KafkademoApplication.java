package com.manoj.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;


/**
 * 
 * The first is running the ZooKeeper process by typing (I’m using Windows):
bin\windows\zookeeper-server-start.bat config/zookeeper.properties

The second step is running the Broker:
bin\windows\kafka-server-start.bat config/server.properties
 * 
 * @author nairm
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.manoj.spring.kafka")
public class KafkademoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(KafkademoApplication.class, args);
		KafkaTemplate kafkaTemplate = (KafkaTemplate)context.getBean("kafkaTemplate");
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topic1", "ABC");
		System.out.println("Started... ");
		
	}
}
