package io.requery.proxy;

import io.requery.meta.Attribute;

public interface Settable<E> {
    void setBoolean(Attribute<E, Boolean> attribute, boolean z, PropertyState propertyState);

    void setByte(Attribute<E, Byte> attribute, byte b, PropertyState propertyState);

    void setDouble(Attribute<E, Double> attribute, double d, PropertyState propertyState);

    void setFloat(Attribute<E, Float> attribute, float f, PropertyState propertyState);

    void setInt(Attribute<E, Integer> attribute, int i, PropertyState propertyState);

    void setLong(Attribute<E, Long> attribute, long j, PropertyState propertyState);

    void setObject(Attribute<E, ?> attribute, Object obj, PropertyState propertyState);

    void setShort(Attribute<E, Short> attribute, short s, PropertyState propertyState);
}
