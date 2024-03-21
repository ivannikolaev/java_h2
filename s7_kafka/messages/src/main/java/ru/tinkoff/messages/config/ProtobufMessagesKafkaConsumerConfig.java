package ru.tinkoff.messages.config;

import messages.MessageOuterClass;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import ru.tinkoff.messages.serdes.MessageDeserializer;

import java.util.Map;

@Configuration
public class ProtobufMessagesKafkaConsumerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageOuterClass.Message> protobufMessageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageOuterClass.Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29091,localhost:29092,localhost:29093",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class,
                ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, MessageDeserializer.class.getName()
        )));
        return factory;
    }
}
