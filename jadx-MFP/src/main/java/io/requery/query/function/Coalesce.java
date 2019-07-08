package io.requery.query.function;

import io.requery.query.Expression;

public class Coalesce<T> extends Function<T> {
    private final Expression<?>[] expressions;

    public Expression<?>[] arguments() {
        return this.expressions;
    }
}
