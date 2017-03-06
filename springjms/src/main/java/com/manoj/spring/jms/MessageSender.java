package com.manoj.spring.jms;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

public class MessageSender {
	
	private static final Logger LOGGER = Logger
			.getLogger(MessageSender.class);
	private final JmsTemplate jmsTemplate;

    public MessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final Map map) {
    	LOGGER.info("Message Send " + map);
        jmsTemplate.convertAndSend(map);
    }
}
