package io.requery.converter;

import io.requery.Converter;
import java.lang.Enum;

public class EnumStringConverter<E extends Enum> implements Converter<E, String> {
    private final Class<E> enumClass;

    public Integer getPersistedSize() {
        return null;
    }

    public EnumStringConverter(Class<E> cls) {
        this.enumClass = cls;
    }

    public Class<E> getMappedType() {
        return this.enumClass;
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    public String convertToPersisted(Enum enumR) {
        if (enumR == null) {
            return null;
        }
        return enumR.toString();
    }

    public E convertToMapped(Class<? extends E> cls, String str) {
        if (str == null) {
            return null;
        }
        return (Enum) cls.cast(Enum.valueOf(cls, str));
    }
}
