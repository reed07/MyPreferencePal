package io.requery.query;

import io.requery.query.function.Substr;
import io.requery.query.function.Sum;

public interface Functional<V> {
    OrderingExpression<V> asc();

    OrderingExpression<V> desc();

    Substr<V> substr(int i, int i2);

    Sum<V> sum();
}
