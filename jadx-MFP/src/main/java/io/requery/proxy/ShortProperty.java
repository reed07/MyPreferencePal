package io.requery.proxy;

public interface ShortProperty<E> extends Property<E, Short> {
    short getShort(E e);

    void setShort(E e, short s);
}
