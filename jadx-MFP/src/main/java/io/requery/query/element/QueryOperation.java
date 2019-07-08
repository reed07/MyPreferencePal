package io.requery.query.element;

public interface QueryOperation<E> {
    E evaluate(QueryElement<E> queryElement);
}
