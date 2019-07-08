package io.requery.proxy;

public interface LongProperty<E> extends Property<E, Long> {
    long getLong(E e);

    void setLong(E e, long j);
}
