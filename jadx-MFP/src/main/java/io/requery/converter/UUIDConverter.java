package io.requery.converter;

import io.requery.Converter;
import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDConverter implements Converter<UUID, byte[]> {
    public Class<UUID> getMappedType() {
        return UUID.class;
    }

    public Class<byte[]> getPersistedType() {
        return byte[].class;
    }

    public Integer getPersistedSize() {
        return Integer.valueOf(16);
    }

    public byte[] convertToPersisted(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        byte[] bArr = new byte[16];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        return bArr;
    }

    public UUID convertToMapped(Class<? extends UUID> cls, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        return new UUID(wrap.getLong(), wrap.getLong());
    }
}
