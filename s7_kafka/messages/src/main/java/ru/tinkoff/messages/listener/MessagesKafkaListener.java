package ru.tinkoff.messages.listener;

import lombok.extern.slf4j.Slf4j;
import messages.MessageOuterClass;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.tinkoff.messages.error.BadMessageException;

@Component
@Slf4j
public class MessagesKafkaListener {
    @KafkaListener(topics = "messages.string",
            groupId = "messages-group",
            containerFactory = "stringMessageKafkaListenerContainerFactory",
            concurrency = "1")
    public void listenStringMessages(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("Received Message from partition {} with key {}: {}", partition, key, message);
    }

//    @KafkaListener(topicPartitions = @TopicPartition(
//            topic = "messages.string",
//            partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")
//    ), containerFactory = "stringMessageKafkaListenerContainerFactory")
//    public void listenWithAssignedPartitions(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        log.info("Received Message from partition {}: {}", partition, message);
//    }

    @KafkaListener(topics = "messages.protobuf",
            groupId = "messages-group",
            containerFactory = "protobufMessageKafkaListenerContainerFactory")
    public void listenProtobufMessages(MessageOuterClass.Message message) {
        log.info("Received Message: {}", message);
        if (message.getCreator().equals("user2")) {
            throw new BadMessageException("Bad user: user2");
        }
    }
}
