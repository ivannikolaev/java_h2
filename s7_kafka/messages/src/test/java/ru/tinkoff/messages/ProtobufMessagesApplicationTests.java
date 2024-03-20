package ru.tinkoff.messages;

import messages.MessageOuterClass;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.tinkoff.messages.config.ProtobufMessagesKafkaProducerConfig;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static ru.tinkoff.messages.util.UuidUtils.asByteString;

@SpringJUnitConfig(classes = ProtobufMessagesKafkaProducerConfig.class)
@Disabled
class ProtobufMessagesApplicationTests {

	@Autowired
	KafkaTemplate<String, MessageOuterClass.Message> protobufMessageKafkaTemplate;
	@Test
	void testProtobufProducer() throws ExecutionException, InterruptedException {
		MessageOuterClass.Message message = MessageOuterClass.Message.newBuilder()
				.setCreator("user1")
				.setId(asByteString(UUID.randomUUID()))
				.setText("Message text")
				.build();
		CompletableFuture<SendResult<String, MessageOuterClass.Message>> future = protobufMessageKafkaTemplate.send("messages.protobuf", message);
		dumpResult(future.get());
	}

	void dumpResult(SendResult<?, ?> result) {
		System.out.println("partition: " + result.getRecordMetadata().partition());
		System.out.println("offset: " + result.getRecordMetadata().offset());
	}

}
