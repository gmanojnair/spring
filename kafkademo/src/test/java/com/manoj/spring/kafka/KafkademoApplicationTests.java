package com.manoj.spring.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={KafkademoApplication.class})
public class KafkademoApplicationTests {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private Receiver listener;

	@Test
	public void contextLoads() throws InterruptedException {

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test", "ABC");
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("success");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("failed");
			}
		});
		System.out.println(Thread.currentThread().getId());
		assertThat(this.listener.getLatch().await(60, TimeUnit.SECONDS)).isTrue();

	}

}
