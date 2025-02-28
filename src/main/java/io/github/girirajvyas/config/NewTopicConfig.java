package io.github.girirajvyas.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

@Configuration
public class NewTopicConfig {

	// The NewTopic bean causes the topic to be created on the broker; it is not
	// needed if the topic already exists.

	// Using Builder
	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}

	// Using New
	@Bean
	public NewTopic topic2() {
		return new NewTopic("fantatstic", 1, (short) 1);
	}

	// Creating array of topics
	@Bean
	public NewTopics topics() {
		return new NewTopics(
				new NewTopic("myTopic", 1, (short) 1),
				new NewTopic("greeting", 1, (short) 1)
				);
	}

}
