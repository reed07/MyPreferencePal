package io.requery.query;

public interface Offset<E> extends Return<E> {
    Return<E> offset(int i);
}
