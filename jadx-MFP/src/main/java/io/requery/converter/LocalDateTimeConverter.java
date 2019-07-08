package io.requery.converter;

import io.requery.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeConverter implements Converter<LocalDateTime, Timestamp> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<LocalDateTime> getMappedType() {
        return LocalDateTime.class;
    }

    public Class<Timestamp> getPersistedType() {
        return Timestamp.class;
    }

    public Timestamp convertToPersisted(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    public LocalDateTime convertToMapped(Class<? extends LocalDateTime> cls, Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
