package io.requery.proxy;

public interface IntProperty<E> extends Property<E, Integer> {
    int getInt(E e);

    void setInt(E e, int i);
}
