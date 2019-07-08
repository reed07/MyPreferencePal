package io.requery.query;

public interface Limit<E> extends Return<E> {
    Offset<E> limit(int i);
}
