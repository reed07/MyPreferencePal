package io.requery.query.function;

import io.requery.query.Expression;

public class Upper<V> extends Function<V> {
    private final Expression<V> expression;

    public Object[] arguments() {
        return new Object[]{this.expression};
    }
}
