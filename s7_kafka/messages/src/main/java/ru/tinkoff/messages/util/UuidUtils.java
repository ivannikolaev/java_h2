package ru.tinkoff.messages.util;

import com.google.protobuf.ByteString;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidUtils {
    public static UUID asUuid(ByteString byteString) {
        ByteBuffer bb = byteString.asReadOnlyByteBuffer();
        long firstLong = bb.getLong();
        long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong);
    }

    public static ByteString asByteString(UUID uuid) {
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return ByteString.copyFrom(bb);
    }
}
