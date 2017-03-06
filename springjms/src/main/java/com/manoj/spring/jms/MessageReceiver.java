package com.manoj.spring.jms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import org.springframework.messaging.MessageHeaders;

public class MessageReceiver implements MessageListener {
	private static final Logger LOGGER = Logger
			.getLogger(MessageReceiver.class);

	public void onMessage(final Message message) {
		LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        /*MessageHeaders headers =  message.getHeaders();
        LOGGER.info("Application : headers received : {}", headers);
         
        Object response = message.getPayload();
        LOGGER.info("Application : response received : {}",response);*/
        
		if (message instanceof MapMessage) {
			final MapMessage mapMessage = (MapMessage) message;
			// do something
		}
	}
}
