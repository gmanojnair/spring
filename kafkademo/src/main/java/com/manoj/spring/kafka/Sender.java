package com.manoj.spring.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(Sender.class);

    private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaTemplate<String, String> getKafkaTemplate() {
		return kafkaTemplate;
	}

	public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String topic, String message) {
        // the KafkaTemplate provides asynchronous send methods returning a
        // Future
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(topic, message);

        // you can register a callback with the listener to receive the result
        // of the send asynchronously
        future.addCallback(
                new ListenableFutureCallback<SendResult<String, String>>() {

                    @Override
                    public void onSuccess(
                            SendResult<String, String> result) {
                        LOGGER.info("sent message='{}' with offset={}",
                                message,
                                result.getRecordMetadata().offset());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        LOGGER.error("unable to send message='{}'",
                                message, ex);
                    }
                });

        // alternatively, to block the sending thread, to await the result,
        // invoke the future's get() method
    }
}