package io.requery.proxy;

import io.requery.meta.Attribute;
import io.requery.meta.Type;

public class EntityBuilderProxy<B, E> implements Settable<E> {
    private final B builder;
    private final Type<E> type;

    public EntityBuilderProxy(Type<E> type2) {
        this.builder = type2.getBuilderFactory().get();
        this.type = type2;
    }

    public void setObject(Attribute<E, ?> attribute, Object obj, PropertyState propertyState) {
        attribute.getBuilderProperty().set(this.builder, obj);
    }

    public void setBoolean(Attribute<E, Boolean> attribute, boolean z, PropertyState propertyState) {
        ((BooleanProperty) attribute.getBuilderProperty()).setBoolean(this.builder, z);
    }

    public void setDouble(Attribute<E, Double> attribute, double d, PropertyState propertyState) {
        ((DoubleProperty) attribute.getBuilderProperty()).setDouble(this.builder, d);
    }

    public void setFloat(Attribute<E, Float> attribute, float f, PropertyState propertyState) {
        ((FloatProperty) attribute.getBuilderProperty()).setFloat(this.builder, f);
    }

    public void setByte(Attribute<E, Byte> attribute, byte b, PropertyState propertyState) {
        ((ByteProperty) attribute.getBuilderProperty()).setByte(this.builder, b);
    }

    public void setShort(Attribute<E, Short> attribute, short s, PropertyState propertyState) {
        ((ShortProperty) attribute.getBuilderProperty()).setShort(this.builder, s);
    }

    public void setInt(Attribute<E, Integer> attribute, int i, PropertyState propertyState) {
        ((IntProperty) attribute.getBuilderProperty()).setInt(this.builder, i);
    }

    public void setLong(Attribute<E, Long> attribute, long j, PropertyState propertyState) {
        ((LongProperty) attribute.getBuilderProperty()).setLong(this.builder, j);
    }

    public E build() {
        return this.type.getBuildFunction().apply(this.builder);
    }
}
