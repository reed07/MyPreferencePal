package io.requery.proxy;

public interface Property<E, V> {
    V get(E e);

    void set(E e, V v);
}
