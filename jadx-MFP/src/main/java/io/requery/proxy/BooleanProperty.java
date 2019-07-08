package io.requery.proxy;

public interface BooleanProperty<E> extends Property<E, Boolean> {
    boolean getBoolean(E e);

    void setBoolean(E e, boolean z);
}
