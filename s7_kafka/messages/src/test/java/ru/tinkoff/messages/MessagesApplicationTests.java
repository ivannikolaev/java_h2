package ru.tinkoff.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.tinkoff.messages.config.KafkaProducerConfig;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringJUnitConfig(classes = KafkaProducerConfig.class)
class MessagesApplicationTests {

	@Autowired
	KafkaTemplate<String, String> stringMessageKafkaTemplate;
	@Test
	void testStringProducer() throws ExecutionException, InterruptedException {
		CompletableFuture<SendResult<String, String>> future = stringMessageKafkaTemplate.send("messages.string", "Hello, World!");
		dumpResult(future.get());
	}

	void dumpResult(SendResult<?, ?> result) {
		System.out.println("partition: " + result.getRecordMetadata().partition());
		System.out.println("offset: " + result.getRecordMetadata().offset());
	}

}
