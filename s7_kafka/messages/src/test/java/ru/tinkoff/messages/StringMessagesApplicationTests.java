package ru.tinkoff.messages;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.tinkoff.messages.config.StringMessagesKafkaProducerConfig;

import java.util.concurrent.CompletableFuture;

@SpringJUnitConfig(classes = StringMessagesKafkaProducerConfig.class)
@Disabled
@Slf4j
class StringMessagesApplicationTests {

    @Autowired
    KafkaTemplate<String, String> stringMessageKafkaTemplate;

    @Test
    void testStringProducer() {
        var f1 = stringMessageKafkaTemplate.send("messages.string", "user1", "Hello from user1");
        var f2 = stringMessageKafkaTemplate.send("messages.string", "user2", "Hello from user2");
        CompletableFuture.allOf(f1, f2)
                .thenAccept(__ -> {
                    dumpResult(f1.join());
                    dumpResult(f2.join());
                }).join();
    }

    void dumpResult(SendResult<?, ?> result) {
        log.info("partition: {}, offset: {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
    }

}
