package io.requery.query.function;

import io.requery.query.Expression;

public class Collate<T> extends Function<T> {
    private final Expression<?> expression;

    public Object[] arguments() {
        return new Object[]{this.expression};
    }
}
