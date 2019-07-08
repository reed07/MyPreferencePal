package io.requery.query;

public interface AndOr<Q> extends Not<Q> {
    <V> Q and(Condition<V, ?> condition);

    <V> Q or(Condition<V, ?> condition);
}
