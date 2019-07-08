package io.requery.converter;

import io.requery.Converter;
import java.sql.Time;
import java.time.LocalTime;

public class LocalTimeConverter implements Converter<LocalTime, Time> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<LocalTime> getMappedType() {
        return LocalTime.class;
    }

    public Class<Time> getPersistedType() {
        return Time.class;
    }

    public Time convertToPersisted(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return Time.valueOf(localTime);
    }

    public LocalTime convertToMapped(Class<? extends LocalTime> cls, Time time) {
        if (time == null) {
            return null;
        }
        return time.toLocalTime();
    }
}
