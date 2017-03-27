package com.manoj.spring.si;



import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource("classpath*:resources/config.xml")
public class SpringintegrationdemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(
				SpringintegrationdemoApplication.class, args);
		
		
		
	/*	ConfigurableApplicationContext mycontext = SpringApplication
				.run(Config.class);
*/
		/*PollableChannel channel = (PollableChannel) context
				.getBean("output");*/
		MessageChannel input = (MessageChannel) context.getBean("input");
		input.send(new GenericMessage("Spring Integration rocks"));
		// channel.send(new GenericMessage("Spring Integration rocks"));
		// Message<?> reply = channel.receive();
		// System.out.println("received: " + reply);
	}
}

@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource("classpath:config.xml")
class Config {

	 public static final String HELLO_QUEUE = "hello.queue";
	    @Bean
	    public Queue helloJMSQueue() {
	        return new ActiveMQQueue(HELLO_QUEUE);
	    }
	    
	    
	    
}