package io.requery.query;

public interface Where<E> extends Return<E>, SetGroupByOrderByLimit<E> {
    <V> WhereAndOr<E> where(Condition<V, ?> condition);
}
