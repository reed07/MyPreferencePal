package io.requery.converter;

import io.requery.Converter;
import java.lang.Enum;

public class EnumOrdinalConverter<E extends Enum> implements Converter<E, Integer> {
    private final Class<E> enumClass;

    public Integer getPersistedSize() {
        return null;
    }

    public Class<E> getMappedType() {
        return this.enumClass;
    }

    public Class<Integer> getPersistedType() {
        return Integer.class;
    }

    public Integer convertToPersisted(E e) {
        if (e == null) {
            return null;
        }
        return Integer.valueOf(e.ordinal());
    }

    public E convertToMapped(Class<? extends E> cls, Integer num) {
        if (num == null) {
            return null;
        }
        return ((Enum[]) getMappedType().getEnumConstants())[num.intValue()];
    }
}
