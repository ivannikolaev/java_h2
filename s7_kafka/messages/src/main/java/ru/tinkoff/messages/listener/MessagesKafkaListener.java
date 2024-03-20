package ru.tinkoff.messages.listener;

import messages.MessageOuterClass;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessagesKafkaListener {
    @KafkaListener(topics = "messages.string", groupId = "messages-group", containerFactory = "stringMessageKafkaListenerContainerFactory")
    public void listenStringMessages(String message) {
        System.out.println("Received Message: " + message);
    }

    @KafkaListener(topics = "messages.protobuf", groupId = "messages-group", containerFactory = "protobufMessageKafkaListenerContainerFactory")
    public void listenProtobufMessages(MessageOuterClass.Message message) {
        System.out.println("Received Message: " + message);
    }
}
