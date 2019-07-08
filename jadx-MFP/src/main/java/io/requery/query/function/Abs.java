package io.requery.query.function;

import io.requery.query.Expression;

public class Abs<V> extends Function<V> {
    private final Expression<V> attribute;

    public Object[] arguments() {
        return new Object[]{this.attribute};
    }
}
