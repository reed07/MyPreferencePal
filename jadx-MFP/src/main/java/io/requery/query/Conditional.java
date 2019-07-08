package io.requery.query;

import java.util.Collection;

public interface Conditional<Q, V> {
    Q eq(Expression<V> expression);

    Q eq(V v);

    Q equal(Expression<V> expression);

    Q equal(V v);

    Q in(Collection<V> collection);

    Q isNull();

    Q lessThan(V v);

    Q ne(V v);

    Q notNull();
}
