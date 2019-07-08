package io.requery.converter;

import io.requery.Converter;
import java.sql.Date;
import java.time.LocalDate;

public class LocalDateConverter implements Converter<LocalDate, Date> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<LocalDate> getMappedType() {
        return LocalDate.class;
    }

    public Class<Date> getPersistedType() {
        return Date.class;
    }

    public Date convertToPersisted(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.valueOf(localDate);
    }

    public LocalDate convertToMapped(Class<? extends LocalDate> cls, Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }
}
