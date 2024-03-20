package ru.tinkoff.messages.serdes;

import com.google.protobuf.InvalidProtocolBufferException;
import messages.MessageOuterClass;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class MessageDeserializer implements Deserializer<MessageOuterClass.Message> {
    @Override
    public MessageOuterClass.Message deserialize(String topic, byte[] data) {
        try {
            return MessageOuterClass.Message.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            throw new SerializationException("Error when deserializing byte[] to protobuf", e);
        }
    }
}
