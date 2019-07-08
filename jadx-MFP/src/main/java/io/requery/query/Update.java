package io.requery.query;

public interface Update<E> extends GroupBy<SetHavingOrderByLimit<E>>, Join<E>, OrderBy<Limit<E>>, Return<E>, Where<E> {
    <V> Update<E> set(Expression<V> expression, V v);
}
