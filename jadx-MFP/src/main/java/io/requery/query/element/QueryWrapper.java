package io.requery.query.element;

public interface QueryWrapper<E> {
    QueryElement<E> unwrapQuery();
}
