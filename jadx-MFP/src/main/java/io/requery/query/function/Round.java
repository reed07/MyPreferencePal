package io.requery.query.function;

import io.requery.query.Expression;

public class Round<V> extends Function<V> {
    private final int decimals;
    private final Expression<V> expression;

    public Object[] arguments() {
        return new Object[]{this.expression, Integer.valueOf(this.decimals)};
    }
}
