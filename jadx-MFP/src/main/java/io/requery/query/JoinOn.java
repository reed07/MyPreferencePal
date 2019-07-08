package io.requery.query;

public interface JoinOn<E> {
    <V> JoinAndOr<E> on(Condition<V, ?> condition);
}
