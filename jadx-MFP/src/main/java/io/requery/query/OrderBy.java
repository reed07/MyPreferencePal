package io.requery.query;

public interface OrderBy<Q> {
    <V> Q orderBy(Expression<V> expression);

    Q orderBy(Expression<?>... expressionArr);
}
