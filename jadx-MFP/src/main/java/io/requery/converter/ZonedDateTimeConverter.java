package io.requery.converter;

import io.requery.Converter;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZonedDateTimeConverter implements Converter<ZonedDateTime, Timestamp> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<ZonedDateTime> getMappedType() {
        return ZonedDateTime.class;
    }

    public Class<Timestamp> getPersistedType() {
        return Timestamp.class;
    }

    public Timestamp convertToPersisted(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }
        return Timestamp.from(zonedDateTime.toInstant());
    }

    public ZonedDateTime convertToMapped(Class<? extends ZonedDateTime> cls, Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.systemDefault());
    }
}
