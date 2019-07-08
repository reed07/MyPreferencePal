package io.requery.proxy;

public interface FloatProperty<E> extends Property<E, Float> {
    float getFloat(E e);

    void setFloat(E e, float f);
}
