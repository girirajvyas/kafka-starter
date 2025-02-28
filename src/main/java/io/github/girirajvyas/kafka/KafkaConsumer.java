package io.github.girirajvyas.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(id = "topic1ListenerId", topics = "topic1")
	public void listen(String message) {
		LOG.info("Message received at kafka consumer for topic1: " + message);
	}
}
