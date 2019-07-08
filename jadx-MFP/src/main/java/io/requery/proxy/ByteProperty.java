package io.requery.proxy;

public interface ByteProperty<E> extends Property<E, Byte> {
    byte getByte(E e);

    void setByte(E e, byte b);
}
