package io.requery.converter;

import io.requery.Converter;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeConverter implements Converter<OffsetDateTime, Timestamp> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<OffsetDateTime> getMappedType() {
        return OffsetDateTime.class;
    }

    public Class<Timestamp> getPersistedType() {
        return Timestamp.class;
    }

    public Timestamp convertToPersisted(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.from(offsetDateTime.toInstant());
    }

    public OffsetDateTime convertToMapped(Class<? extends OffsetDateTime> cls, Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.systemDefault());
    }
}
